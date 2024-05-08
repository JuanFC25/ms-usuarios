package dan.ms.tp.msusuarios.exception.Cliente;


public class ClienteDuplicadoException extends ClienteValidationException{

    public ClienteDuplicadoException(String cuit) {
        super("El cliente con CUIT: "+cuit+" ya existe.");
    }

    public ClienteDuplicadoException(String value, Field field) {
        super("Ya existe un cliente con " + field.name().replace("_", " ") + "" + " " + value);
    }

    public enum Field {
        CUIT,
        RAZON_SOCIAL,
      }

}
