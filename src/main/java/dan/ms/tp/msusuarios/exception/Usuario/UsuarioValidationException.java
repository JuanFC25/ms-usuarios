package dan.ms.tp.msusuarios.exception.Usuario;

import dan.ms.tp.msusuarios.exception.ApiValidationException;

public class UsuarioValidationException extends ApiValidationException{
    public UsuarioValidationException(String errorMessage) {
        super(errorMessage);
    }
}
