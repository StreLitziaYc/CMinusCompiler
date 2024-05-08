package Lexer.enums;

/**
 * DFA中的各个状态
 */
public enum State {
    START,
    INNUM,
    INID,
    INEQ,
    INNE,
    INLESS,
    INGREAT,
    LCOMMENT,
    INCOMMENT,
    RCOMMENT,
    DONE
}
