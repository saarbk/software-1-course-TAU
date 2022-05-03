package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.Comparator;

import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

class RankedWordComparator implements Comparator<RankedWord>{

	private final rankType typeToCompare;

	@Override
	public int compare(RankedWord word1, RankedWord word2) {
		return Integer.compare(word1.getRankByType(typeToCompare), word2.getRankByType(typeToCompare));
	}
	public RankedWordComparator(rankType t) {
		this.typeToCompare = t;
	}

}
