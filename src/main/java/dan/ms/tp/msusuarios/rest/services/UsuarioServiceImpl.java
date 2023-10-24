package dan.ms.tp.msusuarios.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.dao.TipoUsuarioJpaRepository;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
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
    public Usuario createUser(Usuario usuario) {
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

}
