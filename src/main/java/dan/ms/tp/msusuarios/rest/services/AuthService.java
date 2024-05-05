package dan.ms.tp.msusuarios.rest.services;

import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dto.AuthLoginDto;
import dan.ms.tp.msusuarios.dto.AuthRegisterDto;
import dan.ms.tp.msusuarios.dto.AuthTokenDto;
import dan.ms.tp.msusuarios.exception.ApiValidationException;
import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public interface AuthService {

    Usuario register(AuthRegisterDto user) throws ApiValidationException;

    AuthTokenDto login(AuthLoginDto dto) throws Exception;

    Boolean validate(String token);

}
