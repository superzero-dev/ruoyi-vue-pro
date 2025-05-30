package cn.iocoder.yudao.module.warehouse.controller.admin.goods.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 仓库货物主新增/修改 Request VO")
@Data
public class GoodsSaveReqVO {

    @Schema(description = "商品唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "30417")
    private Long id;

    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "商品名称不能为空")
    private String name;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "库存数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库存数量不能为空")
    private Integer quantity;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "单价（精确到分）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2357")
    @NotNull(message = "单价（精确到分）不能为空")
    private BigDecimal price;

    @Schema(description = "供应商")
    private String supplier;

    @Schema(description = "商品分类")
    private String category;

    @Schema(description = "商品描述", example = "你猜")
    private String description;

}