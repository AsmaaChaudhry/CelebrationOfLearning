/**
 * @author asmaachaudhry
 * @date April 19 2021
 */

//import files for PasswordChecker Class 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.lang.StringBuilder;

/**
 * This program reads in a dictionary of words from a file. It then repeatedly
 * prompts the user for a string and tells the user if the string is a good
 * password or not.
 */
public class PasswordChecker {
    // HashSet that contains each String from Dictionary file
    private static Set<String> dictionary = new HashSet<String>();

    // List that contain the passwords read in from Password txt file
    private static List<String> passwords = new ArrayList<>();
    
    
    //String to store user password input
    private String userPassword;
    
    /**
     * Added for testing 
     * Clear variables for testing
     */
    static void initialize() {
        dictionary = new HashSet<String>();
        passwords = new ArrayList<>();
//        String userPassword;
    }
    
    /**
     * Added for testing 
     */
    static Set<String> getDictionary(){
        return dictionary;
    }
    
    /**
     * Added for testing 
     */
    static List<String> getPasswords(){
        return passwords;
    }
    
    /**
     * Added for testing 
     */
    public String getUserName() {
        return userPassword;
    }
    /**
     * Reads the words from the Dictionary file if 1 or 0 command line arguments
     * are passed in Initializes the HashSet by adding each word from the file.
     * 
     * @throws FileNotFoundException if cannot find dictionary file
     */
    static void readDictionaryFile(String fileName)
            throws FileNotFoundException {
        try (Scanner dictFile = new Scanner(new File(fileName))) {
            
            // read in each word from dictionary and add to HashSet
            while (dictFile.hasNext()) {
                dictionary.add(dictFile.next().trim());
            }
            // close file reading
            dictFile.close();
        }
    }

    /**
     * Reads the words from the Passwords file if 2 command line arguments are
     * passed in Initializes the List by adding each password to the Arraylist
     * 
     * @throws FileNotFoundException if cannot find password file
     */
    static void readPasswordFile(String fileName) throws FileNotFoundException {
        try (Scanner pwFile = new Scanner(new File(fileName))) {
           
            // read in each word from password and add to arraylist
            while (pwFile.hasNext()) {
                passwords.add(pwFile.next().trim());
            }
            // close file reading
            pwFile.close();
        }
    }

    /**
     * Checking passwords from passwords.txt file
     * returns to console all INVALID passwords
     * 
     * @param param List<String> passwords 
     */
    static void passwordsCheckingFromFile(List<String> passwords) {
        // for each password in List, check if it is valid or not
        for (String password : passwords) {
            // password length
            int pwLength = password.length();

            // if length of password is greater than or equal to 8 then do more testing
            if (pwLength >= 8) {
                // split string from character and digits -- place into String array
                String[] subString = password.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

                // for each substring in array check to see if word is in dictionary HashSet
                for (int i = 0; i < subString.length; i++) {

                    // if there is a reversed string input -- unreverse it
                    String reverse = new StringBuilder(subString[i]).reverse().toString();

                    // if dictionary contains substring or reverse then its not a good password
                    if (dictionary.contains(subString[i])
                            || dictionary.contains(reverse)) {

                        System.out.println(password + " is NOT a good password");
                    }
                }

                // if length of password is less than 8 -- not a good password
            } else {
                System.out.println(password + " is NOT a good password");
            }
        }
        return;
    }

    /**
     * Getting user password input If 1 or 0 .txt files are in command line
     * arguments
     */
    static void getUserPassword() {
        try (Scanner user = new Scanner(System.in)) {
            // get user input
            System.out.print("\n" + "Enter a word to check: ");

            // save input as userPassword
            String userPassword = user.nextLine();

            // if want to exit program
            if (userPassword.equals("q")) {
                System.out.print("BYE!");
                return;
                // if password contains emty spaces
            } else if (userPassword.contains(" ")) {
                System.out.print("Password cannot contain any spaces!");
                getUserPassword();
            }
            // convert password into all lowercase 
            // call on passwordChecking Method
            else {
                userPassword.toLowerCase();
                passwordChecking(userPassword);
            }

        }

    }

    /**
     * Checking to see if password is valid or not
     * Input from getUserPassword method Continues
     * Prompting user after validating password
     * 
     * @param param userPasword
     */
     static void passwordChecking(String userPassword) {
        // password length
        int passwordLength = userPassword.length();

        // if length of password is greater than or equal to 8 then do more testing
        if (passwordLength >= 8) {
            // split string from character and digits -- place into String array
            String[] subString = userPassword.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

            // for each substring in array check to see if word is in dictionary HashSet
            for (int i = 0; i < subString.length; i++) {

                // for each substring in array check to see if word is in dictionary HashSet
                String reverse = new StringBuilder(subString[i]).reverse().toString();

                // if dictionary contains substring or reverse then its not a good password
                if (dictionary.contains(subString[i])
                        || dictionary.contains(reverse)) {

                    System.out.print(userPassword + " is NOT a good password");
                    getUserPassword();
                    return;

                    // else it is a good password
                } else {
                    System.out.print(userPassword + " is a good password");
                    getUserPassword();
                    return;
                }
            }

            // if length of password is less than 8 -- not a good password
        } else {
            System.out.print(userPassword + " is NOT a good password");
            getUserPassword();
            return;
        }
    }

    /**
     * Main Method Reads words from files, get users input passwords, and checks
     * passwords from file Catches FileNotFoundException if file input is wrong
     * 
     * @param param arguments from command line
     */
    public static void main(String[] args) {

        try {
            // if command line argument is 1 --> read file and get user preferences, check password validation
            if (args.length == 1) {
                System.out.println(
                        "Welcome to Password Checker, enter a Dictionary file and a password to begin and enter q to end the program!");
                readDictionaryFile(args[0]);
                getUserPassword();

                // if command line argument is 2 --> read dictionary and password file and check password validation
            } else if (args.length == 2) {
                System.out.println(
                        "Welcome to Password Checker... checking to see which passwords are INVALID from txt files"
                                + "\n");
                readDictionaryFile(args[0]);
                readPasswordFile(args[1]);
                passwordsCheckingFromFile(passwords);

                // if command line argument is 0 --> read Dict.txt file and get user preferences, check password validation
            } else {
                System.out.println(
                        "Welcome to Password Checker, enter a password to begin and enter q to end the program!");
                readDictionaryFile("Dict.txt");
                getUserPassword();
            }
            
        // catch exception if file input is wrong
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read the Dictionary or Password file");
        }
    }
}


