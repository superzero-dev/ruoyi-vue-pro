package cn.iocoder.yudao.module.infra.controller;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.infra.controller.app.file.vo.AppFileUploadReqVO;
import cn.iocoder.yudao.module.infra.service.warehouse.WareHouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "智能仓管系统")
@RestController
@RequestMapping("/warehouse")
@Validated
@Slf4j
public class WareHouseController {

    @Resource
    private WareHouseService wareHouseService;

    @PostMapping("/upload")
    @Operation(summary = "上传拍照文件进行人脸识别")
    @PermitAll
    public CommonResult<Boolean> uploadFile(AppFileUploadReqVO uploadReqVO) throws Exception {
        // 获取文件
       return CommonResult.success(wareHouseService.faceRecognize(uploadReqVO.getFile(), uploadReqVO.getPath()));
    }
}
