package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Production {
    private NonTerminalSymbol lhs;
    private List<Symbol> rhs;

    public Production(String left, List<String> rightList) {
        lhs = new NonTerminalSymbol(left);
        rhs = new ArrayList<>();
        rightList.forEach(this::addSymbol);
    }

    public static Production emptyProduction(NonTerminalSymbol l) {
        return new Production(l.getName(), Collections.emptyList());
    }

    public List<Symbol> getRhs() {
        return rhs;
    }

    public boolean isTerminal(int dotPosition) {
        if (dotPosition >= length()) return true;
        return rhs.get(dotPosition).getType() == Symbol.SymbolType.TERMINAL;
    }

    public int length() {
        return rhs.size();
    }

    private void addSymbol(String s) {
        if (Symbol.isNonTerminal(s)) {
            rhs.add(new NonTerminalSymbol(s));
        } else {
            TerminalSymbol symbol = new TerminalSymbol(s);
            if (!symbol.isEmpty()) {
                rhs.add(new TerminalSymbol(s));
                Symbol.addTerminalSet(s);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(lhs.toString() + " -> ");
        for (Symbol symbol : rhs) {
            res.append(symbol).append(" ");
        }
        return res.toString();
    }

    public NonTerminalSymbol getLhs() {
        return lhs;
    }

    @Override
    public int hashCode() {
        return lhs.hashCode() + rhs.hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Production production)) return false;
        if (!lhs.equals(production.getLhs())) return false;
        for (int i = 0; i < this.length(); i++) {
            if (!rhs.get(i).equals(production.rhs.get(i))) return false;
        }
        return true;
    }
}
