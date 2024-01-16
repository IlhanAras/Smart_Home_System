import java.time.LocalDateTime;

public class SmartCamera  extends Accessories{

    private float mbPerSecond;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private double MBMemoryUsage=0.00;

    public SmartCamera(String name,float mbPerSecond)  {

        this.mbPerSecond=mbPerSecond;
        super.name = name;
    }

    public SmartCamera(String name,float mbPerSecond,String status)  {
        if(status.equals("On")){
           LocalDateTime dt= DataBase.globalDate;
            openTime=dt;
        }
        super.status = status;
        super.name = name;
        this.mbPerSecond=  mbPerSecond;
    }

    /**
     * calculates the mb usage
     * @author ilhan aras b2210356023.
     */
    public void calcMb(){
        long diffInMinutes = java.time.Duration.between(openTime, closeTime).toMinutes();;
        MBMemoryUsage+= (diffInMinutes*mbPerSecond);
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

    public float getMbPerSecond() {
        return mbPerSecond;
    }

    public void setMbPerSecond(float mbPerSecond)  {

        this.mbPerSecond=mbPerSecond;
    }


    @Override
    public String toString() {
        String  dateString ="";
        if (date != null) {
            dateString = date.format(DataBase.formatter);
        } else {
            dateString = "null";
        }
        return "Smart Camera " +name+" is "+status.toLowerCase()+" and used "+DataBase.decimalFormatForMB.format(MBMemoryUsage)+" MB of storage so far (excluding current status),"+
                " and its time to switch its status is "+dateString+".";
    }
}
