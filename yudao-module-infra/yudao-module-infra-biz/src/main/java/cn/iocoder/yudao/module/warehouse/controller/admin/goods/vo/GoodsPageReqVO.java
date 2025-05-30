package cn.iocoder.yudao.module.warehouse.controller.admin.goods.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 仓库货物主分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GoodsPageReqVO extends PageParam {

    @Schema(description = "商品名称", example = "赵六")
    private String name;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "库存数量")
    private Integer quantity;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "单价（精确到分）", example = "2357")
    private BigDecimal price;

    @Schema(description = "供应商")
    private String supplier;

    @Schema(description = "商品分类")
    private String category;

    @Schema(description = "商品描述", example = "你猜")
    private String description;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}