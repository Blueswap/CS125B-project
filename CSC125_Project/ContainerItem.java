
import java.util.ArrayList;
public class ContainerItem extends Item
{
    private ArrayList<Item> items;
    public ContainerItem(String pName, String pType, String pDescription){
        super(pName, pType, pDescription);
        items = new ArrayList<Item>();
    }
    
    public void addItem(Item pItem){
        items.add(pItem);
    }

    public boolean hasItem(String pName){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getName().toLowerCase().equals(pName)){
                return true;
            }
        }
        return false;
    }

    public Item removeItem(String pName){
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

    @Override
    public String toString() {
        StringBuilder containerDetails = new StringBuilder();
        containerDetails.append(getName());
        containerDetails.append(" [ ");
        containerDetails.append(getType());
        containerDetails.append(" ] : ");
        containerDetails.append(getDescription());
        if(items == null){
            containerDetails.append("\n");
            containerDetails.append("Your inventory is empty");
        }
        else for(int i = 0; i <items.size(); i++)
        {
            containerDetails.append("\n");
            containerDetails.append("+ ");
            containerDetails.append(items.get(i).getName());
        }
        return containerDetails.toString();
    }
}
