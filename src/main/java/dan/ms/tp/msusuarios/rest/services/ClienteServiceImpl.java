package dan.ms.tp.msusuarios.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.exception.ClienteDuplicadoException;

import dan.ms.tp.msusuarios.exception.ClienteInvalidModificationException;
import dan.ms.tp.msusuarios.exception.ClienteNotFoundException;
import dan.ms.tp.msusuarios.modelo.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteJpaRepository clienteRepo;

    @Override
    public Cliente createCliente(Cliente cliente) throws ClienteDuplicadoException {

        if(repeatedCuit(cliente.getCuit())){
            throw new ClienteDuplicadoException(cliente.getCuit());
        }
        return clienteRepo.save(cliente);
    }

   

    @Override
    public Cliente modifyCliente(Cliente cliente) throws ClienteNotFoundException, ClienteInvalidModificationException {

       Cliente c = clienteRepo.findById(cliente.getId()).get();
       if(c==null){
            throw new ClienteNotFoundException(cliente.getId());
       }
       if(c.getCuit() != cliente.getCuit()){
            throw new ClienteInvalidModificationException(cliente.getCuit());
       }
        return clienteRepo.save(cliente);
    }

    @Override
    public Void deleteCliente(Integer id) throws ClienteNotFoundException {
        if(!clienteRepo.existsById(id)){
            throw new ClienteNotFoundException(id);
        }
        clienteRepo.deleteById(id);
        return null;

    }

    @Override
    public Cliente getClienteById(Integer id) throws ClienteNotFoundException {
        Cliente c = clienteRepo.findById(id).get();
        if(c==null){
            throw new ClienteNotFoundException(id);
        }
        return c;
    }

    @Override
    public Cliente getUserByCUIT(String cuit) throws ClienteNotFoundException {
        Cliente c = clienteRepo.findByCuit(cuit);
        if(c == null){
            throw new ClienteNotFoundException(cuit);
        }
        return c;
    }

    private boolean repeatedCuit(String cuit) {
        return clienteRepo.findAll().stream().filter(c->c.getCuit().equals(cuit)).findAny().isPresent();
    }
}
