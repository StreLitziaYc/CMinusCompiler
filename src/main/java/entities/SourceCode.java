package entities;

import java.util.ArrayList;
import java.util.List;

public class SourceCode {
    private final List<String> codeList;

    public SourceCode() {
        this.codeList = new ArrayList<>();
    }

    public void addCode(String codeLine) {
        codeList.add(codeLine);
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public int getLineCnt() {
        return codeList.size();
    }

    public String getCodeLine(int lineNum) {
        return codeList.get(lineNum - 1);
    }
}
