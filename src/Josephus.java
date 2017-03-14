import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Josephus {
    public static void main(String[] args) {
        List items = new ArrayList<Integer>();
        items.add(1);
        items.add(2);
        items.add(3);
        items.add(4);
        items.add(5);
        items.add(6);
        items.add(7);

//        items.add(1);

        System.out.println(josephusPermutation(items, 8));
    }

    public static <T> List<T> josephusPermutation(final List<T> items, final int k) {
        int nums = items.size();
        int[] man = new int[nums];//flag
        int count = 1;// 报数编号
        int i = 0;
        Arrays.fill(man, 0);//未被杀的都被标记为0
        List<T> ret = new ArrayList<T>();

        while (true) {
            boolean alldone = true;
            for (int j = 0; j < nums; j++) {
                if (man[j] == 0) {
                    alldone = false;
                }
            }
            if (alldone) {
                break;
            }

            if (i == nums) {
                i = 0;
            }

            if (man[i] != 0) {
                i++;
                continue;
            }

            if (count == k) {
                man[i] = 1;
                ret.add(items.get(i));
                count=1;
            }else{
                count = (count + 1);
            }

            i++;
        }
        return ret;
    }
}