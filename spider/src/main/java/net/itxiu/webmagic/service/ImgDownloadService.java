package net.itxiu.webmagic.service;

import lombok.extern.slf4j.Slf4j;
import net.itxiu.util.ImageBase64Utils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片下载服务
 * Created by huangshaokang on 17/3/14.
 */
@Service
@Slf4j
public class ImgDownloadService {
    //本地图片地址
//    private static final String PATH = "/www/u/cms/www/";
//    private static final String PATH = "/tmp/";
    private static final String PATH = "/home/web/download/";

    private static final String SEARCH_URL_REG = "src=\"http://.*?\"";
    private static final String SEARCH_IMG_REG = "http://.*?.(jpg|png|gif|jpeg)";

    private static final String ITXIU_FILE_PATH = "http://itxiu.net/u/cms/www/";

    public static String downloadImgAndProcessContent(String content){

        Matcher matcher = Pattern.compile(SEARCH_URL_REG).matcher(content);
        String imgUrl = null;
        String fileName = null;
        DateTime dt = new DateTime();
        while(matcher.find()){
            String match=matcher.group();
            Matcher k=Pattern.compile(SEARCH_IMG_REG,Pattern.CASE_INSENSITIVE).matcher(match);
            if(k.find()){
                imgUrl = match.replaceAll("src=\"","").replaceAll("\"","");
                log.info("match one pic :[{}]",imgUrl);
                fileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
                try {
                    ImageBase64Utils.download(imgUrl, fileName, PATH + dt.toString("yyyyMM") + "/");
                }catch (Exception e){
                    log.error("download img:{} \\n error",imgUrl,e);
                }
                content = content.replace(match,"src=\"" + ITXIU_FILE_PATH + dt.toString("yyyyMM") + "/" + fileName + "\"")
                        .replaceAll("'","&apos;");
            }
        }
        return content;
    }
}
