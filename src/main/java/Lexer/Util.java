package Lexer;

import entities.SourceCode;
import entities.TokenList;
import enums.CharType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Util {
    public static CharType getType(char ch) {
        return switch (ch) {
            case '=' -> CharType.EQUAL;
            case '!' -> CharType.EX;
            case '<' -> CharType.LESS;
            case '>' -> CharType.GREAT;
            case '/' -> CharType.SLASH;
            case '*' -> CharType.STAR;
            default -> {
                if (Character.isDigit(ch)) yield CharType.DIGIT;
                if (Character.isLetter(ch)) yield CharType.LETTER;
                if (Character.isWhitespace(ch)) yield CharType.SPACE;
                yield CharType.OTHER;
            }
        };
    }

    public static boolean isReservedKey(String tokenName) {
        Set<String> reservedKeySet = new HashSet<>(Arrays.asList("if", "else", "int", "return", "void", "while"));
        return reservedKeySet.contains(tokenName);
    }

    public static boolean isSymbol(String tokenName) {
        Set<String> symbolSet = new HashSet<>(Arrays.asList("+", "-", "*", "/", "<", "<=", ">", ">=", "==", "!=", "=",
                ";", ",", "(", ")", "[", "]", "{", "}", "/*", "*/"));
        return symbolSet.contains(tokenName);
    }

    public static void show(SourceCode sourceCode, TokenList tokenList) {
        int lineCnt = sourceCode.getLineCnt();
        for (int i = 0; i < lineCnt; i++) {
            System.out.print((i + 1) + ".\t" + sourceCode.getCodeLine(i + 1));
            System.out.print(tokenList.lineToText(i + 1));
        }
    }
}
