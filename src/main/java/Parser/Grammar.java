package Parser;

import entities.LR0Item;
import entities.NonTerminalSymbol;
import entities.Production;
import entities.Symbol;
import exceptions.GrammarTextErrorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grammar {
    private final List<Production> productionList;
    private final Map<Symbol, List<Production>> map;

    public Grammar(String grammarPath) {
        List<String> lineList = new ArrayList<>();
        map = new HashMap<>();
        productionList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(grammarPath));
            String line;
            while ((line = reader.readLine()) != null) {
                lineList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("读取源文件失败！");
            e.printStackTrace();
        }
        Symbol.setNonTerminalSet(lineList);
        lineList.forEach(this::addGrammar);
    }

    private void addGrammar(String s) {
        List<String> list = Arrays.stream(s.split(" "))
                .filter(e -> !e.isBlank())
                .toList();
        if (!Objects.equals(list.get(1), "->")) throw new GrammarTextErrorException();
        String left = list.get(0);
        List<String> right = new ArrayList<>(list.subList(2, list.size())); // 获取右边表达式
        right.add("|");
        List<String> cur = new ArrayList<>();
        for (String word : right) {
            if (Objects.equals(word, "|")) {
                Production curProd = new Production(left, cur);
                productionList.add(curProd);
                Symbol l = new NonTerminalSymbol(left);
                List<Production> temp = map.getOrDefault(l, new ArrayList<>());
                temp.add(curProd);
                map.put(l, temp);
                cur.clear();
            } else {
                cur.add(word);
            }
        }
    }

    public List<LR0Item> nextItems(LR0Item item) {
        Symbol next = item.getNext();
        if (next == null) return Collections.emptyList();
        List<Production> productions = map.get(next);
        return productions.stream()
                .map(production -> new LR0Item(production, 0))
                .toList();
    }

    public void show() {
        productionList.forEach(System.out::println);
    }

    public List<Production> getProductionList() {
        return productionList;
    }
}
