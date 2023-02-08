package dd.code.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.util.Length;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author zhangyong
 * @Description
 * @Date 13:56 2022/1/26 2022
 **/
public class Test2 {
    @Test
    public void longSubStr(){
        String s ="sdfsafdasfdas";
        getLongSubStr(s);
    }

    public void getLongSubStr(String s){
        int length = s.length();
        int maxSize =0;
        for(int i=0; i<length; i++){
            int maxSubSize = doGetStr(s,i,i,length);
            int maxSubSize2 = doGetStr(s,i,i+1,length);
            if(maxSubSize>maxSize){
                maxSize = maxSubSize;
            }
            if(maxSubSize2>maxSize){
                maxSize = maxSubSize2;
            }
        }
        System.out.printf("maxSize"+maxSize);

    }

    public int doGetStr(String s,int L,int R, int length){
        while(L>=0 && R< length && s.charAt(L) == s.charAt(R) ){
            L--;
            R++;

        }
        return R-L+1;
    }


}