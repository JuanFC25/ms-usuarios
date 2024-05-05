package dan.ms.tp.msusuarios.exception.Usuario;

public class UsuarioExisteValidationException  extends UsuarioValidationException{
    public UsuarioExisteValidationException() {
        super("El nombre de usuario ya existe.");
    }
}


