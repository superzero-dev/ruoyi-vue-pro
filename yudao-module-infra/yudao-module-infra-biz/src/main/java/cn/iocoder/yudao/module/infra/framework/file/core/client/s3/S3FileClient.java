package cn.iocoder.yudao.module.infra.framework.file.core.client.s3;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.iocoder.yudao.module.infra.framework.file.core.client.AbstractFileClient;
import com.obs.services.ObsClient;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.ObsObject;
import com.obs.services.model.PutObjectRequest;

import java.io.ByteArrayInputStream;

/**
 * 基于 S3 协议的文件客户端，实现 MinIO、阿里云、腾讯云、七牛云、华为云等云服务
 * <p>
 * S3 协议的客户端，采用亚马逊提供的 software.amazon.awssdk.s3 库
 *
 * @author 芋道源码
 */
public class S3FileClient extends AbstractFileClient<S3FileClientConfig> {

    private ObsClient client;

    public S3FileClient(Long id, S3FileClientConfig config) {
        super(id, config);
    }

    @Override
    protected void doInit() {
        // 补全 domain
        if (StrUtil.isEmpty(config.getDomain())) {
            config.setDomain(buildDomain());
        }

        // 初始化客户端
        client = new ObsClient(config.getAccessKey(), config.getAccessSecret(), config.getEndpoint());
    }

    /**
     * 基于 bucket + endpoint 构建访问的 Domain 地址
     *
     * @return Domain 地址
     */
    private String buildDomain() {
        // 如果已经是 http 或者 https，则不进行拼接.主要适配 MinIO
        if (HttpUtil.isHttp(config.getEndpoint()) || HttpUtil.isHttps(config.getEndpoint())) {
            return StrUtil.format("{}/{}", config.getEndpoint(), config.getBucket());
        }
        // 阿里云、腾讯云、华为云都适合。七牛云比较特殊，必须有自定义域名
        return StrUtil.format("https://{}.{}", config.getBucket(), config.getEndpoint());
    }

    @Override
    public String upload(byte[] content, String path, String type) throws Exception {
        PutObjectRequest request = new PutObjectRequest();
        request.setBucketName(config.getBucket());
        request.setObjectKey(path); // 相对路径
        request.setInput(new ByteArrayInputStream(content)); // 文件内容

        // 元数据，主要用于设置文件类型
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(type);
        objectMetadata.setContentLength((long) content.length); // 如果不设置，会有 “ No content length specified for stream data” 警告日志
        request.setMetadata(objectMetadata);

        // 文件为公共读
        request.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);

        // 执行上传
        client.putObject(request);

        // 拼接返回路径
        return config.getDomain() + "/" + path;
    }

    @Override
    public void delete(String path) throws Exception {
        client.deleteObject(config.getBucket(), path);
    }

    @Override
    public byte[] getContent(String path) throws Exception {
        ObsObject obsObject = client.getObject(config.getBucket(), path);
        return IoUtil.readBytes(obsObject.getObjectContent());
    }
}
