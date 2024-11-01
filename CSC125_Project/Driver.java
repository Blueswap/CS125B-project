/**
 * The Driver class is the main driver for the text-based adventure game
 * It manages user commands, player movement, item interactions, and displays game world details
 * 
 * @author Quyen Tran, Hung Nguyen, Nhi Do, Quynh Tran
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Driver 
{
    // A static variable to track the player's current location in the game
    public static Location currLocation;
    public static ContainerItem myInventory;

    public static void main(String [] args)
    {
        // Creating the game world 
        createWorld();
        myInventory = new ContainerItem("Backpack", "Container", "An old brown backpack behind your back");
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

                // If the user types go
                case "go":
                if (cmdLine.length == 1) // no direction specify
                {
                    System.out.println("Please specify a direction (north, south, east, or west) to go.");
                }
                else if (cmdLine.length > 1) {
                    String direction = cmdLine[1].toLowerCase();

                    // list of valid directions
                    ArrayList<String> validDirection = new ArrayList<String>();
                    validDirection.add("north");
                    validDirection.add("west");
                    validDirection.add("south");
                    validDirection.add("east");

                    if (!validDirection.contains(direction)) // handle direction that is north, south, east, west
                    {
                        System.out.println("Invalid direction. Please use north, south, east, or west.");
                    }
                    else
                    {
                        Location newLocation = currLocation.getLocation(direction);

                        if (newLocation == null) // no path
                        {
                            System.out.println("There is no path in that direction.");
                        }
                        else
                        {
                            currLocation = newLocation; // update current location
                            System.out.println("You move to: " + currLocation.getName());
                        }
                    }
                }
                break;
                
            
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
                        if (!currLocation.hasItem(cmdLine[1])) // item not found
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

                // If the user types inventory, it shows items in player's inventory
                case "inventory":
                {
                    System.out.println(myInventory.toString());
                    break;
                }

                // If the user types take, it adds an item from the current location to the player's inventory
                // It also remove that item from the current location
                case "take":
                {
                    if (cmdLine.length == 1)
                    {
                        System.out.println("Please specify the item you want to take.");
                    }
                    else
                    {
                        String item = cmdLine[1];
                        if(currLocation.hasItem(item))
                        {
                            Item itemName = currLocation.getItem(item);
                            myInventory.addItem(itemName);
                            currLocation.removeItem(item);
                            System.out.println("Now you have item: " + itemName.getName());
                        }
                        else
                        {
                            System.out.println("Cannot find that item here.");
                        }
                    }
                    break;
                }

                // If the user types drop, it removes an item from the player's inventory and leave it at the current location
                case "drop":
                {
                    if (cmdLine.length == 1)
                    {
                        System.out.println("Please specify the item you want to drop.");
                    }
                    else
                    {
                        String item = cmdLine[1];
                        if(myInventory.hasItem(item))
                        {
                            Item itemName = myInventory.removeItem(item); 
                            currLocation.addItem(itemName);
                            System.out.println("You have drop " + itemName.getName());
                        }
                        else System.out.println("Cannot find that item in your inventory.");
                    }
                    break;
                }

                // If the user types help, it displays available commands
                case "help":
                {
                    helper();
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
    
    
    /**
     * This method displays a list of available commands for the player.
     */
    public static void helper()
    {
        System.out.println("quit : close the game");
        System.out.println("go DIRECTION: move to another place" );
        System.out.println("look: displays the current location's description and items");
        System.out.println("examine NAME: examine a specific item");
        System.out.println("inventory: check what is in your bag");
        System.out.println("take NAME: put an item from current location into your inventory");
        System.out.println("drop NAME: leave an item from your inventory at the current location");
    }
    
    
    /**
     * Creates the game world by constructing and connecting locations.
     * Initializes the starting location for the player.
     */
    public static void createWorld() 
    {
        // Create locations
        Location kitchen = new Location("Kitchen", "A dark, cold kitchen with flickering lights. The smell of burnt food lingers.");
        Location hallway = new Location("Hallway", "A narrow, dim hallway. Faint whispers seem to bounce back from the shadows.");
        Location bedroom = new Location("Bedroom", "A messy room with bloody blankets. Someone might be watching you.");
        Location livingRoom = new Location("Living Room", "An empty room with a broken TV. The silence is deep and cold.");

        // Connect locations 
        kitchen.connect("north", hallway);
        hallway.connect("south", kitchen);
        hallway.connect("east", bedroom);
        bedroom.connect("west", hallway);
        bedroom.connect("south", livingRoom);
        livingRoom.connect("north", bedroom);
        kitchen.connect("east", livingRoom);
        livingRoom.connect("west", kitchen);

        // Add items in the kitchen
        Item knife = new Item("Knife", "Tool", "A kitchen knife with dry blood on its blade.");
        Item meat = new Item("Meat", "Food", "A piece of burnt meat with a bad smell.");
        Item plate = new Item("Plate", "Dishware", "A broken plate with dark stains.");
        Item pot = new Item("Pot", "Cookware", "An old pot with black stuff stuck inside.");
        Item spoon = new Item("Spoon", "Utensil", "A spoon, bent and scratched like someone crushed it.");

        kitchen.addItem(plate);
        kitchen.addItem(knife);
        kitchen.addItem(meat);
        kitchen.addItem(pot);
        kitchen.addItem(spoon);

        // Add items in the hallway
        Item key = new Item("Key", "Tool", "An old, cold, rusty key with some dried blood spots.");
        Item umbrella = new Item("Umbrella", "Accessory", "An umbrella with rips and faint blood spots.");
        Item coat = new Item("Coat", "Clothing", "A thick coat covered in dust.");

        hallway.addItem(key);
        hallway.addItem(umbrella);
        hallway.addItem(coat);

        // Add items in the bedroom
        Item pillow = new Item("Pillow", "Bedding", "A pillow with faint red marks.");
        Item diary = new Item("Diary", "Reading Material", "A diary with some ripped and missing pages. The writing is messy.");
        Item lamp = new Item("Lamp", "Furniture", "A lamp that flickers. The light from the lamp is weak and uneven.");

        bedroom.addItem(pillow);
        bedroom.addItem(diary);
        bedroom.addItem(lamp);

        // Add items in the living room
        Item remote = new Item("Remote", "Electronics", "A remote for the TV, covered in dust.");
        Item magazine = new Item("Magazine", "Reading Material", "An old magazine with ripped pages.");
        Item cushion = new Item("Cushion", "Furniture", "A red cushion with strange marks on it.");

        livingRoom.addItem(remote);
        livingRoom.addItem(magazine);
        livingRoom.addItem(cushion);

        // Set the player's starting location to the kitchen
        currLocation = kitchen;
    }


}
