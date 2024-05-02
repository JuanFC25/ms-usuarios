package dan.ms.tp.msusuarios.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.exception.Cliente.ClienteDuplicadoException;
import dan.ms.tp.msusuarios.exception.Cliente.ClienteInvalidModificationException;
import dan.ms.tp.msusuarios.exception.Cliente.ClienteNotFoundException;
import dan.ms.tp.msusuarios.exception.Cliente.ClienteValidationException;
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
    public Cliente modifyCliente(Cliente cliente) throws ClienteValidationException {

       Optional<Cliente> clienteOpt = clienteRepo.findById(cliente.getId());
       if(!clienteOpt.isPresent()){
           throw new ClienteNotFoundException(cliente.getId());
        }
        
        Cliente c = clienteOpt.get();
        if(!c.getCuit().equals(cliente.getCuit())){
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
        Optional<Cliente> clienteOpt = clienteRepo.findById(id);
        if(!clienteOpt.isPresent()){
           throw new ClienteNotFoundException(id);
        }

        Cliente c = clienteOpt.get();
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

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepo.findAll();
    }
}
