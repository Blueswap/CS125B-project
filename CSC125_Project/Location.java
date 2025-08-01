/**
 * The location class is used to encapsulate information about a single location in the game
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Location 
{
    // Member variables
    /**
     * name: a String represents the location’s name 
     * description: a String represents the location’s description
     * items: an ArrayList that stores Item objects in the location
     * connections: a HashMap that stores directions and connected locations
     */
    private String name; 
    private String description;
    private ArrayList<Item> items;
    private HashMap <String, Location> connections;

    // Constructor
    /**
     * Constructs a new Location object from name and description
     * Initializes an empty list of items for the location
     * @param pName the name of the location
     * @param pDescription the description of the location
     */
    public Location(String pName, String pDescription) 
    {
        name = pName;
        description = pDescription;
        items = new ArrayList<Item>();
        connections = new HashMap<String, Location>();
    }

    // Methods

    /**
    * This method returns the name of the location
    * @return the name of the location in String
    */
    public String getName() 
    {
        return name;
    }

    /**
    * This method returns the description of the location
    * @return the description of the location
    */
    public String getDescription() 
    {
        return description;
    }

    /**
     * This method sets the name of the location
     * @param pName the new name of the location
     */
    public void setName(String pName) 
    {
        name = pName;
    }

    /**
     * This method sets the description of the location
     * @param pDescription the new description of the location
     */
    public void setDescription(String pDescription) 
    {
        description = pDescription;
    }

    /**
     * This methods adds an item to the location
     * @param item the item to add
     */
    public void addItem(Item item) 
    {
        items.add(item);
    }

    /**
     * This method checks if the location contains an item with the specified name
     * @param itemName the name of the item to check for
     * @return true if the item is present, false otherwise
     */
    public boolean hasItem(String itemName)
    {
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getName().equalsIgnoreCase(itemName))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks to see if an Item with that name is in the ArrayList
     * @param itemName the name of the item to check
     * @return the item if found, null otherwise
     */
    public Item getItem(String itemName) 
    {
        for (int i = 0; i < items.size(); i++) 
        {
            if (items.get(i).getName().equalsIgnoreCase(itemName)) 
            {
                return items.get(i);
            }
        }
        return null;
    }

    /**
     * This method returns the item from the location's list of items at the specified index
     * @param index the index of the item in the list
     * @return the item if found, null otherwise
     */
    public Item getItem(int index) 
    {
        if (index >= 0 && index < items.size()) 
        {
            return items.get(index);
        }
        return null;
    }

    /**
     * This method returns the number of items in the location
     * @return the number of items at the location
     */
    public int numItems() 
    {
        return items.size();
    }

    /**
     * This method searches for an item with the specified name in the location's list of items
     * If the item is found, it is removed from the list, and the removed item is returned.
     * If no item is found, the method returns null.
     * @param itemName
     * @return the removed Item if found, null if no item with the specified name exist
     */
    public Item removeItem(String itemName) 
    {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (items.get(i).getName().equalsIgnoreCase(itemName)) 
            {
                items.remove(i); 
                return item;
            }
        }
        return null;
    }

    /**
     *  This method connects this location to another location in the specified direction.
     * @param direction The direction to connect (e.g., "north").
     * @param location The Location object to connect to.
     */
    public void connect(String direction, Location location)
    {
        connections.put(direction.toLowerCase(), location);
    }

    /**
     * This method checks if there is a location in the specified direction.
     * @param direction The direction to check (e.g., "north").
     * @return true if there is a connected location in that direction; false otherwise.
     */
    public boolean canMove(String direction)
    {
        return connections.containsKey(direction.toLowerCase());
    }

    /**
     * This method gets the location connected in the specified direction.
     * @param direction The direction to get the location for (e.g., "north").
     * @return The Location object connected in the specified direction, or null if none exists.
     */
    public Location getLocation(String direction) 
    {
        if (canMove(direction) == true){
            return connections.get(direction);
        } 
        else
        {
            return null;
        }
    }
}
