package dan.ms.tp.msusuarios.dto;

import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.Usuario;

public class AuthRegisterDto {

    public String userName;
    public String password;
    public String correoElectronico;
    public Integer cliente;

    public  Usuario toUser(){
        Usuario user = new Usuario();

        user.setUserName(this.userName);
        user.setPassword(this.password);
        user.setCorreoElectronico(this.correoElectronico);

        Cliente clienteOfUser = new Cliente();
        clienteOfUser.setId(this.cliente);
        user.setCliente(clienteOfUser);

        return user;
    }
}

