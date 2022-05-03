package il.ac.tau.cs.sw1.ex7;

import java.util.*;


public interface Greedy<T>{

    /**
     * A selection function, which chooses the best candidate to be added to the solution
     */
    Iterator<T> selection();

    /**
     * A feasibility function, that is used to determine if a candidate can be used to contribute to a solution
     */
    boolean feasibility(List<T> lst, T element);

    /**
     * An assign function, which assigns a value to a solution, or a partial solution
     */
    void assign(List<T> lst, T element);

    /**
     * A solution function, which will indicate when we have discovered a complete solution
     */
    boolean solution(List<T> lst);

    /**
     * The Greedy Algorithm
     */
    default List<T> greedyAlgorithm(){
        List<T> l = new ArrayList<>();
        Iterator<T> _Iterator = selection();
        T temp;
        do
        {
            temp=_Iterator.next();
            if(feasibility(l,temp)) {
                assign(l,temp);
            }
            if (solution(l))
            {return l;
            }
        }while (_Iterator.hasNext() && temp!=null);
        return l;
    }
}
