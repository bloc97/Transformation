/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformation;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author bowen
 */
public class SearchSpace {
    
    private Map<LinkedFunction, Boolean> map; //Search space, and unique compositions, maps CompositionSearch to if it was already visited
    private LinkedList<LinkedFunction> currentFunctions; //List used to backtrack
    
    private final Function solution;
    private final Integer[] solutionArray;
    
    private LinkedFunction foundSolution = null;
    
    public SearchSpace(Set<Function> functions, Function solution) {
        map = new LinkedHashMap<>();
        currentFunctions = new LinkedList<>();
        this.solution = solution;
        
        for (Function function : functions) {
            map.put(new LinkedFunction(function), Boolean.FALSE);
        }
        
        Integer[] b = solution.getMapping().toArray(new Integer[0]);
        Integer[] t = new Integer[b.length * 2]; //Double the size of b for lcs
        
        for (int i=0; i<t.length; i++) {
            t[i] = b[i%b.length];
        }
        
        solutionArray = t;
        
    }
    
    public LinkedFunction search() {
        
        if (foundSolution != null) {
            return foundSolution;
        }
        
        boolean notFinished = true;
        
        int i = 0;
        
        while (notFinished) {
        
            LinkedFunction linkedSolution = new LinkedFunction(solution);

            currentFunctions.add(new LinkedFunction(solution.size())); //Add first composition

            //System.out.println(currentFunctions);
            //System.out.println(new LinkedFunction(solution));


            while (currentFunctions.size() > 0) {
                System.out.println(i + " - " + currentFunctions.size() + " - " + currentFunctions.getLast().getCurrentAnswer());
                Set<LinkedFunction> unvisitedSet = getAllCurrentUnvisitedCompositions();

                if (unvisitedSet.isEmpty()) {
                    currentFunctions.pollLast();
                } else {
                    LinkedFunction bestUnvisited = getBestComposition(unvisitedSet);

                    if (bestUnvisited.equals(linkedSolution)) {
                        foundSolution = bestUnvisited;
                        return bestUnvisited;
                    }

                    currentFunctions.addLast(bestUnvisited);
                    map.put(bestUnvisited, Boolean.TRUE); //Count function as visited

                }

                i++;

            }
            
            Set<LinkedFunction> fullSet = getFullCompositions(); //One round of brute force to make sure we didn't miss anything
            if (fullSet.isEmpty()) {
                notFinished = false;
            }
            
        }
        
        return null;
        
    }
    
    public LinkedFunction getBestComposition(Set<LinkedFunction> functionSet) {
        int bestScore = -1;
        LinkedFunction f = null;
        
        for (LinkedFunction lf : functionSet) {
            int currentScore = getScore(lf.getCurrentAnswer());
            if (currentScore > bestScore) {
                f = lf;
                bestScore = currentScore;
            }
        }
        
        return f;
    }
    
    
    public Set<LinkedFunction> getFullCompositions() {
        Set<LinkedFunction> functionSet = new HashSet<>();
        
        for (LinkedFunction f1 : map.keySet()) {
            for (LinkedFunction f2 : map.keySet()) {
                LinkedFunction fog = f1.getComposition(f2);
                LinkedFunction gof = f2.getComposition(f1);

                if (checkIsAlive(fog.getCurrentAnswer())) { //Prune dead branches of the graph
                    if (!map.getOrDefault(fog, Boolean.FALSE)) { //Prune already visited branches
                        functionSet.add(fog);
                    }
                }

                if (checkIsAlive(gof.getCurrentAnswer())) { //Prune dead branches of the graph
                    if (!map.getOrDefault(gof, Boolean.FALSE)) { //Prune already visited branches
                        functionSet.add(gof);
                    }
                }

            }
        }
        
        for (LinkedFunction f : functionSet) {
            map.putIfAbsent(f, Boolean.FALSE);
        }
        
        return functionSet;
        
    }
    
