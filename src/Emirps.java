import java.util.Arrays;

class Emirps {
    public static void main(String[] args) {
        findEmirp(1000000);
    }
    public static long[] findEmirp(long n) {
        // your code
        long[] ret = new long[]{0,0,0};
        for(int i=11;i<n;i++){
            if(isLegalPrime(i,n)){
                ret[0]++;
                ret[1]=i;
                ret[2]+=i;
//                System.out.println(i);
            }
        }
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static boolean isLegalPrime(long n,long max) {
            String s = String.valueOf(n);
            String newS="";
            boolean b = true;
            for (int i = 0; i < s.length(); i++) {
                newS+=s.charAt(s.length() - i - 1);
                if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                    b = false;
                }
            }
            long newi = Long.parseLong(newS);
            if(!b){
                return isPrime(n)&&isPrime(newi);
            }else{
                return false;
            }
    }

    public static boolean isPrime(long n) {
        for (int i = 2; i < Math.sqrt(n)+1;i++) {
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

}