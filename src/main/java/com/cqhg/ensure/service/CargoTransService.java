package com.cqhg.ensure.service;

import com.cqhg.ensure.entity.CargoTransportation;
import com.cqhg.ensure.mapper.CargoTransMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *道路货物运输从业人员基本信息
 */
@Service
public class CargoTransService {
    @Autowired
    CargoTransMapper mapper;

    /**
     * 批量新增
     * @param list 道路货物运输从业人员基本信息
     * @param peopleId 人员ID
     * @param commissionId commissionId
     * @return int
     */
    public int addList(List<CargoTransportation> list, String peopleId, String commissionId){
        return mapper.addDataList(list,peopleId,commissionId);
    }

}
