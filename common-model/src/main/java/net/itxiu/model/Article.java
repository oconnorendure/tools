package net.itxiu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by huangshaokang on 17/3/14.
 */
@Data
@Builder
@AllArgsConstructor
public class Article {
    private Content content;
    private ContentExt contentExt;
    private ContentTxt contentTxt;
}
