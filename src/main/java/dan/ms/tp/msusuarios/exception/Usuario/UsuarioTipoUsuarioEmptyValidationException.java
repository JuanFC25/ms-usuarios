package dan.ms.tp.msusuarios.exception.Usuario;

public class UsuarioTipoUsuarioEmptyValidationException  extends UsuarioValidationException{
    public UsuarioTipoUsuarioEmptyValidationException() {
        super("El tipo de usuario debe estar presente en el usuario");
    }
}
