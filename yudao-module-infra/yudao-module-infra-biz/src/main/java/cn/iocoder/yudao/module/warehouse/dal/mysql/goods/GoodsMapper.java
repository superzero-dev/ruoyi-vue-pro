package cn.iocoder.yudao.module.warehouse.dal.mysql.goods;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.warehouse.dal.dataobject.goods.GoodsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.warehouse.controller.admin.goods.vo.*;

/**
 * 仓库货物主 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GoodsMapper extends BaseMapperX<GoodsDO> {

    default PageResult<GoodsDO> selectPage(GoodsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GoodsDO>()
                .likeIfPresent(GoodsDO::getGoodsName, reqVO.getGoodsName())
                .eqIfPresent(GoodsDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(GoodsDO::getStorageLocation, reqVO.getStorageLocation())
                .betweenIfPresent(GoodsDO::getStorageTime, reqVO.getStorageTime())
                .betweenIfPresent(GoodsDO::getLastOutboundTime, reqVO.getLastOutboundTime())
                .eqIfPresent(GoodsDO::getGoodsStatus, reqVO.getGoodsStatus())
                .eqIfPresent(GoodsDO::getWeightKg, reqVO.getWeightKg())
                .eqIfPresent(GoodsDO::getVolumeM3, reqVO.getVolumeM3())
                .eqIfPresent(GoodsDO::getUnitPrice, reqVO.getUnitPrice())
                .betweenIfPresent(GoodsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GoodsDO::getId));
    }

}