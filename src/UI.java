import java.util.*;
import java.io.*;

/**
 * This is the interface that the user will use to process Inventory
 * 
 * @Allen Pan 
 * @ Due Date
 */

public class UI 
{
    private static Scanner in = new Scanner(System.in);
    private static Warehouse wHouse = new Warehouse();
    static final char CHOICE_ONE = '1';
    static final char CHOICE_TWO = '2';
    static final char CHOICE_THREE = '3';
    static final char CHOICE_FOUR = '4';
    static final char CHOICE_FIVE = '5';
    static final char CHOICE_SIX = '6';
    static final char CHOICE_SEVEN = '7';
    static final char CHOICE_EIGHT = '8';
    static final char CHOICE_NINE = '9';
    
    /**
     * main method that calls the menu and other methods to handle 
     * user input
     */
    public static void main(String args[])
    {
        char usrIn;

        wHouse.loadData();
        do 
        {
            showMenu();
            usrIn = in.next().charAt(0);
            handleUsrSelection(usrIn);
        }while (!usrChooseToCont(usrIn));

        System.out.println ("Thank you for using the Inventory Processing System");
    }

    /**
     *  The Inventory processing menu
     */
    public static void showMenu()
    {
        System.out.println("\nMENU:");
        System.out.println("1) Inventory Item Inquiry");
        System.out.println("2) Order inventory items from Supplier");
        System.out.println("3) Receive shipment from Suppliers");
        System.out.println("4) Return items to Supplier");
        System.out.println("5) Ship items to Customers");
        System.out.println("6) Process Customer Order");
        System.out.println("7) Process Customer Returns");
        System.out.println("8) End of Day Processing");
        System.out.println();
        System.out.println("9) Exit");
    }

    /**
     * call different methods to handle tasks based on what the 
     * user has entered
     */
    private static void handleUsrSelection(int usrChoice)
    {
        if (usrChoice == CHOICE_ONE) {
            handleItemInquiry();
        }
        else if (usrChoice == CHOICE_TWO) {
            handleOrderFromSupplier();
        }
        else if (usrChoice == CHOICE_THREE) {
            handleReceiveShipment();
        }
        else if (usrChoice == CHOICE_FOUR) {
            handleReturnToSupplier();
        }
        else if (usrChoice == CHOICE_FIVE) {
            handleShipCus();
        }
        else if (usrChoice == CHOICE_SIX) {
            handleCusOrder();
        }
        else if (usrChoice == CHOICE_SEVEN) {
            handleCusReturn();
        }
        else if (usrChoice == CHOICE_EIGHT) {
            handleEndDayProcessing();
        }
        else if (usrChoice == CHOICE_NINE) {
            System.out.println ("Thank you for using the Inventory Processing System");
            System.exit(0);
        }
        else {
            System.out.println("Invalid choice. Please enter a valid option");
        }
    }

    /**
     * helper method to check if the user still wants to continue
     */
    private static boolean usrChooseToCont(int usrInput) {
        if ( usrInput == CHOICE_NINE)
        {
            return true;
        }

        return false;
    }

    /**
     * helper method to request item number from user
     */
    private static String getItemNumFromUsr()
    {
        in.nextLine();
        System.out.println("Please enter the item number: ");
        String usrItemNo = in.nextLine(); 

        return usrItemNo;
    }

    /**
     * helper method to request the amount from the user
     */
    private static int requestAmount()
    {
        System.out.println("Please enter the amount:");
        int amount = in.nextInt();

        return amount;
    }
    
    /**
     * helper method to print out error message
     */
    private static void errorMsg()
    {
        System.out.println("Invalid item number");
    }
    
    /**
     * helper method to validate item number that the user has entered
     */
    private static boolean itemNumValidator(String usrInNo)
    {
        return wHouse.itemNoValidator(usrInNo);
    }
    
    /**
     * requests the item number from the user and validates the item number, 
     * if the number is valid then call method in warehouse class to get item
     * details and print it out
     * 
     */
    public static void handleItemInquiry() {
        String itemNumberFromUsr = getItemNumFromUsr();
        boolean validator = itemNumValidator(itemNumberFromUsr);
        if (validator)
        {
            String itemDetail = wHouse.itemInquiry(itemNumberFromUsr);
            System.out.println(itemDetail);
        }
        else 
        {
            errorMsg();
        }
    }
    
    /**
     * requests the item number from the user and validates the item number, 
     * if the number is valid then call method in warehouse class to handle 
     * ordering from supplier
     * 
     */
    public static void handleOrderFromSupplier () {
        String itemNumberFromUsr = getItemNumFromUsr();
        boolean validator = itemNumValidator(itemNumberFromUsr);

        if (validator)
        {
            wHouse.orderFromSupplier(requestAmount(), itemNumberFromUsr);
        }
        else 
        {
            errorMsg();
        }

    }

    /**
     * requests the item number from the user and validates the item number, 
     * if the number is valid then call method in warehouse class to handle
     * receiving shipments from supplier
     * 
     */
    public static void handleReceiveShipment () {
        String itemNumberFromUsr = getItemNumFromUsr();
        boolean validator = itemNumValidator(itemNumberFromUsr);

        if (validator)
        {
            wHouse.receiveShipment(requestAmount(), itemNumberFromUsr);
        }
        else 
        {
            errorMsg();
        }
        
    }
    
    /**
     * requests the item number from the user and validates the item number, 
     * if the number is valid then call method in warehouse class to handle
     * returning items to supplier
     * 
     */
    public static void handleReturnToSupplier() {
        String itemNumberFromUsr = getItemNumFromUsr();
        boolean validator = itemNumValidator(itemNumberFromUsr);
        
        if (validator)
        {
            wHouse.returnToSupplier(requestAmount(), itemNumberFromUsr);
        }
        else 
        {
            errorMsg();
        }
        
    }
    
    /**
     * requests the item number from the user and validates the item number, 
     * if the number is valid then call method in warehouse class to handle
     * shipment to customer
     * 
     */
    public static void handleShipCus () {
        String itemNumberFromUsr = getItemNumFromUsr();
        boolean validator = itemNumValidator(itemNumberFromUsr);
        
        if (validator)
        {
            wHouse.shipToCus(requestAmount(), itemNumberFromUsr);
        }
        else 
        {
            errorMsg();
        }
        
    }

    /**
     * requests the item number from the user and validates the item number, 
     * if the number is valid then call method in warehouse class to handle 
     * customer order
     * 
     */
    public static void handleCusOrder () {
        String itemNumberFromUsr = getItemNumFromUsr();
        boolean validator = itemNumValidator(itemNumberFromUsr);
        
        if (validator)
        {
            wHouse.cusOrder(requestAmount(), itemNumberFromUsr);
        }
        else 
        {
            errorMsg();
        }
    }
    
    /**
     * requests the item number from the user and validates the item number, 
     * if the number is valid then call method in warehouse class to handle 
     * customer return
     * 
     */
    public static void handleCusReturn () {
        String itemNumberFromUsr = getItemNumFromUsr();
        boolean validator = itemNumValidator(itemNumberFromUsr);
        
        if (validator)
        {
            wHouse.cusReturn(requestAmount(), itemNumberFromUsr);
        }
        else 
        {
            errorMsg();
        }
    }
    
    /**
     * calls a function in the warehouse class to handle end of day processing
     * 
     */
    public static void handleEndDayProcessing () {
        wHouse.endOfDayProcessing();
    }

}
