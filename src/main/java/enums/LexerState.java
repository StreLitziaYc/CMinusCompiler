package enums;

/**
 * DFA中的各个状态
 */
public enum LexerState {
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
