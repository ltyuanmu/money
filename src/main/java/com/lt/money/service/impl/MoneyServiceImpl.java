package com.lt.money.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.money.common.exception.BusinessException;
import com.lt.money.common.utils.DateFormatUtil;
import com.lt.money.common.utils.KeyGenerator;
import com.lt.money.ibatis.AccountDaoService;
import com.lt.money.ibatis.AccountDetailDaoService;
import com.lt.money.inbean.InAccountDetail;
import com.lt.money.inbean.InGetAccount;
import com.lt.money.model.Account;
import com.lt.money.model.AccountCriteria;
import com.lt.money.model.AccountDetail;
import com.lt.money.model.AccountDetailCriteria;
import com.lt.money.outbean.AccountDay;
import com.lt.money.outbean.AccountDayDetail;
import com.lt.money.outbean.AccountMonth;
import com.lt.money.service.MoneyService;
@Service("moneyService")
public class MoneyServiceImpl implements MoneyService{

	@Autowired
	private AccountDaoService accountDaoService;
	
	@Autowired
	private AccountDetailDaoService accountDetailDaoService;
	
	@Override
	public void saveAccount(InAccountDetail inAccountDetail)
			throws BusinessException {
		//获得要追加金额 
		BigDecimal money = new BigDecimal(inAccountDetail.getMoney());
		BigDecimal addMoney = new BigDecimal(0);
		if("add".equals(inAccountDetail.getType())){
			addMoney=addMoney.add(money);
		}else if("sub".equals(inAccountDetail.getType())){
			addMoney=addMoney.subtract(money);
		}
		
		String userId = inAccountDetail.getUserId();
		Date accountDay = inAccountDetail.getAccountDay();
		accountDay = DateFormatUtil.formatForDay(accountDay);
		//通过用户和账单日获得用户账单
		Account account = this.getAccountForDay(accountDay, userId);
		if(account==null){
			account = new Account();
			account.setId(KeyGenerator.getUuid());
			account.setFkUserId(userId);
			account.setCreateTime(new Date());
			account.setAccountDay(accountDay);
			account.setTotal(addMoney);
			this.accountDaoService.insert(account);
		}else{
			account.setTotal(account.getTotal().add(addMoney));
			this.accountDaoService.updateByPrimaryKey(account);
		}
		
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setId(KeyGenerator.getUuid());
		accountDetail.setFkAccountId(account.getId());
		accountDetail.setFkUserId(userId);
		accountDetail.setCreateTime(new Date());
		accountDetail.setAccountDesc(inAccountDetail.getDesc());
		accountDetail.setMoney(money);
		accountDetail.setAccountTime(inAccountDetail.getAccountDay());
		if("add".equals(inAccountDetail.getType())){
			accountDetail.setType(0);
		}else if("sub".equals(inAccountDetail.getType())){
			accountDetail.setType(1);
		}
		this.accountDetailDaoService.insert(accountDetail);
	}

	@Override
	public List<AccountMonth> getAccountMonthList(InGetAccount inGetAccount)
			throws BusinessException {
		Date beginDate = DateFormatUtil.formatForMonth(inGetAccount.getBeginDate());
		Date endDate = DateFormatUtil.formatForMonth(inGetAccount.getEndDate());
		AccountCriteria example= new AccountCriteria();
		example.createCriteria().andFkUserIdEqualTo(inGetAccount.getUserId())
				.andAccountDayGreaterThanOrEqualTo(beginDate)
				.andAccountDayLessThan(endDate);
		List<Account> accountList = this.accountDaoService.selectByExample(example);
		List<AccountMonth> list = new ArrayList<AccountMonth>();
		for(Account account : accountList){
			boolean isflag = true;
			Date month = DateFormatUtil.formatForMonth(account.getAccountDay());
			for(AccountMonth accountMonth : list){
				if(DateFormatUtil.compareDate(accountMonth.getMonth(), month)==0){
					isflag = false;
					accountMonth.setMoney(accountMonth.getMoney()+account.getTotal().doubleValue());
					break;
				}
			}
			if(isflag){
				AccountMonth accountMonth = new AccountMonth();
				accountMonth.setMonth(month);
				accountMonth.setMoney(account.getTotal().doubleValue());
				list.add(accountMonth);
			}
		}
		return list;
	}

	@Override
	public List<AccountDay> getAccountDayList(InGetAccount inGetAccount)
			throws BusinessException {
		Date beginDate = DateFormatUtil.formatForDay(inGetAccount.getBeginDate());
		Date endDate = DateFormatUtil.formatForDay(inGetAccount.getEndDate());
		AccountCriteria example= new AccountCriteria();
		example.createCriteria().andFkUserIdEqualTo(inGetAccount.getUserId())
				.andAccountDayGreaterThanOrEqualTo(beginDate)
				.andAccountDayLessThan(endDate);
		List<Account> accountList = this.accountDaoService.selectByExample(example);
		List<AccountDay> list = new ArrayList<AccountDay>();
		for(Account account : accountList){
			AccountDay accountDay =new AccountDay();
			accountDay.setAccountId(account.getId());
			accountDay.setDay(account.getAccountDay());
			accountDay.setMoney(account.getTotal().doubleValue());
			list.add(accountDay);
		}
		return list;
	}

	@Override
	public List<AccountDayDetail> getAccountDetail(InGetAccount inGetAccount)
			throws BusinessException {
		AccountDetailCriteria example = new AccountDetailCriteria();
		example.createCriteria().andFkUserIdEqualTo(inGetAccount.getUserId())
				.andFkAccountIdEqualTo(inGetAccount.getAccountId());
		List<AccountDetail> accountDetailList = this.accountDetailDaoService.selectByExampleWithBLOBs(example);
		List<AccountDayDetail> list = new ArrayList<AccountDayDetail>();
		for(AccountDetail accountDetail : accountDetailList){
			AccountDayDetail accountDay =new AccountDayDetail();
			accountDay.setAccountDetailId(accountDetail.getId());
			accountDay.setDesc(accountDetail.getAccountDesc());
			accountDay.setDay(accountDetail.getAccountTime());
			accountDay.setMoney(accountDetail.getMoney().doubleValue());
			if(accountDetail.getType()==0){
				accountDay.setType("add");
			}else if(accountDetail.getType()==1){
				accountDay.setType("sub");
			}
			list.add(accountDay);
		}
		return list;
	}

	@Override
	public Account getAccountForDay(Date accountDay,String userId) {
		Date accountDate=DateFormatUtil.formatForDay(accountDay);
		AccountCriteria example= new AccountCriteria();
		example.createCriteria().andAccountDayEqualTo(accountDate)
				.andFkUserIdEqualTo(userId);
		List<Account> accountList = this.accountDaoService.selectByExample(example);
		if(accountList.size()>0){
			return accountList.get(0);
		}else{
			return null;
		}
	}
}
