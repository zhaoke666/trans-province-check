package com.cqhg.ensure.service;

import com.cqhg.ensure.entity.TownWorkerInsurance;
import com.cqhg.ensure.mapper.TownWorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *城镇职工机关养老保险状态信息
 */
@Service
public class TownWorkerService {
    @Autowired
    TownWorkerMapper mapper;

    /**
     * 批量新增
     * @param list 城镇职工机关养老保险状态信息
     * @param peopleId 人员ID
     * @param commissionId commissionId
     * @return int
     */
    public int addList(List<TownWorkerInsurance> list, String peopleId, String commissionId){
        return mapper.addDataList(list,peopleId,commissionId);
    }

}
