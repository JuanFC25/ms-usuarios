package dan.ms.tp.msusuarios.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.exception.ApiValidationException;
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
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) throws ApiValidationException {
        return ResponseEntity.ok().body(clienteService.createCliente(cliente));
    }

    // ver si es necesario el id en la direccion

    // modificacion cliente
    @PutMapping(value = "/modify")
    public ResponseEntity<Cliente> modifyCliente(@RequestBody Cliente cliente) throws ApiValidationException {
            return ResponseEntity.ok().body(clienteService.modifyCliente(cliente));
    }

    // baja cliente
    // probar a modificar el void
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteCliente(@RequestBody @PathVariable Integer id) throws ApiValidationException {
        ResponseEntity.ok().body(clienteService.deleteCliente(id));
        return null;
    }

    // busqueda por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) throws ApiValidationException {
        return ResponseEntity.ok().body(clienteService.getClienteById(id));       
    }

    // busqueda por CUIT
    @GetMapping(value = "/search/{cuit}")
    public ResponseEntity<Cliente> getClienteByCUIT(@PathVariable String cuit) throws ApiValidationException {
        return ResponseEntity.ok().body(clienteService.getClienteByCUIT(cuit));
    }

    @GetMapping(value = "/search/razonSocial/{razonSocial}")
    public ResponseEntity<Cliente> getClienteByRazonSocial(@PathVariable String razonSocial) throws ApiValidationException {
        return ResponseEntity.ok().body(clienteService.getClienteByRazonSocial(razonSocial));
    }
    

    @GetMapping(value = "/")
    public ResponseEntity<List<Cliente>> getAllClientes() throws ApiValidationException {
        return ResponseEntity.ok().body(clienteService.getAllClientes());
    }

}
