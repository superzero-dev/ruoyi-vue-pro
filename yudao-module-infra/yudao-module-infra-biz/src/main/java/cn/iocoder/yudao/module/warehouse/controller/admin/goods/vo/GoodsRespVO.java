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

    @Schema(description = "商品唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "30417")
    @ExcelProperty("商品唯一标识")
    private Long id;

    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("商品名称")
    private String name;

    @Schema(description = "规格型号")
    @ExcelProperty("规格型号")
    private String specification;

    @Schema(description = "库存数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("库存数量")
    private Integer quantity;

    @Schema(description = "计量单位")
    @ExcelProperty("计量单位")
    private String unit;

    @Schema(description = "单价（精确到分）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2357")
    @ExcelProperty("单价（精确到分）")
    private BigDecimal price;

    @Schema(description = "供应商")
    @ExcelProperty("供应商")
    private String supplier;

    @Schema(description = "商品分类")
    @ExcelProperty("商品分类")
    private String category;

    @Schema(description = "商品描述", example = "你猜")
    @ExcelProperty("商品描述")
    private String description;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}