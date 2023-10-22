package dan.ms.tp.msusuarios.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dan.ms.tp.msusuarios.modelo.Usuario;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Integer> {

    // Optional<Usuario> findByClientId(Integer idCliente);

    // Usuario findByClientIdAndUserType(Integer idCliente, Integer userType);

}
