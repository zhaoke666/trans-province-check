package com.cqhg.ensure.service;

import com.cqhg.ensure.entity.MarryData;
import com.cqhg.ensure.mapper.MarryDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *婚姻登记信息
 */
@Service
public class MarryDataService {
    @Autowired
    MarryDataMapper mapper;

    /**
     * 批量新增
     * @param list 婚姻登记信息
     * @param peopleId 人员ID
     * @param commissionId commissionId
     * @return int
     */
    public int addList(List<MarryData> list, String peopleId, String commissionId){
        return mapper.addDataList(list,peopleId,commissionId);
    }

}
