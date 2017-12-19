package com.university.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by redric on 09.11.17.
 * Implementation of repository for client table.
 */
@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Create Customer entity in database.
     */
    public void setUp() {
        String query = "create table if not exists Customer(id int primary key, firstName varchar(50), " +
                "middleName varchar(50), surname varchar(50), telephoneNumber varchar(10));";
        jdbcTemplate.execute(query);
    }

    /**
     * Remove Customer entity from database.
     */
    public void removeDB() {
        String query = "drop table Customer;";
        jdbcTemplate.execute(query);
    }

    /**
     * Add client to repository.
     *
     * @param newClient is the client to add
     */
    public void addClient(Client newClient) {
        int id = 0;
        setUp();
        if(jdbcTemplate.queryForObject("select max(id) from Customer;", Integer.class) != null) {
            id = jdbcTemplate.queryForObject("select max(id) from Customer;", Integer.class);
        }
        String query = String.format("insert into Customer(id, firstName, middleName, surname, telephoneNumber) values(%d, '%s', '%s', '%s', '%s');",
                ++id, newClient.getFirstName(), newClient.getMiddleName(),
                newClient.getSurname(), newClient.getTelephoneNumber());
        jdbcTemplate.execute(query);
    }

    /**
     * Get client from repository.
     *
     * @param id of the client in repository.
     * @return
     */
    public Client getClient(long id) {
        Client client = new Client();
        String query = String.format("select * from Customer where id=%d", id);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        for (Map row : rows) {
            client.setId((Integer)(row.get("id")));
            client.setFirstName((String)row.get("firstName"));
            client.setMiddleName((String)row.get("middleName"));
            client.setSurname((String)row.get("surname"));
            client.setTelephoneNumber((String)row.get("telephoneNumber"));
        }

        return client;
    }

    /**
     * Get all clients from repository.
     *
     * @return
     */
    public List<Client> getClients() {
        setUp();

        String que = "select * from Customer where firstName is not null";
        List<Client> clientsList = new ArrayList<Client>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(que);
        for (Map row : rows) {
            Client client = new Client();
            client.setId((Integer)(row.get("id")));
            client.setFirstName((String)row.get("firstName"));
            client.setMiddleName((String)row.get("middleName"));
            client.setSurname((String)row.get("surname"));
            client.setTelephoneNumber((String)row.get("telephoneNumber"));
            clientsList.add(client);
        }

        return clientsList;
    }

    /**
     * Edit client entity in repository
     *
     * @param updatedClient is new client entity
     */
    public void editClient(Client updatedClient){
        String query = String.format("update Customer set firstName='%s', middleName='%s'," +
                        " surname='%s', telephoneNumber='%s' where id=%d);",
                updatedClient.getFirstName(), updatedClient.getMiddleName(), updatedClient.getSurname(),
                updatedClient.getTelephoneNumber(), updatedClient.getId());
        jdbcTemplate.execute(query);
    }

    /**
     * Remove client from repository
     *
     * @param id of the client to remove
     */
    public void removeClient(long id) {
        String query = String.format("delete from Customer where id = %d;", id);
        jdbcTemplate.execute(query);
    }
}
