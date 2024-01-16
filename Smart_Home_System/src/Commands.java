import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class Commands {

    /**
     *Switches the smart device on or off
     * if the object has a calculation method,
     * it goes through the calculation method.
     * @return Error message string
     *@param name the name value of the smart device
     *@param status the status value of the smart device
     *@author ilhan aras 2210356023
     *
     */
    public static String switchCommand(String name, String status){

        if(!DataBase.hashMapAccessories.containsKey(name)){
            return "ERROR: There is not such a device!\n";
        }
        if(!(status.equals("Off")||status.equals("On"))){
            return "ERROR: Erroneous command!\n";
        }

        Accessories device = DataBase.hashMapAccessories.get(name);

        if(device.getStatus().equals(status)){
            return "ERROR: This device is already switched on!\n";
        }

        if(device instanceof SmartPlug){
            SmartPlug smartplugDevice= (SmartPlug) device;

            if (status.equals("Off")&&smartplugDevice.getStatus().equals("On")) {
                smartplugDevice.setStatus("Off");
                smartplugDevice.setCloseTime(DataBase.globalDate);

                smartplugDevice.calculateEnergyConsumption();
            }else if(status.equals("On")&&smartplugDevice.getStatus().equals("Off")){
                smartplugDevice.setStatus("On");
                smartplugDevice.setOpenTime(DataBase.globalDate);

            }else if(status.equals("Off")&&smartplugDevice.getStatus().equals("Off")){
                return "ERROR: This device is already switched off!\n";

            }else if(status.equals("On")&&smartplugDevice.getStatus().equals("On")){
                return "ERROR: This device is already switched on!\n";
            }

        }else if(device instanceof SmartCamera){
            if(status.equals("On") && device.getStatus().equals("On")){
                return "ERROR: This device is already switched on!\n";

            } else if (status.equals("On") && device.getStatus().equals("Off")) {
                device.setStatus("On");
                ((SmartCamera) device).setOpenTime(DataBase.globalDate);

            }else if(status.equals("Off")&& device.getStatus().equals("Off")){
                return "ERROR: This device is already switched off!\n";

            }else if(status.equals("Off")&& device.getStatus().equals("On")){
                ((SmartCamera) device).setCloseTime(DataBase.globalDate);
                device.setStatus("Off");
                ((SmartCamera) device).calcMb();
            }
        }
        return "";
    }
    /**
     *Switches the smart device on or off
     * if the smart device has a calculation method,
     * it goes through the calculation method.
     *
     *@param object object has to be smart device
     *@author ilhan aras 2210356023
     *
     */
    public static void  switchCommand(Accessories object){

        if(object instanceof SmartCamera){
            SmartCamera smartCamera= (SmartCamera) object;
            if(smartCamera.getStatus().equals("On")){
                smartCamera.setStatus("Off");

                if (smartCamera.getDate()==(null)){
                    smartCamera.setCloseTime(DataBase.globalDate);
                }else{
                    smartCamera.setCloseTime(smartCamera.getDate());
                }

                smartCamera.calcMb();
            }else if(smartCamera.getStatus().equals("Off")){
                smartCamera.setStatus("On");

                if (smartCamera.getDate()==(null)){
                    smartCamera.setOpenTime(DataBase.globalDate);
                }else{
                    smartCamera.setOpenTime(smartCamera.getDate());
                }
                smartCamera.setOpenTime(DataBase.globalDate);
            }
        }else if(object instanceof SmartPlug){

            SmartPlug smartPlug= (SmartPlug) object;
            if(smartPlug.getStatus().equals("Off")){
                object.setStatus("On");


            }else if(smartPlug.getStatus().equals("On")){
                object.setStatus("Off");
                if (smartPlug.getDate()==(null)){
                    smartPlug.setCloseTime(DataBase.globalDate);
                }else{
                    smartPlug.setCloseTime(smartPlug.getDate());
                }

                smartPlug.calculateEnergyConsumption();
            }
        }else{
            if(object.getStatus().equals("On")){
                object.setStatus("Off");

            }else if(object.getStatus().equals("Off")){
                object.setStatus("On");
            }
        }
        String objectKey="";
        LinkedHashMap<String,Accessories> copyHashMap=new LinkedHashMap<>();

        for (String key:DataBase.hashMapAccessories.keySet()) {
            copyHashMap.put(key,DataBase.hashMapAccessories.get(key));
        }

        Set<String> keySet=DataBase.hashMapAccessories.keySet();

        for (String key:keySet) {
            if(copyHashMap.get(key).equals(object)){
                objectKey=key;
            }
        }

        DataBase.hashMapAccessories.clear();
        copyHashMap.remove(objectKey);
        DataBase.hashMapAccessories.put(objectKey,object);

        for(String key: copyHashMap.keySet()){
            DataBase.hashMapAccessories.put(key,copyHashMap.get(key));
        }


    }

    /**
     * Plug out the smart plug. Otherwise, return an error message.
     * @return Error message string
     *@param name the name value of the smart device
     *@author ilhan aras 2210356023
     *
     */
    public static String plugOut(String name){

        if(!DataBase.hashMapAccessories.containsKey(name)){
            return "ERROR: There is not such a device!\n";
        }
        if(!(DataBase.hashMapAccessories.get(name) instanceof SmartPlug)){
            return "ERROR: This device is not a smart plug!\n";
        }

        SmartPlug smartPlug =((SmartPlug)DataBase.hashMapAccessories.get(name));

        if(smartPlug.getPlugstatus().equals("Out")){
            return"ERROR: This plug has no item to plug out from that plug!\n";
        }

        smartPlug.setPlugstatus("Out");

        return "";
    }


    /**
     * Plug in the smart plug. Otherwise, return an error message.
     * @return Error message string
     *@param name the name value of the smart device
     *@param ampere the ampere value of the smart device to calculate energy consumption.
     *@author ilhan aras 2210356023
     *
     */
    public static String plugIn(String name , String ampere){

        try{
            float floatAmpere=Float.parseFloat(ampere);

            if(!DataBase.hashMapAccessories.containsKey(name)){
                return "ERROR: There is not such a device!\n";
            }

            if(floatAmpere<0){
                return "ERROR: Ampere value must be a positive number!\n";
            }

            if(!(DataBase.hashMapAccessories.get(name) instanceof SmartPlug)){
                return "ERROR: This device is not a smart plug!\n";
            }
            SmartPlug smartPlug = (SmartPlug)DataBase.hashMapAccessories.get(name);

            if(smartPlug.getPlugstatus().equals("In")){
                return  "ERROR: There is already an item plugged in to that plug!\n";
            }

            smartPlug.setAmpere(floatAmpere);
            smartPlug.setOpenTime(DataBase.globalDate);
            smartPlug.setPlugstatus("In");

        }catch (Exception e){
            return "ERROR: Ampere value must be a integer!\n";
        }
        return "";
    }

    /**
     * Sets kelvin value of smart device.
     * @return Error message string
     *@param name the name value of the smart device
     *@param kelvin the kelvin value of the smart device
     *@author ilhan aras 2210356023
     */
    public static String setKelvinCommand(String name,int kelvin)  {

        if(!DataBase.hashMapAccessories.containsKey(name)){
            return "ERROR: There is not such a device!\n";
        }

        if(!(DataBase.hashMapAccessories.get(name) instanceof SmartLamp)){
            return "ERROR: This device is not a smart lamp!\n";
        }

        if (ErrorHandlingClass.isNotValidKelvin(kelvin)) {
            return "ERROR: Kelvin value must be in range of 2000K-6500K!\n";
        }

        ((SmartLamp)DataBase.hashMapAccessories.get(name)).setKelvin(kelvin);
        return "";

    }

    /**
     * Sets the brightness of a smart lamp device.
     * @param name The name of the smart lamp device to set the brightness.
     * @param brightness The brightness value to set the smart lamp to, in the range of 0-100%.
     * @return An error message if the input is invalid or there is no such device; otherwise, an empty string.
     * @author ilhan aras b2210356023.
     */
    public static String setBrightnessCommand(String name,String brightness) {

            try{
                int intBrightness = Integer.parseInt(brightness);
                if(!DataBase.hashMapAccessories.containsKey(name)){
                    return "ERROR: There is not such a device!\n";
                }
                if(!(DataBase.hashMapAccessories.get(name) instanceof SmartLamp)){
                    return "ERROR: This device is not a smart lamp!\n";
                }
                if (ErrorHandlingClass.isNotValidBrightness(intBrightness)) {
                    return "ERROR: Brightness value must be in range of 0-100%!\n";
                }
                ((SmartLamp)DataBase.hashMapAccessories.get(name)).setBrightness(intBrightness);
                return "";
            }catch (Exception e){
                return "ERROR: Erroneous command!\n";
            }
    }

    /**
     * Sets the color code of a smart color lamp device.
     * @param name The name of the smart color lamp device to set the color code.
     * @param colorCode The hexadecimal color code to set the smart color lamp to.
     * @return An error message if the input is invalid or there is no such device; otherwise, an empty string.
     * @author ilhan aras b2210356023.
     */
    public static String setColorCodeCommand(String name, String colorCode){

        try{
            if(!DataBase.hashMapAccessories.containsKey(name)){
            return "ERROR: This device is not a smart color lamp!\n";
            }
            if(!(DataBase.hashMapAccessories.get(name) instanceof SmartColorLamp)){
                return "ERROR: This device is not a smart color lamp!\n";
            }
            if(ErrorHandlingClass.isNotHexadecimal(colorCode)){
                return "ERROR: Erroneous command!\n";
            }
            ((SmartColorLamp)DataBase.hashMapAccessories.get(name)).setColorcode(colorCode);

        }catch (Exception e){
                return "ERROR: Erroneous command!\n";
        }
        return "";
    }




    /**
     * Sets the brightness and color temperature of a smart lamp device to white.
     * @param name The name of the smart lamp device to set to white.
     * @param kelvin The color temperature in Kelvin to set the smart lamp to, ranging from 2000K to 6500K.
     * @param brightness The brightness level to set the smart lamp to, ranging from 0% to 100%.
     * @return An error message if the input is invalid or there is no such device; otherwise, an empty string.
     * @author ilhan aras b2210356023.
     */
    public static String setWhiteCommand(String name, String kelvin,String brightness){

        try{
            int intKelvin = Integer.parseInt(kelvin);
            int intbrightness = Integer.parseInt(brightness);
            if(!DataBase.hashMapAccessories.containsKey(name)){
                return "ERROR: There is not such a device!\n";
            }
            if(!(DataBase.hashMapAccessories.get(name) instanceof SmartLamp)){
                return "ERROR: This device is not a smart lamp!\n";
            }
            if(ErrorHandlingClass.isNotValidKelvin(intKelvin)){
                return "ERROR: Kelvin value must be in range of 2000K-6500K!\n";
            }
            if(ErrorHandlingClass.isNotValidBrightness(intbrightness)){
                return "ERROR: Brightness must be in range of 0%-100%!\n";
            }
            ((SmartLamp)DataBase.hashMapAccessories.get(name)).setBrightness(intbrightness);
            ((SmartLamp)DataBase.hashMapAccessories.get(name)).setKelvin(intKelvin);
            ((SmartLamp)DataBase.hashMapAccessories.get(name)).setColorMode(false);

        }catch (Exception e){
            return "ERROR: Erroneous command!\n";
        }
        return "";
    }


    /**
     * Sets the color and brightness of a smart color lamp device.
     * @param name The name of the smart color lamp device to set the color for.
     * @param colorCode The hexadecimal color code to set the smart color lamp to.
     * @param brightness The brightness level to set the smart color lamp to, ranging from 0% to 100%.
     * @return An error message if the input is invalid or there is no such device; otherwise, an empty string.
     * @author ilhan aras b2210356023.
     */
    public static String setColorCommand(String name,String colorCode,String brightness){

        try {
            int intBrightness = Integer.parseInt(brightness);
            if (!DataBase.hashMapAccessories.containsKey(name)) {
                return "ERROR: There is not such a device!\n";
            }
            if(!(DataBase.hashMapAccessories.get(name) instanceof SmartColorLamp)){
                return "ERROR: This device is not a smart color lamp!\n";
            }
            if(ErrorHandlingClass.isNotValidBrightness(intBrightness)){
                return "ERROR: Brightness must be in range of 0%-100%!\n";
            }
            if(ErrorHandlingClass.isNotHexadecimal(colorCode)){

                return "ERROR: Erroneous command!\n";
            }

            SmartColorLamp smartColorLamp=(SmartColorLamp)DataBase.hashMapAccessories.get(name);

            (smartColorLamp).setBrightness(intBrightness);
            (smartColorLamp).setColorcode(colorCode);
            (smartColorLamp).setColorMode(true);

        }catch (Exception e){
            return "ERROR: Erroneous command!\n";
        }
        return "";
    }


    /**
     * Skips the specified number of minutes and updates the global date in the database accordingly.
     * @param min A string representing the number of minutes to skip.
     * @return An error message if the input is invalid or the time cannot be reversed; otherwise, an empty string.
     * @author ilhan aras b2210356023.
     */
    public static String skipMinutes(String min){

        try{
            int Intmin=Integer.parseInt(min);
            if(Intmin<0){
                return "ERROR: Time cannot be reversed!\n";
            }
            if(Intmin==0){
                return "ERROR: There is nothing to skip!\n";
            }

            DataBase.globalDate=DataBase.globalDate.plusMinutes(Intmin);
            int size=DataBase.timeLine.size();
            for(int i=0;i<size;i++){

                if(DataBase.globalDate.isAfter(DataBase.timeLine.get(0).getDate()) ||
                        DataBase.globalDate.isEqual(DataBase.timeLine.get(0).getDate()))
                {


                    switchCommand(DataBase.timeLine.get(0));
                    DataBase.timeLine.get(0).setDate(null);
                    DataBase.timeLine.remove(0);

                }else{
                    break;
                }
            }


        }catch (Exception e){
            return("ERROR: Erroneous command!\n");
        }
        return "";
    }


    /**
     * Sets the initial time of the program.
     * @param strTime a String representing the initial time in the format "yyyy-MM-dd_HH:mm:ss".
     * @return a String indicating whether the time has been set successfully or if there was an error.
     * If the time was set successfully, the String contains the message "SUCCESS: Time has been set to [date and time in the correct format]!".
     * If there was an error, the String contains the message "ERROR: Format of the initial date is wrong! Program is going to terminate!".
     * @author ilhan aras b2210356023.
     */
    public static String setInitialTime(String strTime) {

            try{
                int Intyear = Integer.parseInt(strTime.split("-")[0]);
                int Intmonth = Integer.parseInt(strTime.split("-")[1]);
                String strOther = strTime.split("-")[2];
                int Intday = Integer.parseInt(strOther.split("_")[0]);
                String strOtherTime = strOther.split("_")[1];

                int IntHour = Integer.parseInt(strOtherTime.split(":")[0]);
                int IntMin = Integer.parseInt(strOtherTime.split(":")[1]);
                int IntSec = Integer.parseInt(strOtherTime.split(":")[2]);

                DataBase.globalDate=LocalDateTime.of(Intyear,Intmonth ,Intday,IntHour,IntMin,IntSec);
                DataBase.initialCounter++;

            }catch (Exception e){
                return "ERROR: Format of the initial date is wrong! Program is going to terminate!";
            }

        return "SUCCESS: Time has been set to "+DataBase.globalDate.format(DataBase.formatter)+"!";
    }


    /**
     * Sets the global date to the specified date and switches any scheduled accessories whose switch time is equal to
     * or earlier than the new global date.
     * @param strTime a string representing the new global date in the format "yyyy-MM-dd_HH:mm:ss"
     * @return an empty string if the command was executed successfully, or an error message if an error occurred
     * @author ilhan aras b2210356023.
     */
    public static String setTime(String strTime) {

        try{int intYear = Integer.parseInt(strTime.split("-")[0]);
            int intMonth = Integer.parseInt(strTime.split("-")[1]);
            String strOther = strTime.split("-")[2];
            int intDay = Integer.parseInt(strOther.split("_")[0]);
            String strOtherTime = strOther.split("_")[1];

            int intHour = Integer.parseInt(strOtherTime.split(":")[0]);
            int intMin = Integer.parseInt(strOtherTime.split(":")[1]);
            int intSec = Integer.parseInt(strOtherTime.split(":")[2]);

            LocalDateTime newdate=LocalDateTime.of(intYear,intMonth ,intDay,intHour,intMin,intSec);
            if(DataBase.globalDate.isAfter(newdate)){
                return "ERROR: Time cannot be reversed!\n";
            }
            if(DataBase.globalDate.isEqual(newdate)){
                return "ERROR: There is nothing to change!\n";
            }
            DataBase.globalDate=newdate;
                int size=DataBase.timeLine.size();
              for(int i=0;i<size;i++){
                  if(DataBase.globalDate.isAfter(DataBase.timeLine.get(0).getDate()) ||
                          DataBase.globalDate.isEqual(DataBase.timeLine.get(0).getDate())){

                      switchCommand(DataBase.timeLine.get(0));
                      DataBase.timeLine.get(0).setDate(null);
                      DataBase.timeLine.remove(0);
                  }else{
                      break;
                  }
              }

            return "";

        }catch (Exception e){
            return ("ERROR: Time format is not correct!\n");
        }
    }

    /**
     * Executes the "nop" command, which does nothing and advances the time to the next scheduled switch time.
     * @return an empty string if the command was executed successfully, or an error message if an error occurred
     * @author ilhan aras b2210356023.
     */
    public static String nopCommand(){

        if(DataBase.initialCounter==0){
            return "ERROR: First command must be set initial time! Program is going to terminate!\n";
        }
        if(DataBase.timeLine.size()<1){
            return "ERROR: There is nothing to switch!\n";
        }

        LocalDateTime newdate=DataBase.timeLine.get(0).getDate();
        DataBase.globalDate=newdate;
        int size=DataBase.timeLine.size();
        for(int i=0;i<size;i++){
            if(DataBase.globalDate.isAfter(DataBase.timeLine.get(0).getDate()) ||
                    DataBase.globalDate.isEqual(DataBase.timeLine.get(0).getDate()))
            {


                switchCommand(DataBase.timeLine.get(0));
                DataBase.timeLine.get(0).setDate(null);
                DataBase.timeLine.remove(0);

            }else{
                break;
            }
        }
        return "";
    }


    /**
     * Sets the switch time for a specified accessory.
     * @param name the name of the accessory for which the switch time is being set
     * @param strTime the string representation of the switch time in the format "yyyy-MM-dd_HH:mm:ss"
     * @return an empty string if the switch time was set successfully, or an error message if an error occurred
     * @author ilhan aras b2210356023.
     */
    public static String setSwitchTime(String name,String strTime) {

        try {
            int Intyear = Integer.parseInt(strTime.split("-")[0]);
            int Intmonth = Integer.parseInt(strTime.split("-")[1]);
            String strOther = strTime.split("-")[2];
            int Intday = Integer.parseInt(strOther.split("_")[0]);
            String strOtherTime = strOther.split("_")[1];

            int IntHour = Integer.parseInt(strOtherTime.split(":")[0]);
            int IntMin = Integer.parseInt(strOtherTime.split(":")[1]);
            int IntSec = Integer.parseInt(strOtherTime.split(":")[2]);

            if( !DataBase.hashMapAccessories.containsKey(name) ) {
                return "ERROR: There is not such a device!\n";
            }
            LocalDateTime switchTime=LocalDateTime.of(Intyear,Intmonth,Intday,IntHour,IntMin,IntSec);
            if(switchTime.isBefore(DataBase.globalDate)){
                return "ERROR: Switch time cannot be in the past!\n";
            }

            DataBase.hashMapAccessories.get(name).setDate(switchTime);
            DataBase.timeLine.add(DataBase.hashMapAccessories.get(name));
            Collections.sort(DataBase.timeLine, Comparator.comparing(Accessories::getDate));

            if(switchTime.equals(DataBase.globalDate)){
                switchCommand(DataBase.hashMapAccessories.get(name));
                DataBase.timeLine.get(0).setDate(null);
                DataBase.timeLine.remove(0);
            }

            return "";
        }catch (Exception e){
            return "ERROR: Erroneous command!\n";
        }
    }


    /**
     *Removes a smart device from the database and sets its status accordingly.
     * @param name the name of the smart device to be removed.
     * @return a success message with information about the removed smart device, or an error message
     * if there is no smart device with the given name in the database.
     * @author ilhan aras b2210356023.
     */
    public static String remove(String name){

        try {
            Accessories accessorie = DataBase.hashMapAccessories.get(name);

            if ( accessorie.getClass() == SmartPlug.class ) {

                SmartPlug acce = (SmartPlug) accessorie;
                acce.setStatus("off");
                acce.setCloseTime(DataBase.globalDate);

                acce.calculateEnergyConsumption();

                DataBase.hashMapAccessories.remove(name);
                return "SUCCESS: Information about removed smart device is as follows:\n" + acce+"\n";
            }

            else if ( accessorie.getClass() == SmartCamera.class ) {

                SmartCamera acce = (SmartCamera) accessorie;
                acce.setStatus("off");
                acce.setCloseTime(DataBase.globalDate);
                acce.calcMb();
                DataBase.hashMapAccessories.remove(name);
                return "SUCCESS: Information about removed smart device is as follows:\n" + acce+"\n";
            }

            else if ( accessorie.getClass() == SmartLamp.class ) {

                SmartLamp acce = (SmartLamp) accessorie;
                acce.setStatus("off");
                DataBase.hashMapAccessories.remove(name);
                return "SUCCESS: Information about removed smart device is as follows:\n" + acce+"\n";
            }

            else if ( accessorie.getClass() == SmartColorLamp.class) {

                SmartColorLamp acce = (SmartColorLamp) accessorie;
                acce.setStatus("off");
                DataBase.hashMapAccessories.remove(name);
                return "SUCCESS: Information about removed smart device is as follows:\n" + acce+"\n";
            }
        }
        catch (Exception e){
            return ("ERROR: There is not such a device!\n");
        }
        return "";

    }

    /**Changes the name of a smart device in the database.
     @param oldName the old name of the smart device.
     @param newName the new name to be given to the smart device.
     @return an empty string if the name change was successful, or an error message
     *@author ilhan aras b2210356023.
     */
    public static String changeName(String oldName, String newName){

        if(oldName.equals(newName)){
            return "ERROR: Both of the names are the same, nothing changed!\n";
        }
        if(!DataBase.hashMapAccessories.containsKey(oldName)){
            return "ERROR: There is not such a device!\n";
        }

        if(DataBase.hashMapAccessories.containsKey(newName)){
            return "ERROR: There is already a smart device with same name!\n";
        }

        Accessories object=DataBase.hashMapAccessories.get(oldName);
        object.setName(newName);

        LinkedHashMap<String,Accessories> copyHashMap=new LinkedHashMap<>();

        for (String key:DataBase.hashMapAccessories.keySet()) {
            copyHashMap.put(key,DataBase.hashMapAccessories.get(key));
        }

        Set<String> keySet=DataBase.hashMapAccessories.keySet();
        int index=0;

        for (String key:keySet) {
            if( ! key.equals(oldName)){
                index++;
            }else{
                break;
            }
        }
        DataBase.hashMapAccessories.clear();

        int counter=0;
        for (String key:copyHashMap.keySet() ) {
            if(counter==index){
                DataBase.hashMapAccessories.put(newName,copyHashMap.get(oldName));
            }else {
                DataBase.hashMapAccessories.put(key, copyHashMap.get(key));

            }
            counter++;
        }

        return "";
    }

    /**
     * Generates a zReport containing the list of accessories sorted by date in ascending order.
     * @return a String containing the zReport with the list of accessories sorted by date
     *@author ilhan aras b2210356023.
     *
     */
    public static String zReport(){

        Comparator<Accessories> accessoriesComparator = Comparator.nullsFirst(Comparator.comparing(Accessories::getDate, Comparator.nullsLast(Comparator.naturalOrder())));

        LinkedHashMap<String, Accessories> sortedAccessoriesMap = DataBase.hashMapAccessories.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(accessoriesComparator))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        String result="Time is:\t";
        result +=DataBase.globalDate.format(DataBase.formatter)+"\n";
        for(String name : sortedAccessoriesMap.keySet()){
            result+=(sortedAccessoriesMap.get(name))+"\n";
        }
        return result;
    }
}
