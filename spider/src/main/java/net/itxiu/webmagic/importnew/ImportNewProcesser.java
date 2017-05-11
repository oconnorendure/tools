package net.itxiu.webmagic.importnew;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ImportNew爬虫
 * Created by huangshaokang on 17/3/14.
 */
@Slf4j
public class ImportNewProcesser implements PageProcessor {
    //部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    //抓取页数
    private static int pages = 0;

    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        Matcher m = Pattern.compile("http://.*?.html").matcher(page.getUrl().toString());
        if(m.find()){
            page.putField("detail",page.getHtml());
        }else{
            List<Selectable> articles = page.getHtml().css("div.grid-8").css("div.floated-thumb").nodes();
            articles.forEach(node ->{
                  page.addTargetRequest(node.xpath("//div[@class='floated-thumb']/div[@class='post-thumb']/a/@href").toString());
             });

            //从页面发现后续的url地址来抓取
            String next = page.getHtml().xpath("//div[@class='navigation']/a[@class='next']/@href").toString();
            page.addTargetRequest(next);
            pages ++;
            log.info("爬取第{}页文章",pages);
        }
    }

}
