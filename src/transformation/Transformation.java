/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
        
        
        Set<Function> functionSet = new LinkedHashSet<>();
        functionSet.add(new Function(3, 1, 3, 4));
        functionSet.add(new Function(3, 4, 2, 1));
        
        System.out.println(composition(functionSet, new Function(1, 1, 1, 2)));
        
        
        functionSet = new LinkedHashSet<>();
        functionSet.add(new Function(1, 2, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        functionSet.add(new Function(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1));
        functionSet.add(new Function(2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        
        System.out.println(composition(functionSet, new Function(2, 1, 4, 3, 5, 7, 7, 7, 7, 7, 7, 7)));
        
        
    }
    
    public static boolean composition(Set<Function> functionSet, Function function) {
        
        if (functionSet.size() < 1) {
            return false;
        }
        
        Iterator<Function> it = functionSet.iterator();
        
        while (it.hasNext()) {
            if (it.next().equals(function)) {
                return true;
            }
        }
        
        functionSet.iterator().forEachRemaining(list -> {System.out.println(list);});
        
        
        Set<Function> functionSetNotChecked = new LinkedHashSet<>(functionSet);
        Set<Function> functionSetChecked = new LinkedHashSet<>();
        
        while(true) {
            Set<Function> newFunctionSetNotChecked = new LinkedHashSet<>();
            
            Function[] notCheckedArray = functionSetNotChecked.toArray(new Function[] {});
            Function[] checkedArray = functionSetChecked.toArray(new Function[] {});
            for (int i=0; i<notCheckedArray.length; i++) {
                for (int j=0; j<notCheckedArray.length; j++) {
                    Function newFunction = Function.composition(notCheckedArray[i], notCheckedArray[j]);
                    if (newFunction.equals(function)) {
                        System.out.println(newFunction);
                        return true;
                    }
                    if (!functionSetNotChecked.contains(newFunction)) {
                        newFunctionSetNotChecked.add(newFunction);
                        System.out.println(newFunction);
                    }
                    
                }
                for (int j=0; j<checkedArray.length; j++) {
                    Function newFunction = Function.composition(notCheckedArray[i], checkedArray[j]);
                    if (newFunction.equals(function)) {
                        System.out.println(newFunction);
                        return true;
                    }
                    if (!functionSetNotChecked.contains(newFunction)) {
                        newFunctionSetNotChecked.add(newFunction);
                        System.out.println(newFunction);
                    }
                    
                    Function newFunctionRev = Function.composition(checkedArray[j], notCheckedArray[i]);
                    if (newFunctionRev.equals(function)) {
                        System.out.println(newFunction);
                        return true;
                    }
                    if (!functionSetNotChecked.contains(newFunctionRev)) {
                        newFunctionSetNotChecked.add(newFunctionRev);
                        System.out.println(newFunctionRev);
                    }
                }
                functionSetChecked.add(notCheckedArray[i]); //Finished checking that function
            }
            
            functionSetNotChecked = newFunctionSetNotChecked;
            
            if (functionSetNotChecked.size() < 1) {
                break;
            }
            
            
        }
        
        
        
        /*
        Set<Function> newFunctionSet = new LinkedHashSet<>(functionSet);
        Set<Function> newFunctionSetPrime = new LinkedHashSet<>(functionSet);
        
        while(true) {
            Function[] listArray = newFunctionSetPrime.toArray(new Function[] {});
            for (int i=0; i<listArray.length; i++) {
                for (int j=0; j<listArray.length; j++) {
                    Function newFunction = Function.composition(listArray[i], listArray[j]);
                    if (!newFunctionSetPrime.contains(newFunction)) {
                        System.out.println(newFunction);
                    }
                    if (newFunction.equals(function)) {
                        return true;
                    }
                    newFunctionSetPrime.add(newFunction);
                    
                }
            }
            
            if (newFunctionSet.equals(newFunctionSetPrime)) {
                newFunctionSet = new LinkedHashSet<>(newFunctionSetPrime);
                break;
            }
            
            newFunctionSet = new LinkedHashSet<>(newFunctionSetPrime);
            
        }
        */
        
        return functionSetChecked.contains(function);
    }
    
    //make a tree relating functions with possible combinations that leads to that function
    //then use A* search
    
    //prune branches that are missing a number (since transformations can't create new entries)
    //prune branches that are not within a cycle or not within reach
    //make method that takes in functions, and then two points, and asks if they are in reach of eachother
    
    
    
}
