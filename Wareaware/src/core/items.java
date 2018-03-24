package core;
import java.util.*;
import java.io.*;


/**
 * A class to hold my items :D
 */

public class items {

    /**
     * Name of the DB.
     */
    static String dbname = "database.dat";
    /**
     * File object.
     */
    static File data = new File (dbname);

    // =====

    /**
     * ARRAY LISTS. Containing the fields of different items.
     */
    public ArrayList <String> item_name, item_ID, origin_name, origin_contact;
    /**
     * ARRAY LISTS. Containing the fields of different items.
     */
    public ArrayList <Integer> item_amount;
    /**
     * ARRAY LISTS. Containing the fields of different items.
     */
    public ArrayList <Double> item_rate, item_space;

    // -----

    /**
     * Has the items in ONE string. Is being split.
     */
    String items = null;
    /**
     * Has the items in INDIVIDUAL idexes. To be split.
     */
    String itemArray [] = null;
    /**
     * Has the fields of ONE item in INDIVIDUAL indices.
     */
    String fieldArray [] = null;

    /**
     * Scanner to read items.
     */
    Scanner readItem = null; // for items

    // =====

    /**
     * Updates the lists.
     */
    public void updateLists () {

        // Setting array lists.

        item_name = new ArrayList <String> ();
        item_ID = new ArrayList <String> ();
        origin_name = new ArrayList <String> ();
        origin_contact = new ArrayList <String> ();

        item_amount = new ArrayList <Integer> ();

        item_rate = new ArrayList <Double> ();
        item_space = new ArrayList <Double> ();

        // Clearing items.

        item_name.clear ();
        item_ID.clear ();
        origin_name.clear ();
        origin_contact.clear ();
        item_amount.clear ();
        item_rate.clear ();
        item_space.clear ();

        // Setting items.

        if (setItems ()) {
            setItemArray ();
            setFields ();
        }

    }

    /**
     * Sets items.
     */
    public boolean setItems () {

        try {
            readItem = new Scanner (data);
        } catch (Exception e) {
            util.log ("Erro while initialising readItem: " + e);
        }

        boolean x = false;

        if (readItem.hasNextLine ()) {
            items = readItem.nextLine ();
            x = true;
        } else {
            System.out.println (">>NO ITEMS<<");
            x = false;
        }

        return x;

    }

    /**
     * Splits the items into individual. Had to be a different function because of checking empty DB.
     */
    public void setItemArray () {

        itemArray = items.split (">::<");

    }

    /**
     * Sets fields to ALL the lists.
     */
    public void setFields () {

        for (int i = 0; i < itemArray.length - 1; i++) {

            fieldArray = itemArray [i].split ("<::>");

            item_name.add (util.decode (fieldArray [0].trim ()));
            item_ID.add (util.decode (fieldArray [1].trim ()));
            origin_name.add (util.decode (fieldArray [2].trim ()));
            origin_contact.add (util.decode (fieldArray [3].trim ()));
            item_amount.add (Integer.parseInt (fieldArray [4].trim ()));
            item_rate.add (Double.parseDouble (fieldArray [5].trim ()));
            item_space.add (Double.parseDouble (fieldArray [6].trim ()));

        }

    }

}