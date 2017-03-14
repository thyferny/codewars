public class IntTriangles {
    public static int giveTriang(int per) {
        // your code
        int count = 0;
        for(int a = 1; a < per; a++)
        {
            for(int b = a+1; a+b < per; b++)
            {
                int left = a * a + a * b + b * b;
                int c = (int)Math.sqrt(left);
                if (left == c*c && c > b && a+b+c <= per)
                {
                    count++;
                }
            }
        }
        return count;
    }
}
