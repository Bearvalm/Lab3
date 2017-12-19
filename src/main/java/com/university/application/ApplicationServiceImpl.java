package com.university.application;

import com.university.domain.Client;
import com.university.domain.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by redric on 09.11.17.
 * Implementation of application service that is dealing with repository functionality
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ClientRepository repository;

    public ApplicationServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    /**
     * Remove entities in repository beginning with character ch
     *
     * @param ch to remove entities
     */
    @Transactional
    public void removeEntitiesBeginningWithChar(char ch) {
        List<Client> clientsList = repository.getClients();

        for(Client client : clientsList) {
            if(client.getFirstName().charAt(0) == Character.toLowerCase(ch)
                    || client.getFirstName().charAt(0) == Character.toUpperCase(ch)) {
                repository.removeClient(client.getId());
            }
        }
    }

    /**
     * Create 100 random entites of class Client in repository
     */
    @Transactional
    public void createHundredRandomEntities() {
        Client client = new Client();

        for(int i = 0; i < 100; i++) {
            if(i % 20 == 0) {
                client.setFirstName("K" + Integer.toString(i));
            } else {
                client.setFirstName("Name" + Integer.toString(i));
            }

            client.setMiddleName("Middle" + Integer.toString(i));
            client.setSurname("Surname" + Integer.toString(i));
            client.setTelephoneNumber("09" + Integer.toString(i));

            repository.addClient(client);
        }
    }
}
