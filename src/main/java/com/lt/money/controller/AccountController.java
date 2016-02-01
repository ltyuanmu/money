package com.lt.money.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.text.DateFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.lt.money.code.BaseCode;
import com.lt.money.common.bean.ResultBean;
import com.lt.money.common.exception.BusinessException;
import com.lt.money.common.utils.DateFormatUtil;
import com.lt.money.inbean.InAccountDetail;
import com.lt.money.inbean.InGetAccount;
import com.lt.money.outbean.AccountDay;
import com.lt.money.outbean.AccountDayDetail;
import com.lt.money.outbean.AccountMonth;
import com.lt.money.service.MoneyService;


@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private MoneyService moneyService;
	/**
	 * 保存账单明细
	 * @param inAccountDetail
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/detail", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<ResultBean> createAccountDetail(InAccountDetail inAccountDetail) {
		Long start = Calendar.getInstance().getTimeInMillis();
		try {
			//校验
			checkCreateAccountDetail(inAccountDetail);
			this.moneyService.saveAccount(inAccountDetail);
			return ResponseEntity.status(HttpStatus.CREATED).body(ResultBean.getSuccess(start, BaseCode.SUCCESS));
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					ResultBean.getSuccess(start, e.getMessage()));
		}
	}
	private void checkCreateAccountDetail(InAccountDetail inAccountDetail) throws BusinessException{
		if(StringUtils.isEmpty(inAccountDetail.getType())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
		if(inAccountDetail.getAccountDay()==null){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
		if(inAccountDetail.getMoney()==0){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
	}
	/**
	 * 获得账单的统计列表
	 * @param inGetAccount
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<ResultBean> getAccountList(InGetAccount inGetAccount) {
		Long start = Calendar.getInstance().getTimeInMillis();
		try {
			//校验
			checkGetAccountList(inGetAccount);
			if("month".equals(inGetAccount.getGetType())){
				List<AccountMonth> list = this.moneyService.getAccountMonthList(inGetAccount);
				return ResponseEntity.ok(ResultBean.getSuccess(start, list));
			}else if("day".equals(inGetAccount.getGetType())){
				List<AccountDay> list = this.moneyService.getAccountDayList(inGetAccount);
				return ResponseEntity.ok(ResultBean.getSuccess(start, list));
			}else{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResultBean.getSuccess(start, BaseCode.NOTFOUND));
			}
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					ResultBean.getSuccess(start, e.getMessage()));
		}
	}
	private void checkGetAccountList(InGetAccount inGetAccount) throws BusinessException{
		if(!"month".equals(inGetAccount.getGetType())
				&&!"day".equals(inGetAccount.getGetType())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
		if(inGetAccount.getBeginDate()==null){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
		if(inGetAccount.getEndDate()==null){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
	}
	/**
	 * 获得账单详细列表
	 * @param inGetAccount
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/detail/list", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<ResultBean> getAccountDetailList(InGetAccount inGetAccount) {
		Long start = Calendar.getInstance().getTimeInMillis();
		try {
			//校验
			checkGetAccountDetailList(inGetAccount);
			List<AccountDayDetail> list = this.moneyService.getAccountDetail(inGetAccount);
			return ResponseEntity.ok(ResultBean.getSuccess(start, list));
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					ResultBean.getSuccess(start, e.getMessage()));
		}
	}
	private void checkGetAccountDetailList(InGetAccount inGetAccount) throws BusinessException{
		if(StringUtils.isEmpty(inGetAccount.getAccountId())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
	}

}
