import java.util.*;

public class BrainLuck {


    public static void main(String[] args) {
        System.out.println(new BrainLuck(",[.[-],]").process("Codewars" + ((char) 0)));
    }
    public String code = null;
    public BrainLuck(String code) {
        this.code = code;
    }

    public String process(String input) {
        int dp = 0;
        int value = 0;
        int ip=0;
        int braceCount = 0;
        String output = "";
        String data="";
        for(int i=0, l= code.length(); i < l; i++) {
            switch(code.charAt(i)){
                case '>':
                    dp++;
                    if(dp >= data.length()) {
                        data += (char)0;
                    }
                    break;
                case '<':
                    dp--;
                    if(dp < 0) {
                        data = (char)0 + data;
                        dp = 0;
                    }
                    break;
                case '+':
                    if(data.charAt(dp)==0){
                        value=0;
                    }else{
                        value=1;
                    }
//                    value = (data.charAt(dp)||0) + 1;
                    if(value>255) value = 0;
                    data = data.substring(0,dp) + (char)(value) + data.substring(dp+1);
                    break;
                case '-':
                    if(data.charAt(dp)==0){
                        value=-1;
                    }else{
                        value=0;
                    }
//                    value = ((int)data.charAt(dp)||0) - 1;
                    if(value<0) value = 255;
                    data = data.substring(0,dp) + (char)(value) + data.substring(dp+1);
                    break;
                case '.':
                    output+=(data.charAt(dp));
                    break;
                case ',':
                    int tmp = 0;
                    if(input.charAt(ip++)==0){
                        tmp=0;
                    }else{
                        tmp=1;
                    }
//                    data = data.substring(0,dp) + String.fromCharCode(input.charCodeAt(ip++)||0) + data.substring(dp+1);
                    if(data.length()>(dp+1)) {
                        data = data.substring(0, dp) + (char)tmp + data.substring(dp + 1);
                    }else{
                        data = data.substring(0, dp) + (char)tmp;
                    }
                    break;
                case '[':
                    value = data.charAt(dp);
                    if(value!=0){
                        braceCount = 1;
                        i++;
                        while(i<l && braceCount>0) {
                            if(code.charAt(i)=='[') braceCount++;
                            if(code.charAt(i)==']') braceCount--;
                            i++;
                        }
                    }
                    break;
                case ']':
                    value = data.charAt(dp);
                    if(value!=0){
                        braceCount = 1;
                        i--;
                        while(i>=0 && braceCount>0) {
                            if(code.charAt(i)=='[') braceCount--;
                            if(code.charAt(i)==']') braceCount++;
                            i--;
                        }
                    }
                    break;
            }
        }
        return output;
    }
}