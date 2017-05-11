package net.itxiu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by huangshaokang on 17/4/13.
 */
@Data
@Builder
@AllArgsConstructor
public class ContentCount {

    private Integer contentId;
    private Integer views;
    private Integer viewsMonth;
    private Integer viewsWeek;
    private Integer viewsDay;
}
