package dan.ms.tp.msusuarios.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.TipoUsuarioJpaRepository;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    @Autowired
    TipoUsuarioJpaRepository tipoUsuarioRepository;

    @Override
    public List<TipoUsuario> findAll() {

        return tipoUsuarioRepository.findAll();
    }

}
