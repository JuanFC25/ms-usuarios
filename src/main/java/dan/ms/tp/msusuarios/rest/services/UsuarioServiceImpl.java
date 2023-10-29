package dan.ms.tp.msusuarios.rest.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.dao.TipoUsuarioJpaRepository;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioPasswordValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioTipoGerenteValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioValidationException;
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

        //validateUserPassword(usuario);
        validateUserClienteType(usuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Void deleteUser(Integer id) {
        usuarioRepository.deleteById(id);
        return null;
    }

    @Override
    public Usuario modifyUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario getUserById(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario getUserByClientId(Integer idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente).get();

        return usuarioRepository.findByCliente(cliente).get();

    }

    @Override
    public Usuario getUserByClientIdAndUserType(Integer idCliente, Integer userType) {
        Cliente cliente = clienteRepository.findById(idCliente).get();
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(userType).get();

        return usuarioRepository.findByClienteAndTipoUsuario(cliente, tipoUsuario).get();
    }

    private void validateUserPassword(Usuario user) throws UsuarioPasswordValidationException{
        if(user.getPassword() == null){
            throw new UsuarioPasswordValidationException();
        }

        String passwordRegex = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.[@#$%^&+=!]).{12,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(user.getPassword());

        if(!matcher.find()){
            throw new UsuarioPasswordValidationException();
        }
    }

    private void validateUserClienteType(Usuario usuario) throws UsuarioTipoGerenteValidationException{
        Optional<TipoUsuario> tipoGerente = tipoUsuarioRepository.findOneByTipo("ADMIN");
        List<Usuario> usuariosDeCliente = usuarioRepository.findAllByCliente(usuario.getCliente());
        if(usuario.getTipoUsuario().getId() != tipoGerente.get().getId()){
            return;
        }

        if(usuariosDeCliente.stream().filter(u -> u.getTipoUsuario().getId() == tipoGerente.get().getId()).toList().size() > 0){
            throw new UsuarioTipoGerenteValidationException();
        }

    }

}
