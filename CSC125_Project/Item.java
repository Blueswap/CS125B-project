public class Item 
{
    // Member variables
    /**
     * name: a String represents the item’s name 
     * type: a String represents the item’s type
     * description: a String represents the item’s description
     */
    private String name;
    private String type;
    private String description;

    // Constructor
    /**
     * Constructs a new Item object from name, type, and description
     * @param pName the name of the item
     * @param pType the type of the item
     * @param pDescription the description of the item
     */
    public Item(String pName, String pType, String pDescription)
    {
        name = pName;
        type = pType;
        description = pDescription;
    }

    // Methods
    /**
     * This method returns the name of the item
     * @return the name of the item
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method returns the type of the item
     * @return the type of the item
     */
    public String getType()
    {
        return type;
    }

    /**
     * This method returns the description of the item
     * @return the description of the item
     */
    public String getDescription()
    {
        return description;
    }

     /**
     * This method sets the name of the item
     * @param pName the new name of the item
     */
    public void setName(String pName)
    {
        name = pName;
    }

    /**
     * This method sets the type of the item
     * @param pType the new type of the item
     */
    public void setType(String pType)
    {
        type = pType;
    }

    /**
     * This method sets the description of the item
     * @param pDescription the new description of the item
     */
    public void setDescripton(String pDescription)
    {
        description = pDescription;
    }

    /**
     * This method returns a string representation of the item, which includes the item's name, type, and description
     * The format of the returned string is: "name [ type ] : description"
     * @return a string that represents the item's name, type, and description
     */
    public String toString() {
        StringBuilder itemDetails = new StringBuilder();
        itemDetails.append(name);
        itemDetails.append(" [ ");
        itemDetails.append(type);
        itemDetails.append(" ] : ");
        itemDetails.append(description);
        return itemDetails.toString();
    }
}