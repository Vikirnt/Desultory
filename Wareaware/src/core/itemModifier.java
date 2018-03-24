package core;
import java.util.*;
import java.io.*;
import java.text.NumberFormat;

/**
 * A holy, legendary entity which modifies the database.
 */

public class itemModifier extends mainDB {

    /**
     * Entry to process.
     */
    int entry = 0;
    /**
     * Index of the entry.
     */
    int index = 0;
    /**
     * Size of the array list. Calculation purposes only.
     */
    int size = 0;

    /**
     * Main constructor. Checks file and updates lists.
     */
    public itemModifier () {

        checkFile ();
        updateLists ();

    }

    /**
     * Gets the entry to process.
     */
    public void getChoice (String text) {

        Scanner inp = new Scanner (System.in);
        boolean isValid = false;
        
        System.out.println (">> ITEM TO " + text.toUpperCase () + " : ");
        displayItems (true);
        
        do {
            try {
                System.out.print ("\n\t- Entry number to " + text + " (EXIT: 0) > #");
                entry = inp.nextInt ();
                isValid = true;
            } catch (Exception e) {
                inp.next ();
                util.log ("\nPlease input a number.\n");
                isValid = false;
            }
        } while (!isValid);
        inp.close ();

    }

    /**
     * Sets the index of the entry.
     */
    public boolean setIndex () {

        if (entry == 0) {
            util.nextScreen ();
            System.out.println (">> Process ended.");
            return false;
        } else {
            if (entry > size) {
                util.nextScreen ();
                System.out.println ("> WRONG INDEX");
                return false;
            } else {
                index = entry - 1;
                return true;
            }
        }

    }

    /**
     * Sets the variable "size" used for calculation purposes.
     */
    public void setSize () {

        size = item_name.size ();
        util.nextScreen();

    }

    /**
     * Edits file.
     */
    public void editFile () {

        FileWriter fw = null;
        try {
            fw = new FileWriter (data, false);
        } catch (Exception e) {
            util.log ("File writer error: " + e);
        }

        try {
            for (int i = 0; i < size ; i++) {

                fw.write (util.encode (item_name.get (i)) + " <::> ");
                fw.write (util.encode (item_ID.get (i)) + " <::> ");
                fw.write (util.encode (origin_name.get (i)) + " <::> ");
                fw.write (util.encode (origin_contact.get (i)) + " <::> ");
                fw.write ("" + item_amount.get (i) + " <::> ");
                fw.write ("" + item_rate.get (i) + " <::> ");
                fw.write ("" + item_space.get (i) + " >::< ");

            }

            fw.flush ();
            fw.close ();
        } catch (Exception e) {
            util.log ("Error while editing file: " + e);
        }

    }

    // -----

    /**
     * Initiates the removal process.
     */
    public void remove () {

        updateLists ();
        setSize ();

        getChoice ("remove");

        if (setIndex ()) {
            if (verifyRemoval ()) {
                removeEntry ();
                setSize ();
                editFile ();
            }
        }

    }

    /**
     * Verifies the removal process.
     */
    public boolean verifyRemoval () {

        Scanner inp = new Scanner (System.in);
        Random dice = new Random ();
        int x = dice.nextInt (9);
        int y = 0;
        boolean isValid = false;

        displayItems (index);
        System.out.println ("\n\nTo delete this entry, TYPE: " + (x+1));

        do {
            try {
                System.out.print ("> ");
                y = inp.nextInt ();
                isValid = true;
            } catch (Exception e) {
                inp.next();
                isValid = false;
            }
        } while (!isValid);

        inp.close ();

        return (x+1) == y? true : false;

    }

    /**
     * Removes entries from the lists.
     */
    public void removeEntry () {

        item_name.remove (index);
        item_ID.remove (index);
        origin_name.remove (index);
        origin_contact.remove (index);
        item_amount.remove (index);
        item_space.remove (index);
        item_rate.remove (index);

    }

    // -----

    /**
     * Method to control the flow for modification.
     */
    public void modify () {

        updateLists ();
        setSize ();

        getChoice ("modify");

        if (setIndex ()) {
            if (getEdit ()) {
                editFile ();
            }
        }

    }

    /**
     * Gets the field to edit.
     */
    public boolean getEdit () {

        Scanner inp = new Scanner (System.in);
        displayItems (index);
        String field = ""; // field to edit.
        System.out.print ("\n\t- Field to edit (EXIT: 0) : ");
        field = inp.nextLine ();
        inp.close ();

        boolean doneEdit = false;

        form f = new form (false);

        switch (field) {

            case "0":
                doneEdit = false;
            break;

            case "1":
                doneEdit = true;
                item_name.set (index, f.setName ());
            break;

            case "2":
                doneEdit = true;
                origin_name.set (index, f.setCompanyName ());
            break;

            case "3":
                doneEdit = true;
                item_ID.set (index, f.setID ());
            break;

            case "4":
                doneEdit = true;
                origin_contact.set (index, f.setCompanyContact ());
            break;

            case "5":
                doneEdit = true;
                item_amount.set (index, f.setAmount ());
            break;

            case "6":
                doneEdit = true;
                item_space.set (index, f.setSpace ());
            break;

            case "7":
                doneEdit = true;
                item_rate.set (index, f.setRate ());
            break;

            default:
                System.out.println (">> WRONG CHOICE.");
                getEdit ();
            break;

        }

        return doneEdit;

    }

    // -----

    /**
     * Controls the flow of the billing system.
     */
    public void bill () {

        int days = 0;
        
        updateLists ();
        setSize ();
        
        getChoice ("bill");

        if (setIndex ()) {
            
            days = getDays ();

            doBill (days);

        }

    }

    /**
     * Sets the number of days the item has been in the warehouse.
     */
    public int getDays () {

        Scanner inp = new Scanner (System.in);
        boolean isValid = false;
        int x = 0;

        do {
            try {
                System.out.print ("\n\t- Entry number of days the item has remained in the warehouse > ");
                x = inp.nextInt ();
                isValid = true;
            } catch (Exception e) {
                inp.next ();
                util.log ("\nPlease input a number.\n");
                isValid = false;
            }
        } while (!isValid);
        inp.close ();

        return x;

    }

    /**
     * Final amount.
     */
    public double credit (int days) {

        return 1 * days * item_rate.get (index) * item_space.get (index) * item_amount.get (index);

    }
    
    /**
     * Prints bill.
     */
    public void doBill (int days) {

        util.nextScreen ();
        
        System.out.println ("");
        System.out.println ("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println ("> Name : " + item_name.get (index) + ".");
        System.out.println ("> ID : " + item_ID.get (index) + ".");
        System.out.println ("\t> Company name : " + origin_name.get (index) + ".");
        System.out.println ("\t> Company contact : " + origin_contact.get (index) + ".");
        System.out.println ("\n> Item occupies : " + item_space.get (index) + " m³.");
        System.out.println ("> For : " + item_amount.get (index) + " items.");
        System.out.println ("> At : " + item_rate.get (index) + " per day per item per m³.");
        System.out.println ("> " + days + " days.");
        System.out.println ("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println ("\n>> TOTAL CREDIT : RS." + "\t" + NumberFormat.getNumberInstance(Locale.US).format(credit (days)) + "\n");
        System.out.println ("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
    }

}