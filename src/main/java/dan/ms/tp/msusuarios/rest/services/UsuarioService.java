package dan.ms.tp.msusuarios.rest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.exception.Usuario.UsuarioClienteNotFoundValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioTipoUsuarioEmptyValidationException;
import dan.ms.tp.msusuarios.exception.Usuario.UsuarioValidationException;
import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public interface UsuarioService {

    Usuario createUser(Usuario usuario) throws UsuarioValidationException;

    Void deleteUser(Integer id);

    Usuario modifyUser(Usuario usuario) throws UsuarioValidationException;

    Usuario getUserById(Integer id);

    List<Usuario> getUsersByClientId(Integer idCliente) throws UsuarioClienteNotFoundValidationException;

    List<Usuario> getUsersByClientIdAndUserType(Integer idCliente, Integer userType) throws UsuarioClienteNotFoundValidationException, UsuarioTipoUsuarioEmptyValidationException;

}
