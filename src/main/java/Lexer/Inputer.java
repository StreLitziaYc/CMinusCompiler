package Lexer;

import Lexer.entities.SourceCode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Inputer {

    private final String FILE_PATH;

    public Inputer(String filePath) {
        FILE_PATH = filePath;
    }

    public SourceCode getSourceCode (){
        SourceCode sourceCode = new SourceCode();
        StringBuilder stringBuilder = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            stringBuilder = new StringBuilder();
            String line;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
                sourceCode.addCode(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("读取源文件失败！");
            e.printStackTrace();
        }
       return sourceCode;
    }
}
