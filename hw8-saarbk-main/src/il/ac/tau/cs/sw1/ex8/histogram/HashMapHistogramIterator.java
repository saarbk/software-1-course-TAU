package il.ac.tau.cs.sw1.ex8.histogram;
import java.util.*;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{
	HashMap<T, Integer> map;
	private final Iterator<T> iterator;

	public HashMapHistogramIterator(HashMapHistogram<T> hashMapHistogram) {
		map = hashMapHistogram.getMap();
		ArrayList<T> entryList = new ArrayList<>(map.keySet());
		entryList.sort(new HashMapHistogramComparator(map));
		this.iterator = entryList.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext(); //replace this with the actual returned value
	}

	@Override
	public T next() {
		return iterator.next(); //replace this with the actual returned value
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}
}

   class HashMapHistogramComparator <T extends Comparable<T>> implements Comparator<T> {
	   HashMap<T, Integer> map;

	   public HashMapHistogramComparator(HashMap<T, Integer> Hmap){
	   	this.map = Hmap;
	   }

	   @Override
	   public int compare(T item1,T item2)
	  {
		  int item1int = map.get(item1);
		  int item2int = map.get(item2);
		  if (item1int == item2int) {
			  return item1.compareTo(item2);
		  }

		  return Integer.compare(item2int, item1int);
	 }
	}