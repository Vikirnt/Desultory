package core;
import java.util.Scanner;

public class formTEST extends itemRecorder {
    
    /**
     * Constructor which enter data automatically.
     */
    public formTEST (boolean dataEntry) {
        if (dataEntry) {
            item_name = takeInput ("name (EXIT: 0)");
            if (!item_name.equals ("0")) {
                item_ID = takeInput ("ID");
                origin_name = takeInput ("company name");
                origin_contact = takeInput ("company contact");
                
                do { // takes .
                    try {
                        item_amount = Integer.parseInt (takeInput ("amount"));
                        isValid = true;
                    } catch (Exception e) {
                        util.log ("Error! Wrong ");
                        isValid = false;
                    }
                } while (!isValid); // verifies if it works.
                
                do { // takes .
                    try {
                        item_space = Double.parseDouble (takeInput ("space occupied in m³"));
                        isValid = true;
                    } catch (Exception e) {
                        util.log ("Error! Wrong ");
                        isValid = false;
                    }
                } while (!isValid); // verifies if it works.
                
                do { // takes .
                    try {
                        item_rate = Double.parseDouble (takeInput ("rate per day per m³"));
                        isValid = true;
                    } catch (Exception e) {
                        util.log ("Error! Wrong ");
                        isValid = false;
                    }
                } while (!isValid); // verifies if it works.
                
                addEntry (fieldsToString ());
            }
        }
        
    }

    static Scanner inp = new Scanner (System.in);
    static boolean isValid = false;

    /**
     *  Input taker.
     */
    public String takeInput (String inputPrompt) {
        String temp = "";
        do {
            try {
                System.out.print ("• Enter " + inputPrompt + " : ");
                temp = inp.nextLine ();
                isValid = true; System.out.println ("\n\n"); 
            } catch (Exception e) {
                inp.next ();
                util.log ("Wrong " + inputPrompt + "!\n");
                isValid = false;
            };
        }
        while (!isValid);

        return temp;
    }
    
}