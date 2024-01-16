import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataBase {

    public static int initialCounter=0;
    public static StringBuffer finalOutputStr=new StringBuffer();
    public static  DecimalFormat decimalFormatForMB = new DecimalFormat("###0.00");

    public static  DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
    public static ArrayList<String[]> linesArray= new ArrayList<>();

    public static LinkedHashMap<String,Accessories> hashMapAccessories = new LinkedHashMap<>();
    public static LocalDateTime globalDate;
    public static LinkedList<Accessories> timeLine=new LinkedList<>();


}
