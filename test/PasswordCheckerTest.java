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
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing PassWord Checker class with JUNIT tests
 */
class PasswordCheckerTest {

    /**
     * Clearing out variables before each test
     */
    @BeforeEach
    void setUp() throws Exception {
        PasswordChecker.initialize();
    }

    /**
     * Testing blank dictionary file Expected output is size is 0
     */
    @Test
    void testBlankDictionaryFile() throws FileNotFoundException {

        // read txt file
        PasswordChecker.readDictionaryFile("BlankDict.txt");

        // check if size of dictionary is zero
        Set<String> blankDictionary = PasswordChecker.getDictionary();
        assertEquals(0, blankDictionary.size());
    }

    /**
     * Testing Dictionary file with 4 words Expect hashset to
     */
    @Test
    void testDictionaryFile() throws FileNotFoundException {

        // read txt file
        PasswordChecker.readDictionaryFile("SmallDict.txt");

        // check if size of dictionary is 4
        Set<String> blankDictionary = PasswordChecker.getDictionary();
        assertEquals(4, blankDictionary.size());
    }

    /**
     * Testing password file with 10 words Expect List to hold 10 elements
     */
    @Test
    void testPasswordFile() throws FileNotFoundException {

        // read txt file
        PasswordChecker.readPasswordFile("passwords.txt");

        // check if size of passwords is 10 from file
        List<String> passwordArray = PasswordChecker.getPasswords();
        assertEquals(10, passwordArray.size());
    }

    /**
     * Testing password file & Dictionary file Expected result is that not valid
     * passwords will print out variables will store string correctly
     */
    @Test
    void testPasswordandDictionaryFile() throws FileNotFoundException {

        // read 2 txt files
        PasswordChecker.readDictionaryFile("SmallDict.txt");
        PasswordChecker.readPasswordFile("passwords2.txt");

        // set dictionary and passwords -- get data from PasswordChecker class
        Set<String> Dictionary = PasswordChecker.getDictionary();
        List<String> passwordArray = PasswordChecker.getPasswords();

        // for each password check if is is invalid
        for (String testPasswords : passwordArray) {
            PasswordChecker.passwordsCheckingFromFile(passwordArray);
        }

        // check to see if cat is both in dictionary and in passwords list
        assertTrue(Dictionary.contains("cat"));
        assertTrue(passwordArray.contains("cat"));
    }

    /**
     * Testing passwordChecking method Variables will store string correctly
     */
    @Test
    void testPasswordandDictionaryFile2() throws FileNotFoundException {

        // read 2 txt files
        PasswordChecker.readDictionaryFile("SmallDict.txt");
        PasswordChecker.readPasswordFile("passwords2.txt");

        // set dictionary and passwords -- get data from PasswordChecker class
        Set<String> Dictionary = PasswordChecker.getDictionary();
        List<String> passwordArray = PasswordChecker.getPasswords();

        // for each password check if is is invalid
        for (String testPasswords : passwordArray) {
            PasswordChecker.passwordsCheckingFromFile(passwordArray);
        }
        // check to see if cat and rooster are both in dictionary and in
        // passwords list
        assertTrue(Dictionary.contains("cat"));
        assertTrue(passwordArray.contains("rooster8"));
        assertTrue(passwordArray.contains("cat"));
        
    }

}
