

public class SmartColorLamp extends SmartLamp{

    private String colorcode="0xFFFFFF";


    public SmartColorLamp(String name) {
        super(name);
    }
    public SmartColorLamp(String name,String status) {
        super(name);

        super.status = status;
    }
    public SmartColorLamp(String name,String status,int kelvin,int brightness)  {
        super(name);
        super.status = status;
        super.kelvin = kelvin;
        super.brightness=brightness;
    }
    public SmartColorLamp(String name,String status,String colorcode,int brightness)  {
        super(name);
        super.isColorMode=true;
        super.status = status;
        this.colorcode=colorcode;
        super.brightness=brightness;

    }


    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode)  {
        this.colorcode=colorcode;
    }

    public boolean isColorMode() {
        return isColorMode;
    }

    public void setColorMode(boolean colorMode) {
        isColorMode = colorMode;
    }

    @Override
    public String toString() {
        String  dateString ="";
        if (date != null) {
            dateString = date.format(DataBase.formatter);
        } else {
            dateString = "null";
        }

        if(isColorMode){
            return "Smart Color Lamp " +name+" is "+status.toLowerCase()+" and its color value is "+colorcode+" with "+brightness+"% brightness,"+
                    " and its time to switch its status is "+dateString+".";
        }
        else{
            return "Smart Color Lamp " +name+" is "+status.toLowerCase()+" and its color value is "+kelvin+"K with "+brightness+"% brightness,"+
                    " and its time to switch its status is "+dateString+".";
        }
    }
}
