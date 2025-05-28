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

    @Schema(description = "货物名称", example = "张三")
    private String goodsName;

    @Schema(description = "当前库存数量")
    private Integer quantity;

    @Schema(description = "仓库位置（例：A-01-005）")
    private String storageLocation;

    @Schema(description = "入库时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] storageTime;

    @Schema(description = "最后出库时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastOutboundTime;

    @Schema(description = "货物状态", example = "1")
    private Integer goodsStatus;

    @Schema(description = "单件重量（kg）")
    private BigDecimal weightKg;

    @Schema(description = "单件体积（m³）")
    private BigDecimal volumeM3;

    @Schema(description = "采购单价", example = "29238")
    private BigDecimal unitPrice;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}