package dan.ms.tp.msusuarios.exception;

public class ClienteInvalidModificationException extends Exception {

    public ClienteInvalidModificationException(String cuit) {
        super("No puede modificarse el CUIT de un cliente.");
    }

}
