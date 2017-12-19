package IT;

import com.university.application.AppConfig;
import com.university.application.ApplicationService;
import com.university.application.ApplicationServiceImpl;
import com.university.domain.Client;
import com.university.domain.ClientRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by redric on 30.11.17.
 * Class for integration testing.
 */
public class ITApplicationServiceTester {

    @After
    public void clearDB() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ClientRepository clientRepository = (ClientRepository) applicationContext.getBean(ClientRepository.class);
        clientRepository.removeDB();
    }

    /**
     * Test creating 100 random entities.
     */
    @Test
    public void testCreateRandomEntities() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ClientRepository clientRepository = (ClientRepository) applicationContext.getBean(ClientRepository.class);
        ApplicationService service = new ApplicationServiceImpl(clientRepository);

        int actual = clientRepository.getClients().size();
        Assert.assertEquals(0, actual);

        service.createHundredRandomEntities();

        actual = clientRepository.getClients().size();
        Assert.assertEquals(100, actual);

    }

    /**
     * Test removing of entities from repository which names start on letter K.
     */
    @Test
    public void testRemovalOfEntitiesBeginningWithCharK() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ClientRepository clientRepository = (ClientRepository) applicationContext.getBean(ClientRepository.class);
        ApplicationService service = new ApplicationServiceImpl(clientRepository);

        List<Client> repositoryList = Arrays.asList(
                new Client(0, "Kevin", "Mn1", "Srn1", "0981"),
                new Client(1, "kob", "Mn2", "Srn2", "0982"),
                new Client(2, "Kolin", "Mn3", "Srn3", "0983"),
                new Client(3, "Helen", "Mn4", "Srn4", "0984"),
                new Client(4, "Tom", "Mn5", "Srn5", "0985"),
                new Client(5, "Red", "Mn6", "Srn6", "09876"),
                new Client(6, "Kod", "Mn7", "Srn7", "09877"));

        int actual = clientRepository.getClients().size();
        Assert.assertEquals(0, actual);

        for(Client client : repositoryList) {
            clientRepository.addClient(client);
        }

        actual = clientRepository.getClients().size();
        Assert.assertEquals(7, actual);

        service.removeEntitiesBeginningWithChar('K');

        actual = clientRepository.getClients().size();
        Assert.assertEquals(3, actual);
    }
}
