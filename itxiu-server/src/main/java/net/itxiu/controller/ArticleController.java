package net.itxiu.controller;

import lombok.extern.slf4j.Slf4j;
import net.itxiu.model.Article;
import net.itxiu.model.ContentTxt;
import net.itxiu.response.ResponseCodeEnum;
import net.itxiu.response.ResponseEntity;
import net.itxiu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangshaokang on 17/3/28.
 */
@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService service;

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public ResponseEntity<Integer> saveArticle(@RequestBody Article article){
        ResponseEntity<Integer> response = new ResponseEntity<>();
        if(service.queryArticleByOrigin(article.getContentExt().getOriginUrl()) > 0){
               log.warn("url [{}] already crawled,break!",article.getContentExt().getOriginUrl());
               response.setCode(ResponseCodeEnum.BUSINESS_ERROR.getCode());
               response.setMessage("url already crawled,break!");
        }else{
            response.setData(service.saveArticle(article));
        }

        return response;
    }

    @RequestMapping(value="/saveContentTxt",method = RequestMethod.POST)
    public ResponseEntity<Integer> saveArticleTxt(@RequestBody ContentTxt contentTxt){
        ResponseEntity<Integer> response = new ResponseEntity<>();
        if(service.queryArticleById(contentTxt.getContentId()) > 0){
            service.saveArticleTxt(contentTxt);
            response.setData(contentTxt.getContentId());
        }else{
            log.error("no content id,stop insert contentTxt");
        }
        return response;
    }
}
