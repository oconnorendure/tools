package net.itxiu.webmagic.importnew;


import lombok.extern.slf4j.Slf4j;
import net.itxiu.SpiderApplication;
import net.itxiu.api.ArticleClient;
import net.itxiu.model.Article;
import net.itxiu.model.Content;
import net.itxiu.model.ContentExt;
import net.itxiu.model.ContentTxt;
import net.itxiu.response.ResponseCodeEnum;
import net.itxiu.response.ResponseEntity;
import net.itxiu.webmagic.service.ImgDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * importnew 页面解析管道
 * Created by huangshaokang on 17/3/14.
 */
@Slf4j
@Repository
public class ImportNewPagePipeline implements Pipeline {

    @Autowired
    ArticleClient client;

    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("get page:{}",resultItems.getRequest().getUrl());
        Matcher m = Pattern.compile("http://.*?.html").matcher(resultItems.getRequest().getUrl().toString());
        if(m.find()){
            resultItems.getAll()
                    .forEach((k,v) ->{
                        Html html = (Html)v;
                            ContentExt contentExt = ContentExt.builder()
                                                            .title(html.xpath("//div[@class=entry-header]/h1/text()").toString())
                                                            .originUrl(resultItems.getRequest().getUrl())
                                                            .releaseDate(new Date())
                                                            .mediaType("CK")
                                                            .author("admin")
                                                            .isBold(0).needRegenerate(new Byte("0"))
                                                            .origin("importNew").build();
                            ContentTxt contentTxt = ContentTxt.builder().txt(html.$("div.grid-8").$("div.status-publish").$("div.entry").toString()).build();
                            Article article = Article.builder().content(new Content())
                                    .contentExt(contentExt)
                                    .contentTxt(contentTxt)
                                    .build();

                            try{
                                ResponseEntity<Long> response = client.saveArticle(article);
                                log.info("insert article operation code = {} ,article id = {}", response.getCode(), response.getData());
                                if(ResponseCodeEnum.SUCCESS.getCode().equals(response.getCode())) {
                                    log.info("download content img and save content");
                                    ContentTxt toProcess = article.getContentTxt();
                                    toProcess.setContentId(response.getData().intValue());
                                    toProcess.setTxt(ImgDownloadService.downloadImgAndProcessContent(toProcess.getTxt()));
                                    ResponseEntity<Long> txtResponse = client.saveArticleTxt(toProcess);
                                    if(txtResponse.success()){
                                        log.info("process contentTxt success");
                                    }else{
                                        log.info(txtResponse.toString());
                                    }

                                }
                            }catch(Exception e){
                                log.error("save content failure:",e);
                            }
                    });
        }
    }
}
