package dan.ms.tp.msusuarios.dto;

import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.modelo.Usuario;
import lombok.Data;

@Data
public class UsuarioDto {

    private Integer id;
    private String userName;
    private String password;
    private String correoElectronico;
    private Cliente cliente;
    private TipoUsuario tipoUsuario;

    public Usuario toUser(){
        Usuario user = new Usuario();

        if(id != null){
            user.setId(id);
        }

        user.setUserName(userName);
        user.setPassword(password);
        user.setCorreoElectronico(correoElectronico);
        user.setCliente(cliente);
        user.setTipoUsuario(tipoUsuario);

        return user;
    }

    public static UsuarioDto from(Usuario usuario){
        UsuarioDto dto = new UsuarioDto();

        dto.setId(usuario.getId());
        dto.setUserName(usuario.getUserName());
        dto.setPassword(usuario.getPassword());
        dto.setCorreoElectronico(usuario.getCorreoElectronico());
        dto.setCliente(usuario.getCliente());
        dto.setTipoUsuario(usuario.getTipoUsuario());

        return dto;
    }
    
}
