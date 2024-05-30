package entities;

public class TerminalSymbol extends Symbol {
    private Token token;

    public TerminalSymbol(String name) {
        this.type = SymbolType.TERMINAL;
        this.name = name;
        this.token = new Token(name);
    }

    public TerminalSymbol (TerminalSymbol symbol) {
        type = symbol.type;
        name = symbol.name;
        token = new Token(symbol.token);
    }

    public Token getToken() {
        return token;
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
