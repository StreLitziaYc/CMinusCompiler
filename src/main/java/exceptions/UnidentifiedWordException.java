package exceptions;

public class UnidentifiedWordException extends RuntimeException{
    public UnidentifiedWordException(int lineNum) {
        super("在第" + lineNum + "行出现了未定义的词！");
    }
}
