package entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LR0Item {
    private Production production;
    private int dotPosition;
    private boolean isGoto;
    private Symbol next;

    public LR0Item(Production production, int dotPosition) {
        if (production.length() == 1 && production.getRhs().get(0).isEmpty())
            this.production = Production.emptyProduction(production.getLhs());
        else this.production = production;
        this.dotPosition = dotPosition;
        this.isGoto = !production.isTerminal(dotPosition);
        this.next = dotPosition >= production.length() ? null : production.getRhs().get(dotPosition);
    }

    public static Set<LR0Item> create(List<Production> productions) {
        Set<LR0Item> res = new HashSet<>();
        for (Production p : productions) {
            for (int i = 0; i <= p.length(); i++) {
                res.add(new LR0Item(p, i));
            }
        }
        return res;
    }

    public boolean isGoto() {
        return isGoto;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(production.getLhs().toString())
                .append(" -> ");
        if (dotPosition == production.length()) return stringBuilder.append(".").toString();
        for (int i = 0; i < production.length(); i++) {
            if (i == dotPosition) stringBuilder.append(". ");
            stringBuilder.append(production.getRhs().get(i)).append(" ");
        }
        return stringBuilder.toString();
    }

    public Symbol getNext() {
        return next;
    }

    public Production getProduction() {
        return production;
    }

    public LR0Item nextItem() {
        return new LR0Item(production, dotPosition + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LR0Item item)) return false;
        return production.equals(item.production) && dotPosition == item.dotPosition;
    }

    @Override
    public int hashCode() {
        return production.hashCode() + dotPosition;
    }

}
