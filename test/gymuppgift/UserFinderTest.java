/*

 */
package gymuppgift;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 *
 * @author stevi
 */
public class UserFinderTest {
    
    public UserFinderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hasName method, of class UserFinder.
     * @throws java.io.IOException
     */
    @Test
    public void testHasNameFail() throws IOException {
        System.out.println("hasName");
        String name = "orvar korvar";
        UserFinder instance = new UserFinder();
        String expResult = null;
        String result = instance.hasName(name);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testHasNameGiveName() throws IOException {
        System.out.println("hasName");
        String name = "Ida Idylle";
        UserFinder instance = new UserFinder();
        String expResult = "Ida Idylle,2222222222,2016-03-07";
        String result = instance.hasName(name);
        assertEquals(expResult, result);
        
    }
    @Test
    public void testHasNameGiveSsc() throws IOException {
        System.out.println("hasName");
        String name = "2222222222";
        UserFinder instance = new UserFinder();
        String expResult = "Ida Idylle,2222222222,2016-03-07";
        String result = instance.hasName(name);
        assertEquals(expResult, result);        
    }

    @Test
    public void testValidPaymentfail1() throws Exception {
        System.out.println("validPayment");
        String date = "Ida Idylle,2222222222,2016-03-07";
        UserFinder instance = new UserFinder();
        boolean expResult = false;
        boolean result = instance.validatePayment(date);
        assertEquals(expResult, result);        
        
    }
    @Test
    public void testValidPayment() throws Exception {
        LocalDate now = LocalDate.now();
        
        System.out.println("validPayment");
        String date = "Liu Lingren,9110261234,"+now.toString().trim();
//      String date = "Liu Lingren,9110261234,2018-02-15";
        UserFinder instance = new UserFinder();
        boolean expResult = true;
        boolean result = instance.validatePayment(date);
        assertEquals(expResult, result);        
    }
    

    @Test
    public void testLogWriter() throws Exception {
        System.out.println("logWriter");
        Path log = Paths.get("src/gymuppgift/test.txt");
        LocalDate n = LocalDate.now();
        String name = "Stevie";
        String sSc = "Oliphant";
        UserFinder instance = new UserFinder();
        instance.logWriter(log, name,sSc);
        Scanner scan = new Scanner(log);
        String result = scan.nextLine().trim();
        String expResult= name+","+sSc+ " tranade den: "+ n;
        Files.delete(log);
        
        assertEquals(expResult, result);        
    }  
    
}
