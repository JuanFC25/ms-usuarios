package dan.ms.tp.msusuarios.rest.services;

import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.modelo.Cliente;

@Service
public interface ClienteService {

    Cliente createCliente(Cliente cliente);

    Cliente modifyCliente(Cliente cliente);

    Void deleteCliente(Cliente cliente);
}
