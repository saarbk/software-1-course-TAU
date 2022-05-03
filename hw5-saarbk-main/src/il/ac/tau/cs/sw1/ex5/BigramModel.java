package il.ac.tau.cs.sw1.ex5;


import java.io.IOException;
import java.io.*;
import java.util.Arrays;


public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	public static final int[] BOTH_ELEMENT_NOT_FOUND={ELEMENT_NOT_FOUND,ELEMENT_NOT_FOUND};
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
	}
	
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		int cnt = 0;
		String[] currLine;
		String[] maxVocSizeTempArr = new String[MAX_VOCABULARY_SIZE];
		boolean hasNum=false;

		BufferedReader br = readFile(new File(fileName));
		String nextLine = br.readLine();

		while (nextLine != null && cnt<MAX_VOCABULARY_SIZE ) {
			currLine= nextLine.split("\\s+");
			for (int i=0;i<currLine.length;i++)
			{
				 String word = currLine[i].toLowerCase();
                 String word_Mapper = wordMapper(word);
                 switch (word_Mapper) {

					 case ("num_only"):
						 if(!hasNum)
						 {
							 hasNum=true;
							 maxVocSizeTempArr[cnt]=SOME_NUM;
							 cnt++;
						 }
					 	break;


					 case ("legal_word"):
						 if(isNewWord(word,maxVocSizeTempArr)==ELEMENT_NOT_FOUND)
						 {  maxVocSizeTempArr[cnt]=word;
							 cnt++; }
				 }
			}
			nextLine = br.readLine();
		}
		br.close();
		return Arrays.copyOf(maxVocSizeTempArr, cnt);
	}

	private static String wordMapper(String st)
	{
		int numcount=0;
		Boolean hasLetter=false;
		char[] char_Arr = st.toCharArray();
		int size=char_Arr.length;
        for(int i =0;i<size;i++)
		{
			if(Character.isDigit(char_Arr[i]))
				numcount++;
			if(Character.isLetter(char_Arr[i]))
				hasLetter=true;
		}
        if (numcount==size)
        	return "num_only";

	return hasLetter ? "legal_word":"illegal_word";}

	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		int size =vocabulary.length;
		int[][] CountsArray= new int[size][size];
		int word1_index=ELEMENT_NOT_FOUND,word2_index;

		BufferedReader br = readFile(new File(fileName));
		String nextLine = br.readLine();

		while (nextLine != null ) {
			String[] currLine= nextLine.split("\\s+");

			for (int i=0;i<currLine.length-1;i++){
				int[] indexArr = findXandY(currLine[i].toLowerCase(),currLine[i+1].toLowerCase(),vocabulary,word1_index);
				word1_index=indexArr[0];
				word2_index=indexArr[1];
				if(word1_index!=ELEMENT_NOT_FOUND && word2_index!=ELEMENT_NOT_FOUND){
					CountsArray[word1_index][word2_index]+=1;
				}
				word1_index=word2_index;
			}

			word1_index=ELEMENT_NOT_FOUND;
			word2_index=ELEMENT_NOT_FOUND;


		nextLine = br.readLine();
		}
		br.close();
		return CountsArray;

	}
	
	
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		writeVocFile(fileName);
		writeCntFile(fileName);
	}
	
	
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		loadVocFile(fileName);
		loadCountFile( fileName);

	}

	
	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: word is in lowercase
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		return isNewWord( word,mVocabulary);
	}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
        int word1_i=getWordIndex(word1);
		int word2_i=getWordIndex(word2);
		return word1_i != ELEMENT_NOT_FOUND && word2_i != ELEMENT_NOT_FOUND ?  mBigramCounts[word1_i][word2_i]  : 0;
	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		int word_i=getWordIndex(word), max=0, indexToReturn=0,size=mBigramCounts[word_i].length;
		for (int i=1 ; i < size ; i++)
		{
			//                                                                       ive no idea why its working
			//                                                                               ¯\_(ツ)_/¯
			max =  (!(mBigramCounts[word_i][indexToReturn] > mBigramCounts[word_i][i])) ? indexToReturn= i : mBigramCounts[word_i][indexToReturn] ;
		}
		return max > 0 ||  indexToReturn==size-1 ? mVocabulary[indexToReturn] : null;
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
        if(sentence.isEmpty())
        	return true;

		String[] str_arr = sentence.toLowerCase().split(" ");
		int size =str_arr.length;
		if(size==1)
			return getWordIndex(sentence)!=ELEMENT_NOT_FOUND;

		for (int i=0;i<size-1;i++)
		{
			if(getBigramCount(str_arr[i],str_arr[i+1])==0)
				return false;
		}
		return true;
	}
	
	
	
	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calcluates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9


	        if (arr1.length!=arr2.length|| arr1.length==0||Arrays.equals(arr1, new int[arr1.length])|| Arrays.equals(arr2, new int[arr2.length])) {
			return ELEMENT_NOT_FOUND;
		}
		double summ_ai_bi = 0,sum_ai = 0,sum_bi=0;

		for (int i = 0; i < arr1.length; i++) {
			int j = arr1[i],k = arr2[i];
			summ_ai_bi += (j * k);
			sum_ai += Math.pow(j, 2);
			sum_bi += Math.pow(k, 2);
		}
		return (summ_ai_bi /(Math.sqrt(sum_ai) * Math.sqrt(sum_bi)));
	}

	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10

		int word_index=isNewWord(word,mVocabulary);
		int word_i=getWordIndex(word), indexToReturn=ELEMENT_NOT_FOUND,size=mBigramCounts[word_i].length;
		double max=0;
		int[] word_arr=mBigramCounts[word_index];
		for (int i=0 ; i<size ; i++ )
		{
			double temp = calcCosineSim(word_arr, mBigramCounts[i]);

			if(temp>max&&word_index!=i)
			{
				indexToReturn=i;
				max=temp;
			}
		}
		return mVocabulary[indexToReturn];
	}





	/*
	 * @pre: word is a String
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the number of word's occurrences in the text.
	 * ??????????????????????????????????
	 */
	public int getWordCount(String word){ //  Q - 11
		// replace with your code
		return 0;
	}




	private BufferedReader readFile (File filePath) throws FileNotFoundException {
		FileReader fr = new FileReader(filePath);
		return new BufferedReader(fr);
	}

	private int isNewWord( String word,String[] arr) {
		int i=0;
		while (i<arr.length&&arr[i]!=null){
			if  (arr[i].equals(word))
				return i;

			i++;
		}
		return ELEMENT_NOT_FOUND;
	}

	private int[] findXandY( String x,String y,String[] arr,int x_index) {
		int i=0,cnt=0;
		int[] index=BOTH_ELEMENT_NOT_FOUND;

		if(x_index!=ELEMENT_NOT_FOUND)
		{
			return new int[]{x_index, isNewWord(y, arr)};
		}
		while ( i<arr.length && cnt<2 ){

			if (arr[i].equals(x)) {
				index[0]=i;
				cnt++;
			}

			if (arr[i].equals(y)) {
				index[1]=i;
				cnt++;
			}
			i++;
		}
		return index; }

	private BufferedWriter writeFile(File filePath) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		return new BufferedWriter(fw);
	}

	private void writeVocFile(String filePath) throws IOException {
		BufferedWriter bw = writeFile(new File(filePath + VOC_FILE_SUFFIX));
		bw.write(mVocabulary.length + " words");
		int i=0;

		    do {
				bw.newLine();
				bw.write(i + "," + mVocabulary[i]);
				i++;
			}while(i < mVocabulary.length);

		bw.close();
	}

	private void writeCntFile(String filePath) throws IOException {
		BufferedWriter wBuffer = writeFile(new File(filePath + COUNTS_FILE_SUFFIX));
		int size =mBigramCounts.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				if (mBigramCounts[i][j] != 0) {
					wBuffer.write( i + "," + j + ":" + mBigramCounts[i][j]);
					wBuffer.newLine();
				}
			}

		}
		wBuffer.close();
	}


	private void loadVocFile(String fileName) throws IOException {

		BufferedReader br = readFile (new File(fileName + VOC_FILE_SUFFIX));
		String line;
		String [] temp;

		mVocabulary  = new String[br.readLine().charAt(0)-'0'];
		while ((line = br.readLine()) != null) {
			temp=line.split(",");
			mVocabulary[Integer.parseInt(temp[0])]=temp[1].toLowerCase();
		}
		br.close();
	}

	private void loadCountFile(String fileName) throws IOException {
		int[][] mBigramCounts = new int[mVocabulary.length][mVocabulary.length];
		BufferedReader br = readFile (new File(fileName + COUNTS_FILE_SUFFIX));
		char [] temp;
		String line;
		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			temp=line.toCharArray();
			mBigramCounts[temp[0]-'0'][temp[2]-'0'] = temp[4]-'0';
		}
		br.close();
	}


}
