class PrimeConsec {

    public static int consecKprimes(int k, long[] arr) {
        if(arr.length <= 1) {
            return 0;
        }
        int[] pcount = new int[arr.length];
        for(int i = 0, l = arr.length; i < l; i++) {
            pcount[i] = count(arr[i]);
        }
        int count = 0;
        for(int i = 0, l = arr.length; i < l - 1; i++) {
            if(pcount[i] == k && pcount[i+1] == k) {
                count++;
            }
        }
        return count;
    }

    private static int count(long n) {
        int count = 0;
        int i = 2;
        while(n > 1) {
            while(n % i == 0) {
                n /= i;
                count++;
            }
            i++;
        }
        return count;
    }
}