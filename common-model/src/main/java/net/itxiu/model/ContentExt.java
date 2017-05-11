package net.itxiu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by huangshaokang on 17/3/14.
 */
@Data
@Builder
@AllArgsConstructor
public class ContentExt {
    //文章id
    private Integer contentId;
    //标题
    private String title;
    //简短标题
    private String shortTitle;
    //作者
    private String author;
    //来源
    private String origin;
    //来源链接
    private String originUrl;
    //描述
    private String description;
    //发布日期
    private Date releaseDate;
    //媒体路径
    private String mediaPath;
    //媒体类型
    private String mediaType;
    //标题颜色
    private String titleColor;
    //是否加粗
    private Integer isBold;
    //标题图片
    private String titleImg;
    //内容图片
    private String contentImg;
    //类型图片
    private String typeImg;
    //外部链接
    private String link;
    //指定模板
    private String tplContent;
    //需要重新生成静态页
    private Byte needRegenerate;
    //手机内容页模板
    private String tplMobileContent;
    //固顶到期日期
    private Date topLevelDate;
    //归档日期
    private Date pigeonholeDate;
}
