package dan.ms.tp.msusuarios.exception.Usuario;

public class UsuarioClienteEmptyValidationException  extends UsuarioValidationException{
    public UsuarioClienteEmptyValidationException() {
        super("Cliente debe estar definido en el usuario");
    }
}


