package pl.oncode.glass;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Order;
import pl.oncode.glass.service.DatabaseService;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GlassApplicationTests {

    @Autowired
    DatabaseService dbs;

    @Test
    public void retrieveOrderTest() {
        
    }

}
