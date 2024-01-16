import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public abstract class ErrorHandlingClass {

    private ErrorHandlingClass() {
    }

    public static boolean isNotValidDate(String date){
        try {
            LocalDateTime dateTime = LocalDateTime.parse(date, DataBase.formatter);
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }


    public static boolean isNotValidStatus(String status){
        if (status.equals("On") || status.equals("Off")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNotValidAmpere(float ampere){
        if (ampere > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNotValidMbPerSecond(float mbPerSecond){
        if (mbPerSecond > 0) {
            return false;
        }else{
            return true;
        }
    }

    public static boolean isNotValidBrightness(int brightness){
        if (brightness >= 0&&brightness<=100) {
            return false;
        }else{
            return true;
        }
    }

    public static boolean isNotHexadecimal(String str) {
        String regex = "^0[xX][0-9a-fA-F]{6}$";
        return !str.matches(regex);
    }

    public static boolean isNotValidKelvin(int kelvin){
        if (kelvin >= 2000&&kelvin<=6500) {
            return false;
        }else{
            return true;
        }
    }
}
