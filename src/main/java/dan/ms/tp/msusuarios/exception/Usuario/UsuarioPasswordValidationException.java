package dan.ms.tp.msusuarios.exception.Usuario;

public class UsuarioPasswordValidationException  extends UsuarioValidationException{
    public UsuarioPasswordValidationException() {
        super("Invalid User password");
    }
}
