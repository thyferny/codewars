import java.lang.reflect.Field;
import java.util.Arrays;

public class Guesser {
    public void guess(){
        System.out.println(Arrays.toString(LittleClass.class.getDeclaredFields()));
        String secretValue = null;
        try {
            Field secret = LittleClass.class.getDeclaredField("secret");
            secret.setAccessible(true);
            secretValue = (String) secret.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        LittleClass.isMySecret(secretValue);
    }
}