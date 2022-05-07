package com.cqhg.ensure.mapper;

import com.cqhg.ensure.entity.TrafficTransit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *道路旅客运输从业人员基本信
 */
@Repository
public interface TrafficTransitMapper {

    //批量新增
    public int addDataList(@Param("list") List<TrafficTransit> list, @Param("peopleId") String peopleId, @Param("commissionId") String commissionId);


}
