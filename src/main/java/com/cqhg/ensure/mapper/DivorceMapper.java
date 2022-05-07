package com.cqhg.ensure.mapper;

import com.cqhg.ensure.entity.DivorceData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *离婚登记信息
 */
@Repository
public interface DivorceMapper {

    //批量新增
    public int addDataList(@Param("list") List<DivorceData> list, @Param("peopleId") String peopleId, @Param("commissionId") String commissionId);
    

}
