package dan.ms.tp.msusuarios.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.exception.ApiValidationException;
import dan.ms.tp.msusuarios.modelo.Usuario;
import dan.ms.tp.msusuarios.rest.services.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // alta usuario
    @PostMapping(value = "/create")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) throws ApiValidationException {
        return ResponseEntity.ok().body(usuarioService.createUser(usuario));
    }

    // baja usuario
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteUser(@RequestBody @PathVariable Integer id) {
        return ResponseEntity.ok().body(usuarioService.deleteUser(id));
    }

    // modificar usuario
    @PutMapping(value = "/modify")
    public ResponseEntity<Usuario> modifyUser(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.modifyUser(usuario));
    }

    // obtener usuario por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUserById(@RequestBody @PathVariable Integer id) {
        return ResponseEntity.ok().body(usuarioService.getUserById(id));
    }

    // obtener usuario por cliente
    @GetMapping(value = "/search/cliente/{idCliente}")
    public ResponseEntity<Usuario> getUserByClientId(@RequestBody @PathVariable Integer idCliente) {
        return ResponseEntity.ok().body(usuarioService.getUserByClientId(idCliente));
    }

    // obtener usuario por cliente y por Tipo de Usuario
    @GetMapping(value = "/search/cliente/{idCliente}/{userType}")
    public ResponseEntity<Usuario> getUserByClientIdAndUserType(@RequestBody @PathVariable Integer idCliente,
            @PathVariable Integer userType) {
        return ResponseEntity.ok()
                .body(usuarioService.getUserByClientIdAndUserType(idCliente, userType));
    }
}
