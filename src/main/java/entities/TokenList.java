package entities;

import Lexer.Util;
import enums.TokenType;
import exceptions.UnidentifiedWordException;

import java.util.ArrayList;
import java.util.List;

public class TokenList {
    private final List<List<Token>> tokenList;

    public TokenList() {
        tokenList = new ArrayList<>();
    }

    public void addTokens(List<Token> tokens) {
        for (Token token : tokens) {
            if (token.getType() == TokenType.SYMBOL && !Util.isSymbol(token.getName())) {
                throw new UnidentifiedWordException(token.getLineNum());
            }
            if (Util.isReservedKey(token.getName())) {
                token.setType(TokenType.RESERVED_KEY);
            }
        }
        tokenList.add(tokens);
    }

    public String lineToText(int lineNum) {
        StringBuilder text = new StringBuilder();
        for (Token token : tokenList.get(lineNum - 1)) {
            text.append(token.toText());
        }
        return text.toString();
    }

    public List<Token> getContent() {
        List<Token> res = new ArrayList<>();
        for (List<Token> line : tokenList) {
            for (Token token : line) {
                if (token.getType() != TokenType.OTHER) res.add(token);
            }
        }
        return res;
    }
}
