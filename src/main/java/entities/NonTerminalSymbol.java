package entities;

public class NonTerminalSymbol extends Symbol {
    public NonTerminalSymbol(String name) {
        this.type = SymbolType.NON_TERMINAL;
        this.name = name;
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
