import com.sun.jndi.toolkit.url.UrlUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filename="words.txt";
        try {
            List<String> list = getMaxLengthListFromFile(filename);
            for(String str:list){
                System.out.print(str+" ");
            }
        }catch (Exception e){
            System.out.println("请检测文件是否存在");
            e.printStackTrace();
        }
    }
    //Get MaxLengthLists from file
    public static List<String > getMaxLengthListFromFile(String filename) throws IOException{
        FileReader fr= new FileReader(filename);
        BufferedReader bufr= new BufferedReader(fr);
        //key is Sorted Letter    value is Collection of Same letter
        HashMap<String ,List<String >> wordcount = new HashMap<>();

        String word=null;
        //Max length Number
        int maxlength=0;
        //one line is one word
        while((word=bufr.readLine())!=null){
            char[] chars = word.toCharArray();
            //sort letter
            Arrays.sort(chars);
            String key=new String(chars);
            List<String> temp = wordcount.getOrDefault(key, new ArrayList<String>());
            temp.add(word);
            wordcount.put(key,temp);
            maxlength=Math.max(maxlength,temp.size());
        }
        for(List<String> ls :wordcount.values()){
            if(ls.size()>=maxlength){
                return ls;
            }
        }
        return null;
    }

}
