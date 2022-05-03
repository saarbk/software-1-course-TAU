package il.ac.tau.cs.sw1.ex4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzleTester {
	public static void main(String[] args) {
		// puzzle1 = {w,h,_,i,_}
		char[] puzzle1 = WordPuzzle.createPuzzleFromTemplate("while",
				new boolean[] { false, false, true, false, true });
		if (!Arrays.equals(puzzle1, new char[] { 'w', 'h', WordPuzzle.HIDDEN_CHAR, 'l', WordPuzzle.HIDDEN_CHAR })) {
			System.out.println("Error 1.1");
		}

		boolean[] template1 = { true, true, true, false, true, true };
		boolean[] template2 = { true, true, false, false, true, true };

		// puzzle = {_,_,_,e,_,_}
		if (WordPuzzle.checkLegalTemplate("wheeps", template1)) {
			System.out.println("Error 2.1");
		}

		// puzzle = {_,_,e,e,_,_}
		if (!WordPuzzle.checkLegalTemplate("wheeps", template2)) {
			System.out.println("Error 2.2");
		}

		boolean[][] templates = WordPuzzle.getAllLegalTemplates("look", 1);
		if (templates.length != 2) {
			System.out.println("Error 3.1");
		}
		
		if (!Arrays.equals(templates[0],(new boolean[] { false, false, false, true }))) {
			System.out.println("Error 3.2");
		}

		// puzzle = {w,_,_,_,_s}
		char[] puzzle = WordPuzzle.createPuzzleFromTemplate("wheeps",
				new boolean[] { false, true, true, true, true, false });

		int numOfChangedLetters = WordPuzzle.applyGuess('h', "wheeps", puzzle);
		if (numOfChangedLetters != 1) {
			System.out.println("Error 4.1");
		}
		numOfChangedLetters = WordPuzzle.applyGuess('e', "wheeps", puzzle);
		if (numOfChangedLetters != 2) {
			System.out.println("Error 4.2");
		}
		boolean[] already_guessed = new boolean[26];
		
		// already guessed [a,b,c,d]
		for (int i = 0; i <4; i++){
			already_guessed[i] = true;
		}
		
		// puzzle = {w,_,_,_,_s}
		puzzle = WordPuzzle.createPuzzleFromTemplate("wheeps",
						new boolean[] { false, true, true, true, true, false });
		for (int j = 0; j < 100; j++){
			char[] hint = WordPuzzle.getHint("wheeps", puzzle, already_guessed);
			/*
			 * correctGuesses - all those letters are a "correct" hint.
			 * incorrectGuesses - none of those letters should be the "misleading" hint. Some
			 * of them have been guessed already (a,b,c,d), and the rest appear in the word
			 */
			String correctGuesses = "hep"; 
			String incorrectGuesses = "whepsabcd";
			boolean case1 = (correctGuesses.contains("" + hint[0]) && 
					!incorrectGuesses.contains("" + hint[1]));  //first char is correct
			boolean case2 = (correctGuesses.contains("" + hint[1]) && 
					!incorrectGuesses.contains("" + hint[0]));  //first char is incorrect
			if ( !case1 && !case2){
				System.out.println("Error 5.1");
			}
		}
		
		System.out.println("Done!");	
	}

}
