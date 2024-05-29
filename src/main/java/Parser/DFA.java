package Parser;

import entities.Closure;
import entities.LR0Item;
import entities.Symbol;

import java.util.*;

public class DFA {
    private List<Closure> closureList;

    public DFA(Grammar grammar) {
        Set<Closure> closures = new HashSet<>();
        Map<Set<LR0Item>, Closure> M = new HashMap<>();
        Closure closure = new Closure();
        LR0Item lr0Item = new LR0Item(grammar.getProductionList().get(0), 0);
        closure.addItem(lr0Item);
        Queue<Closure> queue = new LinkedList<>();
        queue.add(closure);
        while (!queue.isEmpty()) {
            Closure curClosure = queue.poll();
            Set<LR0Item> key = new HashSet<>(curClosure.getItems());
            Queue<LR0Item> itemQueue = new LinkedList<>(curClosure.getItems());
            Set<LR0Item> items = new HashSet<>();
            // 搜索当前闭包
            while (!itemQueue.isEmpty()) {
                LR0Item item = itemQueue.poll();
                items.add(item);
                if (item.isGoto()) {
                    Collection<LR0Item> collection = grammar.nextItems(item);
                    itemQueue.addAll(collection);
                    items.addAll(collection);
                }
            }
            curClosure.addAllItem(items);
            closures.add(curClosure); // 当前的闭包
            M.put(key, curClosure);
            for (Map.Entry<Symbol, Set<LR0Item>> entry : curClosure.getTarget().entrySet()) {
                if (!M.containsKey(entry.getValue())) {
                    queue.add(new Closure(entry.getValue()));
                }
            }
        }
        int name = 0;
        for (Closure c : closures) {
            c.setMap(M);
            c.setName(name++);
        }
        closureList = new ArrayList<>(closures);
    }

    public void showTable() {
        closureList.forEach(System.out::println);
    }
}
