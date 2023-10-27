package dan.ms.tp.msusuarios.rest.services;

import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.exception.ClienteDuplicadoException;
import dan.ms.tp.msusuarios.exception.ClienteInvalidModificationException;
import dan.ms.tp.msusuarios.exception.ClienteNotFoundException;
import dan.ms.tp.msusuarios.modelo.Cliente;

@Service
public interface ClienteService {

    Cliente createCliente(Cliente cliente) throws ClienteDuplicadoException;

    Cliente modifyCliente(Cliente cliente) throws ClienteNotFoundException, ClienteInvalidModificationException;

    Void deleteCliente(Integer id) throws ClienteNotFoundException;

    Cliente getClienteById(Integer id) throws ClienteNotFoundException;

    Cliente getUserByCUIT(String cuit) throws ClienteNotFoundException;
}
