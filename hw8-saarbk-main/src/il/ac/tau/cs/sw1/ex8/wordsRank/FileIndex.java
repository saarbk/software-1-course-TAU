package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	public static final int UNRANKED_CONST = 30;
	private HashMap<String, HashMapHistogram<String>> folderMap;
	private  HashMap<String, HashMap<String, Integer>> Directory;
	private  HashMap<String, RankedWord> rankedWordMap;
	private  ArrayList<String> foundFile;
	private  Iterator<String> iter;
	private  List<String> AverageRankList,MaxRankList,MinRankList;
	boolean findAverageRankList,findMaxRankList,findMinRankList;
	private List<RankedWord> RankedWordList;

	public FileIndex() {
		foundFile= new ArrayList<>();
		folderMap=new HashMap<>();
		rankedWordMap=new HashMap<>();
		Directory=new HashMap<>();
	}


	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */
  	public void indexDirectory(String folderPath) {

		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		List<String> wordsList ;
        Set <String> foundsWorld = new HashSet<String>();

		for (File file : listFiles) {
			if (file.isFile()) {
				HashMapHistogram<String> tempHistogram = new HashMapHistogram<>();
				try {
				wordsList = FileUtils.readAllTokens(file);
				tempHistogram.addAll(wordsList); }
				catch (IOException e)
			  { e.printStackTrace();
		    	}

				int i=1;
				String fileName= file.getName();
				HashMap<String, Integer> t = new HashMap<>();
				iter = tempHistogram.iterator();

				while (iter.hasNext()) {
					t.put(iter.next(), i++);
				}
				foundFile.add(fileName);
				folderMap.put(fileName,tempHistogram);
				Directory.put(fileName, t);
				foundsWorld.addAll(tempHistogram.getItemsSet());
			}
			createRankWorldMap(foundsWorld);
		}
  	}

  	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException{
		if (!containFile(filename))
		{throw new FileIndexException(filename+" : not found");}
		return folderMap.get(filename).getCountForItem(word.toLowerCase());
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException{
		word=word.toLowerCase();
		if (!containFile(filename))
			throw new FileIndexException(filename+" : not found");
		HashMap<String, Integer> hm = Directory.get(filename);
		return containWordInHashMap(hm,word) ? hm.get(word) : WordNotFound(hm) ;
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word){
		int cnt=0;
		if (rankedWordMap.containsKey(word)){
			return rankedWordMap.get(word).getRankByType(RankedWord.rankType.average);
		}
			for(String fileName : foundFile)
			{
				cnt+=WordNotFound(Directory.get(fileName));
			}
		return (int) Math.round(((double)cnt)/foundFile.size());
	}
	
	
	public List<String> getWordsWithAverageRankSmallerThanK(int k){
	return findAverageRankList ? AverageRankList.subList(0,k+1) : createAverageRankList().subList(0,k+1);
	}

	public List<String> getWordsWithMinRankSmallerThanK(int k){
		return findMinRankList ? MinRankList.subList(0,k+1) :  createMinRankList().subList(0,k+1) ;
	}
	
	public List<String> getWordsWithMaxRankSmallerThanK(int k){
		return findMaxRankList ? MaxRankList.subList(0,k+1) : createMaxRankList().subList(0,k+1); //replace this with the actual returned value
	}

	private boolean containFile(String fileName){
		return foundFile.contains(fileName);
	}
	private boolean containWordInHashMap( HashMap<String, Integer> t,String word){
		return t.containsKey(word);
	}

	private int WordNotFound(HashMap<String, Integer> t){
		return t.size()+UNRANKED_CONST;
	}

	private List<String> createAverageRankList()
	{
		boolean findAverageRankList=true;
		RankedWordList.sort(new RankedWordComparator(rankType.average));
		return RankedWordTolist(RankedWordList);
	}


	private List<String> createMaxRankList()
	{
		boolean findMaxRankList=true;
		RankedWordList.sort(new RankedWordComparator(rankType.max));
		return RankedWordTolist(RankedWordList);
	}


	private List<String> createMinRankList()
	{
		boolean findMinRankList=true;
		RankedWordList.sort(new RankedWordComparator(rankType.min));
		return RankedWordTolist(RankedWordList);
	}


	private List<String> RankedWordTolist(List<RankedWord> r){
		List <String> st = new ArrayList<>();
		for(RankedWord rw : r){
			st.add(rw.getWord());
		}
		return st;
	}

	private void createRankWorldMap(Set<String> foundsWorld) {
		for (String fileName : foundsWorld) {
			HashMap<String, Integer> t = new HashMap<>();
			for (String filName : foundFile)
			{
				if (Directory.get(filName).containsKey(fileName)) {
					t.put(filName,Directory.get(filName).get(fileName));
				}
			}
			rankedWordMap.put(fileName, new RankedWord(fileName,t));
		}
		RankedWordList = new ArrayList<RankedWord>(rankedWordMap.values());

	}
}
