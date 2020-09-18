package edu.wit.comp1050;


public class Syllables
{

    public static void main(String[] args)
    {
    }

    public static int getSyllableCount(String word)
    {

        int syllableCount = 0;
        if (wordIsEmpty(word)) {
            return syllableCount = 0;
        }
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a'
                || word.charAt(i) == 'e'
                || word.charAt(i) == 'i'
                || word.charAt(i) == 'o'
                || word.charAt(i) == 'u') {
                syllableCount++;
            }
        }

       if (word.toLowerCase().charAt(0) != 'y' && word.contains("y")) {
           syllableCount++;
       }

        if (word.toLowerCase().charAt(word.length() - 1 ) != 'e'){
            syllableCount--;
        }

        return syllableCount;
    }

    private static boolean wordIsEmpty(String word) {
        return ((word == "null") || (word.isEmpty()));
    }
}
