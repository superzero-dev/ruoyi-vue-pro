package cn.iocoder.yudao.module.infra.service.warehouse;

import lombok.Data;

import java.util.List;

@Data
public class WordsResult {

    /**
     * 预测结果
     */
    private String predictedLabel;

    /**
     * 预测分数明细
     */
    private List<List<String>> scores;

}
