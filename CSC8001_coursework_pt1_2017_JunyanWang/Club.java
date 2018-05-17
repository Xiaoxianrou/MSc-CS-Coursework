
import java.util.*;

/**
 * The Club class store details of each climber.
 * <p>This class has methods about:</p>
 * <ul>
 * <li>store a new climber and get climbers list</li>
 * <li>Get the climber who has recorded the highest average mountain height.</li>
 * <li>Get the highest mountain climbed by a member of the club.</li>
 * <li>Get a list of all mountains that have been recorded, that are higher than a given level.</li>
 * </ul>
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class Club
{
    /**
     * fields for objects of class Club
     */
    private ArrayList<Climber> climberArray;

    /**
     * Constructor for objects of class 
     */
    public Club()
    {
        climberArray = new ArrayList<Climber>();
    }
    
    /**
     * store climber
     *
     * @param  name  the name of a climber
     * @param  age  the age of a climber
     * @param  gender  the gender of a climber
     */
    public void storeClimber(String name, int age, String gender)
    {
        Climber newClimber = new Climber(name, age, gender);
        climberArray.add(newClimber);
    }
    
    /**
     * get all the club's climbers
     * 
     * @return  the club's climbers list 
     */
    public ArrayList<Climber> getClimbers()
    {
        return climberArray;
    }
    
    /**
     * The climber who has recorded the highest average mountain height.
     *  
     * @return  the climbers who climbed the highest average mountain height.
     *          If different climbers have same mountain average height, it will return a list of climber.
     */
    public ArrayList<Climber> getHighestAverageClimber()
    {
        ArrayList<Climber> resultClimbers = new ArrayList<Climber>();
        Climber highestAverageClimber;
        
        if(climberArray.isEmpty())
        {
            System.out.println("This club don't have climbers");
            return null;
        }
        else
        {
            highestAverageClimber = climberArray.get(0);
            
            for(int i = 0; i < climberArray.size(); i++)
            {
                if(climberArray.get(i).getAverageHeight() == highestAverageClimber.getAverageHeight())
                {
                    // if climbers have same average height
                    resultClimbers.add(climberArray.get(i));
                }
                else if(climberArray.get(i).getAverageHeight() > highestAverageClimber.getAverageHeight())
                {
                    resultClimbers.clear();
                    resultClimbers.add(climberArray.get(i));
                    highestAverageClimber = climberArray.get(i);
                }
            }
            
            if (highestAverageClimber.getAverageHeight() == 0)
            {
                return null;
            }
            else
            {
                return resultClimbers;
            }
        }
    }
    
    /**
     * The highest mountain climbed by a member of the club.
     *  
     * @return  the highest mountain. If different climbers have same highest mountain height, it will return all of them. 
     */
    public HashMap<String, Mountain> getHighestMountain()
    {
        HashMap<String,Mountain> highestMap = new HashMap<String,Mountain>();
        
        if(climberArray.isEmpty())
        {
            return null;
        }
        else
        {
            Mountain highestMountain = climberArray.get(0).getHighestMountain();
            highestMap.put(climberArray.get(0).getClimberName(),climberArray.get(0).getHighestMountain());
            
            for(int i = 0; i < climberArray.size(); i++)
            {
                if(climberArray.get(i).getHighestMountain() != null 
                    && climberArray.get(i).getHighestMountain().getHeight() == highestMountain.getHeight())
                {
                    // if climbers have same highest mountain height
                    highestMap.put(climberArray.get(i).getClimberName(),climberArray.get(i).getHighestMountain());
                }
                else if(climberArray.get(i).getHighestMountain() != null 
                    && climberArray.get(i).getHighestMountain().getHeight() > highestMountain.getHeight())
                {
                    highestMap.clear();
                    highestMountain = climberArray.get(i).getHighestMountain();
                    highestMap.put(climberArray.get(i).getClimberName(),climberArray.get(i).getHighestMountain());
                }
            }
            
            if(highestMountain == null)
            {
                return null;
            }
            else
            {
                return highestMap;
            }
        }
    }
    
    /**
     * A list of all mountains that have been recorded, that are higher than a given level.
     * This method has already removed the same mountain in the return list.
     *
     * @param  high level
     * @return  a mountain list which higher than the given high level
     */
    public ArrayList<Mountain> getGreaterList(int hightlevel)
    {
        ArrayList<Mountain> mountainList = new ArrayList<Mountain>();
        ArrayList<Mountain> resultList = new ArrayList<Mountain>();
        boolean sameFlag;
        
        if (climberArray.isEmpty())
        {
            return null;
        }
        else
        {
            for(Climber climber : climberArray)
            {
                if (climber.getGreaterList(hightlevel) != null)
                {
                    mountainList.addAll(climber.getGreaterList(hightlevel));
                }
            }
            
            if (mountainList.isEmpty())
            {
                return null;
            }
            else
            {
                //remove the same mountain
                for (int i = 0; i < mountainList.size(); i++)
                {
                    sameFlag = false;
                    for (int j = 0; j < resultList.size(); j++)
                    {
                        if (mountainList.get(i).getName().equals(resultList.get(j).getName()) 
                            && mountainList.get(i).getHeight() == resultList.get(j).getHeight())
                        {
                            sameFlag = true;
                            break;
                        }
                    }
                    if (!sameFlag)
                    {
                        resultList.add(mountainList.get(i));
                    }
                }
                return resultList;
            }
        }
    }
}
