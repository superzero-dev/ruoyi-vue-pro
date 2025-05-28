package cn.iocoder.yudao.module.warehouse.dal.dataobject.goods;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
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
     * 货物唯一ID
     */
    @TableId
    private Long id;
    /**
     * 货物名称
     */
    private String goodsName;
    /**
     * 当前库存数量
     */
    private Integer quantity;
    /**
     * 仓库位置（例：A-01-005）
     */
    private String storageLocation;
    /**
     * 入库时间
     */
    private LocalDateTime storageTime;
    /**
     * 最后出库时间
     */
    private LocalDateTime lastOutboundTime;
    /**
     * 货物状态
     */
    private Integer goodsStatus;
    /**
     * 单件重量（kg）
     */
    private BigDecimal weightKg;
    /**
     * 单件体积（m³）
     */
    private BigDecimal volumeM3;
    /**
     * 采购单价
     */
    private BigDecimal unitPrice;

}