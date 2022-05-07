package com.cqhg.ensure.service;

import com.cqhg.ensure.entity.TrafficTransit;
import com.cqhg.ensure.mapper.TrafficTransitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *道路旅客运输从业人员基本信息
 */
@Service
public class TrafficTransitService {
    @Autowired
    TrafficTransitMapper mapper;

    /**
     * 批量新增
     * @param list 道路旅客运输从业人员基本信息
     * @param peopleId 人员ID
     * @param commissionId commissionId
     * @return int
     */
    public int addList(List<TrafficTransit> list, String peopleId, String commissionId){
        return mapper.addDataList(list,peopleId,commissionId);
    }

}
