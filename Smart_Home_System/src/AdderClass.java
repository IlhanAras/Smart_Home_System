
public class AdderClass {

    /**
    *Adds new smart camera
    *@return Error message string
    *@param name the name of the object
    *@param mbPerSecond the mbPerSecond of the object
    *@param status the status of the object
    *
    *@author ilhan aras 2210356023
    *
     */
    public  String addSmartCamera(String name,String mbPerSecond,String status){

        if(DataBase.hashMapAccessories.containsKey(name)){
            return ("ERROR: There is already a smart device with same name!\n");
        }
        try{
            float fMbPerSecond =Float.parseFloat(mbPerSecond);
            if(ErrorHandlingClass.isNotValidMbPerSecond(fMbPerSecond)){
                return ("ERROR: Megabyte value must be a positive number!\n");
            }
            if(ErrorHandlingClass.isNotValidStatus(status)){
                return ("ERROR: Erroneous command!\n");
            }

            DataBase.hashMapAccessories.put(name,new SmartCamera(name,fMbPerSecond,status));
            return ("");
        }catch (Exception e){
            return "ERROR: Erroneous command!\n";
        }
    }


    /**
     *Adds new smart camera
     *@return Error message string
     *@param name the name of the object
     *@param mbPerSecond the mbPerSecond of the object
     *
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartCamera(String name,String mbPerSecond){

        if(DataBase.hashMapAccessories.containsKey(name)){
            return ("ERROR: There is already a smart device with same name!\n");
        }
        try{
            float fMbPerSecond =Float.parseFloat(mbPerSecond);
            if(ErrorHandlingClass.isNotValidMbPerSecond(fMbPerSecond)){
                return ("ERROR: Megabyte value must be a positive number!\n");
            }
            DataBase.hashMapAccessories.put(name,new SmartCamera(name,fMbPerSecond));
            return "";
        }catch (Exception e){
            return "ERROR: Erroneous command!\n";
        }
    }

    /**
     *Adds new smart color lamp
     *@return Error message string
     *@param name the name of the object
     *@param status the status of the object
     *@param colorcode the colorcode of the object
     *@param brightness the brightness of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartColorLamp(String name,String status,String colorcode,String brightness){

        try{
            int IntBrightness = Integer.parseInt(brightness);
            if(DataBase.hashMapAccessories.containsKey(name)){
                return ("ERROR: There is already a smart device with same name!\n");
            }
            if(ErrorHandlingClass.isNotValidStatus(status)){
                return ("ERROR: Erroneous command!\n");
            }
            if(ErrorHandlingClass.isNotHexadecimal(colorcode)){
                if(colorcode.length()>8){
                    return ("ERROR: Color code value must be in range of 0x0-0xFFFFFF!\n");
                }
                return ("ERROR: Erroneous command!\n");
            }
            if(ErrorHandlingClass.isNotValidBrightness(IntBrightness)){
                return ("ERROR: Brightness must be in range of 0%-100%!\n");
            }
            DataBase.hashMapAccessories.put(name,new SmartColorLamp(name,status,colorcode,IntBrightness));
            return "";
        }catch (Exception e){
            return ("ERROR: Erroneous command!\n");
        }
    }
    /**
     *Adds new smart color lamp
     * @return Error message string
     *@param name the name of the object
     *@param status the status of the object
     *@param kelvin the kelvin of the object
     *@param brightness the brightness of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartColorLamp(String name,String status,int kelvin,String brightness){

        try{
            int IntBrightness =Integer.parseInt(brightness);

            if(DataBase.hashMapAccessories.containsKey(name)){
                return ("ERROR: There is already a smart device with same name!\n");
            }
            if(ErrorHandlingClass.isNotValidStatus(status)){
                return ("ERROR: Erroneous command!\n");
            }
            if(ErrorHandlingClass.isNotValidKelvin(kelvin)){
                return ("ERROR: Kelvin value must be in range of 2000K-6500K!\n");
            }
            if(ErrorHandlingClass.isNotValidBrightness(IntBrightness)){
                return ("ERROR: Brightness must be in range of 0%-100%!\n");
            }
            DataBase.hashMapAccessories.put(name,new SmartColorLamp(name,status,kelvin,IntBrightness));
            return "";

        }catch (Exception e){
            return ("ERROR: Erroneous command!\n");
        }
    }
    /**
     *Adds new smart color lamp
     * @return Error message string
     *@param name the name of the object
     *@param status the status of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartColorLamp(String name,String status){

        if(DataBase.hashMapAccessories.containsKey(name)){
            return ("ERROR: There is already a smart device with same name!\n");
        }
        if(ErrorHandlingClass.isNotValidStatus(status)){
            return ("ERROR: Erroneous command!\n");
        }
        DataBase.hashMapAccessories.put(name,new SmartColorLamp(name,status));
        return "";
    }
    /**
     *Adds new smart color lamp
     * @return Error message string
     *@param name the name of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartColorLamp(String name){

        if(DataBase.hashMapAccessories.containsKey(name)){
            return ("ERROR: There is already a smart device with same name!\n");
        }
        DataBase.hashMapAccessories.put(name,new SmartColorLamp(name));
        return "";
    }

    /**
     *Adds new smart lamp
     * @return Error message string
     *@param name the name of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartLamp(String name){

        if(DataBase.hashMapAccessories.containsKey(name)){
            return ("ERROR: There is already a smart device with same name!\n");
        }
        DataBase.hashMapAccessories.put(name,new SmartLamp(name));
        return "";
    }
    /**
     *Adds new smart lamp
     * @return Error message string
     *@param name the name of the object
     *@param status the status of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartLamp(String name,String status){

        if(DataBase.hashMapAccessories.containsKey(name)){
            return ("ERROR: There is already a smart device with same name!\n");
        }
        if(ErrorHandlingClass.isNotValidStatus(status)){
            return ("ERROR: Erroneous command!\n");
        }
        DataBase.hashMapAccessories.put(name,new SmartLamp(name,status));
        return "";
    }
    /**
     *Adds new smart lamp
     * @return Error message string
     *@param name the name of the object
     *@param status the status of the object
     *@param kelvin the kelvin of the object
     *@param brightness the brightness of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartLamp(String name,String status,String kelvin,String brightness){

        try{
            int IntBrightness =Integer.parseInt(brightness);
            int IntKelvin =Integer.parseInt(kelvin);
            if(DataBase.hashMapAccessories.containsKey(name)){
                return ("ERROR: There is already a smart device with same name!\n");
            }
            if(ErrorHandlingClass.isNotValidStatus(status)){
                return ("ERROR: Erroneous command!\n");
            }
            if(ErrorHandlingClass.isNotValidKelvin(IntKelvin)){
                return ("ERROR: Kelvin value must be in range of 2000K-6500K!\n");
            }
            if(ErrorHandlingClass.isNotValidBrightness(IntBrightness)){
                return ("ERROR: Brightness must be in range of 0%-100%!\n");
            }
            DataBase.hashMapAccessories.put(name,new SmartLamp(name,status,IntKelvin,IntBrightness));
            return "";
        }catch (Exception e){
            return ("ERROR: Erroneous command!\n");
        }

    }
    /**
     *Adds new smart plug
     * @return Error message string
     *@param name the name of the object
     *@param status the status of the object
     *@param ampere the ampere of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartPlug(String name,String status,String ampere){

        try{
            float fAmpere=Float.parseFloat(ampere);
            if(DataBase.hashMapAccessories.containsKey(name)){
                return ("ERROR: There is already a smart device with same name!\n");
            }
            if(ErrorHandlingClass.isNotValidStatus(status)){
                return ("ERROR: Erroneous command!\n");
            }
            if(ErrorHandlingClass.isNotValidAmpere(fAmpere)){
                return ("ERROR: Ampere value must be a positive number!\n");
            }

            DataBase.hashMapAccessories.put(name,new SmartPlug(name,status,fAmpere));

            return "";
        }catch (Exception e){
            return ("ERROR: Erroneous command!\n");
        }

    }
    /**
     *Adds new smart plug
     * @return Error message string
     *@param name the name of the object
     *@param status the status of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartPlug(String name,String status){

        if(DataBase.hashMapAccessories.containsKey(name)){
            return ("ERROR: There is already a smart device with same name!\n");
        }
        if(ErrorHandlingClass.isNotValidStatus(status)){
            return ("ERROR: Erroneous command!\n");
        }

        DataBase.hashMapAccessories.put(name,new SmartPlug(name,status));
        return "";
    }
    /**
     *Adds new smart plug
     * @return Error message string
     *@param name the name of the object
     *@author ilhan aras 2210356023
     *
     */
    public  String addSmartPlug(String name){

        if(DataBase.hashMapAccessories.containsKey(name)){
            return ("ERROR: There is already a smart device with same name!\n");
        }
        DataBase.hashMapAccessories.put(name,new SmartPlug(name));
        return "";
    }
}
