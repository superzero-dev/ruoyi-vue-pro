package cn.iocoder.yudao.module.warehouse.service.goods;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.warehouse.controller.admin.goods.vo.*;
import cn.iocoder.yudao.module.warehouse.dal.dataobject.goods.GoodsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 仓库货物主 Service 接口
 *
 * @author 芋道源码
 */
public interface GoodsService {

    /**
     * 创建仓库货物主
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGoods(@Valid GoodsSaveReqVO createReqVO);

    /**
     * 更新仓库货物主
     *
     * @param updateReqVO 更新信息
     */
    void updateGoods(@Valid GoodsSaveReqVO updateReqVO);

    /**
     * 删除仓库货物主
     *
     * @param id 编号
     */
    void deleteGoods(Long id);

    /**
     * 获得仓库货物主
     *
     * @param id 编号
     * @return 仓库货物主
     */
    GoodsDO getGoods(Long id);

    /**
     * 获得仓库货物主分页
     *
     * @param pageReqVO 分页查询
     * @return 仓库货物主分页
     */
    PageResult<GoodsDO> getGoodsPage(GoodsPageReqVO pageReqVO);

}