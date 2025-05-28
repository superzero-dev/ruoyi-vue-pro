package cn.iocoder.yudao.module.warehouse.service.goods;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.warehouse.controller.admin.goods.vo.*;
import cn.iocoder.yudao.module.warehouse.dal.dataobject.goods.GoodsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.warehouse.dal.mysql.goods.GoodsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.warehouse.enums.ErrorCodeConstants.*;

/**
 * 仓库货物主 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Long createGoods(GoodsSaveReqVO createReqVO) {
        // 插入
        GoodsDO goods = BeanUtils.toBean(createReqVO, GoodsDO.class);
        goodsMapper.insert(goods);
        // 返回
        return goods.getId();
    }

    @Override
    public void updateGoods(GoodsSaveReqVO updateReqVO) {
        // 校验存在
        validateGoodsExists(updateReqVO.getId());
        // 更新
        GoodsDO updateObj = BeanUtils.toBean(updateReqVO, GoodsDO.class);
        goodsMapper.updateById(updateObj);
    }

    @Override
    public void deleteGoods(Long id) {
        // 校验存在
        validateGoodsExists(id);
        // 删除
        goodsMapper.deleteById(id);
    }

    private void validateGoodsExists(Long id) {
        if (goodsMapper.selectById(id) == null) {
            throw exception(GOODS_NOT_EXISTS);
        }
    }

    @Override
    public GoodsDO getGoods(Long id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public PageResult<GoodsDO> getGoodsPage(GoodsPageReqVO pageReqVO) {
        return goodsMapper.selectPage(pageReqVO);
    }

}