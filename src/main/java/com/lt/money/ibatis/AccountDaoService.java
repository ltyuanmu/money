package com.lt.money.ibatis;

import com.lt.money.model.Account;
import com.lt.money.model.AccountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountDaoService {
    int countByExample(AccountCriteria example);

    int deleteByExample(AccountCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountCriteria example);

    Account selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountCriteria example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountCriteria example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}