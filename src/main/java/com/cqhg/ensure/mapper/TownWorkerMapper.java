package com.cqhg.ensure.mapper;

import com.cqhg.ensure.entity.TownWorkerInsurance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *城镇职工机关养老保险状态信息
 */
@Repository
public interface TownWorkerMapper {

    //批量新增
    public int addDataList(@Param("list") List<TownWorkerInsurance> list, @Param("peopleId") String peopleId, @Param("commissionId") String commissionId);


}
