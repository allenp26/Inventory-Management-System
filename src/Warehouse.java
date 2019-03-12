
/**
 * Warehouse contains the different items in stock
 * 
 * @author Allen Pan
 *
 */
public class Warehouse
{
    // the constructor
    Item[] itemInfoList = new Item[60];
    int numOfItems;

    /**
     * This is the hardcoded data to be loaded into the instance variables.  
     */
    public int loadData()
    {
        Item item = new Item("A11111", "Widgets", 30, 50, 70, 2.50, 20, 50);
        itemInfoList[0] = item;
        item = new Item("B22222", "Gadgets", 10, 20, 0, 4.00, 50, 100);
        itemInfoList[1] = item;
        item = new Item("C33333", "Trinkets", 100, 20, 35, 3.75, 80, 150);
        itemInfoList[2] = item;
        item = new Item("D44444", "Pickets", 0, 100, 20, 8.35, 25, 75);
        itemInfoList[3] = item;
        item = new Item("E55555", "Sockets", 200, 300, 150, 1.00, 200, 400);
        itemInfoList[4] = item;
        numOfItems = 5;

        //(A11111, Widgets, 30, 50, 70, 2.50, 20, 50;
        //("B22222", "Gadgets", 10, 20, 0, 4.00, 50, 100);
        //("C33333", "Trinkets", 100, 20, 35, 3.75, 80, 150);
        //("D44444", "Pickets", 0, 100, 20, 8.35, 25, 75);
        //("E55555", "Sockets", 200, 300, 150, 1.00, 200, 400);
        // number of items is 5;

        return numOfItems;
    }

