package com.university.application;

import com.university.domain.Client;
import com.university.domain.ClientRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by redric on 16.11.17.
 * For testing purposes.
 */
public class ApplicationServiceTester {
    @Mock
    ClientRepository mockedRepository;

    @InjectMocks
    ApplicationService service = new ApplicationServiceImpl(mockedRepository);

    @Before
    public void setUp(){
        service = new ApplicationServiceImpl(mockedRepository);
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void validate() {
        validateMockitoUsage();
    }

    /**
     * Test removing of entities from repository which names start on letter K.
     */
    @Test
    public void testRemovalOfEntitiesBeginningWithCharK() {
        List<Client> repositoryList = Arrays.asList(
                new Client(0, "Kevin", "Mn1", "Srn1", "0981"),
                new Client(1, "kob", "Mn2", "Srn2", "0982"),
                new Client(2, "Kolin", "Mn3", "Srn3", "0983"),
                new Client(3, "Helen", "Mn4", "Srn4", "0984"),
                new Client(4, "Tom", "Mn5", "Srn5", "0985"),
                new Client(5, "Red", "Mn6", "Srn6", "09876"),
                new Client(6, "Kod", "Mn7", "Srn7", "09877"));

        when(mockedRepository.getClients()).thenReturn(repositoryList);

        service.removeEntitiesBeginningWithChar('K');

        verify(mockedRepository).getClients();
        verify(mockedRepository).removeClient(0);
        verify(mockedRepository).removeClient(1);
        verify(mockedRepository).removeClient(2);
        verify(mockedRepository).removeClient(6);
    }

    /**
     * Test removing of entities with ' ' as first character.
     */
    @Test
    public void testSpaceAsInputChar() {
        List<Client> repositoryList = Arrays.asList(
                new Client(0, " Kevin", "Mn1", "Srn1", "0981"),
                new Client(1, "Bob", "Mn2", "Srn2", "0982"),
                new Client(2, "Kolin", "Mn3", "Srn3", "0983"),
                new Client(3, "Helen", "Mn4", "Srn4", "0984"),
                new Client(4, "Tom", "Mn5", "Srn5", "0985"),
                new Client(5, "Red", "Mn6", "Srn6", "09876"),
                new Client(6, "Kod", "Mn7", "Srn7", "09877"));

        when(mockedRepository.getClients()).thenReturn(repositoryList);

        service.removeEntitiesBeginningWithChar(' ');

        verify(mockedRepository).getClients();
        verify(mockedRepository, times(1)).removeClient(0);
        for(int i = 1; i < 7; i++) {
            verify(mockedRepository, never()).removeClient(i);
        }
    }

    /**
     * Test removing of entities on 'K' with empty repository.
     */
    @Test
    public void testEmptyRepository() {
        List<Client> repositoryList = new ArrayList<Client>();

        when(mockedRepository.getClients()).thenReturn(repositoryList);

        service.removeEntitiesBeginningWithChar('K');

        verify(mockedRepository).getClients();
        verify(mockedRepository, never()).removeClient(anyInt());
    }

    /**
     * Test creating 100 random entities.
     */
    @Test
    public void testCreateRandomEntities() {
        service.createHundredRandomEntities();
        verify(mockedRepository, times(100)).addClient(any(Client.class));
    }

    /**
     * Test creating of 100 not empty entities.
     */
    @Test
    public void testNoEmptyEntities() {
        Client client = new Client();
        service.createHundredRandomEntities();
        verify(mockedRepository, never()).addClient(eq(client));
        verify(mockedRepository, times(100)).addClient(any(Client.class));
    }
}
