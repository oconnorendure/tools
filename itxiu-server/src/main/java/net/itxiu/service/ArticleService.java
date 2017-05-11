package net.itxiu.service;

import net.itxiu.model.Article;
import net.itxiu.model.ContentTxt;

/**
 * Created by huangshaokang on 17/3/16.
 */
public interface ArticleService {
     int saveArticle(Article article);

     int queryArticleByOrigin(String originUrl);

     int queryArticleById(Integer contentId);

     int saveArticleTxt(ContentTxt contentTxt);
}
