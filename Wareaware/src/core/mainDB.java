package core;
import java.io.*;

/**
 * Class which actually handles the DB.
 */

public abstract class mainDB extends items {
    
    /**
     * File Writer which writes to the file. (APPENDS)
     */
    static FileWriter toDB = null;
    
    /**
     * Checks if the file exists or not. Creates the file if not.
     */
    public void checkFile () {

        try {
            if (data.createNewFile ()) {
                util.log (" New database created. Old database might not be existant."); // if new database is created.
            } else {
                System.out.println ("ß\n"); // if the database exists and is loaded. (User shouldn't know much IMO)
            }
        } catch (IOException e) {
            util.log ("" + e);
        }

    }

    /**
     * Adds entry by appending.
     */
    public void addEntry (String text) {

        try {
            toDB = new FileWriter (dbname, true);

            toDB.write (text + " >::< ");

            toDB.flush ();
            toDB.close ();
        } catch (Exception e) {
            util.log ("" + e);
        }

        util.nextScreen ();

    }

    /**
     * Displays ALL items. ALL fields.
     */
    public void displayItems () {

        updateLists ();

        try {

            for (int i = 0; i < item_name.size (); i++) {

                System.out.println ("#" + (i+1));
                System.out.println (">> " + origin_name.get (i) + " - " + item_name.get (i));
                System.out.println ("\t• ID : " + item_ID.get (i));
                System.out.println ("\t• Company contact : " + origin_contact.get (i));
                System.out.println ("\t• Total amount : " + item_amount.get(i));
                System.out.println ("• Total space occupied : " + item_space.get(i));
                System.out.println ("• Rate per day per m³: " + item_rate.get(i));
                System.out.println ("\n");

            }

        } catch (Exception e) {
            util.log ("Nice job, Watson. " + e);
        }

    }

    /**
     * Displays ALL items. ONLT item name, item ID and origin name.
     */
    public void displayItems (boolean x) {

        updateLists ();

        if (x) {

            try {

                for (int i = 0; i < item_name.size (); i++) {

                    System.out.println ("#" + (i+1) + "\t" + origin_name.get (i) + " >> " + item_name.get (i) + " - " + item_ID.get (i));

                }

            } catch (Exception e) {
                util.log ("What are you even doing? " + e);
            }

        }

    }

    /**
     * Displays ONLY 1 item. ALL fields.
     */
    public void displayItems (int index) {

        updateLists ();

        System.out.println ("\n\n \t\t 1 > Item name :" + item_name.get (index));
        System.out.println ("\t\t 2 > Company name :" + origin_name.get (index));
        System.out.println ("\t\t 3 > Item ID :" + item_ID.get (index));
        System.out.println ("\t\t 4 > Company contact :" + origin_contact.get (index));
        System.out.println ("\t\t\t 5 > Item amount :" + item_amount.get (index));
        System.out.println ("\t\t\t 6 > Item space :" + item_space.get (index));
        System.out.println ("\t\t\t 7 > Item rate :" + item_rate.get (index));

    }

}