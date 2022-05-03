package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.Set;

/* don't change this interface */
public interface IHistogram<T> extends Iterable<T>{
	
	public void addItem(T item);
	public void removeItem(T item) throws IllegalItemException;
	public void addItemKTimes(T item, int k) throws IllegalKValueException;
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException;
	public int getCountForItem(T item);
	public void addAll(Collection<T> items);
	public void clear();
	public Set<T> getItemsSet();
	public void update(IHistogram<T> anotherHistogram);
}