    /**
     * validates the item number that the user has entered
     * 
     * @param String item num //item number that the user entered
     * @return boolean true/false // if the item number is found in the array, return true, vice versa
     */
    public boolean itemNoValidator(String itemNum)
    {
        for (int i = 0; i < numOfItems; i++)
        {
            if ( itemNum.equals(itemInfoList[i].getItemNo()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * handles the item inquiry, which calls another method to find item details 
     * and return the item details
     * 
     * @param String itemNumber // item number that the user entered
     * @return String itemDetails //item details in a string
     */
    public String itemInquiry(String itemNumber)
    {
        String itemDetails = "";
        for (int i = 0; i < numOfItems; i++)
        {
            if ( itemNumber.equals(itemInfoList[i].getItemNo()))
            {
                itemDetails = itemInfoList[i].itemDetails();
                return itemDetails;
            }
        }
        return itemDetails;
    }

    /**
     * Handles the task of ordering from the supplier, which calls the setter method and set the amount
     * to the amount that the user has entered
     * 
     * 
     */
    public void orderFromSupplier(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for (int i = 0; i < numOfItems; i++)
            {
                if (itemNum.equals(itemInfoList[i].getItemNo()))
                {
                    itemInfoList[i].setOnOrder(itemInfoList[i].getOnOrder() + amt);
                }
            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles the task of receving shipments from supplier, which compares the amount received with the 
     * on order amount and set the amount on hand and on order based on the amount received
     * 
     * @param int amt // the amount that the user wants to order
     * @param String itemNum // the item number that the user has entered
     */
    public void receiveShipment(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for (int i = 0; i < numOfItems; i++)
            {
                if (itemNum.equals(itemInfoList[i].getItemNo()))
                {
                    itemInfoList[i].setOnHand(itemInfoList[i].getOnHand() + amt);
                    if ( amt <= itemInfoList[i].getOnOrder())
                    {
                        itemInfoList[i].setOnOrder(itemInfoList[i].getOnOrder() - amt);
                    }
                    else
                    {
                        itemInfoList[i].setOnOrder(0);
                    }
                }
            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles the task of returning items to the supplier, which compares the amount to return with 
     * the amount on hand and call the setter method to set the amount on hand
     * 
     * @param int amt // the amount that the user wants to order
     * @param String itemNum // the item number that the user has entered
     */
    public void returnToSupplier(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for (int i = 0; i < numOfItems; i++)
            {
                if (itemNum.equals(itemInfoList[i].getItemNo()))
                {
                    if ( amt <= itemInfoList[i].getOnHand())
                    {
                        itemInfoList[i].setOnHand(itemInfoList[i].getOnOrder() - amt);
                    }
                    else
                    {
                        itemInfoList[i].setOnHand(0);
                    }
                }

            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles the shipment to customer, which compares the amount entered by user with the committed amount
     * and the on hand amount and set the committed amount and on hand amount
     * 
     * @param int amt // the amount that the user wants to order
     * @param String itemNum // the item number that the user has entered
     */
    public void shipToCus(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for ( int i = 0; i < numOfItems; i++)
            {
                if (itemNum.equals(itemInfoList[i].getItemNo()))
                {
                    if ( amt < itemInfoList[i].getCommitted())
                    {
                        itemInfoList[i].setCommitted(itemInfoList[i].committed - amt);
                    }

                    else if (amt > itemInfoList[i].getCommitted() && amt <= 
                    (itemInfoList[i].getCommitted() + itemInfoList[i].getOnHand()))
                    {
                        itemInfoList[i].setOnHand(amt - itemInfoList[i].getCommitted());
                        itemInfoList[i].setCommitted(0);

                    }

                    else if (amt > (itemInfoList[i].getCommitted() + itemInfoList[i].getOnHand()))
                    {
                        System.out.println("The requested amount is larger than the amount on hand and committed");
                    }
                }
            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles customer orders, which compares the amount entered with the amount on hand and call 
     * different setters to set the values for on hand and committed
     * 
     * @param int amt // the amount that the user wants to order
     * @param String itemNum // the item number that the user has entered
     */
    public void cusOrder(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for ( int i = 0; i < numOfItems; i++)
            {
                if (itemNum.equals(itemInfoList[i].getItemNo()))
                {
                    if ( amt <= itemInfoList[i].getOnHand())
                    {
                        itemInfoList[i].setCommitted( itemInfoList[i].getCommitted() + amt);
                        itemInfoList[i].setOnHand(itemInfoList[i].getOnHand() - amt);
                    }
                    else 
                    {
                        itemInfoList[i].setCommitted(itemInfoList[i].getCommitted() + 
                            itemInfoList[i].getOnHand());
                        itemInfoList[i].setOnHand(0);
                    }
                }

            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles customer returns, which adds the amount on hand with the amount that the user has entered
     * 
     * @param int amt // the amount that the user wants to order
     * @param String itemNum // the item number that the user has entered
     */
    public void cusReturn (int amt, String itemNum)
    {
        if (amt > 0)
        {
            for ( int i = 0; i < numOfItems; i++)
            {
                if (itemNum.equals(itemInfoList[i].getItemNo()))
                {
                    itemInfoList[i].setOnHand( itemInfoList[i].getOnHand() + amt);
                }
            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * handles the end of day processing, which prints out all item details, and calls autoOrder method 
     * to order items 
     * 
     */
    public void endOfDayProcessing()
    {
        System.out.printf("%-12s%-12s%-12s%-12s%-12s%-12s%-12s%n", "Item Number", "Item Name", "On Hand", "Committed", "On Order", "Unit Price", "Item Value");
        for (int i =0; i < numOfItems; i++)
        {
            System.out.println(itemInfoList[i].toString());
        }
        autoOrder();
    }

    /**
     * helper method for endOfDayProcess, which place orders if the item on hand is less than the 
     * reorder point amount 
     * 
     */
    private void autoOrder()
    {
        for(int i = 0; i < numOfItems; i++)
        {
            if (itemInfoList[i].getOnHand() <= itemInfoList[i].getReorderPoint())
            {
                orderFromSupplier(itemInfoList[i].getEconOrderQty(), itemInfoList[i].getItemNo());
            }
        }
    }

}
