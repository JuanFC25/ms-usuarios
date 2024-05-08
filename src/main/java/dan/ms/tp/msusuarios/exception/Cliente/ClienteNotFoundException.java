package dan.ms.tp.msusuarios.exception.Cliente;

public class ClienteNotFoundException extends ClienteValidationException {

    public ClienteNotFoundException(Integer id) {
        super("No existe un cliente con id "+id);
    }
    public ClienteNotFoundException(String value, Field field) {
        super("No existe un cliente con " + field.name().replace("_", " ") + "" + " " + value);
    }

    public enum Field {
        CUIT,
        RAZON_SOCIAL,
      }
}
