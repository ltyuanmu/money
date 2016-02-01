package com.lt.money.service;

import java.util.Date;
import java.util.List;

import com.lt.money.common.exception.BusinessException;
import com.lt.money.inbean.InAccountDetail;
import com.lt.money.inbean.InGetAccount;
import com.lt.money.model.Account;
import com.lt.money.outbean.AccountDay;
import com.lt.money.outbean.AccountDayDetail;
import com.lt.money.outbean.AccountMonth;

public interface MoneyService {
	/**
	 * 保存账目明细
	 */
	public void saveAccount(InAccountDetail inAccountDetail)throws BusinessException;
	/**
	 * 获得月份账务列表
	 * @return
	 * @throws BusinessException
	 */
	public List<AccountMonth> getAccountMonthList(InGetAccount inGetAccount)throws BusinessException;
	/**
	 * 获得天数账务列表
	 * @return
	 * @throws BusinessException
	 */
	public List<AccountDay> getAccountDayList(InGetAccount inGetAccount)throws BusinessException;
	/**
	 * 获得某一天详细列表
	 * @return
	 * @throws BusinessException
	 */
	public List<AccountDayDetail> getAccountDetail(InGetAccount inGetAccount)throws BusinessException;
	/**
	 * 通过用户名id 账单日获得该日统计账单
	 * @param accountDay
	 * @param userId
	 * @return
	 */
	public Account getAccountForDay(Date accountDay,String userId);
}
