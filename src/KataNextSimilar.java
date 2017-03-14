import java.util.Arrays;

/**
 * Created by thyferny on 2017/3/3.
 */


public class KataNextSimilar {
    public static long nextSmaller(long n)
    {
        String nStr = new String(n+"");
        int nLen = nStr.length();
        int[] nArr = new int[nLen];
        for(int i = 0; i < nLen; i++){
            nArr[i] = Integer.parseInt(nStr.charAt(i)+"");
        }
        boolean flag = true;
        for (int i = nLen - 1; i > -1 && flag; i--) {//
            int x = getBigestSmallerNumIndex(nArr,i);
            if(i != x && x > -1){
                if(0 == nArr[x] && i == 0){
                    continue;
                }
                int tmp = nArr[x];
                for (int j2 = x; j2 > i; j2--) {
                    nArr[j2] = nArr[j2-1];
                }
                nArr[i] = tmp;
                int[] B = Arrays.copyOfRange(nArr, i+1, nLen);
                Arrays.sort(B);
                B = reverse(B);
                System.arraycopy(B, 0, nArr, i+1, B.length);
                flag = false;
            }
        }

        long sum = -1;
        if(!flag){
            sum = 0;
            for (int j = 0; j < nLen; j++) {
                long tmp = (long) (nArr[j]*Math.pow(10, nLen-j-1));
                sum += tmp;
            }
        }

        return sum;
    }

    public static int getBigestSmallerNumIndex(int[] x,int i){
        int tmp = x[i];
        int min=-1;
        int idx = -1;
        for (; i < x.length; i++) {
            if(x[i] < tmp && x[i] > min){
                min = x[i];
                idx = i;
            }
        }
        return idx;
    }

    public static int[] reverse(int[] validData){
        for(int i = 0; i < validData.length / 2; i++)
        {
            int temp = validData[i];
            validData[i] = validData[validData.length - i - 1];
            validData[validData.length - i - 1] = temp;
        }

        return validData;
    }

}
