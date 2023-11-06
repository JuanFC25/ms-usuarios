package dan.ms.tp.msusuarios.exception.Cliente;

public class ClienteNotFoundException extends ClienteValidationException {

    public ClienteNotFoundException(Integer id) {
        super("No existe un cliente con id "+id);
    }
    public ClienteNotFoundException(String cuit) {
        super("No existe un cliente con CUIT "+cuit);
    }
}
