package com.cqhg.ensure.service;


import com.cqhg.ensure.entity.LowinfoData;
import com.cqhg.ensure.mapper.LowinfoDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 民政对象信息service
 */
@Service
public class LowinfoDataService {

    @Autowired
    LowinfoDataMapper lowinfoDataMapper;
    public int addLowinfoDataList(List<LowinfoData> lowinfoDataList,String peopleId,String commissionId){
        return lowinfoDataMapper.addLowinfoDataList(lowinfoDataList,peopleId,commissionId);
    }

    public int addLowinfoData(LowinfoData lowinfoData){
        System.out.println("开始执行LowinfoDataService");
        System.out.println("lowinfoDataMapper"+lowinfoDataMapper);
        return lowinfoDataMapper.addLowinfoData(lowinfoData);
    }


}
