package cn.iocoder.yudao.module.warehouse.controller;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.infra.controller.admin.file.vo.file.FilePageReqVO;
import cn.iocoder.yudao.module.infra.controller.admin.file.vo.file.FileRespVO;
import cn.iocoder.yudao.module.infra.controller.app.file.vo.AppFileUploadReqVO;
import cn.iocoder.yudao.module.infra.dal.dataobject.file.FileDO;
import cn.iocoder.yudao.module.infra.service.file.FileService;
import cn.iocoder.yudao.module.infra.service.warehouse.WareHouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "智能仓管系统")
@RestController
@RequestMapping("/app-api/warehouse")
@Validated
@Slf4j
public class WareHouseController {

    @Resource
    private FileService fileService;

    @Resource
    private WareHouseService wareHouseService;

    @PostMapping("/photo/upload")
    @Operation(summary = "上传拍照文件进行人脸识别")
    @PermitAll
    public CommonResult<Boolean> uploadFile(AppFileUploadReqVO uploadReqVO) throws Exception {
        // 获取文件
       return CommonResult.success(wareHouseService.faceRecognize(uploadReqVO.getFile(), uploadReqVO.getPath()));
    }

    @GetMapping("/photo/page")
    @Operation(summary = "获得文件分页")
    @PermitAll
    public CommonResult<PageResult<FileRespVO>> getFilePage(@Valid FilePageReqVO pageVO) {
        PageResult<FileDO> pageResult = fileService.getFilePage(pageVO);
        return success(BeanUtils.toBean(pageResult, FileRespVO.class));
    }
}
