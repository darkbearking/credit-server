package com.zjzy.credit.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zjzy.credit.common.model.ResultInfo;
import com.zjzy.credit.common.model.page.Direction;
import com.zjzy.credit.common.model.page.Order;
import com.zjzy.credit.common.model.page.Pagination;
import com.zjzy.credit.common.model.page.Sort;
import com.zjzy.credit.server.model.Dummy;
import com.zjzy.credit.server.service.DummyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Dummy controller.
 * @title DummyController
 * @description  用于演示为前端请求提供的服务接口.
 * @author zhiwei.han
 * @date 2019年8月12日
 */
@RestController
@RequestMapping("/dummy")
@Api(value = "用于演示的虚拟服务", tags = { "Dummy服务" })
public class DummyController {
    @Autowired
    private DummyService dummyService;

    /**
     * 所有dummys列表.
     * 其中swagger的各种注解用于生成swagger描述页面，
     * 以便前端团队查看接口情况.
     * @return dummy列表
     */
    @ApiOperation(value = "all dummys", notes = "查看全部dummy列表", response = List.class, httpMethod = "GET")
    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo<List<Dummy>> dummys() {
        return ResultInfo.success(dummyService.allDummys());
    }

    /**
     * 分页查询演示
     * @param pageNumber 当前页码,页码从0开始计数.
     * @param pageSize 每页数据量
     * @param sort 排序字段
     * @param order 排序方向
     * @param q 查询值
     * @return 分页数据信息
     */
    @ApiOperation(value = "查询dummy", notes = "依据查询条件筛选dummy", response = Pagination.class, httpMethod = "GET")
    @RequestMapping(value = "pages", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo<Pagination<Dummy>> queryDummy(@RequestParam(value = "page") Integer pageNumber,
            @RequestParam(value = "rows") Integer pageSize,
            @RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
            @RequestParam(value = "q", required = false) String q) {
        Sort sortObj = new Sort(new Order(Direction.fromString(order), sort));
        Pagination<Dummy> pagination = new Pagination<>(pageNumber, pageSize, sortObj);
        return ResultInfo.success(dummyService.queryByPage(q, pagination));
    }

    /**
     * 新增操作演示
     * @param dummy 新增的dummy记录
     * @return 操作结果，返回数据为操作影响行数
     */
    @ApiOperation(value = "新增dummy", notes = "新增dummy", response = Integer.class, httpMethod = "POST")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo<Integer> add(@RequestBody Dummy dummy) {
        return ResultInfo.success(dummyService.add(dummy));
    }
}
