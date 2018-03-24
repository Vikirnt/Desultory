package core;
import java.util.Scanner;

public class mainmenu {

    /**
     * Scanner object for input from keyboard.
     */
    static Scanner inp = new Scanner (System.in);

    /**
     * The user's choice.
     */
    static String choice = null;

    /**
     * Very important object for FORM class.
     */
    form f = null;
    
    /**
     * Giving birth to "Viewer", the almighty god who manages the database.
     */
    itemModifier v = new itemModifier ();

    /**
     * The options are in an array.
     */
    static String [] options = {"1. help", "2. enter", "3. view", "4. modify", "5. remove", "6. bill", "7. about", "0. exit"};

    // -----

    /**
     * Main constructor of this class. Prints the name of the project and the version, also checks if the file exists.
     */
    public mainmenu (String version) {

        form f = new form (false);
        util.nextScreen ();
        f.checkFile ();
        
        System.out.println ("_|          _|    _|_|    _|_|_|    _|_|_|_|    _|_|    _|          _|    _|_|    _|_|_|    _|_|_|_| ");
        System.out.println ("_|          _|  _|    _|  _|    _|  _|        _|    _|  _|          _|  _|    _|  _|    _|  _|     ");
        System.out.println ("_|    _|    _|  _|_|_|_|  _|_|_|    _|_|_|    _|_|_|_|  _|    _|    _|  _|_|_|_|  _|_|_|    _|_|_|  ");
        System.out.println ("  _|  _|  _|    _|    _|  _|    _|  _|        _|    _|    _|  _|  _|    _|    _|  _|    _|  _|  ");
        System.out.println ("    _|  _|      _|    _|  _|    _|  _|_|_|_|  _|    _|      _|  _|      _|    _|  _|    _|  _|_|_|_|  ");
        System.out.println ("\t\t\tVersion " + version);

    }

    /**
     * Main constructor of this class. Prints the name of the project and the version, also checks if the file exists.
     */
    public mainmenu ( ) {

        util.nextScreen ();

        //         System.out.println ("__        ___    ____  _____    ___        ___    ____  _____ ");
        //         System.out.println ("\\ \\      / / \\  |  _ \\| ____|  / \\ \\      / / \\  |  _ \\| ____|");
        //         System.out.println (" \\ \\ /\\ / / _ \\ | |_) |  _|   / _ \\ \\ /\\ / / _ \\ | |_) |  _|  ");
        //         System.out.println ("  \\ V  V / ___ \\|  _ <| |___ / ___ \\ V  V / ___ \\|  _ <| |___ ");
        //         System.out.println ("   \\_/\\_/_/   \\_\\_| \\_\\_____/_/   \\_\\_/\\_/_/   \\_\\_| \\_\\_____|");

        System.out.println ("_|          _|    _|_|    _|_|_|    _|_|_|_|    _|_|    _|          _|    _|_|    _|_|_|    _|_|_|_| ");
        System.out.println ("_|          _|  _|    _|  _|    _|  _|        _|    _|  _|          _|  _|    _|  _|    _|  _|     ");
        System.out.println ("_|    _|    _|  _|_|_|_|  _|_|_|    _|_|_|    _|_|_|_|  _|    _|    _|  _|_|_|_|  _|_|_|    _|_|_|  ");
        System.out.println ("  _|  _|  _|    _|    _|  _|    _|  _|        _|    _|    _|  _|  _|    _|    _|  _|    _|  _|  ");
        System.out.println ("    _|  _|      _|    _|  _|    _|  _|_|_|_|  _|    _|      _|  _|      _|    _|  _|    _|  _|_|_|_|  ");
        System.out.println ("");

        System.out.println ("Created by:\n");
        System.out.println ("\t\t____________________");
        System.out.println ("\t\t| Vikrant Gajria   |");
        System.out.println ("\t\t| 10 C             |");
        System.out.println ("\t\t| Roll #34         |");
        System.out.println ("\t\t|__________________|");
        System.out.println ("\n\n What is Wareaware?");
        System.out.println ("\t Wareaware is a simplistic method of storing files and managing the items you have in your warehouse");
        System.out.println ("\t It takes care about most of the essentials. It is completely free and open source.");
        System.out.println ("\n How to use it?");
        System.out.println ("\t Wareaware works on a simple CLI.");
        System.out.println ("\t To access the command list, type \"1\" or \"help\". ");
        System.out.println ("\t You can type the command or the number of the command. \n");
        System.out.println ("\n ENCODING and DECODING OTG.");

    }

    /**
     * Small method.
     */
    public void proceed () {

        help ();

        takeInput ();

    }

    /**
     * Takes input about what command the user wants to input.
     */
    private void takeInput () {

        System.out.print ("\n\t COMMAND > ");
        choice = inp.nextLine ();

        evaluateInput ();

    }

    /**
     * Checks what input the user has input.
     */
    public void evaluateInput () {

        util.nextScreen ();

        choice = choice.toLowerCase ();

        switch (choice) {

            case "exit":
            case "0":
            close ();
            break;

            case "help":
            case "1":
            help ();
            break;

            case "enter":
            case "2":
            f = new form (true);
            break;

            case "view":
            case "3":
            v.displayItems ();
            break;

            case "modify":
            case "4":
            v.modify ();
            break;

            case "remove":
            case "5":
            v.remove ();
            break;

            case "bill":
            case "6":
            v.bill ();
            break;

            case "7":
            case "about":
            about ();
            break;

            default:
            System.out.println ("invalid choice.\n");
            takeInput ();
            break;

        }

        takeInput ();

    }

    // -- OPTIONS

    /**
     * Shows the commands.
     */
    private void help () {

        System.out.println ("\n\t COMMAND LIST • ");

        for (int i = 0; i < options.length; i++) {
            System.out.println (options [i]);
        }

        System.out.println ("\n\n");

    }

    /**
     * Terminates the program.
     */
    private void close () {

        System.out.print ("Shutting down");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep (200);
                System.out.print (".");
            } catch (Exception e) {
                util.log ("" + e);
                System.exit (-1);
            }
        }

        System.exit (0);

    }

    /**
     * About screen. Will change this to something logical.
     */
    public void about () {

        new mainmenu ();

    }

}