package com.lt.money.service.impl;

import java.util.List;

import com.lt.money.common.exception.BusinessException;
import com.lt.money.inbean.InAccountDetail;
import com.lt.money.inbean.InGetAccount;
import com.lt.money.outbean.AccountDay;
import com.lt.money.outbean.AccountDayDetail;
import com.lt.money.outbean.AccountMonth;
import com.lt.money.service.MoneyService;

public class MoneyServiceImpl implements MoneyService{

	@Override
	public void saveAccount(InAccountDetail inAccountDetail)
			throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AccountMonth> getAccountMonthList(InGetAccount inGetAccount)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountDay> getAccountDayList(InGetAccount inGetAccount)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountDayDetail> getAccountDetail(InGetAccount inGetAccount)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
