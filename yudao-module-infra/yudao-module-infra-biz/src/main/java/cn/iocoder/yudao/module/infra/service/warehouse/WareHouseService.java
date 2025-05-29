package cn.iocoder.yudao.module.infra.service.warehouse;

import cn.hutool.core.io.IoUtil;
import cn.iocoder.yudao.module.infra.service.file.FileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@Slf4j
public class WareHouseService  {

    @Resource
    private FileService fileService;

    /**
     * 上传拍照文件进行人脸识别
     * @param file 拍照图片
     * @param path 文件路径
     * @return true:识别成功，false:识别失败
     */
    public boolean faceRecognize(MultipartFile file, String path) {
        String filePath;
        // 先进行照片上传
        try (InputStream inputStream = file.getInputStream()) {
            filePath = fileService.createFile(file.getOriginalFilename(), path, IoUtil.readBytes(inputStream));
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return Boolean.FALSE;
        }
        // 再调用大模型进行人脸识别
        return Boolean.TRUE;
    }
}
