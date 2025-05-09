package cn.iocoder.yudao.module.system.job;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestJob implements JobHandler {
    @TenantJob
    @Override
    public String execute(String param) throws Exception {
        log.info("this is a test Job!!!!!!!!!!!!!!!");

        return "success";
    }
}
