/**
 * The ContainerItem class represents a specialized item that can hold other items.
 * It inherits from the Item class
 */

import java.util.ArrayList;

public class ContainerItem extends Item
{
    // Member variables
    /**
     * items: the list of items contained within this container.
     */
    private ArrayList<Item> items;

    // Constructor
    /**
     * Constructs a new Item object from name, type, and description inherits from Item class
     * @param pName the name of the item inherits from Item class
     * @param pType the type of the item inherits from Item class
     * @param pDescription the description of the item inherits from Item class
     * Initializes an empty ArrayList to store items within the container.
     */
    public ContainerItem(String pName, String pType, String pDescription)
    {
        super(pName, pType, pDescription);
        items = new ArrayList<Item>();
    }
    
    //Methods
    /**
     * This method adds an item to the container
     * @param pItem The item to be added to the container
     */
    public void addItem(Item pItem)
    {
        items.add(pItem);
    }

    /**
     * This method checks if an item with the specified name exists in the container
     * @param pName The name of the item to check for
     * @return true if the item exists in the container, false otherwise
     */
    public boolean hasItem(String pName)
    {
        for(int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getName().toLowerCase().equals(pName)){
                return true;
            }
        }
        return false;
    }

    
    /**
     * This method removes and returns an item with the specified name from the container
     * @param pName The name of the item to remove
     * @return The removed item if found, null if no item with the specified name exists
     */
    public Item removeItem(String pName)
    {
        Item removedItem;
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getName().toLowerCase().equals(pName)){
                removedItem = items.get(i);
                items.remove(i);
                return removedItem;
            }
        }
        return null;
    }

    /**
     * This method retrieves and returns an item with the specified name from the container.
     * 
     * @param itemName The name of the item to retrieve (case-insensitive).
     * @return The item if found, null if no item with the specified name exists.
     */
    public Item getItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(itemName)) {
                return items.get(i);
            }
        }
        return null; // Return null if the item is not found
    }

    /**
     * This method returns a string representation of the container item, including its name, type,
     * description, and details of any contained items. 
     * If no items are contained, it says that "Your inventory is empty."
     * @return A string representation of the container item and its contents.
     */
    @Override
    public String toString() 
    {
        StringBuilder containerDetails = new StringBuilder();
        containerDetails.append(getName());
        containerDetails.append(" [ ");
        containerDetails.append(getType());
        containerDetails.append(" ] : ");
        containerDetails.append(getDescription());

        if(items.size() == 0)
        {
            containerDetails.append("\n");
            containerDetails.append("It is empty");
        }
        else 
        {
            containerDetails.append(" that contains ");
            for(int i = 0; i < items.size(); i++)
            {
                containerDetails.append("\n + ");
                containerDetails.append(items.get(i).getName());
            }
        }
        
        return containerDetails.toString();
    }
}
