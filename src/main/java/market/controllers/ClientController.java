package market.controllers;

import market.entity.Client;
import market.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client client = clientService.getById(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAll();

        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client) {
        HttpHeaders headers = new HttpHeaders();

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client checkClient = clientService.getById(client.getId());
        if (checkClient == null){
            clientService.save(client);
            return new ResponseEntity<>(client, headers, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(checkClient, headers, HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> updateClient(@PathVariable("id") Integer id, @RequestBody @Valid Client client) {
        HttpHeaders headers = new HttpHeaders();

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client checkClient = clientService.getById(id);

        if(checkClient != null){
            checkClient.setClientName(client.getClientName());
            checkClient.setPassword(client.getPassword());
            clientService.save(checkClient);
            return new ResponseEntity<>(client, headers, HttpStatus.OK);
        }
        else
            return  new ResponseEntity<>( headers, HttpStatus.NOT_FOUND);


    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Integer id) {
        Client client = clientService.getById(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clientService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
