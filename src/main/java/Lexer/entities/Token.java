package Lexer.entities;

import Lexer.enums.TokenType;

/**
 * 识别出来的token
 */
public class Token {
    private TokenType type;
    private StringBuilder name;
    private Integer lineNum;

    public Token() {
        type = null;
        name = new StringBuilder();
        lineNum = null;
    }

    public Token(Token token) {
        type = token.type;
        lineNum = token.lineNum;
        name = token.name;
    }

    public Token(TokenType type, Integer lineNum) {
        this.type = type;
        this.lineNum = lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public void clear() {
        type = null;
        name = new StringBuilder();
        lineNum = null;
    }

    public void addChar(char ch) {
        name.append(ch);
    }

    public String getName() {
        return name.toString();
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public TokenType getType() {
        return type;
    }

    public String toText() {
        String prefix = switch (type) {
            case RESERVED_KEY -> "reserved word: ";
            case ID -> "ID, name = ";
            case NUM -> "NUM, val = ";
            case SYMBOL -> "symbol: ";
            case OTHER -> "";
        };
        return "\t" + lineNum + ".\t" + prefix + name + "\n";
    }
}
