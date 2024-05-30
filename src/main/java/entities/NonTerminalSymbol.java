package entities;

import java.util.HashSet;
import java.util.Set;

public class NonTerminalSymbol extends Symbol {
    private Set<TerminalSymbol> firstSet, followSet;
    public NonTerminalSymbol(String name) {
        this.type = SymbolType.NON_TERMINAL;
        this.name = name;
        firstSet = new HashSet<>();
        followSet = new HashSet<>();
    }

    public void addEndSymbol() {
        firstSet.add(new TerminalSymbol("empty"));
    }

    public void addSymbol(TerminalSymbol terminalSymbol) {
        firstSet.add(new TerminalSymbol(terminalSymbol));
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
