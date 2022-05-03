package il.ac.tau.cs.sw1.ex7;
import java.util.*;

public class FractionalKnapSack implements Greedy<FractionalKnapSack.Item> {
    int capacity;
    List<Item> lst;

    FractionalKnapSack(int c, List<Item> lst1){
        capacity = c;
        lst = lst1;
    }

    public static class Item {
        double weight, value;
        Item(double w, double v) {
            weight = w;
            value = v;
        }
        public double geteVal(){
            return weight/value;
        }
        public double getWeight()
        {return weight;}

        public double getValue()
        {return value;}
        @Override
        public String toString() {
            return "{" + "weight=" + weight + ", value=" + value + '}';
        }
    }

    @Override
    public Iterator<Item> selection() {
        lst.sort(new CustomComparator());
        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Item> candidates_lst, Item element) {
        return weightSum(candidates_lst) < capacity ;
    }

    @Override
    public void assign(List<Item> candidates_lst, Item element) {
        double s = weightSum(candidates_lst);
      if(s+element.getWeight() <= capacity)
      {
          candidates_lst.add(element);
      }
      else {
          double d = element.getValue();
          candidates_lst.add(new Item(capacity-s,d));
      }
    }

    @Override
    public boolean solution(List<Item> candidates_lst) {

        return weightSum(candidates_lst)==capacity || lst.isEmpty() ;
    }

    public class CustomComparator implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            return Double.compare(o1.geteVal(), o2.geteVal());
        }
    }

    private double weightSum(List<Item> lst){
        double sum = 0;
                for (Item i  : lst){
            sum += i.getWeight();
        }
        return sum;
    }
}
