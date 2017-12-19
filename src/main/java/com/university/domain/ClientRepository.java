package com.university.domain;

import java.util.List;

/**
 * Created by redric on 09.11.17.
 * Interface for repository of client entity
 */
public interface ClientRepository {
    void addClient(Client newClient);
    Client getClient(long id);
    List<Client> getClients();
    void editClient(Client updatedClient);
    void removeClient(long id);
    void removeDB();
}
