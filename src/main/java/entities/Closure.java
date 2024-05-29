package entities;

import java.util.*;

public class Closure {
    private Set<LR0Item> items;
    private int name;
    private Map<NonTerminalSymbol, Closure> gotoMap;
    private Map<TerminalSymbol, Closure> nextMap;
    private Map<Symbol, Set<LR0Item>> target;

    public Closure(int name) {
        items = new HashSet<>();
        this.name = name;
        gotoMap = new HashMap<>();
        nextMap = new HashMap<>();
        target = new HashMap<>();
    }

    public Closure() {
        items = new HashSet<>();
        name = -1;
        gotoMap = new HashMap<>();
        nextMap = new HashMap<>();
        target = new HashMap<>();
    }

    public Closure(Set<LR0Item> items) {
        this.items = new HashSet<>();
        this.items.addAll(items);
        name = -1;
        gotoMap = new HashMap<>();
        nextMap = new HashMap<>();
        target = new HashMap<>();
    }

    public void addItem(LR0Item lr0Item) {
        items.add(lr0Item);
        Symbol symbol = lr0Item.getNext();
        if (symbol == null) return;
        if (!target.containsKey(symbol)) target.put(symbol, new HashSet<>());
        target.get(symbol).add(lr0Item.nextItem());
    }

    public Map<TerminalSymbol, Closure> getNextMap() {
        return nextMap;
    }

    public Map<NonTerminalSymbol, Closure> getGotoMap() {
        return gotoMap;
    }

    public Set<LR0Item> getItems() {
        return items;
    }

    public void addAllItem(Collection<LR0Item> items) {
        items.forEach(this::addItem);
    }

    public Set<NonTerminalSymbol> getGotoList() {
        return gotoMap.keySet();
    }

    public Map<Symbol, Set<LR0Item>> getTarget() {
        return target;
    }

    public void setMap(Map<Set<LR0Item>, Closure> map) {
        for (Map.Entry<Symbol, Set<LR0Item>> entry : target.entrySet()) {
            Symbol symbol = entry.getKey();
            Closure closure = map.get(entry.getValue());
            if (symbol.getType() == Symbol.SymbolType.TERMINAL) {
                nextMap.put((TerminalSymbol) symbol, closure);
            } else {
                gotoMap.put((NonTerminalSymbol) symbol, closure);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("name: \t\n").append(name).append("\n");
        res.append("LR0Items: \t\n");
        items.forEach(item -> res.append(item).append("\n"));
        res.append("\n").append("nextMap:\n");
        nextMap.forEach((key, value) -> res.append(key).append("\t\t").append(value.name));
        res.append("\n").append("gotoMap:\n");
        gotoMap.forEach((key, value) -> res.append(key).append("\t\t").append(value.name));
        res.append("\n----------------------------------------\n");
        return res.toString();
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Closure closure)) return false;
        return items.equals(closure.items);
    }

}
