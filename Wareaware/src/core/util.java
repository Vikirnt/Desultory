package core;
/**
 * Utilities class which bundles utility functions accessed in a lot of classes.
 * ALL Encryption credit goes to my friend, Divyanshu.
 */
public class util {

    // Random functions.

    /**
     * Inputs a ton of new lines to separate all commands. Only for console. When I add GUI, this will be removed.
     */
    public static void nextScreen () {

        for (int i = 1; i <= 25; i++) {
            System.out.println ("\n\n\n");
        }

    }

    /**
     * DEBUGGING PURPOSES ONLY. Prings random crap into the console.
     */
    public static void log (String e) {

        System.out.println ("LOG>> " + e);

    }

    // ===
    /**
     * The split text to be encrypted.
     */
    static String[] splitWords = null;
    /**
     * The output.
     */
    static String OutStr = "";

    /**
     * Method to encode.
     */
    public static String encode (String text){

        splitWords = text.split(" "); // Text is split into words.
        OutStr = ""; // this is the output string.

        for(int i = 0; i < splitWords.length; i++) { // looping for every word.

            for(int j = 0; j < splitWords [i].length(); j++) { // looping for every alphabet of the word.

                char [] digit = splitWords [i].toCharArray(); // initialising an array for all alphabets.  
                int dgt = (int) digit[j]; // converting character into digit form (ASCII?)

                //Adding 3 to even indexes and 4 to odd indexes

                if(j%2==0){
                    dgt+=3;
                }
                else{
                    dgt+=4;
                }

                //Overwriting the new value 
                digit[j]=(char)dgt;
                //Appending the new message
                OutStr=OutStr+digit[j];
            }

            //Appending a whitespace between words

            if (i != splitWords.length - 1) {
                OutStr += " ";
            }

        }

        return OutStr;

    }

    /**
     * Method to decode.
     */
    public static String decode (String text) {

        splitWords = text.split(" "); // Text is split into words.
        OutStr = ""; // this is the output string.

        for(int i=0; i < splitWords.length; i++){

            for(int j = 0; j < splitWords [i].length(); j++) { // looping for every alphabet of the word.

                char[] digit = splitWords [i].toCharArray(); // initialising an array for all alphabets. 
                int dgt = (int) digit[j]; // converting character into digit form (ASCII?)

                //Subracting 3 from even indexes and 4 from odd indexes

                if(j % 2 == 0){
                    dgt-=3;
                }
                else{
                    dgt-=4;
                }
                //Overwriting the new value 
                digit[j]=(char)dgt;
                //Appending the new message
                OutStr=OutStr+digit[j];
            }

            //Appending a whitespace between words

            if (i != splitWords.length - 1) {
                OutStr += " ";
            }

        }

        return OutStr;

    }

}