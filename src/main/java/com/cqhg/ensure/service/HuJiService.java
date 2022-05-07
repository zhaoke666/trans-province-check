package com.cqhg.ensure.service;

import com.cqhg.ensure.entity.CargoTransportation;
import com.cqhg.ensure.entity.HuJiData;
import com.cqhg.ensure.mapper.CargoTransMapper;
import com.cqhg.ensure.mapper.HuJiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *户籍信息
 */
@Service
public class HuJiService {
    @Autowired
    HuJiMapper mapper;

    /**
     * 批量新增
     * @param list 户籍信息
     * @param peopleId 人员ID
     * @param commissionId commissionId
     * @return int
     */
    public int addList(List<HuJiData> list, String peopleId, String commissionId){
        return mapper.addDataList(list,peopleId,commissionId);
    }

}
