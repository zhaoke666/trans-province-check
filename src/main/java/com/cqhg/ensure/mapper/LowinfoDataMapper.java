package com.cqhg.ensure.mapper;


import com.cqhg.ensure.entity.LowinfoData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 民政对象mapper
 */
@Repository
public interface LowinfoDataMapper {
    //批量新增
    public int addLowinfoDataList(@Param("list") List<LowinfoData> list,@Param("peopleId") String peopleId,@Param("commissionId") String commissionId);
    //单个新增
    public int addLowinfoData(@Param("lowinfoData") LowinfoData lowinfoData);

}
