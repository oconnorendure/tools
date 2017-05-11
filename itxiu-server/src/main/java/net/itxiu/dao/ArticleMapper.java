package net.itxiu.dao;

import net.itxiu.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by huangshaokang on 17/3/14.
 */

@Mapper
@Component
public interface ArticleMapper {

    @Insert("insert into jc_content(channel_id,user_id,type_id,model_id,site_id,sort_date,top_level,has_title_img,is_recommend,status,views_day,comments_day,downloads_day,ups_day,score,recommend_level) " +
            "values (#{content.channelId},#{content.userId},#{content.typeId},#{content.modelId},#{content.siteId},#{content.sortDate},#{content.topLevel},#{content.hasTitleImg},#{content.isRecommend},#{content.status},#{content.viewsDay},#{content.commentsDay},#{content.downloadsDay},#{content.upsDay},#{content.score},#{content.isRecommendLevel})")
    @Options(useGeneratedKeys = true, keyProperty = "content.contentId")
    int insertContent(@Param("content") Content content);

    @Insert("insert into jc_content_ext(content_id,title,short_title,author,origin,origin_url,description,release_date,media_path,media_type,title_color,is_bold,title_img,content_img,type_img,link,tpl_content,need_regenerate,tpl_mobile_content,toplevel_date,pigeonhole_date) " +
            "values (#{contentExt.contentId},#{contentExt.title},#{contentExt.shortTitle},#{contentExt.author},#{contentExt.origin},#{contentExt.originUrl},#{contentExt.description},#{contentExt.releaseDate},#{contentExt.mediaPath},#{contentExt.mediaType},#{contentExt.titleColor},#{contentExt.isBold},#{contentExt.titleImg},#{contentExt.contentImg},#{contentExt.typeImg},#{contentExt.link},#{contentExt.tplContent},#{contentExt.needRegenerate},#{contentExt.tplMobileContent},#{contentExt.topLevelDate},#{contentExt.pigeonholeDate})")
    int insertContentExt(@Param("contentExt") ContentExt contentExt);

    @Insert("insert into jc_content_txt(content_id,txt) values (#{contentTxt.contentId},#{contentTxt.txt})")
    int insertContentTxt(@Param("contentTxt") ContentTxt contentTxt);

    @Select("select count(1) from jc_content_ext where origin_url=#{originUrl}")
    int queryArticle(@Param("originUrl") String originUrl);

    @Select("select count(1) from jc_content where content_id=#{contentId}")
    int queryArticleById(@Param("contentId") Integer contentId);

    @Insert("insert into jc_content_count(content_id,views,views_month,views_week,views_day) values (#{count.contentId},#{count.views},#{count.viewsMonth},#{count.viewsWeek},#{count.viewsDay})")
    int insertContentCount(@Param("count") ContentCount count);

    @Insert("insert into jc_content_check(content_id,check_step,is_rejected) values(#{check.contentId},#{check.checkStep},#{check.isRejected})")
    int insertContentCheck(@Param("check")ContentCheck check);
}
