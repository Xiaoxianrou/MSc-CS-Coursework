
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MountainTest.
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class MountainTest
{
    /**
     * fields for objects of class MountainTest
     */
    private Mountain testMountain;
    
    /**
     * Default constructor for test class MountainTest
     */
    public MountainTest()
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
        testMountain = new Mountain("Usb",100);
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
     * Test getName method.
     */
    @Test
    public void testGetName()
    {   
        assertEquals("Usb", testMountain.getName());
    }
    
    /**
     * Test getHeight method.
     */
    @Test
    public void testGetHeight()
    {   
        assertEquals(100, testMountain.getHeight());
    }
    
    /**
     * Test setName method.
     */
    @Test
    public void testSetName()
    {   
        testMountain.setName("newUsb");
        assertEquals("newUsb", testMountain.getName());
    }
    
    /**
     * Test setHeight method.
     */
    @Test
    public void testSetHeight()
    {   
        testMountain.setHeight(200);
        assertEquals(200, testMountain.getHeight());
    }
}
