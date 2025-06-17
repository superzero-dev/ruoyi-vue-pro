package cn.iocoder.yudao.module.infra.service.warehouse;

import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import lombok.Data;

@Data
public class ModelArtsResult {

    private String erno;

    private String msg;

    private WordsResult wordsResult;

    public static ModelArtsResult fromJson(String jsonStr) {
        return JsonUtils.parseObject(jsonStr, ModelArtsResult.class);
    }

    public String toString() {
        return JsonUtils.toJsonString(this);
    }
}
