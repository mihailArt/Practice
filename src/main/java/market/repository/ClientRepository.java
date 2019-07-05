package market.repository;

import market.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findById(int id);
    List<Client> findAll();
}
