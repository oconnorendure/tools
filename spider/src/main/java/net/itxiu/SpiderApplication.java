package net.itxiu;

import net.itxiu.api.ArticleClient;
import net.itxiu.webmagic.importnew.ImportNewPagePipeline;
import net.itxiu.webmagic.importnew.ImportNewProcesser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;

/**
 * Created by huangshaokang on 17/3/14.
 */
@ComponentScan(basePackages = {"net.itxiu"})
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class SpiderApplication implements CommandLineRunner {
    public static ImportNewPagePipeline importNewPagePipeline;
    public static ArticleClient client;

    public SpiderApplication(ImportNewPagePipeline importNewPagePipeline,ArticleClient client){
        this.importNewPagePipeline = importNewPagePipeline;
        this.client = client;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(SpiderApplication.class).web(false).run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Spider.create(new ImportNewProcesser())
                .addUrl("http://www.importnew.com/all-posts/page/1")
                .addPipeline(new FilePipeline("/tmp"))
                .addPipeline(importNewPagePipeline)
                .thread(1)
                .run();
    }
}
