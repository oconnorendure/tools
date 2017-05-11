package net.itxiu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

/**
 * Created by huangshaokang on 17/3/14.
 */
@Data
public class Content {

    private Integer contentId;
    //栏目ID
    private Integer channelId = 95;
    //用户id
    private Integer userId = 1;
    //属性ID 1:普通,2:图文,3:焦点,4:头条,5:首页置顶,6:首页4条图文
    private Integer typeId = 1;
    //模型ID
    private Integer modelId = 1;
    //站点ID
    private Integer siteId = 1;
    //排序日期
    private Date sortDate = new Date();
    //固顶级别
    private Integer topLevel = 1;
    //是否有标题图
    private Integer hasTitleImg = 0;
    //是否推荐
    private Integer isRecommend = 0;
    //状态(0:草稿;1:审核中;2:审核通过;3:回收站;4:投稿;5:归档)
    private Integer status = 0;
    //日访问数
    private Integer viewsDay = 0;
    //日评论数
    private Integer commentsDay = 0;
    //日下载数
    private Integer downloadsDay = 0;
    //日顶数
    private Integer upsDay = 0;
    //得分
    private Integer score = 0;
    //推荐级别
    private Integer isRecommendLevel = 1;
}
