/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author bowen
 */
public class Transformation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Set<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(new Integer[] {1, 5, 6}));
        //System.out.println(set.contains(Arrays.asList(new Integer[] {1, 5, 6})));
        
        List<Integer> f1 = Arrays.asList(new Integer[] {1, 2, 1, 3});
        List<Integer> f2 = Arrays.asList(new Integer[] {1, 2, 4, 3});
        
        //System.out.println(comp(f1, f2));
        
        
        Set<List<Integer>> functionSet = new LinkedHashSet<>();
        functionSet.add(Arrays.asList(new Integer[] {3, 1, 3, 4}));
        functionSet.add(Arrays.asList(new Integer[] {3, 4, 2, 1}));
        
        System.out.println(composition(functionSet, Arrays.asList(new Integer[] {1, 1, 1, 2})));
        
        
        functionSet = new LinkedHashSet<>();
        functionSet.add(Arrays.asList(new Integer[] {1, 2, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12}));
        functionSet.add(Arrays.asList(new Integer[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1}));
        functionSet.add(Arrays.asList(new Integer[] {2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));
        
        System.out.println(composition(functionSet, Arrays.asList(new Integer[] {2, 1, 4, 3, 5, 7, 7, 7, 7, 7, 7, 7})));
        
        
    }
    
    public static boolean composition(Set<List<Integer>> functionSet, List<Integer> function) {
        
        Set<List<Integer>> newFunctionSet = new LinkedHashSet<>(functionSet);
        Set<List<Integer>> newFunctionSetPrime = new LinkedHashSet<>(functionSet);
        
        while(true) {
            List<Integer>[] listArray = newFunctionSetPrime.toArray(new List[] {});
            for (int i=0; i<listArray.length; i++) {
                for (int j=0; j<listArray.length; j++) {
                    List<Integer> newFunction = comp(listArray[i], listArray[j]);
                    newFunctionSetPrime.add(newFunction);
                }
            }
            
            if (newFunctionSet.equals(newFunctionSetPrime)) {
                newFunctionSet = new LinkedHashSet<>(newFunctionSetPrime);
                break;
            }
            
            newFunctionSet = new LinkedHashSet<>(newFunctionSetPrime);
            
        }
        
        
        return newFunctionSet.contains(function);
    }
    
    //make a tree relating functions with possible combinations that leads to that function
    //then use A* search
    
    //prune branches that are missing a number (since transformations can't create new entries)
    //prune branches that are not within a cycle or not within reach
    //make method that takes in functions, and then two points, and asks if they are in reach of eachother
    
    public static List<Integer> comp(List<Integer> f1, List<Integer> f2) {
        List<Integer> f3 = new LinkedList<>();
        for (int i=0; i<f1.size(); i++) {
            f3.add(f2.get(f1.get(i) - 1));
        }
        return f3;
    }
    
    
}
