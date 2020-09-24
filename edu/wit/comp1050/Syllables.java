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
        // what prints out to the console
    }

    public static int getSyllableCount(String word)
    {
        // Initiates the syllableCount variable as a integer
        int syllableCount = 1; // Subject to change due to some words ending up at 0
        String lowercaseWord = word.toLowerCase();

        if (wordIsEmpty(word)) {
            // Returns 0 if word is null or empty from the helper class
            return syllableCount = 0;
        }

        // For loop for the length of the word
        for (int i = 0; i < lowercaseWord.length(); i++)
        {
            //Counts the number of vowels (a, e, i, o, u) in the word.
            if (lowercaseWord.charAt(i) == 'a' || lowercaseWord.charAt(i) == 'e' || lowercaseWord.charAt(i) == 'i' || lowercaseWord.charAt(i) == 'o' || lowercaseWord.charAt(i) == 'u')
            {
                // Add one to syllableCount
                syllableCount++;
            }
        }

       // Add 1 every time the letter y makes the sound of a vowel. (When y is not the first letter in the word.)
       if (lowercaseWord.charAt(0) != 'y' && lowercaseWord.contains("y"))
       {
           // Add one to syllableCount
           syllableCount++;
       }
        // If last letter of the word is a e then subtract 1 (silent e at the end of a word)
        if (lowercaseWord.charAt(lowercaseWord.length() - 1 ) == 'e')
        {
            // Subtract one to syllableCount
            syllableCount--;
        }

        // Subtract 1 for each diphthong or triphthong in the word
        // Reads the triphthongs.txt file
        try
        {
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
        } catch (FileNotFoundException e)
        {
            // If file not found print string below.
            out.print("Attempt to open the triphthongs.txt file has failed. File does not exist.");
            // Exits out of the program
            exit(0);
        }

        // Reads the diphthongs.txt file.
        try
        {
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
        } catch (FileNotFoundException e)
        {
            // If file not found print string below
            out.print("Attempt to open the diphthongs.txt file has failed. File does not exist.");
            // Exits out of the program
            exit(0);
        }

        // Checks if the word ends with le or les and if the letter before is a consonant
        if (lowercaseWord.endsWith("le") || lowercaseWord.endsWith("les")){
            // Gets the character before attached to the le
            String checkFirstLetterVowel = lowercaseWord.substring(lowercaseWord.lastIndexOf("le") - 1);
            // Checks if the string has a vowel in it
            if (!(checkFirstLetterVowel.startsWith("a") ||checkFirstLetterVowel.startsWith("e") ||checkFirstLetterVowel.startsWith("i") || checkFirstLetterVowel.startsWith("o") || checkFirstLetterVowel.startsWith("u"))) {
                // Add one to syllableCount
                syllableCount++;
            }
        }
        // If the word starts with io --> add 1 to the syllable count.
        if (lowercaseWord.startsWith("io"))
        {
            // Add one to syllableCount
            syllableCount++;
        }
        // If the word ends with ee or ie --> add 1 to the syllable count.
        if (lowercaseWord.endsWith("ee") || lowercaseWord.endsWith("ie"))
        {
            // Add one to syllableCount
            syllableCount++;
        }

        // Returns the syllableCount to the getSyllableCount function
        return syllableCount;
    }

    // Helper class to check if the String word is null or empty.
    private static boolean wordIsEmpty(String word) {
        return word == null || word.isEmpty();
    }
}
