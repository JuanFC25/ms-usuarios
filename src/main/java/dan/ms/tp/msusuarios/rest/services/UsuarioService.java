package dan.ms.tp.msusuarios.rest.services;

import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public interface UsuarioService {

    Usuario createUser(Usuario usuario);

    Void deleteUser(Integer id);

    Usuario modifyUser(Usuario usuario);

    Usuario getUserById(Integer id);

    // Usuario getUserByClientId(Integer idCliente);

    // Usuario getUserByClientIdAndUserType(Integer idCliente, Integer userType);

}
