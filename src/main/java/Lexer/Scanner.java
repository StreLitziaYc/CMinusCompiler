package Lexer;

import Lexer.entities.SourceCode;
import Lexer.entities.Token;
import Lexer.entities.TokenList;
import Lexer.enums.CharType;
import Lexer.enums.State;
import Lexer.enums.TokenType;

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
        State state = State.START;
        for(String codeLine : sourceCode.getCodeList()){
            List<Token> list = new ArrayList<>();
            for (int i = 0; i < codeLine.length(); i++) {
                char ch = codeLine.charAt(i);
                CharType charType = Util.getType(ch);
                switch (state) {
                    case START -> {
                        switch (charType) {
                            case SPACE -> state = State.START;
                            case DIGIT -> {
                                state = State.INNUM;
                                token.setType(TokenType.NUM);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case LETTER -> {
                                state = State.INID;
                                token.setType(TokenType.ID);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case EQUAL -> {
                                state = State.INEQ;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case EX -> {
                                state = State.INNE;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case LESS -> {
                                state = State.INLESS;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case GREAT -> {
                                state = State.INGREAT;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            case SLASH -> {
                                state = State.LCOMMENT;
                                token.setType(TokenType.SYMBOL);
                                token.setLineNum(lineNum);
                                token.addChar(ch);
                            }
                            default -> {
                                state = State.DONE;
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
                            default -> state = State.DONE;
                        }
                    }
                    case INID -> {
                        switch (charType) {
                            case LETTER -> token.addChar(ch);
                            default -> state = State.DONE;
                        }
                    }
                    case INEQ, INNE, INLESS, INGREAT -> {
                        switch (charType) {
                            case EQUAL -> {
                                token.addChar(ch);
                                finish = true;
                            }
                            default -> state = State.DONE;
                        }
                    }
                    case LCOMMENT -> {
                        switch (charType) {
                            case STAR -> {
                                state = State.INCOMMENT;
                                token.addChar(ch);
                                token.setType(TokenType.OTHER);
                            }
                            default -> state = State.DONE;
                        }
                    }
                    case INCOMMENT -> {
                        switch (charType) {
                            case STAR -> {
                                state = State.RCOMMENT;
                                token.addChar(ch);
                            }
                            default -> {
                                state = State.INCOMMENT;
                                token.addChar(ch);
                            }
                        }
                    }
                    case RCOMMENT -> {
                        switch (charType) {
                            case SLASH -> {
                                state = State.START;
                                token.addChar(ch);
                                list.add(new Token(token));
                                token.clear();
                            }
                            default -> {
                                state = State.INCOMMENT;
                                token.addChar(ch);
                            }
                        }
                    }
                }
                if (state == State.DONE) {
                    list.add(new Token(token));
                    token.clear();
                    state = State.START;
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
