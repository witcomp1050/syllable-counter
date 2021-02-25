package xyz.ramonasuncion;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.*;


// TODO: Check the arguments length into a switch.

public class Syllables {
    /*
     * Gets the the arguments passed in from the terminal/console and checks if it
     * contains "-d" as an argument to test if the word is in the dictionary and
     * returns the number of the syllables. If does not pass in "-d" runs the
     * program with the word the user passes in without checking if its in the
     * dictionary and returns the number of syllables.
     */
    public static void main(String[] args) throws IOException {
        String captureTheWord = "";
        int numberOfSyllables = 0;

        if (!(args.length == 0)) {
            captureTheWord = args[0];
            numberOfSyllables = getSyllableCount(captureTheWord);
        }
        else {
            out.println("usage: Syllables <English word>");
            exit(0);
        }
        if (args.length == 1)
        {
            if (numberOfSyllables == 1) {
                out.printf("'%s' has %d syllable%n", captureTheWord, numberOfSyllables);
            }
            else {
                out.printf("'%s' has %d syllables%n", captureTheWord, numberOfSyllables);
            }
        }
        else if(args.length == 2)
        {
            if (args[1].equals("-d"))
            {
                boolean wordCheckDictionary = checkEnglishWord(captureTheWord);

                if (wordCheckDictionary) {
                    if (numberOfSyllables == 1) {
                        out.printf("'%s' has %d syllable%n", captureTheWord, numberOfSyllables);
                    }
                    else {
                        out.printf("'%s' has %d syllables%n", captureTheWord, numberOfSyllables);
                    }
                }
                else {
                    out.printf("'%s' is not an English word that I know!%n", captureTheWord);
                }
            }
            else {
                out.println("usage: Syllables <English word>"); // argument other than a -d
            }
        }
        else {
            out.println("usage: Syllables <English word>"); // more than two arguments
        }
    }


    /*
     * Function checks for vowels, if the string ends with e, contains y but it
     * can't be the first letter, tripthongs, dipthongs, if it starts with io, ends
     * with ee or ie, and le and les and the letter before le and les have to be
     * constant.
     */
    public static int getSyllableCount(String word) {
        int syllableCount = 0;
        String lowercaseWord = word.toLowerCase();

        if (wordIsEmpty(word)) {
            return 0;
        }

        for (int i = 0; i < lowercaseWord.length(); i++) {
            if (lowercaseWord.charAt(i) == 'a' || lowercaseWord.charAt(i) == 'e' || lowercaseWord.charAt(i) == 'i'
                    || lowercaseWord.charAt(i) == 'o' || lowercaseWord.charAt(i) == 'u') {
                syllableCount++;
            }
        }

        Pattern doesNotStartWithY = Pattern.compile("(\\By)");
        Matcher matchRegexForY = doesNotStartWithY.matcher(lowercaseWord);
        while (matchRegexForY.find()) {
            syllableCount++;
        }

        Pattern p = Pattern.compile("(e\\b)");
        Matcher m = p.matcher(lowercaseWord);

        while (m.find()) {
            syllableCount--;
        }

        try {
            InputStream inputTriph = Syllables.class.getResourceAsStream("/triphthongs.txt");
            BufferedReader readTriphthongsFile = new BufferedReader(new InputStreamReader(inputTriph));

            String contentsFromTriphthongsFile = "";
            while ((contentsFromTriphthongsFile = readTriphthongsFile.readLine()) != null) {
                Pattern readFilePattern = Pattern.compile(contentsFromTriphthongsFile);
                Matcher matchFilePattern = readFilePattern.matcher(lowercaseWord);

                while (matchFilePattern.find()) {
                    syllableCount--;
                }
            }
        } catch (IOException ex) {
            out.println(ex.toString());
            exit(0);
        }

        try {
            InputStream inputDiph = Syllables.class.getResourceAsStream("/diphthongs.txt");
            BufferedReader readDiphthongsFile = new BufferedReader(new InputStreamReader(inputDiph));

            String contentsFromDiphthongsFile = "";

            while ((contentsFromDiphthongsFile = readDiphthongsFile.readLine()) != null) {
                Pattern readFilePattern = Pattern.compile(contentsFromDiphthongsFile);
                Matcher matchFilePattern = readFilePattern.matcher(lowercaseWord);

                while (matchFilePattern.find()) {
                    syllableCount--;
                }
            }
        } catch (IOException ex) {
            out.println(ex.toString());
            exit(0);
        }

        Pattern le = Pattern.compile("(le\\b)");
        Matcher matchLe = le.matcher(lowercaseWord);

        while (matchLe.find()) {
            String leBeforeIfConsonant = lowercaseWord.substring(lowercaseWord.lastIndexOf("le") - 1);

            if (!(leBeforeIfConsonant.startsWith("a") || leBeforeIfConsonant.startsWith("e")
                    || leBeforeIfConsonant.startsWith("i") || leBeforeIfConsonant.startsWith("o")
                    || leBeforeIfConsonant.startsWith("u"))) {
                syllableCount++;
            }
        }

        Pattern les = Pattern.compile("(les\\b)");
        Matcher matchLes = les.matcher(lowercaseWord);

        String lesBeforeIfConsonant = "";

        while (matchLes.find()) {
            lesBeforeIfConsonant = lowercaseWord.substring(lowercaseWord.lastIndexOf("les") - 1);

            if (!(lesBeforeIfConsonant.startsWith("a") || lesBeforeIfConsonant.startsWith("e")
                    || lesBeforeIfConsonant.startsWith("i") || lesBeforeIfConsonant.startsWith("o")
                    || lesBeforeIfConsonant.startsWith("u"))) {
                syllableCount++;
            }
        }

        Pattern iO = Pattern.compile("(\\bio)");
        Matcher startsWithIO = iO.matcher(lowercaseWord);

        while (startsWithIO.find()) {
            syllableCount++;
        }

        Pattern eEIE = Pattern.compile("(ee\\b|ie\\b)");
        Matcher endsWithEEorIE = eEIE.matcher(lowercaseWord);

        while (endsWithEEorIE.find()) {
            syllableCount++;
        }

        if (syllableCount == 0) {
            syllableCount++;
        }

        return syllableCount;
    }

    // Function to check if the word that is being passed into the program is a null or empty.
    private static boolean wordIsEmpty(String word) {
        return word == null || word.length() == 0;
    }

    // Function checks if the word is in the english.txt file and if it does returns
    // true.
    public static boolean checkEnglishWord(String word) throws IOException {

        String noCaseMatter = word.toLowerCase();
        String currentLine;
        ArrayList<String> captureDictionary = new ArrayList<String>();

        BufferedReader fileReader = new BufferedReader(new FileReader("english.txt"));

        while ((currentLine = fileReader.readLine()) != null) {
            captureDictionary.add(currentLine);
        }

        return captureDictionary.contains(noCaseMatter);
    }
}