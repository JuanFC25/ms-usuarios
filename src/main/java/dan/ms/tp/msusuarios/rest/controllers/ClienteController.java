package dan.ms.tp.msusuarios.rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.dao.TipoUsuarioJpaRepository;
import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.rest.services.ClienteService;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping(value = "/create")
    public ResponseEntity<Cliente> createUser(@RequestBody Cliente cliente) {
        return ResponseEntity.ok().body(clienteService.createCliente(cliente));

    }

}
