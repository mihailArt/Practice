package market.service;

import market.entity.Client;
import market.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Client getById(int id){
        return clientRepository.findById(id);
    }

    public Client save(Client client){
         return clientRepository.save(client);
    }

    public void deleteById(int id){
        clientRepository.deleteById(id);
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }
}
