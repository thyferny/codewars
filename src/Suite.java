import java.text.DecimalFormat;

public class Suite {
    public static void main(String[] args) {
        System.out.println(going(10));
    }

    public static double going(int n) {
		// your code
    
    double sum=0.0;
    for(int i=n;i>0;i--){
      double tmp=1;
      for(int j=n;j>i;j--){
        tmp*=j;
      }
      sum+=1.0/tmp;
    }
        return sum;
	}

}