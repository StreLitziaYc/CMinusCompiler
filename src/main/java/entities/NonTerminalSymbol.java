package entities;

import java.util.HashSet;
import java.util.Set;

public class NonTerminalSymbol extends Symbol {
    private Set<TerminalSymbol> firstSet, followSet;
    public NonTerminalSymbol(String name) {
        this.type = SymbolType.NON_TERMINAL;
        this.name = name;
        firstSet = new HashSet<>();
        firstSet = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
