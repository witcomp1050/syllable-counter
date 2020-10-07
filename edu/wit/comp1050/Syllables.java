package edu.wit.comp1050;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.*;


public class Syllables
{
    public static void main(String[] args) throws IOException
    {
        // Initiates the variable
        String captureTheWord = "";

        // If the length of the argument is not 0 then continue
        if (!(args.length == 0))
        {
            // Word from terminal into variable captureTheWord
            captureTheWord = args[0];
        } else
            {
            // Print if the if statement is true
            out.println("usage: Syllables <English word>");
            // return to main method and exits
            return;
        }

        // Captures the int front sending the capture word into getSyllableCount parameter.
         int numberOfSyllables = getSyllableCount(captureTheWord);


        // Checks if there is 1 argument
        if (args.length == 1)
        {
            // Prints if true
            if (numberOfSyllables == 1)
            {
                out.printf("'%s' has %d syllable%n", captureTheWord, numberOfSyllables);
            } else{
                out.printf("'%s' has %d syllables%n", captureTheWord, numberOfSyllables);
            }
            // Checks if there is a argument
        } else if(args.length == 2)
        {
                // if that argument is equal to the string -d then continue
                if (args[1].equals("-d"))
                {
                    // Checks if the word is in the english.txt
                    boolean wordCheckDictionary = checkEnglishWord(captureTheWord);
                    // If true print out the word with the syllables
                    if (wordCheckDictionary)
                    {
                        // If the number of Syllables is equal to one print out 'syllable'
                        if (numberOfSyllables == 1)
                        {
                            out.printf("'%s' has %d syllable%n", captureTheWord, numberOfSyllables);
                        } else{
                            out.printf("'%s' has %d syllables%n", captureTheWord, numberOfSyllables);
                        }
                    } else
                        {
                         // If false print out the word could not be found
                        out.printf("'%s' is not an English word that I know!%n", captureTheWord);
                    }
                } else{
                    // Prints out when the second argument is not -d
                    out.println("usage: Syllables <English word>");
                }
            } else
                {
                // Prints out when there are more than two arguments
                out.println("usage: Syllables <English word>");
            }
    }

