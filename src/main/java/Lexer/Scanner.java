package Lexer;

import entities.SourceCode;
import entities.Token;
import entities.TokenList;
import enums.CharType;
import enums.LexerState;
import enums.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final SourceCode sourceCode;
    private final TokenList tokenList;

    public Scanner(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
        this.tokenList = new TokenList();
    }

    public TokenList getTokenList() {
        int lineNum = 1;
        Token token = new Token();
        boolean finish = false;
        LexerState state = LexerState.START;
        for(String codeLine : sourceCode.getCodeList()){
            List<Token> list = new ArrayList<>();
            for (int i = 0; i < codeLine.length(); i++) {
                char ch = codeLine.charAt(i);
                CharType charType = Util.getType(ch);
                switch (state) {
                    case START -> {
                        switch (charType) {
                            case SPACE -> state = LexerState.START;
                            case DIGIT -> {
                                state = LexerState.INNUM;
                                token.setType(TokenType.NUM);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case LETTER -> {
                                state = LexerState.INID;
                                token.setType(TokenType.ID);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case EQUAL -> {
                                state = LexerState.INEQ;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case EX -> {
                                state = LexerState.INNE;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case LESS -> {
                                state = LexerState.INLESS;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case GREAT -> {
                                state = LexerState.INGREAT;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case SLASH -> {
                                state = LexerState.LCOMMENT;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            default -> {
                                state = LexerState.DONE;
                                token.setType(TokenType.SYMBOL);
                                token.addChar(ch);
                                token.setLineNum(lineNum);
                                finish = true;
                            }
                        }
                    }
                    case INNUM -> {
                        switch (charType) {
                            case DIGIT -> token.addChar(ch);
                            default -> state = LexerState.DONE;
                        }
                    }
                    case INID -> {
                        switch (charType) {
                            case LETTER -> token.addChar(ch);
                            default -> state = LexerState.DONE;
                        }
                    }
                    case INEQ, INNE, INLESS, INGREAT -> {
                        switch (charType) {
                            case EQUAL -> {
                                token.addChar(ch);
                                finish = true;
                            }
                            default -> state = LexerState.DONE;
                        }
                    }
                    case LCOMMENT -> {
                        switch (charType) {
                            case STAR -> {
                                state = LexerState.INCOMMENT;
                                token.addChar(ch);
                                token.setType(TokenType.OTHER);
                            }
                            default -> state = LexerState.DONE;
                        }
                    }
                    case INCOMMENT -> {
                        switch (charType) {
                            case STAR -> {
                                state = LexerState.RCOMMENT;
                                token.addChar(ch);
                            }
                            default -> {
                                state = LexerState.INCOMMENT;
                                token.addChar(ch);
                            }
                        }
                    }
                    case RCOMMENT -> {
                        switch (charType) {
                            case SLASH -> {
                                state = LexerState.START;
                                token.addChar(ch);
                                list.add(new Token(token));
                                token.clear();
                            }
                            default -> {
                                state = LexerState.INCOMMENT;
                                token.addChar(ch);
                            }
                        }
                    }
                }
                if (state == LexerState.DONE) {
                    list.add(new Token(token));
                    token.clear();
                    state = LexerState.START;
                    if (!finish) i--; //是否需要回溯
                    finish = false;
                }
            }
            lineNum++;
            tokenList.addTokens(list);
        }
        return tokenList;
    }
}
