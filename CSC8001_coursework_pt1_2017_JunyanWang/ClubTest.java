
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ClubTest.
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class ClubTest
{
    /**
     * fields for objects of class ClubTest
     */
    private Club testClub;
    
    /**
     * Default constructor for test class ClubTest
     */
    public ClubTest()
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
        testClub = new Club();
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
     * Test storeClimber method.
     */
    @Test
    public void testStoreClimber()
    {
        testClub.storeClimber("Junyan",26,"male");
        assertEquals("Junyan", testClub.getClimbers().get(testClub.getClimbers().size()-1).getClimberName());
    }
    
    /**
     * Test getClimbers method.
     */
    @Test
    public void testGetClimbers()
    {
        // test no climber case
        assertEquals(0,testClub.getClimbers().size());
        
        // test normal case
        testClub.storeClimber("Junyan",26,"male");
        assertEquals("Junyan", testClub.getClimbers().get(testClub.getClimbers().size()-1).getClimberName());
    }
    
    /**
     * Test getHighestAverageClimber method.
     */
    @Test
    public void testHighestAverageClimber()
    {
        // test no climber case
        assertNull(testClub.getHighestAverageClimber());
        
        // test no mountain case
        testClub.storeClimber("Junyan",26,"male");
        testClub.storeClimber("Jake",24,"male");
        assertNull(testClub.getHighestAverageClimber());

        // test normal case
        testClub.getClimbers().get(0).storeMountain("usba",1000);
        testClub.getClimbers().get(0).storeMountain("usbb",2000);
        testClub.getClimbers().get(1).storeMountain("usbc",1000);
        assertEquals("Junyan", testClub.getHighestAverageClimber().get(0).getClimberName());
        // test same average case
        testClub.getClimbers().get(1).storeMountain("usbd",2000);
        assertEquals("Junyan", testClub.getHighestAverageClimber().get(0).getClimberName());
        assertEquals("Jake", testClub.getHighestAverageClimber().get(1).getClimberName());
    }
    
    /**
     * Test getHighestMountain method.
     */
    @Test
    public void testHighestMountain()
    {
        // test no climber case
        assertNull(testClub.getHighestMountain());
        
        // test no mountain case
        testClub.storeClimber("Junyan",26,"male");
        testClub.storeClimber("Jake",24,"male");
        assertNull(testClub.getHighestMountain());
        
        // test normal case
        testClub.getClimbers().get(0).storeMountain("usba",1000);
        testClub.getClimbers().get(0).storeMountain("usbb",2000);
        testClub.getClimbers().get(1).storeMountain("usbc",1000);
        assertEquals("usbb", testClub.getHighestMountain().get("Junyan").getName());
        // test same height case
        testClub.getClimbers().get(1).storeMountain("usbb",2000);
        assertEquals("usbb", testClub.getHighestMountain().get("Jake").getName());
    }
    
    /**
     * Test getGreaterList method.
     */
    @Test
    public void testGreaterList()
    {
        // test no climber case
        assertNull(testClub.getGreaterList(100));
        
        // test no mountain case
        testClub.storeClimber("Junyan",26,"male");
        testClub.storeClimber("Jake",24,"male");
        assertNull(testClub.getGreaterList(100));
        
        // test normal case
        testClub.getClimbers().get(0).storeMountain("usba",1000);
        testClub.getClimbers().get(0).storeMountain("usbb",2000);
        testClub.getClimbers().get(1).storeMountain("usbc",1000);
        assertEquals("usbb", testClub.getGreaterList(1000).get(0).getName());
    }
}
