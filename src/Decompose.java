import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Decompose {

  public static void main(String[] args) {
    System.out.println(decompose(11));
  }

  private static String tryDecomp(long nb, long rac) {
    if (nb == 0) return "";
    String l = null;
    long i = rac;
    while (i >= (long)Math.sqrt(nb/2.0) + 1) {
      long diff = nb - i * i;
      rac = (long)Math.sqrt(diff);
      l = tryDecomp(diff, rac);
      if (l != null) { return l + " " + i; }
      i -=1;
    }
    return null;
  }
  public static String decompose(long n) {
    String l = tryDecomp(n * n, (long)Math.sqrt(n * n - 1));
    return l != null ? l.trim() : l;
  }

//  static List<Long> res = null;
//  static int flag = 0;
//
//  public static String decompose(long n) {
//
//    res = null;
//    flag = 0;
//    dfs(n-1, new ArrayList(), n, Math.pow(n, 2));
//
////    return res.size() == 0 ? null : ;
//    if(res==null)
//      return null;
//    else{
//      Collections.reverse(res);
//      StringBuffer sb = new StringBuffer();
//      for (int i = 0; i < res.size(); i++) {
//        sb.append(res.get(i)).append(" ");
//      }
//      sb.deleteCharAt(sb.length()-1);
//      System.out.println(Arrays.toString(res.toArray(new Long[res.size()])));
//      return sb.toString();
//    }
//  }
//
//  public static void dfs(long start, List<Long> path, long n, double nsquare)
//  {
//    if (nsquare == 0)
//    {
//      flag = 1;
//      res = path;
//      return;
//    }
//    if (start == 0)
//      return;
//    for (int i = 0; i < 2; i ++)
//    {
//      if (i == 0)
//      {
//        if (path.size()>0&&start >= path.get(path.size()-1))
//          return;
//        int new_n = (int) Math.floor(Math.sqrt(nsquare - start * start));
//        double new_nsquare = nsquare - start * start;
//        if (new_n >= 0)
//        {
//          List<Long> temp =new ArrayList<>(path);
////          Collections.copy(path,temp);
//          temp.add(start);
//          dfs(new_n, temp, new_n, new_nsquare);
//        }
//      }
//      else
//        dfs(start - 1, path, n, nsquare);
//      if (flag == 1)
//        return;
//    }
//  }
}