    public Set<LinkedFunction> getAllCurrentUnvisitedCompositions() {
        Set<LinkedFunction> functionSet = new HashSet<>();
        
        LinkedFunction f1 = currentFunctions.getLast();
        
        for (LinkedFunction f2 : map.keySet()) {
            LinkedFunction fog = f1.getComposition(f2);
            LinkedFunction gof = f2.getComposition(f1);
            
            if (checkIsAlive(fog.getCurrentAnswer())) { //Prune dead branches of the graph
                if (!map.getOrDefault(fog, Boolean.FALSE)) { //Prune already visited branches
                    functionSet.add(fog);
                }
            }
            
            if (checkIsAlive(gof.getCurrentAnswer())) { //Prune dead branches of the graph
                if (!map.getOrDefault(gof, Boolean.FALSE)) { //Prune already visited branches
                    functionSet.add(gof);
                }
            }
            
        }
        
        for (LinkedFunction f : functionSet) {
            map.putIfAbsent(f, Boolean.FALSE);
        }
        
        return functionSet;
        
    }
    
    public static Integer[] longestCommonSubsequence(Integer[] a, Integer[] b) {
        
        int[][] lengths = new int[a.length+1][b.length+1];

        // row 0 and column 0 are initialized to 0 already

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b.length; j++)
                if (a[i] == b[j])
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                        Math.max(lengths[i+1][j], lengths[i][j+1]);

        // read the substring out from the matrix
        LinkedList<Integer> sb = new LinkedList<>();
        for (int x = a.length, y = b.length;
             x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x-1][y])
                x--;
            else if (lengths[x][y] == lengths[x][y-1])
                y--;
            else {
                assert a[x-1] == b[y-1];
                sb.addFirst(a[x-1]);
                x--;
                y--;
            }
        }

        return sb.toArray(new Integer[0]);
    }
    public static int subStringIndex(Integer[] largeArray, Integer[] subArray) {
        if (largeArray.length == 0 || subArray.length == 0){
          throw new IllegalArgumentException();
    }
        if (subArray.length > largeArray.length){
          throw new IllegalArgumentException();
    }

        int[] prefixArr = getPrefixArr(subArray);
        int indexToReturn = -1;

        for (int m = 0, s = 0; m < largeArray.length; m++) {
          if (subArray[s] == largeArray[m]) {
            s++;
          } else {
            if (s != 0) {
              s = prefixArr[s - 1];
              m--;
            }
          }
          if (s == subArray.length) {
            indexToReturn = m - subArray.length + 1;
            break;
          }
        }

        return indexToReturn;
      }

      private static int[] getPrefixArr(Integer[] subArray) {
        int[] prefixArr = new int[subArray.length];
        prefixArr[0] = 0;

        for (int i = 1, j = 0; i < prefixArr.length; i++) {
          while (subArray[i] != subArray[j]) {
            if (j == 0) {
              break;
            }
            j = prefixArr[j - 1];
          }

          if (subArray[i] == subArray[j]) {
            prefixArr[i] = j + 1;
            j++;
          } else {
            prefixArr[i] = j;
          }

        }
        return prefixArr;
      }
    
    public int getScore(Function f) {
        
        Integer[] b = f.getMapping().toArray(new Integer[0]);
        Integer[] t = new Integer[b.length * 2]; //Double the size of b for lcs
        
        for (int i=0; i<t.length; i++) {
            t[i] = b[i%b.length];
        }
        
        b = t;
        
        Integer[] lcs = longestCommonSubsequence(b, solutionArray);
        
        if (lcs.length == 0) {
            return 0;
        }
        
        int index1 = subStringIndex(b, lcs);
        int index2 = subStringIndex(solutionArray, lcs);
        
        int distance = Math.abs(index1 - index2);
        int length = lcs.length;
        
        return f.size() * length - distance;
        
        /*
        
        if (f.size() == solution.size()) {
            int score = 0;
            for (int i=0; i<f.size(); i++) {
                if (Objects.equals(f.getMapping().get(i), solution.getMapping().get(i))) {
                    //score += i+1;
                    //score++;
                    score += Math.pow(2, f.size() - i);
                }
            }
            return score;
        }
        return Integer.MAX_VALUE;*/
    }
    public boolean checkIsAlive(Function f) {
        boolean[] fB = new boolean[f.size()];
        boolean[] sB = new boolean[solution.size()];
        for (int i=0; i<f.size(); i++) {
            fB[f.getMapping().get(i) - 1] = true;
        }
        for (int i=0; i<solution.size(); i++) {
            sB[solution.getMapping().get(i) - 1] = true;
        }
        for (int i=0; i<fB.length; i++) {
            if (sB[i] && !fB[i]) {
                return false;
            }
        }
        return true;
    }
    
}
