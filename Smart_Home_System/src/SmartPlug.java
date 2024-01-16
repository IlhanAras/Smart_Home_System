import java.time.LocalDateTime;

public class SmartPlug  extends Accessories{

    private int volt=220;
    private float ampere;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private double  totalenergyconsumption=0.0;
    private String plugstatus="off";


    public SmartPlug(String name) {
        super.name = name;

    }

    public SmartPlug(String name, String status) {
        if(status.equals("On")){

            this.openTime=DataBase.globalDate;
        }
        super.status = status;
        super.name = name;

    }

    public SmartPlug(String name, String status, float ampere){
        if(status.equals("On")){
            this.openTime=DataBase.globalDate;
        }
        super.status = status;
        super.name = name;
        this.ampere = ampere;

    }

    /**
     * calculates the energy consumption.
     * @author ilhan aras b2210356023.
     */
    public void calculateEnergyConsumption(){
        try {
            long diffInMinutes = java.time.Duration.between(openTime, closeTime).toMinutes();
            totalenergyconsumption += ampere * volt * diffInMinutes / 60;
        }catch (Exception e){

        }
    }


    public int getVolt() {
        return volt;
    }

    public void setVolt(int volt) {
        this.volt = volt;
    }

    public void setAmpere(float ampere) {
        this.ampere = ampere;
    }

    public double getTotalenergyconsumption() {
        return totalenergyconsumption;
    }

    public void setTotalenergyconsumption(double totalenergyconsumption) {
        this.totalenergyconsumption = totalenergyconsumption;
    }

    public String getPlugstatus() {
        return plugstatus;
    }

    public void setPlugstatus(String plugstatus) {
        this.plugstatus = plugstatus;
    }

    public float getAmpere() {
        return ampere;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        String  dateString ="";
        if (date != null) {
             dateString = date.format(DataBase.formatter);
        } else {
            dateString = "null";
        }
        return "Smart Plug "+name+" is "+status.toLowerCase()+" and consumed "+DataBase.decimalFormat.format(totalenergyconsumption)+"W so far (excluding current device)," +
                " and its time to switch its status is "+dateString+".";
    }
}

