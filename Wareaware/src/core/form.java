package core;
import java.util.Scanner;

/**
 * Class to enter items into the DB.
 * temp variables are temperory variables.
 */

class form extends itemRecorder {

    /**
     * A constructor which initiates the data entry process.
     * IF dataEntry == true;
     */
    public form (boolean dataEntry) {
        if (dataEntry == true) {
            item_name = setName ();
            if (!item_name.equals ("0")) {
                item_ID = setID ();
                origin_name = setCompanyName ();
                origin_contact = setCompanyContact ();
                item_amount = setAmount ();
                item_space = setSpace ();
                item_rate = setRate ();

                addEntry (fieldsToString ());
            }
        }
    }

    static Scanner inp = new Scanner (System.in);
    static boolean isValid = false;

    /**
     * Taking name.
     */
    public String setName (){
        String temp = "";
        while (true) {
            try {
                System.out.print ("• Enter item name (EXIT: 0) : ");
                temp = inp.nextLine ();
                break;
            } catch (Exception e) {
                inp.next ();
                util.log ("\n Wrong NAME! \n");
            };
        }

        return temp;
    }

    /**
     * Taking UUID.
     */
    public String setID ()  {
        String temp = "";
        while (true) {
            try {
                System.out.print ("• Enter item ID : ");
                temp = inp.nextLine ();
                break;
            } catch (Exception e) {
                inp.next ();
                util.log ("\n Wrong ID! \n");
                isValid = false;
            };
        }

        return temp;
    }

    /**
     * Taking company name.
     */
    public String setCompanyName ()  {
        String temp = "";
        while (true) 
            try {
                System.out.print ("• Enter company name : ");
                temp = inp.nextLine ();
                break;
            } catch (Exception e) {
                inp.next ();
                util.log ("\n Wrong COMPANY NAME! \n");
                isValid = false;
        };
        return temp;
    }

    /**
     * Taking company contact.
     */
    public String setCompanyContact ()  {
        String temp = "";
        while (true) 
            try {
                System.out.print ("• Enter company contact : ");
                temp = inp.nextLine ();
                break;
            } catch (Exception e) {
                inp.next ();
                util.log ("\n Wrong COMPANY CONTACT! \n");
        };

        return temp;
    }

    /**
     * Taking amount.
     */
    public int setAmount ()  {
        int temp = 0;
        while (true) 
            try {
                System.out.print ("• Enter item quantity : ");
                temp = inp.nextInt ();
                break;
            } catch (Exception e) {
                inp.next ();
                util.log ("\n Wrong AMOUNT! \n");
        };

        return temp;
    }

    /**
     * Taking Space.
     */
    public double setSpace ()  {
        double temp = 0.0D;
        while (true) 
            try {
                System.out.print ("• Enter space occupied (in m³) : ");
                temp = inp.nextDouble ();
                break;
            } catch (Exception e) {
                inp.next ();
                util.log ("\n Wrong SPACE! \n");
        };

        return temp;
    }

    /**
     * Taking rate.
     */
    public double setRate ()  {
        double temp = 0.0D;
        while (true) 
            try {
                System.out.print ("• Enter rate of rent per day per space : ");
                temp = inp.nextDouble ();
                break;
            } catch (Exception e) {
                inp.next ();
                util.log ("\n Wrong RATE! \n");
        };

        return temp;
    }

}