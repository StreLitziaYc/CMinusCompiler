import Parser.DFA;
import Parser.Grammar;
import entities.Closure;
import entities.LR0Item;
import entities.Production;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestParser {
    @Test
    public void testClosureEqual() {
        Closure closure1 = new Closure();
        Production production1 = new Production("S", List.of("E", "a", "B")),
                production2 = new Production("B", List.of("R", ")"));
        LR0Item item1 = new LR0Item(production1, 0),
                item2 = new LR0Item(production2, 1);
        closure1.addItem(item1);
        closure1.addItem(item2);
        Closure closure2 = new Closure();
        Production production3 = new Production("S", List.of("E", "a", "B")),
                production4 = new Production("B", List.of("R", ")"));
        LR0Item item3 = new LR0Item(production3, 0),
                item4 = new LR0Item(production4, 1);
        closure2.addItem(item3);
        closure2.addItem(item4);
        closure2.addItem(item1);
        System.out.println(closure1.equals(closure2));
        Set<Closure> closures = new HashSet<>();
        closures.add(closure1);
        closures.add(closure2);
        System.out.println(closure1.hashCode());
        System.out.println(closure2.hashCode());
        System.out.println(closures.size());
    }

    @Test
    public void testClosure() {
        String grammarRulePath = "src/main/resources/testGrammar.txt";
        Grammar grammar = new Grammar(grammarRulePath);
        grammar.show();
        DFA dfa = new DFA(grammar);
        dfa.showTable();
    }

    @Test
    public void testToString() {
        Production production1 = new Production("S", List.of("E", "a", "B"));
        LR0Item item1 = new LR0Item(production1, 0);
        item1.toString();
    }

    @Test
    public void testEqual() {
        Closure closure1 = new Closure();
        Production production1 = new Production("S", List.of("E", "a", "B")),
                production2 = new Production("B", List.of("R", ")"));
        LR0Item item1 = new LR0Item(production1, 0),
                item2 = new LR0Item(production2, 1);
        Production production3 = new Production("S", List.of("E", "a", "B")),
                production4 = new Production("B", List.of("R", ")"));
        LR0Item item3 = new LR0Item(production3, 0),
                item4 = new LR0Item(production4, 1);
        Set<LR0Item> set1 = new HashSet<>();
        set1.add(item1);
        set1.add(item2);
        Set<LR0Item> set2 = new HashSet<>();
        set2.add(item3);
        set2.add(item4);
        Set<Set<LR0Item>> sets = new HashSet<>();
        sets.add(set2);
        System.out.println(set1.equals(set2));
        System.out.println(sets.contains(set1));
    }
}
