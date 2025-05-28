package cn.iocoder.yudao.module.infra.service.warehouse;

import cn.iocoder.yudao.module.infra.service.file.FileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
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


        return Boolean.FALSE;
    }
}
