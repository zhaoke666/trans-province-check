package com.cqhg.ensure.mapper;

import com.cqhg.ensure.entity.CargoTransportation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *道路货物运输从业人员基本信
 */
@Repository
public interface CargoTransMapper {

    //批量新增
    public int addDataList(@Param("list") List<CargoTransportation> list, @Param("peopleId") String peopleId, @Param("commissionId") String commissionId);


}
