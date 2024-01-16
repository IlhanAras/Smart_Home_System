import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterClass {

    public static String fileName;

    public static void setFile(String filename){
        fileName = filename;
    }


    public static void concatOtputStr(String s)  {
        DataBase.finalOutputStr.append(s);
    }

    /**
     * writes the output to the specified file.
     * @param s is a output string
     * @throws IOException
     */
    public static void writeFinalOutput(String s) throws IOException {
        File fileWrite=new File(fileName);
        FileWriter fileWriter=new FileWriter(fileWrite);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

        bufferedWriter.write(s);
        bufferedWriter.close();

    }

    public static void lastZreport(){
        String lastCommand=DataBase.linesArray.get(DataBase.linesArray.size()-1)[0];
        if( (! lastCommand.equals("ZReport"))&&(!DataBase.finalOutputStr.toString().contains("terminate"))){
            WriterClass.concatOtputStr("ZReport:\n");
            WriterClass.concatOtputStr(Commands.zReport());

        }
    }
}
