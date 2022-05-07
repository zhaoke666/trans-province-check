package com.cqhg.ensure.mapper;

import com.cqhg.ensure.entity.TaxiTransportation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *出租汽车运输从业人员基本信息
 */
@Repository
public interface TaxiTransMapper {

    //批量新增
    public int addDataList(@Param("list") List<TaxiTransportation> list, @Param("peopleId") String peopleId, @Param("commissionId") String commissionId);


}
