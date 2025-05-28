package cn.iocoder.yudao.module.warehouse.controller.admin.goods.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 仓库货物主新增/修改 Request VO")
@Data
public class GoodsSaveReqVO {

    @Schema(description = "货物唯一ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28766")
    private Long id;

    @Schema(description = "货物名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "货物名称不能为空")
    private String goodsName;

    @Schema(description = "当前库存数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前库存数量不能为空")
    private Integer quantity;

    @Schema(description = "仓库位置（例：A-01-005）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "仓库位置（例：A-01-005）不能为空")
    private String storageLocation;

    @Schema(description = "入库时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入库时间不能为空")
    private LocalDateTime storageTime;

    @Schema(description = "最后出库时间")
    private LocalDateTime lastOutboundTime;

    @Schema(description = "货物状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "货物状态不能为空")
    private Integer goodsStatus;

    @Schema(description = "单件重量（kg）")
    private BigDecimal weightKg;

    @Schema(description = "单件体积（m³）")
    private BigDecimal volumeM3;

    @Schema(description = "采购单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "29238")
    @NotNull(message = "采购单价不能为空")
    private BigDecimal unitPrice;

}