package dan.ms.tp.msusuarios.exception.Usuario;

public class UsuarioNotFoundValidationException extends UsuarioValidationException{
    public UsuarioNotFoundValidationException(int id) {
        super("No existe un usuario con este "+id+" id");
    }
    //public UsuarioNotFoundByClienteValidationException(int idCliente) {
    //  super("No existe un usuario con este "+id+" id");
    //}
    public UsuarioNotFoundValidationException(int idCliente,int userType) {
        super("No existe un usuario para este "+idCliente+" id, del tipo"+userType);
    }
}
    

