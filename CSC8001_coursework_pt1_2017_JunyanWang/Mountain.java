
/**
 * The Mountain class store the mountain's name and height.
 * <p>It has methods to get and set name and height.</p>
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class Mountain
{
    /**
     * fields for objects of class Mountain
     */
    private String name;
    private int height;

    /**
     * Constructor for objects of class Mountain
     * 
     * @param  name  the name of the mountain  
     * @param  height  the height of the mountain
     */
    public Mountain(String name, int height)
    {
        this.name = name;
        this.height = height;
    }
    
    /**
     * get the mountain's height
     *
     * @return  the height of the mountain
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * get the mountain's name
     *
     * @return  the name of the mountain 
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * set the mountain's name
     *
     * @param  name  the new name of the mountain 
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * set the mountain's height
     *
     * @param  name  the new height of the mountain 
     */
    public void setHeight(int height)
    {
        this.height = height;
    }
}
