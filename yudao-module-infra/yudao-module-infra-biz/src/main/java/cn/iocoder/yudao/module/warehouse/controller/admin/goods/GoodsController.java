package cn.iocoder.yudao.module.warehouse.controller.admin.goods;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.warehouse.controller.admin.goods.vo.*;
import cn.iocoder.yudao.module.warehouse.dal.dataobject.goods.GoodsDO;
import cn.iocoder.yudao.module.warehouse.service.goods.GoodsService;

@Tag(name = "管理后台 - 仓库货物主")
@RestController
@RequestMapping("/warehouse/goods")
@Validated
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @PostMapping("/create")
    @Operation(summary = "创建仓库货物主")
    @PreAuthorize("@ss.hasPermission('warehouse:goods:create')")
    public CommonResult<Long> createGoods(@Valid @RequestBody GoodsSaveReqVO createReqVO) {
        return success(goodsService.createGoods(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新仓库货物主")
    @PreAuthorize("@ss.hasPermission('warehouse:goods:update')")
    public CommonResult<Boolean> updateGoods(@Valid @RequestBody GoodsSaveReqVO updateReqVO) {
        goodsService.updateGoods(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除仓库货物主")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('warehouse:goods:delete')")
    public CommonResult<Boolean> deleteGoods(@RequestParam("id") Long id) {
        goodsService.deleteGoods(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得仓库货物主")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('warehouse:goods:query')")
    public CommonResult<GoodsRespVO> getGoods(@RequestParam("id") Long id) {
        GoodsDO goods = goodsService.getGoods(id);
        return success(BeanUtils.toBean(goods, GoodsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得仓库货物主分页")
    @PreAuthorize("@ss.hasPermission('warehouse:goods:query')")
    public CommonResult<PageResult<GoodsRespVO>> getGoodsPage(@Valid GoodsPageReqVO pageReqVO) {
        PageResult<GoodsDO> pageResult = goodsService.getGoodsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GoodsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出仓库货物主 Excel")
    @PreAuthorize("@ss.hasPermission('warehouse:goods:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGoodsExcel(@Valid GoodsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GoodsDO> list = goodsService.getGoodsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "仓库货物主.xls", "数据", GoodsRespVO.class,
                        BeanUtils.toBean(list, GoodsRespVO.class));
    }

}