package dan.ms.tp.msusuarios.rest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.exception.Cliente.ClienteDuplicadoException;
import dan.ms.tp.msusuarios.exception.Cliente.ClienteInvalidModificationException;
import dan.ms.tp.msusuarios.exception.Cliente.ClienteNotFoundException;
import dan.ms.tp.msusuarios.exception.Cliente.ClienteValidationException;
import dan.ms.tp.msusuarios.modelo.Cliente;

@Service
public interface ClienteService {

    Cliente createCliente(Cliente cliente) throws ClienteValidationException;

    Cliente modifyCliente(Cliente cliente) throws ClienteValidationException;

    Void deleteCliente(Integer id) throws ClienteValidationException;

    Cliente getClienteById(Integer id) throws ClienteValidationException;

    Cliente getUserByCUIT(String cuit) throws ClienteValidationException;

    List<Cliente> getAllClientes();
}
