package dan.ms.tp.msusuarios.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.modelo.Usuario;
import java.util.List;


@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByCliente(Cliente cliente);

    List<Usuario> findByClienteAndTipoUsuario(Cliente cliente, TipoUsuario tipoUsuario);

    List<Usuario> findAllByCliente(Cliente cliente);

    Optional<Usuario> findByUserName(String username);

    // Usuario findByClientIdAndUserType(Integer idCliente, Integer userType);

}
