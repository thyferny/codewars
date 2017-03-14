public class DeltaBits {
    public static void main(String[] args) {
        System.out.println(convertBits(31,14));
    }
    public static int convertBits(int a, int b) {
        int sum= 0 ;
        int and=a^b;
        for(int i=0;i<32;i++){
            sum+=(and>>i&0x01);
        }
      return sum;
    }
}