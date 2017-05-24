package net.itxiu.webmagic.schedule;

import lombok.extern.slf4j.Slf4j;
import net.itxiu.webmagic.importnew.ImportNewPagePipeline;
import net.itxiu.webmagic.importnew.ImportNewProcesser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * importnew 定时抓取当天新闻
 * Created by huangshaokang on 17/3/18.
 */
@Component
@Slf4j
public class importNewSchedule {

    @Autowired
    private ImportNewPagePipeline pipeline;


    @Scheduled(cron = "0 0 0/2 * * ? ")//从0点开始,每2个小时执行一次
    public void crawl(){
        log.info("importnew site crawl start");
        Spider.create(new ImportNewProcesser())
                .addUrl("http://www.importnew.com/all-posts/page/1")
                .addPipeline(pipeline)
                .thread(1)
//                .setExitWhenComplete(true)
                .run();
    }
}
