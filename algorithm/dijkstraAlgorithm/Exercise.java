package algorithm.dijkstraAlgorithm;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.lang.Character;
import java.lang.Integer;

public class Exercise {

    public static void main(String[] args) {
        Map<Character, Map<Character, Integer>> graph = new HashMap<>();
        Map<Character, Integer> s = new HashMap<>();
        s.put('a', 10);
        graph.put('s', s);
        Map<Character, Integer> a = new HashMap<>();
        a.put('c', 20);
        graph.put('a', a);
        Map<Character, Integer> c = new HashMap<>();
        c.put('b', 1);
        c.put('e', 30);
        graph.put('c', c);
        Map<Character, Integer> b = new HashMap<>();
        b.put('a', 1);
        graph.put('b', b);
        System.out.println(solution(graph));
    }

    private static int solution(Map<Character, Map<Character, Integer>> graph) {
        if (graph == null || graph.isEmpty()) {
            return -1;
        }
        Map<Character, Integer> costs = new HashMap<>();
        Set<Character> nodes = graph.keySet();
        for (Character node : nodes) {
            if (!Objects.equals(node, 's')) {
                costs.put(node, Integer.MAX_VALUE);
            }
        }
        costs.put('e', Integer.MAX_VALUE);
        Map<Character,Character> parent = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : graph.get('s').entrySet()) {
            costs.put(entry.getKey(), entry.getValue());
            parent.put(entry.getKey(),'s');
        }

        
        costs.forEach((k,v)->System.out.println("key:"+k+" cost "+v));
        Set<Character> checked = new HashSet<>();
        Character minUncheckedNode = getMinUncheckedNode(costs, checked);
        while (graph.get(minUncheckedNode) != null) {
                        System.out.println("minUncheckedNode : "+minUncheckedNode);
            Map<Character, Integer> childNodes = graph.get(minUncheckedNode);
            for (Character child : childNodes.keySet()) {
                System.out.println("check : "+child);
                int cCost = childNodes.get(child) + costs.get(minUncheckedNode);
                if (costs.get(child) > cCost) {
                    costs.put(child, cCost);
                    parent.put(child,minUncheckedNode);
                }
            }
            costs.forEach((k,v)->System.out.println("key:"+k+" cost "+v));
            checked.add(minUncheckedNode);
            minUncheckedNode = getMinUncheckedNode(costs, checked);
        }
        Character p = parent.get('e');
        System.out.print("e");
        while(p!=null){
            System.out.print("<-"+p);
            p = parent.get(p);
        }
        System.out.println();
        return costs.get('e');
    }

    private static Character getMinUncheckedNode(Map<Character, Integer> costs, Set<Character> checked) {
        if (costs == null) {
            return null;
        }
        int min = Integer.MAX_VALUE;
        Character minCharacter = null;
        for (Map.Entry<Character, Integer> entry : costs.entrySet()) {
            if (entry.getValue() < min && (!checked.contains(entry.getKey()))) {
                min = entry.getValue();
                minCharacter = entry.getKey();
            }
        }
        return minCharacter;
    }


}