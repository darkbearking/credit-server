package com.zjzy.credit.server.service;

import java.util.List;

import com.zjzy.credit.common.model.page.Pagination;
import com.zjzy.credit.server.model.Dummy;

/**
 * @title DummyService
 * @description 用于演示的虚拟服务 
 * @author zhiwei.han
 * @date 2019年8月12日
 */
public interface DummyService {
    /**
     * 列举dummy 列表
     * @return dummy列表
     */
    List<Dummy> allDummys();

    /**
     * 分页查询
     * @param q 查询参数
     * @param pagination   分页参数
     * @return 包含分页的统计信息和数据
     */
    Pagination<Dummy> queryByPage(String q, Pagination<Dummy> pagination);

    /**
     * 新增
     * @param dummy 待新增操作
     * @return 操作影响行数
     */
    int add(Dummy dummy);
}
