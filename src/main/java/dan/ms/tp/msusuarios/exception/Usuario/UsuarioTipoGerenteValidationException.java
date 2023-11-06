package dan.ms.tp.msusuarios.exception.Usuario;

public class UsuarioTipoGerenteValidationException  extends UsuarioValidationException{
    public UsuarioTipoGerenteValidationException() {
        super("Cliente ya contiene un gerente");
    }
}
