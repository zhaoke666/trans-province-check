package com.cqhg.ensure.service;

import com.cqhg.ensure.entity.DivorceData;
import com.cqhg.ensure.mapper.DivorceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *离婚登记信息
 */
@Service
public class DivorceService {
    @Autowired
    DivorceMapper mapper;

    /**
     * 批量新增
     * @param list 离婚登记信息
     * @param peopleId 人员ID
     * @param commissionId commissionId
     * @return int
     */
    public int addList(List<DivorceData> list, String peopleId, String commissionId){
        return mapper.addDataList(list,peopleId,commissionId);
    }

}
