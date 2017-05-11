package net.itxiu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangshaokang on 17/3/14.
 */
public class ImageBase64Utils {

    public static void download(String uri,String filename,String savePath) throws Exception{
        URL url = new URL(uri);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(5*1000);
        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        int len;
        File sf = new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+"/"+filename);
        while((len = is.read(bs)) != -1){
            os.write(bs,0,len);
        }
        os.close();
        is.close();
    }

    public static void main(String[] args) {
        String contents = "";
        Matcher matcher = Pattern.compile("(src|SRC|background|BACKGROUND)=\"http://.*?\"").matcher(contents);
        String imgUrl = null;
        String fileName = null;
        while(matcher.find()){
            String match=matcher.group();
            Matcher k=Pattern.compile("http://.*?.(jpg|png|gif)",Pattern.CASE_INSENSITIVE).matcher(match);
            if(k.find()){
                System.out.println(match);
                imgUrl = match.replaceAll("src=\"","").replaceAll("\"","");
                fileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
                try {
                    download(imgUrl, fileName, "/tmp/downloadimg/");
                }catch (Exception e){
                    System.out.println(e);
                }
                contents = contents.replace(match,"src=\"http://itxiu.net/u/"+fileName+"\"");
            }
        }
        System.out.println(contents);
    }
}
