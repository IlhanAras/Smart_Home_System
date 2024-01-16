
public class SmartLamp extends Accessories{

    protected int kelvin=4000;
    protected int brightness=100;
    protected boolean isColorMode=false;

    public SmartLamp(String name) {
        super.name=name;
    }

    public SmartLamp(String name,String status) {
        super.name=name;
        super.status = status;
    }
    public SmartLamp(String name,String status,int kelvin,int brightness)  {
        super.name=name;
        super.status = status;
        this.kelvin = kelvin;
        this.brightness=brightness;
    }


    public int getKelvin() {
        return kelvin;
    }

    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness=brightness;
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
        return "Smart Lamp "+name+" is "+status.toLowerCase()+" and its kelvin value is "+kelvin+"K with "+brightness+"% brightness," +
                " and its time to switch its status is "+dateString+".";
    }
}
