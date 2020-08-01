package FinalProject;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class FinalProject {
	private Scanner input = new Scanner(System.in);
	private final double SALES_TAX = 0.07;
	private Item[] items = new Item[100];
	private int selectedItem = -1;
	boolean exit = false;
	Scanner keyboard = new Scanner(System.in);
	//FinalProject foo = new FinalProject();
	//foo.mainMenu();

	public FinalProject() {
	try(Scanner file = new Scanner(Paths.get("./Data/inventory.txt")))
	{
		int count = 0;
		while(file.hasNext()) {
			items[count++] = new Item(file.next(), file.next(), file.nextDouble(), file.nextInt());
			//System.out.println(items[0].getPrice());
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	
	public static void main(String[] args) throws FileNotFoundException {
		FinalProject p1 = new FinalProject();
		p1.mainMenu();
		
	
	}

	public void mainMenu() {
		// display menu and ask for user selection (validate)
		String name = "", password = "";
		String adminName = "cody", adminPassword = "345";
		boolean again = true;
		while (again) {
			System.out.println("1. Admin \n2. User\n3. Exit \n Please enter you choice: ");
			int option = 0;
			option = input.nextInt();
			while(option != 1 && option != 2 && option != 3) {
				System.out.println("Please enter an option (1-3): ");
				option = input.nextInt();
			}
		
		
		//int option = input.nextInt();
		
		switch (option) {
		case 1:
			// verify the admin login
			input.nextLine();
			System.out.print("Enter user name:");
			name = input.nextLine();
			System.out.println("Enter your password: ");
			password = input.nextLine();
			
			if(name.equals(adminName) && password.equals(adminPassword)) {
				adminMenu();
				
			}
			else {
				System.out.println("Invalid password or login.");
				break;
			}
			// if login correct go to the admin menu
			
			break;

		case 2:
			userMenu();
			break;

		case 3:
			// confirm they want to exit and then exit
			String choice = "";
			input.nextLine();
			
			if(option == 3)
			{
				System.out.print("Are you sure you would like exit? (Y/N)");
				choice = input.nextLine();
			}
			if (choice.equalsIgnoreCase("y")) {
				again = false;
			}
		}
		}
	}
	
	
	

	public void adminMenu() {
		// display menu and ask for user selection (validate)
		System.out.println("1. Add Item \n2. Search & Update Item \n3.Display all items. \n4. Search Delete item.  \nPlease enter you choice: ");
		int option = input.nextInt();
		while(option != 1 && option != 2 && option != 3 && option != 4)
		{
			System.out.println("Please enter a valid option (1-4): ");
			option = input.nextInt();
		}
		int found = -1;
		switch (option) {
		case 1:
			addItem();
			break;

		case 2:
			//System.out.println("Which item would you like to update?\n");
			//String id = input.next();
			found = searchForItem();
			 
				if (found != -1) {
					updateItem(found);
				}

				break;
			

		case 3:
			//Display all Items.
			displayAllItems();
			break;

		case 4:
			//delete item
			System.out.println("Enter the ID of the item you would like to delete");
			String id = input.next();
			deleteItem(id);
			return;
			
		}
	}



	public void userMenu() {
		// display menu and ask for user selection (validate)
		System.out.println("1. Display All Items\n2. Purchase Items\n3. Search Item\n Enter your choice: "); 
		int option = input.nextInt();
		while(option!=1 && option!=2 && option!=3)
        {
            System.out.println("Please enter a valid option (1-3): ");
            option= input.nextInt();
        }
		int found = -1;
		switch (option) {
		case 1:
			displayAllItems();
			
			break;

		case 2:
			//System.out.print("Enter item number to be purchased: ");
			//found= input.nextInt();
			//String find = input.next();
			System.out.print("Enter the name of the item you would like to purchase: ");
			String itemName = input.next();
			for(int i = 0; i < items.length; i++)
			{
				if(itemName.equalsIgnoreCase(items[i].getName())){
					purchaseItem(i);
				}
			}
			//purchaseItem(found);
			break;

		default:
			return;
		}
	}

	// Method to display all the items from the array.
	private void displayAllItems() {
		if(selectedItem == 0) {
			System.out.println("Store is currently empty");
		}
            
        for (int i = 0; i <items.length ; i++) {
            if(items[i] != null)
            {
        	System.out.println("Item ID: " + items[i].getId());
            System.out.println("Item Name: " + items[i].getName());
            System.out.println("Item Price: " + items[i].getPrice());
            System.out.println("Item Quanitity on hand: " + items[i].getQuantity());
            System.out.println();
            }
     
            }
			}
        
     
	
	
	//Method that searches for an item in the array.
	public int searchForItem() {
		//if(selectedItem == 0)
           //System.out.println("Couldn't find item.");
	
		input.nextLine();
        System.out.print("Enter product ID or Product Name: ");
        String id = input.nextLine();
        int index=-1;
        for(int i=0; i<items.length; i++)
        {
        	//items[i] != null &&
        	if(items[i] != null && (id.equalsIgnoreCase(items[i].getId())|| id.equalsIgnoreCase(items[i].getName())))
        {
            index = i;
        }
        }
        
        return index;
		
		//return -1;
	}
	
	//Method to delete an item from the array.
	private void deleteItem(String index) {
			// TODO Auto-generated method stub
		 
		for (int i = 0; i < items.length; i++)
		{
			if (items[i] != null && index.equalsIgnoreCase(items[i].getId()))
			{
				items[i] = null;
				
				
			}
			
		}
		
	}
	// Method that updates the item by asking which particular aspect they want to update.
	private void updateItem(int index) {
			// TODO Auto-generated method stub
		
	
		//System.out.print(items[index].getId());
		if(items[index] != null) {
			
			System.out.print("The item : " + items[index].getName() + " has " + items[index].getQuantity() + " left in stock. With the price of $" + items[index].getPrice() + "\n");
			
			System.out.print("Would you like to change the quantity of " + items[index].getName() + "? (Enter Y/N)");
			String decision3 = input.next();
			if(decision3.equalsIgnoreCase("Y"))
			{
				System.out.print(" Enter the new quantity amount: ");
				int newQuant = input.nextInt();
				items[index].setQuantity(newQuant);
				System.out.println();
			}
			
			System.out.print("Would you like to change the price of " + items[index].getName() + "? (Enter Y/N)");
			String decision4 = input.next();
			if(decision4.equalsIgnoreCase("Y"))
			{
				System.out.print("Enter the new price: ");
				double newPrice = input.nextInt();
				items[index].setPrice(newPrice);
				System.out.println();
			
			}
			
			System.out.print("Would you like to change the name of " + items[index].getName() + "? (Enter Y/N)");
			String decision = input.next();
			System.out.println();
			if(decision.equalsIgnoreCase("Y"))
			{
				System.out.print("Enter the new name: ");
				String newName = input.next();
				items[index].setName(newName);
				System.out.println();
			}
			
			System.out.print("Would you like to change the ID of " + items[index].getName() + "? (Enter Y/N)");
			String decision2 = input.next();
			System.out.println();
			if(decision2.equalsIgnoreCase("Y"))
			{
				System.out.print("Enter the new ID: ");
				String newID = input.next();
				items[index].setId(newID);
				System.out.println();
			}
		
		
		
		}
		
		
		

	}
	//Method to add an item into the array.
	private void addItem() {
			// TODO Auto-generated method stub
			System.out.println("Enter the item ID: ");
			String id = input.next();
			System.out.println("Enter the item name: ");
			String name = input.next();
			System.out.println("Enter the item price: ");
			double price = input.nextDouble();
			System.out.println("Enter the item quanitiy on hand ");
			int quantity =  input.nextInt();
			
			for (int i = 0; i < items.length; i++)
			{
				if(items[i] == null)
				{
					items[i] = new Item(id, name, price, quantity);
					
					//mainMenu();	
					break;
					
				}
			}
			
	}
	//Method to purchase Item from the array.
	private void purchaseItem(int index) {
		int purchaseNum = 0;
		if(items[index] != null)
		{
		System.out.print("The item : " + items[index].getName() + " has " + items[index].getQuantity() + " left in stock. With the price of $" + items[index].getPrice() + "\n");
		System.out.print("How many would you like to purchase?");
		purchaseNum = input.nextInt();
		
		}
		if((items[index].getQuantity() - purchaseNum) > 0)
		{
			double total = items[index].getPrice() + purchaseNum;
			System.out.print("The total price of " + items[index].getName() + " is $" + ((items[index].getPrice() * purchaseNum) * SALES_TAX  + total) + "\n");
			items[index].setQuantity(items[index].getQuantity() - purchaseNum);
			System.out.println();
		}
		mainMenu();
	}

}
class Item {

	private String id;
	private String name;
	private double price;
	private int quantity;

	//add constructors
	public Item(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
	//add get and set methods
	 public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }
}