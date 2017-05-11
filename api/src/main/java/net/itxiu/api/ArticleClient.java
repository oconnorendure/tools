package net.itxiu.api;

import net.itxiu.model.Article;
import net.itxiu.model.ContentTxt;
import net.itxiu.response.ResponseEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by huangshaokang on 17/3/28.
 */
@FeignClient(name="itxiu-server",url="http://10.27.212.117:8088")
public interface ArticleClient {


    @RequestMapping(value = "/article/save",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    ResponseEntity<Long> saveArticle(@RequestBody Article article);

    @RequestMapping(value = "/article/saveExt",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    ResponseEntity<Long> saveArticleExt(@RequestBody Article article);

    @RequestMapping(value = "/article/saveContentTxt",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    ResponseEntity<Long> saveArticleTxt(@RequestBody ContentTxt contentTxt);

}
