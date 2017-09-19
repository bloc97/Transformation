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
import java.util.Map;
import java.util.Objects;
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
        
        
        
        Set<LinkedFunction> set = new HashSet<>();
        set.add(new LinkedFunction(new Function(2,1,4,3)));
        
        System.out.println(set.contains((new LinkedFunction(new Function(3,4,2,1))).getComposition(new LinkedFunction(new Function(3,4,2,1)))));
        
        Set<Function> functionSet0 = new LinkedHashSet<>();
        functionSet0.add(new Function(3, 1, 3, 4));
        functionSet0.add(new Function(3, 4, 2, 1));
        
        SearchSpace space0 = new SearchSpace(functionSet0, new Function(4, 3, 2, 1));
        System.out.println(space0.search());
        
        Set<Function> functionSet1 = new LinkedHashSet<>();
        functionSet1.add(new Function(3, 1, 3, 4));
        functionSet1.add(new Function(3, 4, 2, 1));
        
        SearchSpace space1 = new SearchSpace(functionSet1, new Function(1, 1, 1, 2));
        
        System.out.println(space1.search());
        
        Set<Function> functionSet2 = new LinkedHashSet<>();
        functionSet2.add(new Function(3, 5, 4, 2, 6, 8, 1, 7));
        functionSet2.add(new Function(1, 4, 3, 2, 5, 6, 7, 8));
        
        SearchSpace space2 = new SearchSpace(functionSet2, new Function(8, 7, 6, 5, 4, 3, 2, 1));
        
        System.out.println(space2.search());
        
        Set<Function> functionSet3 = new LinkedHashSet<>();
        functionSet3.add(new Function(3, 3, 4, 4, 5, 5));
        functionSet3.add(new Function(3, 4, 2, 1, 6, 6));
        functionSet3.add(new Function(4, 4, 4, 4, 5, 5));
        
        SearchSpace space3 = new SearchSpace(functionSet3, new Function(2, 1, 4, 3, 5, 5));
        
        System.out.println(space3.search());
        
        Set<Function> functionSet4 = new LinkedHashSet<>();
        functionSet4.add(new Function(1, 2, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        functionSet4.add(new Function(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1));
        functionSet4.add(new Function(2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        
        SearchSpace space4 = new SearchSpace(functionSet4, new Function(2, 1, 4, 3, 5, 7, 7, 7, 7, 7, 7, 7));
        
        System.out.println(space4.search());
        
        Set<Function> functionSet5 = new LinkedHashSet<>();
        functionSet5.add(new Function(3, 3, 5, 5, 7, 7, 1, 1, 11, 12, 12, 1));
        functionSet5.add(new Function(3, 3, 1, 1, 5, 5, 7, 7, 7, 4, 12, 11));
        functionSet5.add(new Function(1, 1, 3, 3, 5, 5, 9, 7, 9, 9, 10, 12));
        
        SearchSpace space5 = new SearchSpace(functionSet5, new Function(7, 7, 3, 3, 1, 1, 5, 5, 12, 11, 10, 4));
        
        System.out.println(space5.search());
        
        Set<Function> functionSet6 = new LinkedHashSet<>();
        functionSet6.add(new Function(3, 4, 4, 2, 6, 8, 1, 7, 3));
        functionSet6.add(new Function(4, 4, 4, 4, 4, 5, 5, 8, 1));
        
        SearchSpace space6 = new SearchSpace(functionSet6, new Function(2, 1, 4, 4, 5, 6, 8, 7, 3));
        
        System.out.println(space6.search());
        /*
        
        Set<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(new Integer[] {1, 5, 6}));
        //System.out.println(set.contains(Arrays.asList(new Integer[] {1, 5, 6})));
        
        List<Integer> f1 = Arrays.asList(new Integer[] {1, 2, 1, 3});
        List<Integer> f2 = Arrays.asList(new Integer[] {1, 2, 4, 3});
        
        //System.out.println(comp(f1, f2));
        
        
        Set<Function> functionSet1 = new LinkedHashSet<>();
        functionSet1.add(new Function(3, 1, 3, 4));
        functionSet1.add(new Function(3, 4, 2, 1));
        
        System.out.println(composition(functionSet1, new Function(1, 1, 1, 2)));
        
        Set<Function> functionSet2 = new LinkedHashSet<>();
        functionSet2.add(new Function(3, 5, 4, 2, 6, 8, 1, 7));
        functionSet2.add(new Function(1, 4, 3, 2, 5, 6, 7, 8));
        
        System.out.println(composition(functionSet2, new Function(8, 7, 6, 5, 4, 3, 2, 1)));
        
        Set<Function> functionSet3 = new LinkedHashSet<>();
        functionSet3.add(new Function(3, 3, 4, 4, 5, 5));
        functionSet3.add(new Function(3, 4, 2, 1, 6, 6));
        functionSet3.add(new Function(4, 4, 4, 4, 5, 5));
        
        System.out.println(composition(functionSet3, new Function(2, 1, 4, 3, 5, 5)));
        
        Set<Function> functionSet4 = new LinkedHashSet<>();
        functionSet4.add(new Function(1, 2, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        functionSet4.add(new Function(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1));
        functionSet4.add(new Function(2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        
        System.out.println(composition(functionSet4, new Function(2, 1, 4, 3, 5, 7, 7, 7, 7, 7, 7, 7), 4));
        
        Set<Function> functionSet5 = new LinkedHashSet<>();
        functionSet5.add(new Function(3, 3, 5, 5, 7, 7, 1, 1, 11, 12, 12, 1));
        functionSet5.add(new Function(3, 3, 1, 1, 5, 5, 7, 7, 7, 4, 12, 11));
        functionSet5.add(new Function(1, 1, 3, 3, 5, 5, 9, 7, 9, 9, 10, 12));
        
        System.out.println(composition(functionSet5, new Function(7, 7, 3, 3, 1, 1, 5, 5, 12, 11, 10, 4), 4));
        
        Set<Function> functionSet6 = new LinkedHashSet<>();
        functionSet6.add(new Function(3, 4, 4, 2, 6, 8, 1, 7, 3));
        functionSet6.add(new Function(4, 4, 4, 4, 4, 5, 5, 8, 1));
        
        System.out.println(composition(functionSet6, new Function(2, 1, 4, 4, 5, 6, 8, 7, 3)));
        
        */
    }
    
    public static boolean composition(Set<Function> functionSet, Function function) {
        return composition(functionSet, function, Integer.MAX_VALUE);
    }
    public static boolean composition(Set<Function> functionSet, Function function, int maxBreakout) {
        
        if (functionSet.size() < 1) {
            return false;
        }
        
        Iterator<Function> it = functionSet.iterator();
        
        while (it.hasNext()) {
            if (it.next().equals(function)) {
                return true;
            }
        }
        final Integer ind = 1;
        functionSet.iterator().forEachRemaining(list -> {
            System.out.println(list);
        });
        System.out.println("Find: " + function);
        
        
        Set<Function> functionSetNotChecked = new LinkedHashSet<>(functionSet);
        Set<Function> functionSetChecked = new LinkedHashSet<>();
        
        int loop = 0;
        while(true) {
            long no = 0;
            if (loop >= maxBreakout) {
                System.out.println("Warning! Early Termination");
                break;
            }
            //System.out.println(loop);
            Set<Function> newFunctionSetNotChecked = new LinkedHashSet<>();
            
            Function[] notCheckedArray = functionSetNotChecked.toArray(new Function[] {});
            Function[] checkedArray = functionSetChecked.toArray(new Function[] {});
            for (int i=0; i<notCheckedArray.length; i++) {
                for (int j=0; j<notCheckedArray.length; j++) {
                    no++;
                    if (put(functionSetChecked, functionSetNotChecked, newFunctionSetNotChecked, notCheckedArray[i], notCheckedArray[j], function)) {
                        return true;
                    }
                    
                }
                for (int j=0; j<checkedArray.length; j++) {
                    no++;
                    no++;
                    if (put(functionSetChecked, functionSetNotChecked, newFunctionSetNotChecked, notCheckedArray[i], checkedArray[j], function)) {
                        return true;
                    }
                    if (put(functionSetChecked, functionSetNotChecked, newFunctionSetNotChecked, checkedArray[j], notCheckedArray[i], function)) {
                        return true;
                    }
                }
                functionSetChecked.add(notCheckedArray[i]); //Finished checking that function
                System.out.println(no);
            }
            
            functionSetNotChecked = newFunctionSetNotChecked;
            
            if (functionSetNotChecked.size() < 1) {
                break;
            }
            loop++;
            
        }
        
        boolean isFind = functionSetChecked.contains(function);
        if (!isFind) {
            System.out.println("Closest: " + findClosest(functionSetChecked, function));
        }
        return isFind;
    }
    
    public static boolean put(Set<Function> functionSetChecked, Set<Function> functionSetNotChecked, Set<Function> newFunctionSetNotChecked, Function f1, Function f2, Function solutionFunction) {
        
        Function newFunction = Function.composition(f1, f2);
        if (newFunction.equals(solutionFunction)) {
            System.out.println(newFunction);
            System.out.println("Answer: " + newFunction);
            return true;
        }
        if (!functionSetNotChecked.contains(newFunction) && !functionSetChecked.contains(newFunction) && checkIsAlive(newFunction, solutionFunction)) {
            newFunctionSetNotChecked.add(newFunction);
            //System.out.println(newFunction);
        }
        return false;
    }
    
    public static Function findClosest(Set<Function> functionSet, Function function) {
        Function[] array = functionSet.toArray(new Function[] {});
        
        int score = Integer.MAX_VALUE;
        Function closest = new Function(0);
        
        for (int i=0; i<array.length; i++) {
            Function thisFunction = array[i];
            int thisScore = getScore(thisFunction, function);
            if (thisScore < score) {
                score = thisScore;
                closest = thisFunction;
            }
        }
        
        return closest;
    }
    
    public static int getScore(Function f1, Function f2) {
        if (f1.size() == f2.size()) {
            int score = 0;
            for (int i=0; i<f1.size(); i++) {
                if (!Objects.equals(f1.getMapping().get(i), f2.getMapping().get(i))) {
                    score++;
                }
            }
            return score;
        }
        return Integer.MAX_VALUE;
    }
    public static boolean checkIsAlive(Function f, Function solutionFunction) {
        for (int i=0; i<f.size(); i++) {
            if (!f.getMapping().contains(solutionFunction.getMapping().get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkIsAliveFast(Function f, Function solutionFunction) {
        boolean[] fB = new boolean[f.size()];
        boolean[] sB = new boolean[solutionFunction.size()];
        for (int i=0; i<f.size(); i++) {
            fB[f.getMapping().get(i) - 1] = true;
        }
        for (int i=0; i<solutionFunction.size(); i++) {
            sB[solutionFunction.getMapping().get(i) - 1] = true;
        }
        for (int i=0; i<fB.length; i++) {
            if (sB[i] && !fB[i]) {
                return false;
            }
        }
        return true;
    }
    
    //make a tree relating functions with possible combinations that leads to that function
    //then use A* search
    
    //prune branches that are missing a number (since transformations can't create new entries)
    //prune branches that are not within a cycle or not within reach
    //make method that takes in functions, and then two points, and asks if they are in reach of eachother
    
    
    
}
