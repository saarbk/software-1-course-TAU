package il.ac.tau.cs.sw1.ex7;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Coins implements Greedy<Integer>{
    int amount;

    Coins(int a){
        amount = a;
    }

    @Override
    public Iterator<Integer> selection() {
        List<Integer> lst = Arrays.asList(10,5,2,1);
        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Integer> lst, Integer element) {
        double s=sum(lst);

        return sum(lst) + element <= amount;
    }

    @Override
    public void assign(List<Integer> lst, Integer element) {
        double s=sum(lst);
        while (sum(lst) + element <= amount)
            lst.add(element);
    }

    @Override
    public boolean solution(List<Integer> lst) {
        return sum(lst) == amount;
    }

    private Integer sum(List<Integer> lst){
        Integer sum = 0;
        for (Integer element : lst){
            sum += element;
        }
        return sum;
    }
}
