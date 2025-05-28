package cn.iocoder.yudao.module.warehouse.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Infra 错误码枚举类
 *
 * infra 系统，使用 1-001-000-000 段
 */
public interface ErrorCodeConstants {
    ErrorCode GOODS_NOT_EXISTS = new ErrorCode(1_001_000_001, "仓库货物主不存在");


}
