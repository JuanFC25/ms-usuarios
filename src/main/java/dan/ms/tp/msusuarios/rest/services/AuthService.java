package dan.ms.tp.msusuarios.rest.services;

import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dto.AuthLoginDto;
import dan.ms.tp.msusuarios.dto.AuthTokenDto;
import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public interface AuthService {

    Usuario register(Usuario dto);

    AuthTokenDto login(AuthLoginDto dto) throws Exception;

    Boolean validate(String token);

}
