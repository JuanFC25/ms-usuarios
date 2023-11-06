package dan.ms.tp.msusuarios.exception.Cliente;


public class ClienteInvalidModificationException extends ClienteValidationException {

    public ClienteInvalidModificationException(String cuit) {
        super("No puede modificarse el CUIT de un cliente.");
    }

}
