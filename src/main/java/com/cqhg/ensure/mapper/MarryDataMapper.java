package com.cqhg.ensure.mapper;

import com.cqhg.ensure.entity.MarryData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *婚姻登记信息
 */
@Repository
public interface MarryDataMapper {

    //批量新增
    public int addDataList(@Param("list") List<MarryData> list, @Param("peopleId") String peopleId, @Param("commissionId") String commissionId);


}
