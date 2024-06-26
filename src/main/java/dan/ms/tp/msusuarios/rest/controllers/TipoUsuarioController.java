package dan.ms.tp.msusuarios.rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.rest.services.TipoUsuarioService;

@RestController
@RequestMapping("api/tipo-usuario")
@CrossOrigin(origins = "http://localhost")
public class TipoUsuarioController {

    @Autowired
    TipoUsuarioService tipoUsrService;

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> getAllTipoUsuario() {
        return ResponseEntity.ok().body(tipoUsrService.findAll());
    }

}
