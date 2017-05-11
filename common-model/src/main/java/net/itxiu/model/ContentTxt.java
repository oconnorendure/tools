package net.itxiu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 文章内容实体
 * Created by huangshaokang on 17/3/14.
 */
@Data
@Builder
@AllArgsConstructor
public class ContentTxt {

    private Integer contentId;
    //文章内容
    private String txt;

    public int txtSize(){
        return this.txt.length();
    }

    public boolean unOverSize(){
        return !overSize();
    }

    public boolean overSize(){
        return txtSize() > 20000;
    }
}
