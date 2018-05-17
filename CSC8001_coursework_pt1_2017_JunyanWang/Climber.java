
import java.util.*;

/**
 * The Climber class store its name, age, gender and climbed mountain details.
 * <p>This class has methods about:</p>
 * <ul>
 * <li>get and set name, age and gender.</li>
 * <li>store a new climbed mountain.</li>
 * <li>return the climbed mountain list, the highest mountain and the mountain list higher than a given level.</li>
 * </ul>
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class Climber
{
    /**
     * fields for objects of class Climber
     */
    private String name;
    private int age;
    private String gender;
    private ArrayList<Mountain> mountainArray;

    /**
     * Constructor for objects of class Climber
     * 
     * @param  name  the name of the climber
     * @param  age  the age of the climber
     * @param  gender  the gender of the climber
     */
    public Climber(String name, int age, String gender)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        mountainArray = new ArrayList<Mountain>();
    }

    /**
     * get climber name
     *  
     * @return  the name of the climber  
     */
    public String getClimberName()
    {
        return name;
    }
    
    /**
     * get climber age
     *  
     * @return  the age of the climber  
     */
    public int getClimberAge()
    {
        return age;
    }
    
    /**
     * get climber gender
     *  
     * @return  the gender of the climber  
     */
    public String getClimberGender()
    {
        return gender;
    }
    
    /**
     * set climber name
     *  
     * @param  newName  new name 
     */
    public void setClimberName(String newName)
    {
         name = newName;
    }
    
    /**
     * set climber age
     *  
     * @param  newAge  new age 
     */
    public void setClimberAge(int newAge)
    {
         age = newAge;
    }
    
    /**
     * set climber gender
     *  
     * @param  newGender  new gender 
     */
    public void setClimberGender(String newGender)
    {
         gender = newGender;
    }
    
    /**
     * get mountain list
     *  
     * @return  the climber climbed mountains  
     */
    public ArrayList<Mountain> getMountainList()
    {
        return mountainArray;   
    }
    
    /**
     * store mountain per climber
     *
     * @param  name  the name of the mountain climbed by the climber  
     * @param  height  the height of the mountain climbed by the climber
     */
    public void storeMountain(String name, int height)
    {
        Mountain newMountain = new Mountain(name, height);
        
        if (mountainArray.contains(newMountain))
        {
            System.out.println("Mountain " + name + " already exists");
        }
        else
        {
            mountainArray.add(newMountain);
        }
    }

    /**
     * get the highest mountain. 
     *  
     * @return  the highest mountain. If no data, it will return null.  
     */
    public Mountain getHighestMountain()
    {
        Mountain highestMountain;
        
        if(mountainArray.isEmpty())
        {
            return null;
        }
        else
        {
            //initial mountain
            highestMountain = mountainArray.get(0);
            
            for(int i = 0; i < mountainArray.size(); i++)
            {
                if(mountainArray.get(i).getHeight() > highestMountain.getHeight())
                {
                    highestMountain = mountainArray.get(i);
                }
            }
            
            return highestMountain;
        }
    }
    
    /**
     * get the average mountain height. 
     * 
     * @return  the average height. If no data, it will return 0.  
     */
    public int getAverageHeight()
    {
        int totalHeight;
        int averageHeight;
        
        if(mountainArray.isEmpty())
        {
            return 0;
        }
        else
        {
            //initial total height
            totalHeight = 0;
            
            for(int i = 0; i < mountainArray.size(); i++)
            {
                totalHeight = totalHeight + mountainArray.get(i).getHeight();
            }
            
            averageHeight = totalHeight / mountainArray.size();
            return averageHeight;
        }
    }
    
    /**
     * get A list of all mountains recorded by the climber with a height greater than a given level.
     *
     * @param  heightLevel  the compared height level
     * @return  A list of mountains. If no data, it will return null. 
     */
    public ArrayList<Mountain> getGreaterList(int heightLevel)
    {
        ArrayList<Mountain> mountainList = new ArrayList<Mountain>();
        
        if(mountainArray.isEmpty())
        {
            return null;
        }
        else
        {
            for(int i = 0; i < mountainArray.size(); i++)
            {
                if(mountainArray.get(i).getHeight() > heightLevel)
                {
                    mountainList.add(mountainArray.get(i));
                }
            }
            
            if(mountainList.isEmpty())
            {
                return null;
            }
            else
            {
                return mountainList;
            }
        }
    }
}
