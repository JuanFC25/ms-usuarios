package dan.ms.tp.msusuarios.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost")
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
    public ResponseEntity<Usuario> modifyUser(@RequestBody Usuario usuario) throws ApiValidationException{
        return ResponseEntity.ok().body(usuarioService.modifyUser(usuario));
    }

    // obtener usuario por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUserById(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping(value = "/search/username/{username}")
    public ResponseEntity<Usuario> getUserByUsername(@PathVariable String username) {
        Usuario usuario = usuarioService.getUserByUsername(username);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuario);
    }

    // obtener usuarios por cliente
    @GetMapping(value = "/search/cliente/{idCliente}")
    public ResponseEntity<List<Usuario>> getUsersByClientId(@RequestBody @PathVariable Integer idCliente) throws ApiValidationException {
        return ResponseEntity.ok().body(usuarioService.getUsersByClientId(idCliente));
    }

    // obtener usuarios por cliente y por Tipo de Usuario
    @GetMapping(value = "/search/cliente/{idCliente}/{userType}")
    public ResponseEntity<List<Usuario>> getUsersByClientIdAndUserType(@RequestBody @PathVariable Integer idCliente,
            @PathVariable Integer userType) throws ApiValidationException{
        return ResponseEntity.ok()
                .body(usuarioService.getUsersByClientIdAndUserType(idCliente, userType));
    }
}
