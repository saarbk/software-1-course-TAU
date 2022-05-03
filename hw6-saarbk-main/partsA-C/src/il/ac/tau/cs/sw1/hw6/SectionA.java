package il.ac.tau.cs.sw1.hw6;

import java.util.LinkedList;

/**
 *  @inv !isEmpty() implies top() != null   //  this means no null objects are allowed to be returned from top()
 */
public class SectionA {
	
	 private final LinkedList<Object> elements = new LinkedList<Object>();
	
	    /**
	     *  @post !isEmpty()
	     *  @post top() == o
	     */
	 public void push(Object o)
	    {
	        elements.add(o);
	    }
	    /**
	     *  @pre !isEmpty()
	     *  @post @return == top()@pre
	     */
	  public Object pop()
	    {
	        final Object popped = top();
	        elements.removeLast();
	        return popped;
	    }
	    /**
	     *  @pre !isEmpty()
	     *  @post @return != null
	     */
	  public Object top()
	    {
		  Object top;
		  do{
	    	  top = elements.getLast();
	      }
	      while (top == null);
		  return top;
	    }
	   
	  /**
	     *  
	     *  @post @return == true iff elements.size() > 0
	     */
	    public boolean isEmpty()
	    {
	        return elements.size() == 0;
	    }
	
}
