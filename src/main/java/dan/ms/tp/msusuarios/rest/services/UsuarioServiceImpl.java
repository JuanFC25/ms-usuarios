package dan.ms.tp.msusuarios.rest.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.dao.TipoUsuarioJpaRepository;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioClienteEmptyValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioClienteNotFoundValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioExisteValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioPasswordValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioTipoGerenteValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioTipoUsuarioEmptyValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioTipoUsuarioNotFoundValidationException;
import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioJpaRepository usuarioRepository;

    @Autowired
    ClienteJpaRepository clienteRepository;

    @Autowired
    TipoUsuarioJpaRepository tipoUsuarioRepository;

    @Override
    public Usuario createUser(Usuario usuario) throws UsuarioValidationException  {
        validateUniqueUser(usuario);
        validateAndSetTipoUsuario(usuario);
        validateAndSetCliente(usuario);
        validateUserPassword(usuario);
        validateUserTypeOfCliente(usuario);      
        
        return usuarioRepository.save(usuario);
    }

    //allow default user without cliente, to be set by admin
    @Override
    public Usuario createDefaultFieldsUser(Usuario usuario) throws UsuarioValidationException  {
        validateUniqueUser(usuario);
        validateAndSetCliente(usuario);
        validateAndSetTipoUsuario(usuario);
        validateUserPassword(usuario);

        Cliente clienteSaved = clienteRepository.save(usuario.getCliente());
        usuario.setCliente(clienteSaved);
        Usuario saved = usuarioRepository.save(usuario);
        return saved;
    }

    @Override
    public Void deleteUser(Integer id) {
        usuarioRepository.deleteById(id);
        return null;
    }

    @Override
    public Usuario modifyUser(Usuario usuario) throws UsuarioValidationException  {
        validateAndSetTipoUsuario(usuario);
        validateAndSetCliente(usuario);
        validateUserPassword(usuario);
        validateUserTypeOfCliente(usuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario getUserById(Integer id) {
        Optional<Usuario> user = usuarioRepository.findById(id);
        return user.isPresent() ? user.get() : null; // avoid internal server error;
    }

    @Override
    public Usuario getUserByUsername(String username) {
        Optional<Usuario> user = usuarioRepository.findByUserName(username);
        return user.isPresent() ? user.get() : null; // avoid internal server error;
    }

    @Override
    public List<Usuario> getUsersByClientId(Integer idCliente) throws UsuarioClienteNotFoundValidationException {

        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if(!cliente.isPresent()){
            throw new UsuarioClienteNotFoundValidationException();
        }

        return usuarioRepository.findByCliente(cliente.get());

    }

    @Override
    public List<Usuario> getUsersByClientIdAndUserType(Integer idCliente, Integer userType) throws UsuarioClienteNotFoundValidationException, UsuarioTipoUsuarioEmptyValidationException {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if(!cliente.isPresent()){
            throw new UsuarioClienteNotFoundValidationException();
        }

        Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findById(userType);
        if(!tipoUsuario.isPresent()){
            throw new UsuarioTipoUsuarioEmptyValidationException();
        }

        return usuarioRepository.findByClienteAndTipoUsuario(cliente.get(), tipoUsuario.get());
    }

    private void validateUserPassword(Usuario user) throws UsuarioPasswordValidationException{
        if(user.getPassword() == null){
            throw new UsuarioPasswordValidationException();
        }
        
        if(!checkPassword(user.getPassword())){
            throw new UsuarioPasswordValidationException();
        } 
    }

    private boolean checkPassword(String password) {
        if (password.length() < 12 || 
        !password.matches(".*[A-Z].*") || 
        !password.matches(".*[a-z].*") || 
        !password.matches(".*\\d.*") || 
        !password.matches(".*[()@#$%^&+=!?-_].*")) {
            return false;
        }
        return true;
    }

    private void validateUserTypeOfCliente(Usuario usuario) throws UsuarioTipoGerenteValidationException, UsuarioValidationException{
        Optional<TipoUsuario> tipoGerente = tipoUsuarioRepository.findOneByTipo("ADMIN");
        if(!tipoGerente.isPresent()){
            // No TipoUsuario admin/gerente on db
            // one option is to crash the app for not initialized db... the other is to only throw TODO: Should result in a StatusCode 500 here.
            throw new UsuarioValidationException("Tipo de usuario gerente no encontrado.");
        }

        List<Usuario> usuariosDeCliente = usuarioRepository.findAllByCliente(usuario.getCliente());
        if(usuariosDeCliente == null || usuariosDeCliente.size() == 0){
            return;
        }
        if(usuario.getTipoUsuario().getId() != tipoGerente.get().getId()){
            return;
        }

        if(usuariosDeCliente.stream().filter(u -> u.getTipoUsuario().getId() == tipoGerente.get().getId()).toList().size() > 0){
            throw new UsuarioTipoGerenteValidationException();
        }

    }

    private void validateAndSetTipoUsuario(Usuario usuario) throws UsuarioTipoUsuarioNotFoundValidationException, UsuarioTipoUsuarioEmptyValidationException{
        // we don't allow Usuario without TipoUsuario
        if(usuario.getTipoUsuario() == null){
            throw new UsuarioTipoUsuarioEmptyValidationException();
        }

        Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findById(usuario.getTipoUsuario().getId());

        if(!tipoUsuario.isPresent()){
            throw new UsuarioTipoUsuarioNotFoundValidationException();
        }

        usuario.setTipoUsuario(tipoUsuario.get());
    }

    private void validateAndSetCliente(Usuario usuario) throws UsuarioClienteNotFoundValidationException, UsuarioClienteEmptyValidationException{
        // we don't allow Usuario without Cliente
        if(usuario.getCliente() == null){
            throw new UsuarioClienteEmptyValidationException();
        }

        // Optional<Cliente> clienteUsuario = clienteRepository.findById(usuario.getCliente().getId());
        Cliente clienteUsuario = new Cliente();
        clienteUsuario.setHabilitadoOnline(true);
        clienteUsuario.setCorreoElectronico(usuario.getCorreoElectronico());
        clienteUsuario.setId(usuario.getCliente().getId());  
        clienteUsuario.setMaximoCuentaCorriente(10000.00);
        clienteUsuario.setCuit("undefined");
        clienteUsuario.setRazonSocial(usuario.getCliente().getRazonSocial());



        // if(!clienteUsuario.isPresent()){
        //     throw new UsuarioClienteNotFoundValidationException();
        // }

        usuario.setCliente(clienteUsuario);
    }

    private void validateUniqueUser(Usuario usuario) throws UsuarioExisteValidationException{
        Optional<Usuario> dupedUser = usuarioRepository.findByUserName(usuario.getUserName());

        if(dupedUser.isPresent()){
            throw new UsuarioExisteValidationException();
        }
    }
}
