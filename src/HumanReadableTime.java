import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HumanReadableTime {
  public static void main(String[] args) {
    System.out.println(makeReadable(0));
  }
  public static String makeReadable(int seconds) {
    // Do something

    String hh = String.format("%02d",seconds / 3600);
    String mm = String.format("%02d",seconds % 3600/60);
    String ss = String.format("%02d",seconds % 60);
    return hh+":"+mm+":"+ss;
  }
}