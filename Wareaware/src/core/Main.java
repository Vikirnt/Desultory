package core;
/**
 * Main class. Initiates the program.
 */

public class Main {
    
    /**
     * Version number.
     */
    static String version = "3.1";

    /**
     * The method which is executed first.
     */
    public static void main (String [] args) {

        try {

            mainmenu mainu = new mainmenu (version);
            mainu.proceed ();
            
        } catch (ExceptionInInitializerError e) {
            util.log ("Error: " + e);
        }

    }

}