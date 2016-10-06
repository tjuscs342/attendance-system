package vo;

import lombok.Data;

/**
 * Created by lichen.ll on 2016/10/6.
 */
public @Data
class UpdateApplyVO {
    Long applyId;
    Integer type;
    String start;
    String end;
    String reason;
}
