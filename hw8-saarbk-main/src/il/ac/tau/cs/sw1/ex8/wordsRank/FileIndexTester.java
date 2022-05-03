package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.List;

import il.ac.tau.cs.sw1.ex8.AbstractTester;

public class FileIndexTester extends AbstractTester {
	
	public static final String INPUT_FOLDER = "./resources/hw8/input";

	public static void main(String[] args){
		testFileIndex();
	}
	
	public static void testFileIndex(){
		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);
		try {
			if (fIndex.getCountInFile("rocky1.txt", "Rocky") != 5) {
				System.out.println(fIndex.getCountInFile("rocky1.txt", "Rocky"));
				printErrorNum(1);
			}
			if (fIndex.getRankForWordInFile("rocky3.txt", "and") != 1){
				printErrorNum(2);
			}

		} 
		catch (FileIndexException e) {
			printErrorNum(3);
		}
		
		try{
			//"revolution" doesn't appear at all in the documents, "doctor" appears in rocky3.txt
			int unknownWordRankInFile1 = fIndex.getRankForWordInFile("rocky1.txt", "revolution");
			int unknownWordRankInFile2 = fIndex.getRankForWordInFile("rocky2.txt", "revolution");
			if (unknownWordRankInFile1 != fIndex.getRankForWordInFile("rocky1.txt", "doctor")){
				//a word that's not exist in the file
				printErrorNum(4);
			}
			if (unknownWordRankInFile1 >= unknownWordRankInFile2){
				printErrorNum(5);
			}
			if (fIndex.getRankForWordInFile("rocky3.txt", "revolution") - fIndex.getRankForWordInFile("rocky3.txt", "doctor") < FileIndex.UNRANKED_CONST){
				printErrorNum(6);
			}
		}
		catch (FileIndexException e) {
			printErrorNum(7);
		}
		
		try{
			fIndex.getRankForWordInFile("help.txt", "rocky"); //non existing file
			printErrorNum(8);
		}
		catch (FileIndexException e) {
		}
		
		//in rocky1.txt: rank = 1, in rocky2.txt: rank = 4, in rocky3.txt: rank = 2
		if (fIndex.getAverageRankForWord("rocky") != Math.round((1+4+2)/3.)){
			printErrorNum(9);
		}
		
		List<String> topByMin = fIndex.getWordsWithMinRankSmallerThanK(2);
		//RankedWord [word=his, ranksForFile={rocky2.txt=1, rocky1.txt=23, rocky3.txt=31}, average=18, min=1, max=31]
		//RankedWord [word=rocky, ranksForFile={rocky2.txt=4, rocky1.txt=1, rocky3.txt=2}, average=2, min=1, max=4]
		//RankedWord [word=and, ranksForFile={rocky2.txt=2, rocky1.txt=11, rocky3.txt=1}, average=5, min=1, max=11] 
		
		if (!topByMin.contains("rocky") || !topByMin.contains("and") || !topByMin.contains("his") || topByMin.size() > 3){
			printErrorNum(10);
		}
		
		//highest rank for rocky is 4
		List<String> topByMax = fIndex.getWordsWithMaxRankSmallerThanK(6);
		if (!topByMax.get(0).equals("rocky")){
			printErrorNum(11);
		}
//		
		System.out.println("finished fileIndex Test!");

	}
	
	
}
