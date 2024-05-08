package Lexer;

import Lexer.entities.SourceCode;
import Lexer.entities.TokenList;

public class Application {
    public static void main(String[] args) {
        // 获取源文件
        String filePath = "src/main/resources/src.txt";
        Inputer inputer = new Inputer(filePath);
        SourceCode sourceCode = inputer.getSourceCode();
        // 进行词法扫描
        Scanner scanner = new Scanner(sourceCode);
        TokenList tokenList = scanner.getTokenList();
        // 输出结果
        Util.show(sourceCode, tokenList);
    }
}
