package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.Collections;
import java.util.Map;



/*********************************************************************************
 *   This is a complete implementation.                                          *
 *   you can add more code or alter the existing code, but                       *
 *   keep in mind that this class is designed for the purpose of this exercise   *
 *   you you should be able to use it just as it is                              *
 *********************************************************************************/

class RankedWord{
	
	public static enum rankType {average, min, max};

	
	private String word;
	private Map<String, Integer> ranksForFile;
	private int average;
	private int min;
	private int max;
	
	public RankedWord(String word, Map<String, Integer> ranksForFiles){
		this.word = word;
		this.ranksForFile = ranksForFiles;
		this.min = Collections.min(ranksForFile.values()); // retrieve the minimal value in a collection
		this.max = Collections.max(ranksForFile.values()); // retrieve the maxinum value in a collection
		int sum = 0;
		for (Integer rank : ranksForFile.values()){
			sum += rank;
		}
		this.average = (int)Math.round(((double)sum)/ranksForFile.size());
	}

	public String getWord() {
		return word;
	}

	public Map<String, Integer> getRanksForFile() {
		return ranksForFile;
	}

	public int getRankByType(rankType rType){
		// This is how you use an enum in a switch/case block
		switch(rType){
		case average:
			return average;
		case min:
			return min;
		default: //case max
			return max;
		}
	}

	@Override
	public String toString() {
		return "RankedWord [word=" + word + ", ranksForFile=" + ranksForFile + ", average=" + average + ", min="
				+ min + ", max=" + max + "]";
	}
}
