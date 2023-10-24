package dan.ms.tp.msusuarios.rest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.modelo.TipoUsuario;

@Service
public interface TipoUsuarioService {

    List<TipoUsuario> findAll();

}
