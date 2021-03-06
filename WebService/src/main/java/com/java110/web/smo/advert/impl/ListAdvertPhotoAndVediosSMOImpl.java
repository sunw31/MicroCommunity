package com.java110.web.smo.advert.impl;

import com.alibaba.fastjson.JSONObject;
import com.java110.core.component.AbstractComponentSMO;
import com.java110.core.context.IPageData;
import com.java110.entity.component.ComponentValidateResult;
import com.java110.utils.constant.ServiceConstant;
import com.java110.utils.exception.SMOException;
import com.java110.utils.util.Assert;
import com.java110.utils.util.BeanConvertUtil;
import com.java110.web.smo.advert.IListAdvertPhotoAndVediosSMO;
import com.java110.web.smo.advert.IListAdvertsSMO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 查询advert服务类
 */
@Service("listAdvertPhotoAndVediosSMOImpl")
public class ListAdvertPhotoAndVediosSMOImpl extends AbstractComponentSMO implements IListAdvertPhotoAndVediosSMO {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> listAdvertPhotoAndVideos(IPageData pd) throws SMOException {
        return businessProcess(pd);
    }

    @Override
    protected void validate(IPageData pd, JSONObject paramIn) {

        //super.validatePageInfo(pd);
        Assert.hasKeyAndValue(paramIn, "machineCode", "请求报文中未包含设备编码");
        Assert.hasKeyAndValue(paramIn, "communityId", "请求报文中未包含小区信息");

        //super.checkUserHasPrivilege(pd, restTemplate, PrivilegeCodeConstant.AGENT_HAS_LIST_ADVERT);
    }

    @Override
    protected ResponseEntity<String> doBusinessProcess(IPageData pd, JSONObject paramIn) {
        //ComponentValidateResult result = super.validateStoreStaffCommunityRelationship(pd, restTemplate);

        /*Map paramMap = BeanConvertUtil.beanCovertMap(result);
        paramIn.putAll(paramMap);*/

        String apiUrl = ServiceConstant.SERVICE_API_URL + "/api/advert.listAdvertPhotoAndVedios" + mapToUrlParam(paramIn);


        ResponseEntity<String> responseEntity = this.callCenterService(restTemplate, pd, "",
                apiUrl,
                HttpMethod.GET);

        return responseEntity;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
