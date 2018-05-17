import java.util.*;
/**
 * The ClubStats class has main method.
 * <p>It will show a menu list:<p>
 * <ul>
 * <li>Add a new climber to the club, given their name, age and gender;</li>
 * <li>Add details of a mountain (name, height) to the record for a given climber;</li>
 * <li>Provide statistics;</li>
 * <li>Exit;</li>
 * </ul>
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class ClubStats
{   
    /**
     * fields for objects of class ClubStats
     */
    private Scanner scanner;
    private Club myClub;
    
    /**
     * Constructor for objects of class ClubStats
     */
    public ClubStats()
    {
        scanner = new Scanner(System.in);
        myClub = new Club();
    }
    
    /**
     * main method. 
     * <p>The menu has four options, each option has its own method.</p>
     *
     * @param  
     * @return    
     */
    public static void main(String args[])
    {
        ClubStats newClubStats = new ClubStats();
        Boolean finished = false;
        
        while (!finished)
        {
            int mainChoose = 0;
            
            System.out.println("-------------------");
            System.out.println("Welcome to the club menu. Please input the option number");
            System.out.println("(1). Add a new climber to the club, given their name, age and gender;");
            System.out.println("(2). Add details of a mountain (name, height) to the record for a given climber;");
            System.out.println("(3). Provide statistics;");
            System.out.println("(4). Exit;");
            System.out.println("-------------------");
            
            mainChoose = newClubStats.checkInt(1,4);
            switch (mainChoose)
            {
                case 1: newClubStats.optionOne();
                        break;
                case 2: newClubStats.optionTwo(-1);
                        break;
                case 3: newClubStats.optionThree();
                        break;
                case 4: finished = true;
                        System.out.println("See you!");
                        break;
            }
        }
    }
    
    /**
     * Option one. 
     * <p>Add a new climber to the club, given their name, age and gender;</p>    
     */
    public void optionOne()
    {
        String cName;
        int cAge;
        String cGender;
        int cIndex;
        int firstChoose;
        
        System.out.println("give the climber name");
        cName = checkName();
        System.out.println("give the climber age");
        cAge = checkInt(0,150);
        System.out.println("give the climer gender (male or female)");
        cGender = checkGender();
        
        // add mountain details of add a new climber
        myClub.storeClimber(cName,cAge,cGender);
        System.out.println("-------------------");
        System.out.println("add " + cName + " complete");
        System.out.println("(1). Add " + cName + " climbed mountain detail");
        System.out.println("(2). Continue add a new climber to the club, given their name, age and gender;");
        System.out.println("(3). Back to the main menu");
        System.out.println("-------------------");
        
        firstChoose = checkInt(1,3);
        switch (firstChoose)
        {
            case 1: cIndex = myClub.getClimbers().size() - 1;
                    optionTwo(cIndex);
                    break;
            case 2: optionOne();
                    break;
            case 3: 
                    break;
        }
    }
    
    /**
     * Option two. 
     * <p>Add details of a mountain (name, height) to the record for a given climber;</p>
     *
     * @param  index  the climber list index   
     */
    public void optionTwo(int index)
    {
        ArrayList<Climber> myClubClimbers = myClub.getClimbers();
        String mName;
        int mHeight;
        int listChoose;
        int secondChoose;
        
        if (myClubClimbers.size() == 0)
        {
            System.out.println("You need add climbers first");
        }
        else
        {
            if (index < 0)
            {
                System.out.println("-------------------");
                System.out.println("Climber List");
                for (int i = 0; i < myClubClimbers.size(); i ++){
                    System.out.println("(" + i + "). " + myClubClimbers.get(i).getClimberName());
                }
                System.out.println("Choose one climber, give the index of climer");
                System.out.println("-------------------");
                
                listChoose = checkInt(0,myClubClimbers.size()-1);
                optionTwo(listChoose);
            }
            else if(index > (myClubClimbers.size()-1))
            {
                System.out.println("no No." + index + "climber");
                optionTwo(-1);
            }
            else
            {
                System.out.println("give the mountain name");
                mName = checkName();
                System.out.println("give the mountain height");
                mHeight = checkInt();
                
                myClubClimbers.get(index).storeMountain(mName,mHeight);
                System.out.println("-------------------");
                System.out.println("add mountain " + mName + " complete");
                System.out.println("(1). Continue add another mountain detail");
                System.out.println("(2). Back to the main menu");
                System.out.println("-------------------");
                
                secondChoose = checkInt(1,2);
                switch (secondChoose)
                {
                    case 1: optionTwo(index);
                            break;
                    case 2: 
                            break;
                }
            }
        }
    }
    
    /**
     * Option three. 
     * <p>Provide statistics;</p>    
     * <ul>
     * <li>The climber who has recorded the highest average mountain height;</li>
     * <li>The highest mountain recorded by a club member;</li>
     * <li>All mountains recorded with a height greater than a given level;</li>
     * <li>Back to the main menu;</li>
     * </ul>
     */
    public void optionThree()
    {
        ArrayList<Climber> firstClimbers;
        HashMap<String,Mountain> highestMountain;
        int mountainLevel;
        ArrayList<Mountain> mountainList;
        Boolean threeFinished = false;
        
        while (!threeFinished){
            int thirdChoose;
            
            System.out.println("-------------------");
            System.out.println("Please input the option number");
            System.out.println("(1). The climber who has recorded the highest average mountain height;");
            System.out.println("(2). The highest mountain recorded by a club member;");
            System.out.println("(3). All mountains recorded with a height greater than a given level;");
            System.out.println("(4). Back to the main menu;");
            System.out.println("-------------------");
    
            thirdChoose = checkInt(1,4);
            switch (thirdChoose)
            {
                case 1: firstClimbers = myClub.getHighestAverageClimber();
                        if (firstClimbers == null)
                        {
                            System.out.println("You need add climbers and mountains first");
                        }
                        else
                        {
                            System.out.println("-------------------");
                            for (int i = 0; i < firstClimbers.size(); i++)
                            {
                                System.out.println(firstClimbers.get(i).getClimberName());
                            }
                        }
                        break;  
                case 2: highestMountain = myClub.getHighestMountain();
                        if(highestMountain == null)
                        {
                            System.out.println("no mountains");
                        }
                        else
                        {
                            System.out.println("The highest mountain is: ");
                            for(Map.Entry<String, Mountain> entry : highestMountain.entrySet()) {
                                System.out.println("The mountain " + entry.getValue().getName() + " climbed by " + entry.getKey());       
                            }
                        }
                        break;
                case 3: System.out.println("give the mountain level");
                        mountainLevel = checkInt();
                        mountainList = myClub.getGreaterList(mountainLevel);
                        if(mountainList == null)
                        {
                            System.out.println("no mountains");
                        }
                        else
                        {
                            System.out.println("-------------------");
                            for (int i = 0; i < mountainList.size(); i++)
                            {
                                System.out.println(mountainList.get(i).getName());
                            }
                        }
                        break;
                case 4: threeFinished = true;
                        break;
            }
        }
    }
    
    /**
     * check name type
     *
     * @return  name
     */
    public String checkName()
    {
        String name;
        
        do
        {
            name = scanner.nextLine();
            
            if (name.matches("[a-zA-z]+([a-zA-z]|\\s)*")){
                break;
            }else{
                System.out.println("please input correct name type");
            }
        }
        while(true);
        
        return name; 
    }
    
    /**
     * check gender type
     *
     * @return  gender
     */
    public String checkGender()
    {
        String gender;
        
        do
        {
            gender = scanner.nextLine();
            
            if (gender.equals("male") || gender.equals("female")){
                break;
            }else{
                System.out.println("please input correct gender type: male or female");
            }
        }
        while(true);
        
        return gender; 
    }
    
    /**
     * check int type
     *
     * @return int number
     */
    public int checkInt()
    {
        int result;
        
        do
        {
            try{
               String s = scanner.nextLine();
               result = Integer.parseInt(s);
               
               if (result > 0){
                   break;
               }else{
                   System.out.println("please input number more than zero");
               }
            }
            catch (Exception e)
            {
               System.out.println("please input int number more than zero");
            }
        }
        while(true);
        
        return result; 
    }
    
    /**
     * check int type and given range
     *
     * @param  min  the min range
     * @param  max  the max range
     * @return  choose option
     */
    public int checkInt(int min, int max)
    {
        int choose;
        
        do
        {
            try{
               String s = scanner.nextLine();
               choose = Integer.parseInt(s);
               
               if (choose >= min && choose <= max){
                   break;
               }else{
                   System.out.println("please input from " + min + " to " + max);
               }
            }
            catch (Exception e)
            {
               System.out.println("please input int number from " + min + " to " + max);
            }
        }
        while(true);
        
        return choose;
    }
}
