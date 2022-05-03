package il.ac.tau.cs.sw1.ex8.histogram;
import java.util.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{
	private final HashMap<T, Integer> hm = new HashMap<>();

	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<>(this);
	}

	public HashMap<T, Integer> getMap(){

		return this.hm;
	}

	@Override
	public void addItem(T item) {
		Integer i = hm.get(item);
		hm.put(item, (i==null) ? 1 : i+1);
	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
			int i = getCountForItem(item);
			if(i<1)
			{
				throw new  IllegalItemException();
			}
			hm.put(item,i-1);
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if(k<1){
			throw new  IllegalKValueException(k);
		}
       hm.put(item,getCountForItem(item)+k);
	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		int i=getCountForItem(item);
		if (i==0)
		{
			throw new  IllegalItemException();
		}
		if ( k<1 || k>i )
		{
			throw new  IllegalKValueException(k);
		}
		if(k==i)
		{
			removeItem(item);}
		else
		{
		hm.put(item,getCountForItem(item)-k);}
	}

	@Override
	public int getCountForItem(T item) {
		Integer i=hm.get(item);
		return i==null ? 0 : i ;
		//replace this with the actual returned value
	}

	@Override
	public void addAll(Collection<T> items) {
        for(T item : items)
		addItem(item);
	}

	@Override
	public void clear() {
        hm.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		return hm.keySet();
	}

	@Override
	public void update(IHistogram<T> anotherHistogram) {
		Set<T> itemsSet = anotherHistogram.getItemsSet();

		for (T item : itemsSet) {
			int itemCount = anotherHistogram.getCountForItem(item);
			if (itemCount != 0) {
				try {
					this.addItemKTimes(item, itemCount);
				} catch (IllegalKValueException e) {
					e.printStackTrace();
				}
			}
		}

	}
	}
