package net.itxiu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by huangshaokang on 17/5/5.
 */
@Data
@Builder
@AllArgsConstructor
public class ContentCheck {

    private Integer contentId;
    private Integer checkStep;
    private Integer isRejected;
}
