package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IllegalItemException;
import il.ac.tau.cs.sw1.ex8.histogram.IllegalKValueException;


public class HashMapHistogramTester {
	public static void main(String[] args) {
		List<Integer> intLst = Arrays.asList(1, 2, 1, 2, 3, 4, 3, 1);
		IHistogram<Integer> intHist = new HashMapHistogram<>();
		for (int i : intLst) {
			intHist.addItem(i);
		}
		if (intHist.getCountForItem(1) != 3) {
			System.out.println("ERROR 1");
		}
		if (intHist.getCountForItem(5) != 0) {
			System.out.println("ERROR 2");
		}
		Iterator<Integer> intHistIt = intHist.iterator();
		List<Integer> tmpList = new ArrayList<Integer>();
		while (intHistIt.hasNext()) {
			tmpList.add(intHistIt.next());
		}
		if (tmpList.get(0) != 1) {
			System.out.println("ERROR 3");
		}
		if (tmpList.size() != 4) {
			System.out.println("ERROR 4");
		}

		IHistogram<String> stringHist = new HashMapHistogram<>();
		IHistogram<String> anotherHist = new HashMapHistogram<>();
		try {
			stringHist.addItemKTimes("bb", 5);
			stringHist.addItemKTimes("aa", 5);
		} catch (IllegalKValueException exp) {
			System.out.println("ERROR 5");
		}
		stringHist.addItem("abc");
		stringHist.addItem("de");
		stringHist.addItem("abc");
		stringHist.addItem("de");
		stringHist.addItem("abc");
		stringHist.addItem("de");
		stringHist.addItem("de");
		if (stringHist.getCountForItem("abc") != 3) {
			System.out.println("ERROR 6");
		}
		try {
			stringHist.removeItem("abba"); // this should throw an exception
			System.out.println("ERROR 7");
		} catch (IllegalItemException exp) {

		}

		try {
			stringHist.removeItemKTimes("de", 2);
		} catch (Exception exp) {
			System.out.println("ERROR 8");
		}
		try {
			stringHist.removeItemKTimes("abc", -3);
			System.out.println("ERROR 9");
		} catch (Exception exp) {
			
		}
		if (stringHist.getCountForItem("de") != 2) {
			System.out.println("ERROR 10");
		}
		try {
			stringHist.addItemKTimes("de", 2);
		} catch (IllegalKValueException exp) {
			System.out.println("ERROR 11");
		}

		Iterator<String> it = stringHist.iterator();
		/*
		 * the order of the returned items should be: "aa", "bb", "de", "abc" aa
		 * " and "bb" both appear 5 times, so in this case we sort by the
		 * natural order of the elements "aa" and "bb". This is why "aa" should
		 * appear before "bb"
		 */
		if (!it.next().equals("aa")) {
			System.out.println("ERROR 12");
		}
		if (!it.next().equals("bb")) {
			System.out.println("ERROR 13");
		}
		if (!it.next().equals("de")) {
			System.out.println("ERROR 14");
		}

		anotherHist.addAll(Arrays.asList("abc", "ff"));
		anotherHist.update(stringHist);
		if (anotherHist.getCountForItem("abc") != 4) {
			System.out.println("ERROR 15");
		}

		if (anotherHist.getCountForItem("de") != 4) {
			System.out.println("ERROR 16");
		}

		System.out.println("done!");
	}

}
