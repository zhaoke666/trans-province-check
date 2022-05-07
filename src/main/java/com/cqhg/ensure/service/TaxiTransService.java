package com.cqhg.ensure.service;

import com.cqhg.ensure.entity.TaxiTransportation;
import com.cqhg.ensure.mapper.TaxiTransMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *出租汽车运输从业人员基本信息
 */
@Service
public class TaxiTransService {
    @Autowired
    TaxiTransMapper mapper;

    /**
     * 批量新增
     * @param list 出租汽车运输从业人员基本信息
     * @param peopleId 人员ID
     * @param commissionId commissionId
     * @return int
     */
    public int addList(List<TaxiTransportation> list, String peopleId, String commissionId){
        return mapper.addDataList(list,peopleId,commissionId);
    }

}
