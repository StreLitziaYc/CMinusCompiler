package entities;

import exceptions.GrammarTextErrorException;

import java.util.*;

public abstract class Symbol {
    private static Set<String> terminalSet, nonTerminalSet;

    static {
        terminalSet = new HashSet<>();
        nonTerminalSet = new HashSet<>();
    }

    protected SymbolType type;
    protected String name;

    public static boolean isNonTerminal(String word) {
        return nonTerminalSet.contains(word);
    }

    /**
     * 设置非终结符集合
     *
     * @param lineList
     */
    public static void setNonTerminalSet(List<String> lineList) {
        for (String s : lineList) {
            List<String> list = Arrays.stream(s.split(" "))
                    .filter(e -> !e.isBlank())
                    .toList();
            if (!Objects.equals(list.get(1), "->")) throw new GrammarTextErrorException();
            nonTerminalSet.add(list.get(0));
        }
    }

    public static void addTerminalSet(String s) {
        terminalSet.add(s);
    }

    public SymbolType getType() {
        return this.type;
    }

    public abstract String getName();

    public abstract String toString();

    public boolean isEmpty() {
        return this instanceof TerminalSymbol terminalSymbol && terminalSymbol.getToken().isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Symbol symbol)) return false;
        return name.equals(symbol.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public enum SymbolType {
        TERMINAL,
        NON_TERMINAL
    }
}
