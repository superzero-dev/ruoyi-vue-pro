package cn.iocoder.yudao.module.warehouse.controller.admin.goods.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 仓库货物主 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GoodsRespVO {

    @Schema(description = "货物唯一ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28766")
    @ExcelProperty("货物唯一ID")
    private Long id;

    @Schema(description = "货物名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("货物名称")
    private String goodsName;

    @Schema(description = "当前库存数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前库存数量")
    private Integer quantity;

    @Schema(description = "仓库位置（例：A-01-005）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("仓库位置（例：A-01-005）")
    private String storageLocation;

    @Schema(description = "入库时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("入库时间")
    private LocalDateTime storageTime;

    @Schema(description = "最后出库时间")
    @ExcelProperty("最后出库时间")
    private LocalDateTime lastOutboundTime;

    @Schema(description = "货物状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("货物状态")
    private Integer goodsStatus;

    @Schema(description = "单件重量（kg）")
    @ExcelProperty("单件重量（kg）")
    private BigDecimal weightKg;

    @Schema(description = "单件体积（m³）")
    @ExcelProperty("单件体积（m³）")
    private BigDecimal volumeM3;

    @Schema(description = "采购单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "29238")
    @ExcelProperty("采购单价")
    private BigDecimal unitPrice;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}