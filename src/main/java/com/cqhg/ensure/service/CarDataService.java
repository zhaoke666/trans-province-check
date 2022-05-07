package com.cqhg.ensure.service;

import com.cqhg.ensure.entity.CarData;
import com.cqhg.ensure.entity.LowinfoData;
import com.cqhg.ensure.mapper.CarDataMapper;
import com.cqhg.ensure.mapper.LowinfoDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *车辆信息
 */
@Service
public class CarDataService {
    @Autowired
    CarDataMapper mapper;

    /**
     * 批量新增
     * @param list 车辆信息list
     * @param peopleId 人员ID
     * @param commissionId commissionId
     * @return int
     */
    public int addList(List<CarData> list, String peopleId, String commissionId){
        return mapper.addDataList(list,peopleId,commissionId);
    }

}
