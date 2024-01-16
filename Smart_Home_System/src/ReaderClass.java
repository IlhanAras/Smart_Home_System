import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReaderClass {

    public static void read(String file) throws IOException {

        File fileMovement = new File(file);
        FileReader fileMovementReader = new FileReader(fileMovement);
        BufferedReader bufferedReaderMove = new BufferedReader(fileMovementReader);
        String lineMove;

        while ((lineMove = bufferedReaderMove.readLine()) != null) {
            if (!(lineMove.trim().isEmpty())) {
                DataBase.linesArray.add(lineMove.split("\t"));
            }
        }
        fileMovementReader.close();
        bufferedReaderMove.close();
    }


    /**
     * starts the program
     * @author ilhan aras b2210356023.
     */
    public static void start()  {

        AdderClass adderClass=new AdderClass();

      for(String[] line :DataBase.linesArray){

          if(line[0].equals("SetInitialTime")) {
              if (line.length != 2) {
                  WriterClass.concatOtputStr(commandWriter(line));
                  WriterClass.concatOtputStr("ERROR: First command must be set initial time! Program is going to terminate!\n");
                  break;
              }
              else if (ErrorHandlingClass.isNotValidDate(line[1])) {
                  WriterClass.concatOtputStr(commandWriter(line));
                  WriterClass.concatOtputStr("ERROR: Format of the initial date is wrong! Program is going to terminate!\n");
                  break;
              }
              else if (DataBase.initialCounter != 0) {
                  WriterClass.concatOtputStr(commandWriter(line));
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              } else {

                  WriterClass.concatOtputStr(commandWriter(line));
                  WriterClass.concatOtputStr(Commands.setInitialTime(line[1]) + "\n");


              }
          }else if(DataBase.initialCounter!=1) {
              WriterClass.concatOtputStr(commandWriter(line));
              WriterClass.concatOtputStr("ERROR: First command must be set initial time! Program is going to terminate!\n");
              break;

          }else if (line[0].equals("Nop")) {
              WriterClass.concatOtputStr(commandWriter(line));

              if(line.length!=1){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.nopCommand());

              }
          }
          else if (line[0].equals("Remove")) {
              WriterClass.concatOtputStr(commandWriter(line));

              if(line.length!=2){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.remove(line[1]));
              }
          }
          else if (line[0].equals("SetSwitchTime")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=3){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.setSwitchTime(line[1],line[2]));
              }
          }
          else if (line[0].equals("Switch")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=3){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.switchCommand(line[1], line[2]));
              }

          }
          else if (line[0].equals("ChangeName")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=3){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.changeName(line[1], line[2]));
              }
          }
          else if (line[0].equals("PlugIn")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=3){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.plugIn(line[1], line[2]));
              }

          }
          else if (line[0].equals("PlugOut")) {
              WriterClass.concatOtputStr(commandWriter(line));

              if(line.length!=2){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");
              }else{
                  WriterClass.concatOtputStr(Commands.plugOut(line[1]));
              }

          }
          else if (line[0].equals("SetKelvin")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=3){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  if((line[2]).matches("\\d+")){
                      int kelvin = Integer.parseInt(line[2]);
                      WriterClass.concatOtputStr(Commands.setKelvinCommand(line[1], kelvin));
                  }else {
                          System.out.println("kelvin int olmalı");
                  }
              }

          }
          else if (line[0].equals("SetBrightness")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=3){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.setBrightnessCommand(line[1],line[2]));
              }
          }
          else if (line[0].equals("SetColorCode")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=3){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.setColorCodeCommand(line[1],line[2]));
              }
          }
          else if (line[0].equals("SetWhite")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=4){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.setWhiteCommand(line[1], line[2], line[3]));
              }
          }
          else if (line[0].equals("SetColor")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=4){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.setColorCommand(line[1], line[2], line[3]));
              }
          }
          else if (line[0].equals("ZReport")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=1){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.zReport());
              }
          }
          else if (line[0].equals("SkipMinutes")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=2){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.skipMinutes(line[1]));
              }
          }
          else if (line[0].equals("SetTime")) {
              WriterClass.concatOtputStr(commandWriter(line));
              if(line.length!=2){
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }else{
                  WriterClass.concatOtputStr(Commands.setTime(line[1]));
              }
          }
          else if (line[0].equals("Add")&&line[1].equals("SmartPlug")) {
              WriterClass.concatOtputStr(commandWriter(line));

              if (line.length == 3) {
                  WriterClass.concatOtputStr(adderClass.addSmartPlug(line[2]));

              } else if (line.length == 4) {
                  WriterClass.concatOtputStr(adderClass.addSmartPlug(line[2], line[3]));

              } else if (line.length == 5) {
                  WriterClass.concatOtputStr(adderClass.addSmartPlug(line[2], line[3], line[4]));

              }else{
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }
          }
          else if (line[0].equals("Add")&&line[1].equals("SmartLamp")) {
              WriterClass.concatOtputStr(commandWriter(line));

              if (line.length == 3) {
                  WriterClass.concatOtputStr(adderClass.addSmartLamp(line[2]));

              } else if (line.length == 4) {
                  WriterClass.concatOtputStr(adderClass.addSmartLamp(line[2], line[3]));

              } else if (line.length == 6) {
                  WriterClass.concatOtputStr(adderClass.addSmartLamp(line[2], line[3], line[4], line[5]));

              }else{
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }
          }
          else if (line[0].equals("Add")&&line[1].equals("SmartColorLamp")) {
              WriterClass.concatOtputStr(commandWriter(line));

              if (line.length == 3) {
                  WriterClass.concatOtputStr(adderClass.addSmartColorLamp(line[2]));

              } else if (line.length == 4) {
                  WriterClass.concatOtputStr(adderClass.addSmartColorLamp(line[2], line[3]));

              } else if (line.length == 6) {
                  if ((line[4]).matches("\\d+")) {
                      int kelvin = Integer.parseInt(line[4]);
                      WriterClass.concatOtputStr(adderClass.addSmartColorLamp(line[2], line[3], kelvin, line[5]));

                  } else {
                      WriterClass.concatOtputStr(adderClass.addSmartColorLamp(line[2], line[3], line[4], line[5]));
                  }

              }else{
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

              }
          }
          else if (line[0].equals("Add")&&line[1].equals("SmartCamera")) {
              WriterClass.concatOtputStr(commandWriter(line));

              if(line.length==4){
                  WriterClass.concatOtputStr(adderClass.addSmartCamera(line[2], line[3]));

              }else if(line.length==5){
                  WriterClass.concatOtputStr(adderClass.addSmartCamera(line[2], line[3],line[4]));

              }else{
                  WriterClass.concatOtputStr("ERROR: Erroneous command!\n");
              }

          }
          else{
              WriterClass.concatOtputStr(commandWriter(line));
              WriterClass.concatOtputStr("ERROR: Erroneous command!\n");

          }
      }
    }

    /**
     * Formats a given command into a user-friendly output string.
     * @param line an array of command keywords to be formatted
     * @return a string representation of the formatted command
     * @author ilhan aras b2210356023.
     */
    public static String commandWriter(String[] line) {
        String result = "COMMAND: ";

        for (String vocab : line) {
            result += vocab + "\t";
        }
        String output = result.endsWith("\t") ? result.substring(0, result.length() - 1) : result;
        return output+"\n";
    }

}