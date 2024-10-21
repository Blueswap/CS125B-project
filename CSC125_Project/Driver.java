import java.util.Scanner;
public class Driver 
{
    // A static variable to track the player's current location in the game
    public static Location currLocation;
    
    public static void main(String [] args)
    {
        // Assign the currLocation variable to “point” at a new Location object
        currLocation = new Location("Kitchen", "A dark kitchen whose lights are flickering currently has the following items:");
        
        // Create items
        Item knife = new Item("Knife", "Tool", "A sharp kitchen knife");
        Item turkey = new Item("Turkey", "Food", "A roasted turkey");
        Item plate = new Item("Plate", "Dishware", "A dirty white plate");

        // Add the created items to the current location
        currLocation.addItem(plate);
        currLocation.addItem(knife);
        currLocation.addItem(turkey);

        // Create a Scanner object to read its data from the standard input stream
        Scanner read = new Scanner(System.in);

        String command;
        
        // An infinite loop to ask the user for commands
        while(true)
        {
            // Request the user to enter a command
            System.out.print("Enter command:");
            command = read.nextLine();
            command = command.toLowerCase();
            String[] cmdLine = command.split(" ");
            
            // Switch statement to handle different commands
            switch(cmdLine[0])
            {
                // If the user types quit, the “infinite” loop ends and the program exits
                case "quit":
                {
                    read.close();
                    System.exit(0);
                }
                
            
                // If the user types look, it displays the current location's description and items
                case "look":
                {
                    System.out.println(currLocation.getName() + " - " + currLocation.getDescription());

                    for (int i = 0; i < currLocation.numItems(); i++)
                    {
                        System.out.println("+ " + currLocation.getItem(i).getName());
                    }
                    break;
                }
            
                // If the user types examine, it examines a specific item
                case "examine":
                {
                    if (cmdLine.length > 1) // Check if the user specified an item to examine
                    {
                        // Check if the specified item exists in the current location
                        if (currLocation.getItem(cmdLine[1]) == null) // item not found
                        {
                            System.out.println("Cannot find that item");
                        }
                        else // item found
                        {
                            // print the item's detail
                            System.out.println(currLocation.getItem(cmdLine[1].toString()));
                        } 
                        
                    }
                    else // no specific item was provided
                    {
                        // Ask the user to enter an object name if none was provided
                        System.out.println("Please enter an object name to examine");
                    }
                    break;
                }
            
                // Default case to handle unknown commands
                default:
                {
                    System.out.println("I don't know how to do that");
                    break;
                }
            }
        }
    }       
}