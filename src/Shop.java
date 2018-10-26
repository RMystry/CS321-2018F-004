/**
 * Keeps track of items in the shop on the 
 */

//import java.util.Iterator;
import java.util.LinkedList;
public class Shop
{
	//Max of 10 items in this list
	private LinkedList<Object> inventory;
	
	//List of objects that want to be indemand for the shop
	private LinkedList<Object> inDemand;
	
	// List of players in this shop
	private PlayerList playerlist;

	private String description;

	private String title;
	
	
	public Shop()
	{
		this.inventory = new LinkedList<Object>();
		this.inDemand = new LinkedList<Object>();
		this.playerlist = new PlayerList();
		this.description = "The shopping destination for all of your gaming needs.";
		this.title = "The Shop";
	}
	
	//In terms of the player buying items
	public void buy(Object k) {}
	
	//In terms of the player selling items
	public void sell(Object k) {}
	
	//used to add methods to the linked list
	public void add(Object k) {}
	
	//prints the inventory of the shop class
	public void printInv() {}
	
	//Prints the list of object in demand
	public void printDem() {}
	
	//Menu for the player to be interacting with the shop 
	//use something like a switch statement
	public void printMenu() {}
	
	public void addPlayer(Player p) {
		playerlist.addPlayer(p);
	}	

	/**
	 * @return The tag line of the shop
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @return The shop name
	 */
	public String getTitle() {
        return this.title;
    }
	
	public String toString(PlayerList playerList, Player player) {
        
		// white spaces around the billboard
		String billboard = "Welcome to " + this.getTitle();
        // spaces before
        int i = 25 - billboard.length() / 2;
        for (; i > 0; i--) billboard = " " + billboard;
        // spaces after
        i = 25 - billboard.length();
        for (; i > 0; i--) billboard = billboard + " ";
        
		
		// shop header
		String result = ".-------------------------.\n";
        result += 		"|"+ billboard + "|\n";
        result += 		".-------------------------.\n";
        result += this.getDescription() + "\n";
        
        result += "\n";
        
        // catalog
        result += "We sell:";
        result += "...................\n";
        result += this.getObjects();
        
        String players = this.getPlayers(playerList);
        if (players.length() == 0) result += "You are here by yourself.\n";
        else {
        	result += "You are here along with:\n";
        	result += players;
        }
        
        result += "How can we help you?\n";
        return result;
    }
	
	/**
	 * returns a list of the players, separated by comma and using the Oxford comma.
	 * 
	 * @param players
	 * @return list of players
	 */
	public String getPlayers(PlayerList players) {
		String result = "";
		
		int i = 0;
		for (Player p : this.playerlist) {
			result += String.format("%s, ", p.toString());
			i++;
		}
		
		// removes the comma at the end of the line
		if (i > 0) result = result.substring(0, result.length()-2);
		
		// fixes the oxford comma
		if (i > 1) {
			String temp = result.substring(0, result.lastIndexOf(", "));
			temp += " and ";
			temp += result.substring(result.lastIndexOf(", ")+2);
			return temp + "\n";
		}
		return result + "\n";
		
	}
	
	/**
	 * Iterates through the list of the objects and creates a table populated with object names and prices.
	 * @return table of the objects
	 */
	private String getObjects() {
		
		int itemLen = 15, countLen = 2, f1 = 3, f2 = 2, priceField = f1 + f2 + 2;
		int menuWidth = itemLen + countLen + f1 + f2 + 6 + 2; // 6 = column padding, 2 = currency + decimal point
		
		// String formats for consistency
		String format = "%" + countLen +"s | %" + itemLen + "s | $%" + f1 + "." + f2 + "f\n";
		String headerFormat = "%" + countLen +"s | %" + itemLen + "s | $%" + priceField + "f\n";
		
		// generates menu separator
		String separator = "";
		for (int s = 0; s < menuWidth; s++) separator += "-";
		
		String menu = "";
		
		// menu header
		menu += String.format(headerFormat, "#", "Item", "Price");
		
		menu += separator + "\n";
		
		// adding menu items
		int i = 1;
		for (Object obj : inventory) {
			float price = 0; // TODO: replace with price getter
			
			if (this.inDemand.contains(obj)) price *= 1.2; // change price according to demand list
			String item = obj.toString();
			
			// handles items with long names
			if (item.length() > itemLen) {
				menu += String.format(format, i++, item.substring(0,itemLen), price);
				for (int j = 1; j <= item.length() % 15; j--) {
					menu += String.format(format, "", item.substring((itemLen*j)+1 ,itemLen*(j+1)), "");
				}
			}
			// names that aren't long
			else menu += String.format(format, i++, item, price);
		}
		
		menu += separator + "\n";
		
		return menu;
	}
}
