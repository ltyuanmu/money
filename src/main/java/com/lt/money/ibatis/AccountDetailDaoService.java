package com.lt.money.ibatis;

import com.lt.money.model.AccountDetail;
import com.lt.money.model.AccountDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountDetailDaoService {
    int countByExample(AccountDetailCriteria example);

    int deleteByExample(AccountDetailCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(AccountDetail record);

    int insertSelective(AccountDetail record);

    List<AccountDetail> selectByExampleWithBLOBs(AccountDetailCriteria example);

    List<AccountDetail> selectByExample(AccountDetailCriteria example);

    AccountDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AccountDetail record, @Param("example") AccountDetailCriteria example);

    int updateByExampleWithBLOBs(@Param("record") AccountDetail record, @Param("example") AccountDetailCriteria example);

    int updateByExample(@Param("record") AccountDetail record, @Param("example") AccountDetailCriteria example);

    int updateByPrimaryKeySelective(AccountDetail record);

    int updateByPrimaryKeyWithBLOBs(AccountDetail record);

    int updateByPrimaryKey(AccountDetail record);
}