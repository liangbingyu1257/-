package main;

import java.util.HashSet;


public class Money {
// 50 yuan, one 20 yuan, two 5 yuan bills and three 1 yuan coins
//
     HashSet<Integer> store = new HashSet<>();
     public void init(){
        store.add(0);
        int[] m1 = {0, 50};
        int[] m2 = {0, 20};
        int[] m3 = {0, 5, 10};
        int[] m4 = {0, 1, 2, 3};

        addNew(m1);
        addNew(m2);
        addNew(m3);
        addNew(m4);
    }
     void addNew(int[] m){
        HashSet<Integer> temp = new HashSet<>();
        for(Integer i:store){
            for(int j = 0; j < m.length; j++){
                temp.add(i+m[j]);
                temp.add(i);
            }
        }
        store = temp;
    }
     public boolean Judge(int aim){
        return store.contains(aim);
    }
 
}
