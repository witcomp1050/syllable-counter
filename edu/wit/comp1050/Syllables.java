package edu.wit.comp1050;

import static java.lang.System.exit;
import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException ;
import java.util.Scanner;


public class Syllables
{

    public static void main(String[] args)
    {
    }

    public static int getSyllableCount(String word)
    {
        // Initiates the syllableCount variable as a integer
        int syllableCount = 0;
        String lowercaseWord = word.toLowerCase();

        if (wordIsEmpty(word)) {
            // Returns 0 if word is null or empty from the helper class
            return syllableCount = 0;
        }
        // For loop for the length of the word
        for (int i = 0; i < lowercaseWord.length(); i++) {
            // Checks if string has a vowel
            if (lowercaseWord.charAt(i) == 'a'
                || lowercaseWord.charAt(i) == 'e'
                || lowercaseWord.charAt(i) == 'i'
                || lowercaseWord.charAt(i) == 'o'
                || lowercaseWord.charAt(i) == 'u') {
                // Adds one if true
                syllableCount++;
            }
        }
       // If the word does not start with Y, however, contains the letter y
       if (lowercaseWord.charAt(0) != 'y' && lowercaseWord.contains("y")) {
           // Add one to syllableCount
           syllableCount++;
       }
        // If last letter of the word is a e then subtract 1.
        if (lowercaseWord.charAt(lowercaseWord.length() - 1 ) == 'e'){
            // Subtract one to syllableCount
            syllableCount--;
        }

        // Reads the triphthongs.txt file
        try {
            File file = new File("triphthongs.txt");
            Scanner readTriphthongsFile = new Scanner(file);
            while (readTriphthongsFile.hasNextLine()) {
                String contentsFromTriphthongsFile = readTriphthongsFile.nextLine();
                // If word contains a triphthong.
                if (lowercaseWord.contains(contentsFromTriphthongsFile)) {
                    // Subtract one to syllableCount.
                    syllableCount--;
                }
            }
        } catch (FileNotFoundException e){
            // If file not found print string below.
            out.print("Attempt to open the triphthongs.txt file has failed. File does not exist.");
            // Exits out of the program
            exit(0);
        }
        // Reads the diphthongs.txt file.
        try {
            File file = new File("diphthongs.txt");
            Scanner readDiphthongsFile = new Scanner(file);
            while (readDiphthongsFile.hasNextLine()) {
                String contentsFromDiphthongsFile = readDiphthongsFile.nextLine();
                // If word contains a Diphthongs.
                if (lowercaseWord.contains(contentsFromDiphthongsFile)) {
                    // Subtract one to syllableCount.
                    syllableCount--;
                }
            }
        } catch (FileNotFoundException e){
            // If file not found print string below
            out.print("Attempt to open the diphthongs.txt file has failed. File does not exist.");
            // Exits out of the program
            exit(0);
        }

        // Checks if the word ends with le or les

        if (lowercaseWord.endsWith("le") || lowercaseWord.endsWith("les")){
        }





        // Returns the syllableCount to the getSyllableCount function
        return syllableCount;
    }

    // Helper class to check if the String word is null or empty.
    private static boolean wordIsEmpty(String word) {
        return word == null || word.isEmpty();
    }
}
