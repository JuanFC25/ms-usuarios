package dan.ms.tp.msusuarios.exception;

public class ClienteDuplicadoException extends Exception{

    public ClienteDuplicadoException(String cuit) {
        super("El cliente con CUIT: "+cuit+" ya existe.");
    }

}
