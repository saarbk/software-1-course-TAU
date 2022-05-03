package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.HashMap;
import java.util.Map;

import il.ac.tau.cs.sw1.ex8.AbstractTester;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;


public class RankedWordComparatorTester extends AbstractTester{
	public static void main(String[] args){
		testRankedWordComparator();
	}
	

	public static void testRankedWordComparator(){
		Map<String, Integer> ranks1 = new HashMap<>();
		ranks1.put("file1", 1);
		ranks1.put("file2", 120);
		ranks1.put("file3", 8);
		RankedWord rWord1 = new RankedWord("Julia", ranks1);
		
		Map<String, Integer> ranks2 = new HashMap<>();
		ranks2.put("file1", 70);
		ranks2.put("file2", 50);
		ranks2.put("file3", 3);
		RankedWord rWord2 = new RankedWord("Eleanor", ranks2);
		RankedWordComparator minComp = new RankedWordComparator(rankType.min);
		RankedWordComparator maxComp = new RankedWordComparator(rankType.max);
		RankedWordComparator averageComp = new RankedWordComparator(rankType.average);

		if (minComp.compare(rWord1, rWord2) >= 0){
			printErrorNum(1);
		}
		if (maxComp.compare(rWord1, rWord2) <= 0){
			printErrorNum(2);
		}
		if (averageComp.compare(rWord1, rWord2) <= 0){
			printErrorNum(3);
		}
		System.out.println("finished RankedWord Test!");
	}

}
