package com.alibaba.csb.sentinel;

import com.alibaba.csb.BaseSelfDefProcess;

import java.util.Map;

/**
 * 用户自定义流控接口
 * 通过SPI方式加载，用户将符合 SelfDefFlowControl 接口定义的类打jar包放到broker的 patchLib 目录下。
 * 要求jar 内有 META-INF/services/com/alibaba/csb/sentinel/SelfDefFlowControl 文件，且文件内容为“用户自定义流控实现类完整名”
 * <p>
 * Created by tingbin.ctb
 * 2019/3/25-18:03.
 */
public interface SelfDefFlowControl extends BaseSelfDefProcess {

    /**
     * @param contextMap 服务请求上下文信息map，各信息的key见 BaseSelfDefProcess 常量定义:
     *                   <ul>
     *                   <li> _inner_ecsb_trace_id {@link com.alibaba.csb.BaseSelfDefProcess#TRACE_ID}</li>
     *                   <li> _csb_internal_name_  {@link com.alibaba.csb.BaseSelfDefProcess#CSB_INTERNAL_NAME}</li>
     *                   <li>_csb_broker_ip  {@link com.alibaba.csb.BaseSelfDefProcess#CSB_BROKER_IP}</li>
     *                   <li>_api_name  {@link com.alibaba.csb.BaseSelfDefProcess#API_NAME}</li>
     *                   <li>_api_version  {@link com.alibaba.csb.BaseSelfDefProcess#API_VERION}</li>
     *                   <li>_api_group  {@link com.alibaba.csb.BaseSelfDefProcess#API_GROUP}</li>
     *                   <li>userId  {@link com.alibaba.csb.BaseSelfDefProcess#USER_ID}</li>
     *                   <li>credentail_name  {@link com.alibaba.csb.BaseSelfDefProcess#CREDENTIAL_NAME}</li>
     *                   <li>_api_access_key  {@link com.alibaba.csb.BaseSelfDefProcess#ACCESS_KEY}</li>
     *                   <li>_remote_peer_ip  {@link com.alibaba.csb.BaseSelfDefProcess#REMOTE_PEER_IP}</li>
     *                   </ul>
     * @throws LimitExceedException 如果流控异常，则终止服务后续处理流程，将异常信息返回给CSB客户端
     */
    void process(final Map<String, Object> contextMap) throws LimitExceedException;
}
