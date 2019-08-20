package com.zjzy.credit.server.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zjzy.credit.common.model.ResultInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @title DummyProvider
 * @description 演示为外部系统提供的功能性服务接口，
 * 这里的服务最终需要注册到eureka。 
 * @author zhiwei.han
 * @date 2019年8月11日
 */
@RestController
@RequestMapping("/dummy")
@Api(value = "用于演示的虚拟服务", tags = { "Dummy服务" })
public class DummyProvider {
    /**
     * say hi
     * @param name 用户名
     * @return 包含打招呼信息的统一返回值
     */
    @ApiOperation(value = "say hello", notes = "用于基础功能演示", response = String.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "name", value = "say hi的目标用户", required = true) })
    @RequestMapping(value = "sayHello", method = RequestMethod.GET)
    public ResultInfo<String> sayHello(String name) {
        return ResultInfo.success("hello," + name);
    }
}
