package il.ac.tau.cs.sw1.ex4;

import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;

	
	/*
	 * @pre: template is legal for word
	 */
	public static char[] createPuzzleFromTemplate(String word, boolean[] template) { // Q - 1
		char[] word_array=new char[template.length];

		for (int i=0;i<template.length;i++){
			if(!template[i])
			{
			word_array[i]=word.charAt(i);
			}else word_array[i]=HIDDEN_CHAR;
		}
		return word_array;
	}

	public static boolean checkLegalTemplate(String word, boolean[] template) { // Q - 2
         int[] AtoZ=new int[26];
          int count_true=0;
		  AtoZ[0]=-1;
          if(word.length()!=template.length||template.length<1)
                return false;

          for (int i=0;i<template.length;i++)
		  {
		  	  if (template[i]){
		  	  	count_true++;
			  }
			  int c = (int) word.charAt(i)-97;
			  if(AtoZ[c]>0&&c!=0)
			  	if(template[AtoZ[c]]!=template[i])
			  		return false;
			  AtoZ[c]=i;
		  }


		return count_true>0&&count_true<template.length;
	}
	
	/*
	 * @pre: 0 < k < word.lenght(), word.length() <= 10
	 */
	public static boolean[][] getAllLegalTemplates(String word, int k){  // Q - 3
		int  n=word.length(),cnt=0;
		boolean [][] alloption = new boolean[nCk(n,k)][n];

		int srart_binary=0,  end_binary=0;
		for(int i=0; i<k;i++) {
			srart_binary += (int)Math.pow(2, i);
			end_binary += (int)Math.pow(2, n-1-i);
		}

		boolean [] temp;
		for(int i = srart_binary; i<= end_binary; i++) {
              temp= getgoodtemp(word,i,k);
              if(temp!=null){
				  alloption[cnt]=temp;
				  cnt++;
			  }
		}


		return removenull(alloption,word, cnt);
	}


	private static boolean []  getgoodtemp(String word,int i,int k)
	{

		int cnt=0;
		boolean [] templateTocheck = new boolean[word.length()];
		String binaryVal = Integer.toBinaryString(i);
		binaryVal = new String(new char[word.length()-binaryVal.length()]).replace("\0","0")+ binaryVal;
		char [] goodtemplate= binaryVal.toCharArray();
		for(int j=0; j<goodtemplate.length; j++) {
			if(goodtemplate[j] == '1') {
				templateTocheck[j]= true;
					cnt++;
				}
			}
			if (checkLegalTemplate(word,templateTocheck)&&cnt==k)
                return templateTocheck;
	return  null;}


    private static boolean[][] removenull(boolean [][] _alloptiontemplate,String word,int cnt)
	{
		boolean[][] finalBollianArray=new boolean[cnt][word.length()];
		for (int i=0;i<cnt;i++)
			finalBollianArray[i]=_alloptiontemplate[i];
		return finalBollianArray;
	}


		private static int fact(int i){
			if (i == 1)
				return 1;
			else
				return(i * fact(i-1));
	}
	private static int nCk (int n, int k) {
		return fact(n) / (fact(k) * fact(n - k));
	}





	/*
	 * @pre: puzzle is a legal puzzle constructed from word, guess is in [a...z]
	 */


	public static int applyGuess(char guess, String word, char[] puzzle) { // Q - 4
		int cnt =0;
		for(int i =0; i<puzzle.length; i++) {
			if(word.charAt(i)==guess && puzzle[i]==HIDDEN_CHAR) {
				puzzle[i] = guess;
				cnt ++;
			}
		}
		return cnt;
	}
	

	/*
	 * @pre: puzzle is a legal puzzle constructed from word
	 * @pre: puzzle contains at least one hidden character. 
	 * @pre: there are at least 2 letters that don't appear in word, and the user didn't guess
	 */
	public static char[] getHint(String word, char[] puzzle, boolean[] already_guessed) { // Q - 5
		char[] hint=new char[2];
		int n=word.length();
		char[] charThatnotFound= new char[n];
		boolean[] charInPuzzele=new boolean[26];
		char[] charThatFound= new char[26];
		 int letterThatNotFount=0;
		 int letterThatFount=0;
		Random rn = new Random();

		for (int i=0;i<n;i++){
			if(puzzle[i]==HIDDEN_CHAR && !(already_guessed[word.charAt(i)-97]))
			{
				charThatnotFound[letterThatNotFount] = word.charAt(i);
				letterThatNotFount++;}

				charInPuzzele[word.charAt(i)-97]=true;

		}

		for (int i=0;i<26;i++){
			if(!already_guessed[i]&&!charInPuzzele[i]){
				char c = (char)(i +97);
				charThatFound[letterThatFount]=c;
				letterThatFount++;
			}
		}



		char c1=charThatnotFound[rn.nextInt(letterThatNotFount)];
		while ((int) c1 <97||(int) c1 > 122)
			c1=charThatnotFound[rn.nextInt(letterThatNotFount)];


		char c2=charThatFound[rn.nextInt(letterThatFount)];
		while ((int) c2 <97||(int) c2 > 122||c2==c1)
			c2=charThatFound[rn.nextInt(letterThatFount)];





		hint[0]= c1>c2 ? c2 : c1;
        hint [1]=c1>c2 ? c1 : c2;
	return hint;
	}

	

	public static char[] mainTemplateSettings(String word, Scanner inputScanner) { // Q - 6
		Random rd = new Random();
		printSelectTemplate();
			int way = inputScanner.nextInt();
			switch (way) {

				case (1):

					printSelectNumberOfHiddenChars();
					boolean [][] allOption = getAllLegalTemplates(word,inputScanner.nextInt());

                    if( allOption.length !=0 ){
						int randomTemplate= rd.nextInt(allOption.length);
                    	return createPuzzleFromTemplate(word,allOption[randomTemplate]);}
                    break;

				case (2):

					printEnterPuzzleTemplate();
					String st = inputScanner.next();
					String [] templteToCreate = st.split(",");
					boolean[] puzle=new boolean[templteToCreate.length];

					for (int i=0;i<templteToCreate.length;i++){
						if(templteToCreate[i].equals("X"))
						puzle[i]=true ;}

					if(checkLegalTemplate(word,puzle))
						return createPuzzleFromTemplate(word,puzle);
			}
		printWrongTemplateParameters();
		inputScanner.nextLine();
		return mainTemplateSettings(word,inputScanner);
	}
	
	public static void mainGame(String word, char[] puzzle, Scanner inputScanner){ // Q - 7
		printGameStageMessage();
		int user_try = 3, numberofhiiden = 0;
		boolean [] char_that_guessed = new boolean[26];

		for(int i = 0; i< puzzle.length;  i++) {
			if(puzzle[i] == HIDDEN_CHAR) {
				user_try++;
				numberofhiiden++;
			}
		}

		while(numberofhiiden > 0 && user_try > 0) {

			printPuzzle(puzzle);
			printEnterYourGuessMessage();
			String s = inputScanner.next();
			char c=s.charAt(0);

			int CorrectGuess = c=='H' ? -1 : applyGuess(c,word,puzzle);
			switch (CorrectGuess){

				case (-1):
					char[] hint = getHint(word,puzzle,char_that_guessed);
					printHint(hint);
					break;

				case (0):
					user_try--;
					printWrongGuess(user_try);
					char_that_guessed[(int)c-97] = true;

					break;

				default:
					char_that_guessed[(int)c-97] = true;
					numberofhiiden -= CorrectGuess;
					user_try--;
					if(numberofhiiden>0)
					printCorrectGuess(user_try);
					if(numberofhiiden==0)
						printWinMessage();


			}


			 if(user_try ==0&&numberofhiiden!=0)
			 	printGameOver();
		}
	}


				


/*************************************************************/
/********************* Don't change this ********************/
/*************************************************************/

	public static void main(String[] args) throws Exception {
		if (args.length < 1){
			throw new Exception("You must specify one argument to this program");
		}
		String wordForPuzzle = args[0].toLowerCase();
		if (wordForPuzzle.length() > 10){
			throw new Exception("The word should not contain more than 10 characters");
		}
		Scanner inputScanner = new Scanner(System.in);
		char[] puzzle = mainTemplateSettings(wordForPuzzle, inputScanner);
		mainGame(wordForPuzzle, puzzle, inputScanner);
		inputScanner.close();
	}


	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printSelectNumberOfHiddenChars(){
		System.out.println("Enter number of hidden characters:");
	}
	public static void printSelectTemplate() {
		System.out.println("Choose a (1) random or (2) manual template:");
	}
	
	public static void printWrongTemplateParameters() {
		System.out.println("Cannot generate puzzle, try again.");
	}
	
	public static void printEnterPuzzleTemplate() {
		System.out.println("Enter your puzzle template:");
	}


	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}


	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printHint(char[] hist){
		System.out.println(String.format("Here's a hint for you: choose either %s or %s.", hist[0] ,hist[1]));

	}
	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle!");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}

}