    public static int getSyllableCount(String word)
    {
        // Sends the word as a parameter to the wordIsEmpty function
        if (wordIsEmpty(word))
        {
            // Returns 0 if word is null or empty from the helper class.
            return 0;
        }
        // Initiates the syllableCount variable as a integer.
        int syllableCount = 0;
        // Converts the word to lowercase
        String lowercaseWord = word.toLowerCase();


        // For loop for the length of the word.
        for (int i = 0; i < lowercaseWord.length(); i++)
        {
            //Counts the number of vowels (a, e, i, o, u) in the word.
            if (lowercaseWord.charAt(i) == 'a' || lowercaseWord.charAt(i) == 'e' || lowercaseWord.charAt(i) == 'i' || lowercaseWord.charAt(i) == 'o' || lowercaseWord.charAt(i) == 'u')
            {
                // Add one to syllableCount.
                syllableCount++;
            }
        }

       // Add 1 every time the letter y makes the sound of a vowel. (When y is not the first letter in the word.)
       if (lowercaseWord.charAt(0) != 'y' && lowercaseWord.contains("y"))
       {
           // Add one to syllableCount.
           syllableCount++;
       }
        // If last letter of the word is a e then subtract 1 (silent e at the end of a word).
        if (lowercaseWord.endsWith("e"))
        {
            // Subtract one to syllableCount.
            syllableCount--;
        }

        // Subtract 1 for each diphthong or triphthong in the word.
        // Reads the triphthongs.txt file.
        try
        {
            InputStream inputTriph = Syllables.class.getResourceAsStream("/triphthongs.txt");
            BufferedReader readTriphthongsFile = new BufferedReader(new InputStreamReader(inputTriph));

            String contentsFromTriphthongsFile = "";
            while((contentsFromTriphthongsFile = readTriphthongsFile.readLine() ) != null) {
                if (lowercaseWord.contains(contentsFromTriphthongsFile)) {
                    // Subtract one to syllableCount.
                    syllableCount--;
                }
            }

        } catch (IOException ex)
        {
            // If file not found print string below.
            out.println (ex.toString());
            // Exits out of the program.
            exit(0);
        }

        // Reads the diphthongs.txt file.
        try
        {
            InputStream inputDiph = Syllables.class.getResourceAsStream("/diphthongs.txt");
            BufferedReader readDiphthongsFile = new BufferedReader(new InputStreamReader(inputDiph));

            String contentsFromDiphthongsFile = "";
            while((contentsFromDiphthongsFile = readDiphthongsFile.readLine() ) != null) {
                if (lowercaseWord.contains(contentsFromDiphthongsFile)) {
                    // Subtract one to syllableCount.
                    syllableCount--;
                }
            }

        } catch (IOException ex)
        {
            // If file not found print string below.
            out.println (ex.toString());
            // Exits out of the program.
            exit(0);
        }

        // Checks if the word ends with le.
        if (lowercaseWord.endsWith("le"))
        {
            // Gets the character before attached to the le.
            String leBeforeIfConsonant = lowercaseWord.substring(lowercaseWord.lastIndexOf("le") - 1);
            // Checks if it contains a vowel if not then continue if statement.
            if (!(leBeforeIfConsonant.startsWith("a") || leBeforeIfConsonant.startsWith("e") || leBeforeIfConsonant.startsWith("i") || leBeforeIfConsonant.startsWith("o") || leBeforeIfConsonant.startsWith("u")))
            {
                // Add one to syllableCount
                syllableCount++;
            }
        }

        // Checks if the word ends with les.
        if (lowercaseWord.endsWith("les"))
        {
        // Gets the character before attached to the le.
        String lesBeforeIfConsonant = lowercaseWord.substring(lowercaseWord.lastIndexOf("les") - 1);
        // Checks if it contains a vowel if not then continue if statement.
        if (!(lesBeforeIfConsonant.startsWith("a") || lesBeforeIfConsonant.startsWith("e") || lesBeforeIfConsonant.startsWith("i") || lesBeforeIfConsonant.startsWith("o") || lesBeforeIfConsonant.startsWith("u")))
        {
            // Add one to syllableCount.
            syllableCount++;
        }
    }

        // If the word starts with io --> add 1 to the syllable count.
        if (lowercaseWord.startsWith("io"))
        {
            // Add one to syllableCount.
            syllableCount++;
        }
        // If the word ends with ee or ie --> add 1 to the syllable count.
        if (lowercaseWord.endsWith("ee") || lowercaseWord.endsWith("ie"))
        {
            // Add one to syllableCount.
            syllableCount++;
        }

        // (Most likely a better way to do this)
        // If for any circumstance the syllableCount is equal to 0 it returns 1 instead
        if (syllableCount == 0)
            return 1;

        // Returns the syllableCount to the getSyllableCount function.
        return syllableCount;
    }

    // Helper class to check if the String word is null or empty.
    private static boolean wordIsEmpty(String word)
    {
        // Checks if the word is null or is a empty string.
        return word == null || word.length() == 0;
    }

    // Check if the word is in the dictionary (english.txt)
    public static boolean checkEnglishWord(String word) throws IOException
    {
        // Turns the word into lowercase
        String noCaseMatter = word.toLowerCase();

        // BufferedReader initiated
        BufferedReader fileReader = new BufferedReader(new FileReader("english.txt"));
        // Creates the array
        ArrayList<String> captureDictionary = new ArrayList<String>();
        // String for current line
        String currentLine;

        // If not null then add current line to the array
        while ((currentLine = fileReader.readLine()) != null) {
            captureDictionary.add(currentLine);
        }

        // Returns true or false based on if the word could be found
        return captureDictionary.contains(noCaseMatter);
    }
}