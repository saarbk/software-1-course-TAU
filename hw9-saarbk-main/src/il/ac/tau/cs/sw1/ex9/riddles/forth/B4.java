package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class B4 implements Iterator<String>{
    String[] strings;
    int k,c;
    List<String> listS;
    public B4(String[] strings, int k) {
        this.strings = strings;
        this.k = k;
        listS = new ArrayList<String>();
        for(int j = 0; j < k; j++) {
            for (int i = 0; i < strings.length; i++) {
                listS.add(i +strings.length*j, strings[i]); } } }
    @Override
    public boolean hasNext() {
        if (c < listS.size()) {
            return true;
        }
        return false;
    }
    @Override
    public String next() {
        return listS.get(c++);
    }
}