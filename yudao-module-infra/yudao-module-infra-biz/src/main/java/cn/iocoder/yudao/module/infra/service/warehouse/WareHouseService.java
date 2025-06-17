package cn.iocoder.yudao.module.infra.service.warehouse;

import cn.hutool.core.io.IoUtil;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.infra.api.config.ConfigApi;
import cn.iocoder.yudao.module.infra.dal.dataobject.file.FileDO;
import cn.iocoder.yudao.module.infra.service.file.FileService;
import com.cloud.apigateway.sdk.utils.Client;
import com.cloud.apigateway.sdk.utils.Request;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class WareHouseService  {

    /**
     * 模型Art人脸识别接口地址
     */
    private static final String URL = "warehouse.modelArts.url";
    /**
     * 模型Art人脸识别接口的AK
     */
    private static final String AK = "warehouse.modelArts.ak";
    private static final String SK = "warehouse.modelArts.sk";
    private static final String RESULT_SET = "warehouse.modelArts.resultSet";

    @Resource
    private FileService fileService;
    @Resource
    private ConfigApi configApi;

    /**
     * 上传拍照文件进行人脸识别
     * @param file 拍照图片
     * @param path 文件路径
     * @return true:识别成功，false:识别失败
     */
    public boolean faceRecognize(MultipartFile file, String path) throws Exception {
        FileDO fileDO;
        byte[] fileData;
        // 先进行照片上传
        try (InputStream inputStream = file.getInputStream()) {
            fileData = IoUtil.readBytes(inputStream);
            fileDO = fileService.saveFile(file.getOriginalFilename(), path, fileData);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return Boolean.FALSE;
        }
        // 再上传照片到modelArt接口
        ModelArtsResult result = getModelArtResult(fileData);
        // 获取最终预测结果
        String predictedLabel = result.getWordsResult().getPredictedLabel();
        // 查看是否与结果集匹配
        List<String> resultSet = Arrays.stream(configApi.getConfigValueByKey(RESULT_SET).split(",")).toList();
        String resultSetStr = resultSet.stream().filter(str -> StringUtils.equals(str, predictedLabel))
                .findFirst()
                .orElse(null);


        return resultSet.contains(predictedLabel);
    }

    /**
     * 获取模型Art人脸识别结果
     * @param fileData 文件
     * @throws Exception 异常
     */
    private ModelArtsResult getModelArtResult(byte[] fileData) throws Exception {
        Request httpClientRequest = new Request();
        httpClientRequest.setKey(configApi.getConfigValueByKey(AK));
        httpClientRequest.setSecret(configApi.getConfigValueByKey(SK));
        httpClientRequest.setMethod("POST");
        httpClientRequest.setUrl(configApi.getConfigValueByKey(URL));
        httpClientRequest.addHeader("Content-Type", "application/json");

        String fileStr = new String(Base64.encodeBase64(fileData));
        log.info("文件转换为Base64字符串，fileStr: " + fileStr);

       // String body = "{\"images\":\"" + fileStr + "\"}";

        String body = "{" +
                "    \"images\": [" +
                "        {" +
                "            \"fileName.png\": \"" + fileStr +
                "        \"}" +
                "    ]" +
                "}";
        httpClientRequest.setBody(body);
        // 进行签名校验
        HttpRequestBase signedRequest = Client.sign(httpClientRequest);
        log.info("调用模型Art人脸识别接口，httpClientRequest: " + JsonUtils.toJsonString(httpClientRequest));

        log.info("调用模型Art人脸识别接口，signedRequest: " + JsonUtils.toJsonString(signedRequest));

        // 发送请求
        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            CloseableHttpResponse response = closeableHttpClient.execute(signedRequest);
            int resultCode = response.getStatusLine().getStatusCode();
            log.info("response code: " + resultCode);

            String result = EntityUtils.toString(response.getEntity());
            log.info("response info: " + result);
            if (resultCode != 200) {
                throw new RuntimeException("调用大模型失败，结果：" + result);
            }
            // 转换结果为对象
            ModelArtsResult modelArtsResult = ModelArtsResult.fromJson(result);
            if (StringUtils.isNotBlank(modelArtsResult.getErno())) {
                throw new RuntimeException("调用大模型失败，结果：" + result);
            }
            return modelArtsResult;
        } catch (Exception e) {
            log.error("调用模型Art人脸识别接口失败", e);
            throw e;
        }
    }
}
