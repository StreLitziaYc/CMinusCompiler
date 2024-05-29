import Lexer.Inputer;
import Lexer.Scanner;
import Lexer.Util;
import Parser.Grammar;
import entities.SourceCode;
import entities.TokenList;

public class Application {
    public static void main(String[] args) {
        // 获取源文件
        String filePath = "src/main/resources/src.txt";
        Inputer inputer = new Inputer(filePath);
        SourceCode sourceCode = inputer.getSourceCode();
        // 进行词法扫描
        Scanner scanner = new Scanner(sourceCode);
        TokenList tokenList = scanner.getTokenList();
        // 进行语法分析
        String grammarRulePath = "src/main/resources/grammar.txt";
        Grammar grammar = new Grammar(grammarRulePath);
        grammar.show();
        // 输出结果
        //Util.show(sourceCode, tokenList);
    }
}
