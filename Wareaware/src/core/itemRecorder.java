package core;

/**
 * Class to add to DB.
 */

abstract class itemRecorder extends mainDB {

    /**
     * Fields of item: In strings.
     */
    public String
        item_name, item_ID,
        origin_name, origin_contact;

    /**
     * Fields of item: In integer.
     */
    public int
        item_amount;

    /**
     * Fields of item: In double.
     */
    public double
        item_rate, item_space;

    // =====
    
    /**
     * Converts fields to a single string.
     */
    public String fieldsToString () {
        
        String sf = util.encode (item_name) + " <::> " + util.encode (item_ID) + " <::> " + util.encode (origin_name) + " <::> " + util.encode (origin_contact) + " <::> " + "" + item_amount + " <::> " + "" + item_rate + " <::> " + "" + item_space;
        
        return sf;
        
    }

}