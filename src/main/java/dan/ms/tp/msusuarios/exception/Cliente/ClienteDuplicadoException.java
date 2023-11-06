package dan.ms.tp.msusuarios.exception.Cliente;


public class ClienteDuplicadoException extends ClienteValidationException{

    public ClienteDuplicadoException(String cuit) {
        super("El cliente con CUIT: "+cuit+" ya existe.");
    }

}
