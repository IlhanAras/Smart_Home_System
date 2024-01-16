import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


      ReaderClass.read(args[0]);
      WriterClass.setFile(args[1]);

      ReaderClass.start();

      WriterClass.lastZreport();

      WriterClass.writeFinalOutput(DataBase.finalOutputStr.toString());


    }
}
