package net.itxiu.service.impl;

import net.itxiu.dao.ArticleMapper;
import net.itxiu.model.*;
import net.itxiu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章保存service
 * Created by huangshaokang on 17/3/15.
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    ArticleMapper mapper;

    @Override
    @Transactional
    public int saveArticle(Article article){
        Integer id = mapper.insertContent(article.getContent());

        if(id >0){
            ContentExt ext = article.getContentExt();
            ext.setContentId(article.getContent().getContentId());
            ContentTxt txt = article.getContentTxt();
            txt.setContentId(article.getContent().getContentId());
            mapper.insertContentExt(ext);
        }
        return article.getContent().getContentId();
    }

    @Override
    public int queryArticleByOrigin(String originUrl) {
        return mapper.queryArticle(originUrl);
    }

    @Override
    public int queryArticleById(Integer contentId) {
        return mapper.queryArticleById(contentId);
    }

    @Override
    @Transactional
    public int saveArticleTxt(ContentTxt txt){
        Integer id = mapper.insertContentTxt(txt);
        ContentCount contentCount = ContentCount.builder()
                .contentId(txt.getContentId())
                .views(1).viewsDay(1).viewsWeek(1).viewsMonth(1)
                .build();
        mapper.insertContentCount(contentCount);
        ContentCheck check = ContentCheck.builder().contentId(txt.getContentId())
                .checkStep(1).isRejected(0).build();
        mapper.insertContentCheck(check);
        return id;
    }
}
