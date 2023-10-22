package dan.ms.tp.msusuarios.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.modelo.Cliente;

import dan.ms.tp.msusuarios.rest.services.ClienteService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    // alta cliente
    @PostMapping(value = "/create")
    public ResponseEntity<Cliente> createUser(@RequestBody Cliente cliente) {
        return ResponseEntity.ok().body(clienteService.createCliente(cliente));
    }

    // ver si es necesario el id en la direccion

    // modificacion cliente
    @PutMapping(value = "/modify")
    public ResponseEntity<Cliente> modifyUser(@RequestBody Cliente cliente) {
        return ResponseEntity.ok().body(clienteService.modifyCliente(cliente));
    }

    // baja cliente
    // probar a modificar el void
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteUser(@RequestBody @PathVariable Integer id) {
        ResponseEntity.ok().body(clienteService.deleteCliente(id));
        return null;
    }

    // busqueda por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(clienteService.getClienteById(id));
    }

    // busqueda por CUIT
    @GetMapping(value = "/search/{cuit}")
    public ResponseEntity<Cliente> getUserByCUIT(@PathVariable String cuit) {
        return ResponseEntity.ok().body(clienteService.getUserByCUIT(cuit));
    }

}
