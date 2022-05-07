package com.cqhg.ensure.mapper;

import com.cqhg.ensure.entity.CarData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *车辆信息
 */
@Repository
public interface CarDataMapper {

    //批量新增
    public int addDataList(@Param("list") List<CarData> list, @Param("peopleId") String peopleId, @Param("commissionId") String commissionId);


}
