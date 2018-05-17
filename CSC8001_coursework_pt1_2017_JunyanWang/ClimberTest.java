
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class ClimberTest.
 *
 * @author  Junyan Wang
 * @version 1.0
 */
public class ClimberTest
{
    /**
     * fields for objects of class ClimberTest
     */
    private Climber testClimber;
    
    /**
     * Default constructor for test class ClimberTest
     */
    public ClimberTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        testClimber = new Climber("Junyan",26,"male");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Test getClimberName method.
     */
    @Test
    public void testGetClimberName()
    {
        assertEquals("Junyan", testClimber.getClimberName());
    }
    
    /**
     * Test getClimberAge method.
     */
    @Test
    public void testGetClimberAge()
    {
        assertEquals(26, testClimber.getClimberAge());
    }
    
    /**
     * Test getClimberGender method.
     */
    @Test
    public void testGetClimberGender()
    {
        assertEquals("male", testClimber.getClimberGender());
    }
    
    /**
     * Test setClimberName method.
     */
    @Test
    public void testSetClimberName()
    {
        testClimber.setClimberName("Jake");
        assertEquals("Jake", testClimber.getClimberName());
    }
    
    /**
     * Test setClimberAge method.
     */
    @Test
    public void testSetClimberAge()
    {
        testClimber.setClimberAge(24);
        assertEquals(24, testClimber.getClimberAge());
    }
    
    /**
     * Test setClimberGender method.
     */
    @Test
    public void testSetClimberGender()
    {
        testClimber.setClimberGender("female");
        assertEquals("female", testClimber.getClimberGender());
    }
    
    /**
     * Test getMountainList method.
     */
    @Test
    public void testMountainList()
    {
        // test no mountain case
        assertEquals(0,testClimber.getMountainList().size());
        
        // test normal case
        testClimber.storeMountain("usb1",1000);
        testClimber.storeMountain("usb2",2000);
        assertEquals("usb1", testClimber.getMountainList().get(0).getName());
        assertEquals("usb2", testClimber.getMountainList().get(1).getName());
    }

    /**
     * Test storeMountain method.
     */
    @Test
    public void testStoreMountain()
    {   
        testClimber.storeMountain("usb1",1000);
        assertEquals("usb1", testClimber.getMountainList().get(testClimber.getMountainList().size()-1).getName());
    }

    /**
     * Test getHighestMountain method.
     */
    @Test
    public void testHighestMountain()
    {   
        // test no mountain case
        assertNull(testClimber.getHighestMountain());
        
        // test normal case
        testClimber.storeMountain("usb1",1000);
        testClimber.storeMountain("usb2",2000);
        assertEquals("usb2", testClimber.getHighestMountain().getName());
    }
    
    /**
     * Test getAverageHeight method.
     */
    @Test
    public void testAverageHeight()
    {
        // test no mountain case
        assertEquals(0, testClimber.getAverageHeight());
        
        // test normal case
        testClimber.storeMountain("usb1",1000);
        testClimber.storeMountain("usb2",2000);
        assertEquals(1500, testClimber.getAverageHeight());
    }
    
    /**
     * Test getGreaterList method.
     */
    @Test
    public void testGreaterList()
    {
        // test no mountain case
        assertNull(testClimber.getGreaterList(1000));
        
        // test normal case
        testClimber.storeMountain("usb1",1000);
        testClimber.storeMountain("usb2",1500);
        testClimber.storeMountain("usb3",2000);
        // test no greater mountain case
        assertNull(testClimber.getGreaterList(2000));
        // test exist greater mountain case
        ArrayList<Mountain> mountainList = testClimber.getGreaterList(1000);
        assertEquals("usb2", mountainList.get(0).getName());
        assertEquals("usb3", mountainList.get(1).getName());
    }
}
