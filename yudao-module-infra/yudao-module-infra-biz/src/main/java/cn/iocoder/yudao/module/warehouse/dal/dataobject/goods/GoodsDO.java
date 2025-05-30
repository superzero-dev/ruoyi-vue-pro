package cn.iocoder.yudao.module.warehouse.dal.dataobject.goods;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 仓库货物主 DO
 *
 * @author 芋道源码
 */
@TableName("warehouse_goods")
@KeySequence("warehouse_goods_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDO extends BaseDO {

    /**
     * 商品唯一标识
     */
    @TableId
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 规格型号
     */
    private String specification;
    /**
     * 库存数量
     */
    private Integer quantity;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 单价（精确到分）
     */
    private BigDecimal price;
    /**
     * 供应商
     */
    private String supplier;
    /**
     * 商品分类
     */
    private String category;
    /**
     * 商品描述
     */
    private String description;

}