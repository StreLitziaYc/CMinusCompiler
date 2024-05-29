package entities;

import enums.TokenType;

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

    public Token(String name) {
        switch (name) {
            case "empty" -> this.type = TokenType.EMPTY;
            case "ID" -> this.type = TokenType.ID;
            case "[" -> {
                this.type = TokenType.SYMBOL;
                this.name = new StringBuilder("[");
            }
            case "]" -> {
                this.type = TokenType.SYMBOL;
                this.name = new StringBuilder("]");
            }
            case "NUM" -> type = TokenType.NUM;
            case "INT" -> {
                type = TokenType.RESERVED_KEY;
                this.name = new StringBuilder("int");
            }
            case "VOID" -> {
                type = TokenType.RESERVED_KEY;
                this.name = new StringBuilder("void");
            }
            case "(" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("(");
            }
            case ")" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder(")");
            }
            case "{" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("{");
            }
            case "}" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("}");
            }
            case ";" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder(";");
            }
            case "IF" -> {
                type = TokenType.RESERVED_KEY;
                this.name = new StringBuilder("if");
            }
            case "ELSE" -> {
                type = TokenType.RESERVED_KEY;
                this.name = new StringBuilder("else");
            }
            case "WHILE" -> {
                type = TokenType.RESERVED_KEY;
                this.name = new StringBuilder("while");
            }
            case "RETURN" -> {
                type = TokenType.RESERVED_KEY;
                this.name = new StringBuilder("return");
            }
            case "=" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("=");
            }
            case "LE" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("<=");
            }
            case "LT" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("<");
            }
            case "GT" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder(">");
            }
            case "GE" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder(">=");
            }
            case "EQ" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("==");
            }
            case "NE" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("!=");
            }
            case "+" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("+");
            }
            case "-" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("-");
            }
            case "*" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("*");
            }
            case "/" -> {
                type = TokenType.SYMBOL;
                this.name = new StringBuilder("/");
            }
        }
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

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String toText() {
        String prefix = switch (type) {
            case RESERVED_KEY -> "reserved word: ";
            case ID -> "ID, name = ";
            case NUM -> "NUM, val = ";
            case SYMBOL -> "symbol: ";
            case OTHER, EMPTY -> "";
        };
        return type == TokenType.OTHER ? "" : "\t" + lineNum + ".\t" + prefix + name + "\n";
    }

    public boolean isEmpty() {
        return this.type == TokenType.EMPTY;
    }
}
