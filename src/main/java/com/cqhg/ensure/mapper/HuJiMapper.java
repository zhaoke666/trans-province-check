package com.cqhg.ensure.mapper;

import com.cqhg.ensure.entity.HuJiData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *户籍信息
 */
@Repository
public interface HuJiMapper {

    //批量新增
    public int addDataList(@Param("list") List<HuJiData> list, @Param("peopleId") String peopleId, @Param("commissionId") String commissionId);
    

}
