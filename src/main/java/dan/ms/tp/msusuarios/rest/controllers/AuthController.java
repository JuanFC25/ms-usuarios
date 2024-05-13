package dan.ms.tp.msusuarios.rest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.dto.AuthLoginDto;
import dan.ms.tp.msusuarios.dto.AuthRegisterDto;
import dan.ms.tp.msusuarios.dto.AuthTokenDto;
import dan.ms.tp.msusuarios.dto.UsuarioDto;
import dan.ms.tp.msusuarios.exception.ApiValidationException;
import dan.ms.tp.msusuarios.modelo.Usuario;
import dan.ms.tp.msusuarios.rest.services.AuthService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/register")
    private  ResponseEntity<UsuarioDto> register(@RequestBody(required = true) AuthRegisterDto usuario) throws ApiValidationException {
        Usuario registeredUser = authService.register(usuario);

        UsuarioDto dtoResponse = UsuarioDto.from(registeredUser);
        return ResponseEntity.ok().body(dtoResponse);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthTokenDto> login(@RequestBody AuthLoginDto loginDto) {
        try {
            return ResponseEntity.ok().body(authService.login(loginDto));
            
        } catch (Exception e) {
        return ResponseEntity.status(401).build();

        }
    }


    @PostMapping(value = "/validate")
    public ResponseEntity<Void> validate(@RequestBody AuthTokenDto token){
        Boolean isValid = authService.validate(token.getToken());
        if(isValid){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(401).build();
    }


    
}
