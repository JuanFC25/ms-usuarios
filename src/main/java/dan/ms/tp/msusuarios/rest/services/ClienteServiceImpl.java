package dan.ms.tp.msusuarios.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.modelo.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteJpaRepository clienteRepo;

    @Override
    public Cliente createCliente(Cliente cliente) {

        // implementar validaciones
        return clienteRepo.save(cliente);
    }

    @Override
    public Cliente modifyCliente(Cliente cliente) {

        // implementar validaciones
        return clienteRepo.save(cliente);
    }

    @Override
    public Void deleteCliente(Cliente cliente) {
        clienteRepo.delete(cliente);
        return null;

    }

}
