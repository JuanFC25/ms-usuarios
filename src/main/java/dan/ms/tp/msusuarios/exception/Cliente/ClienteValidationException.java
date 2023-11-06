package dan.ms.tp.msusuarios.exception.Cliente;

import dan.ms.tp.msusuarios.exception.ApiValidationException;

public class ClienteValidationException extends ApiValidationException{
    public ClienteValidationException(String errorMessage) {
        super(errorMessage);
    }
}
