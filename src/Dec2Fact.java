public class Dec2Fact {


    public static void main(String[] args) {
//		System.out.println();
        long a = 101129453440100l;
//		System.out.println(factString2Dec(dec2FactString()));
        System.out.println(dec2FactString(a));
        System.out.println(factString2Dec("341010"));
    }

    public static String dec2FactString(long nb) {
        StringBuffer sb = new StringBuffer();
        int rd = 1;
        while (nb > 0) {
            sb.append(Long.toHexString(nb % rd).toUpperCase());
            nb = nb / rd;
            rd++;
        }
        return sb.reverse().toString();
    }

    public static long factString2Dec(String str) {

        int multi = Integer.parseInt(str.charAt(0) + "", 16);
        long ret = multi * (str.length() - 1);
        for (int i = 1; i < str.length() - 1; i++) {
            multi = Integer.parseInt(str.charAt(i) + "", 16);
            ret += multi;
            ret *= (str.length() - i - 1);
        }
        return ret;
    }

}