package exceptions;

public class GrammarTextErrorException extends RuntimeException{
    public GrammarTextErrorException() {
        super("语法规则文本有误！");
    }
}
