/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author bowen
 */
public class LinkedFunction {
    private final List<LinkedFunction> compositionHistory;
    private final Function currentAnswer;
    private final boolean isRoot;
    private final boolean isIdentity;
    
    public LinkedFunction(Function initialFunction) {
        compositionHistory = new LinkedList<>();
        isRoot = true;
        isIdentity = false;
        currentAnswer = initialFunction;
    }
    
    private LinkedFunction(LinkedFunction f, LinkedFunction f2) {
        compositionHistory = new LinkedList<>();
        compositionHistory.add(f);
        compositionHistory.add(f2);
        isRoot = false;
        isIdentity = false;
        currentAnswer = Function.composition(f.currentAnswer, f2.currentAnswer);
    }

    public LinkedFunction(int size) {
        compositionHistory = new LinkedList<>();
        isRoot = true;
        isIdentity = true;
        
        LinkedList<Integer> mapping = new LinkedList<>();
        
        for (int i=0; i<size; i++) {
            mapping.addLast(i+1);
        }
        
        currentAnswer = new Function(mapping);
    }
    
    public boolean isRoot() {
        return isRoot;
    }

    public Function getCurrentAnswer() {
        return currentAnswer;
    }
    
    public LinkedFunction getComposition(LinkedFunction f2) {
        return new LinkedFunction(this, f2);
    }
    
    public LinkedFunction getCompositionReverse(LinkedFunction f2) {
        return new LinkedFunction(f2, this);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedFunction) {
            LinkedFunction f = (LinkedFunction) o;
            return f.currentAnswer.getMapping().equals(this.currentAnswer.getMapping());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return currentAnswer.hashCode();
    }

    @Override
    public String toString() {
        if (isIdentity) {
            return "I";
        } else if (isRoot) {
            return currentAnswer.toString();
        } else {
            String s = "(";
            for (LinkedFunction f : compositionHistory) {
                s += f.toString() + " -> ";
            }
            s = s.substring(0, s.length() - 4);
            s += ")";
            return s;
        }
    }
    
    
    
}
