package il.ac.tau.cs.sw1.ex7;
import java.util.*;


public class Graph implements Greedy<Graph.Edge>{
    List<Edge> lst; //Graph is represented in Edge-List. It is undirected. Assumed to be connected.
    int n; //nodes are in [0,...,n-1]
    int[] _visited;
    private final int A=1;
    private final int B=2;
    private static Boolean A_TURN=true;


    Graph(int n1, List<Edge> lst1){
        lst = lst1;
        n = n1;
        _visited=new int[n+1];
    }


    public static class Edge {
        int node1, node2, source;
        double weight;

        Edge(int n1, int n2, double w) {
            node1 = n1;
            node2 = n2;
            weight = w;
            source=-1;
        }
        public int getnode2(){
            return node2;
        }

        public int getnode1()
        {
            return node1;
        }

        public double weight()
        {return weight;}
        @Override
        public String toString() {
            return "{" + "(" + node1 + "," + node2 + "), weight=" + weight + '}';
        }
    }

    @Override
    public Iterator<Edge> selection() {
        lst.sort(new Graph.CustomComparator());
        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Edge> candidates_lst, Edge element) {
        int _node1=_visited[element.node1];
        int _node2=_visited[element.node2];
        if(n>1)
        {
            return (_node1 == 0 || _node1 != _node2);
        }
        return _node1 != _node2;
    }

    @Override
    public void assign(List<Edge> candidates_lst, Edge element) {
        candidates_lst.add(element);
        int _node1=element.getnode1();
        int _node2=element.getnode2();
        int[] S= {_visited[_node1] ,_visited[_node2]};

        if((Arrays.equals(S, new int[]{0, 0})))
        {
            S = A_TURN ?  new int[]{0, A} :  new int[]{0, B};
            A_TURN ^= true;
        }

        if (Arrays.equals(S, new int[]{0, A}) || Arrays.equals(S, new int[]{A, 0})) {
            _visited[_node2] = A;
            _visited[_node1] = A;
        } else
            if (Arrays.equals(S, new int[]{B, 0}) || Arrays.equals(S, new int[]{0, B})) {
            _visited[_node2] = B;
            _visited[_node1] = B;
        }
      n--;
    }


    @Override
    public boolean solution(List<Edge> candidates_lst) {
        return n==0;
    }



    public class CustomComparator implements Comparator<Graph.Edge> {
        @Override
        public int compare(Graph.Edge o1, Graph.Edge o2) {
            int n= Double.compare(o1.weight(), o2.weight());
            if(n==0){
                n=Integer.compare(o1.getnode1(),o2.getnode1());
            }
            return n != 0 ? n : Integer.compare(o1.getnode2(),o2.getnode2()) ;
        }
    }
}


