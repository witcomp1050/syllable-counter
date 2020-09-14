# Wentworth Institute of Technology
## COMP1050
## Assignment #1, Syllables

Last updated: 24-May-2020

### Description
The purpose of this console-based program is to determine the number of syllables in a given English word, 
entered from the keyboard. The following shows some example runs:

```
$ java edu.wit.comp1050.Syllables apple
'apple' has 2 syllables

$ java edu.wit.comp1050.Syllables
usage: Syllables <English word>

$ java edu.wit.comp1050.Syllables COMPUTER
'COMPUTER' has 3 syllables
```
Note that this program should work with uppercase, lowercase and mixed-case words, e.g., *APPLE*, *apple* and *Apple*.

### Algorithm
In order to compute the syllables for a given word, you can use the following algorithm:

* Count the number of vowels (*a*, *e*, *i*, *o*, *u*) in the word.
* Add 1 every time the letter *y* makes the sound of a vowel. This is when *y* is not the first letter in the word.
* Subtract 1 for each silent *e* at the end of a word.
* Subtract 1 for each diphthong or triphthong in the word (see below).
* Does the word end with *le* or *les?* If so, add 1 only if the letter *before* the *le* is a consonant
* If the word starts with *io*, add 1 to the syllable count.
* If the word ends with *ee* or *ie*, add 1 to the syllable count.

The number you get is the number of syllables in your word.

Although this algorithm is quite good and produces accurate results, it's not perfect, so don't worry about
esoteric words and corner cases. As long as the algorithm produces the correct results for the given unit tests, 
you're good to go.

Note: Algorithms, in general, are *ordered* lists of steps; therefore, it's important to pay
attention to the order of operations in *any* algorithm that you implement.

#### Diphthongs & Triphthongs
*Diphthongs* & *triphthongs* are sequences of 2 and 3 vowels that form a single sound.
For example, the word *read* contains 2 vowels, but the combination of the letters *ea* makes a single sound. The
same goes for the *oo* in *book*. This means that in our program, we only "count" these as a single syllable.

### Files
In the git repo, you are given 2 files:
* diphthongs.txt
* triphthongs.txt

Using the text file IO capabilities of Java, read the contents of these files into
your program. You can use the contents of these files as the definitive list of 
diphthongs and triphthongs for this assignment.

### Extra Credit
Use the provided file *english.txt* to ensure that the word entered into the program is a valid 
English word. If the word cannot be found in the file, return an appropriate message
and exit the application without calculating the syllables.

Note that it is a good idea to use a "switch", e.g., *-d* (see below) to tell the
program to use the dictionary. For example:

```
$ java edu.wit.comp1050.Syllables dlhsdlkh -d
'dlhsdlkh' is not an English word that I know! Exiting.
```
For this assignment, you can load the words into a String[] so that you can 
have all the words in memory. This will enable you to compare the entered word with
the list of valid words that you store in the String[]. On my Mac, loading the
words into the String[] consumes about 10MB (a small amount of memory relative to
the capacity of most modern PCs).

---
