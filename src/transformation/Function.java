/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author bowen
 */
public class Function {
    private final List<Integer> mapping;
    
    public Function(Integer... mapping) {
        this.mapping = Arrays.asList(mapping);
    }
    public Function(List<Integer> mapping) {
        this.mapping = new ArrayList<>(mapping);
    }
    
    
    public List<Integer> getMapping() {
        return mapping;
    }
    
    public int size() {
        return mapping.size();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Function) {
            Function f = (Function) o;
            return f.getMapping().equals(this.getMapping());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return mapping.hashCode();
    }

    @Override
    public String toString() {
        return mapping.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Function composition(Function f1, Function f2) {
        List<Integer> l1 = f1.getMapping();
        List<Integer> l2 = f2.getMapping();
        List<Integer> l3 = new LinkedList<>();
        for (int i=0; i<l1.size(); i++) {
            l3.add(l2.get(l1.get(i) - 1));
        }
        return new Function(l3);
    }
}
