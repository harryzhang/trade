/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserAccountDetailDao;
import com.redpack.account.faced.IUserAccountDetailService;
import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.constant.AccountMsg;
import com.redpack.utils.DateUtil;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("userAccountDetailService")
public class UserAccountDetailServiceImpl implements IUserAccountDetailService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IUserAccountDetailDao  userAccountDetailDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public UserAccountDetailDo getById(int id){
	  return userAccountDetailDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<UserAccountDetailDo> selectUserAccountDetail(Map<String,Object> parameterMap){
		return userAccountDetailDao.selectUserAccountDetail(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateUserAccountDetailById(UserAccountDetailDo newUserAccountDetailDo){
		logger.debug("updateUserAccountDetail(UserAccountDetailDo: "+newUserAccountDetailDo);
		return userAccountDetailDao.updateUserAccountDetailById(newUserAccountDetailDo);
		
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addUserAccountDetail(UserAccountDetailDo newUserAccountDetailDo){
		logger.debug("addUserAccountDetail: "+newUserAccountDetailDo);
		return userAccountDetailDao.addUserAccountDetail(newUserAccountDetailDo);
		
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		logger.debug("deleteByIdUserAccountDetail:"+id);
		return userAccountDetailDao.deleteById(id);
	}

	/**
	 * 截止到当前日期可转换的积分
	 * @param userId
	 * @return
	 */
	@Override
	public BigDecimal selectUserTransPoint(Long userId) {
		Map<String,Object> lastTranMap = userAccountDetailDao.selectLastTransPoint(userId);
		Date startDate = null;
		Date endDate;
		try {
			endDate = DateUtil.getCurrentDate();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("获取转换结束日期出错");
		}
		
		if(null != lastTranMap ){
			startDate = (Date)lastTranMap.get("endDate");
			Date createtime = (Date)lastTranMap.get("createTime");
			String createTimeStr = DateUtil.formatDate(createtime, "yyyy-MM-dd");
			String currentTimeStr = DateUtil.formatDate(endDate, "yyyy-MM-dd");
			if(createTimeStr.equals(currentTimeStr) ){
				throw new RuntimeException("一天只能转换一次");
			}
		}
		
		
		Map<String,Object>  paraMap= new HashMap<String,Object>();
		if(null != startDate){
			paraMap.put("startDate", startDate);
		}
		
		
		endDate = DateUtil.dateAddDay(endDate, -90);//90天之前的
		paraMap.put("endDate", endDate);
		paraMap.put("userId", userId);
		return userAccountDetailDao.selectUserTransPoint(paraMap);
		
		
	}

	/**
	 * 记录积分转换记录
	 */
	@Override
	public int insertPointTranRec(BigDecimal jifengAmt, BigDecimal pointAmt,
			Long userId) {
		Date endDate;
		try {
			endDate = DateUtil.getCurrentDate();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("获取转换结束日期出错");
		}
		endDate = DateUtil.dateAddDay(endDate, -90);//90天之前的
		String currentTimeStr = DateUtil.formatDate(endDate, "yyyy-MM-dd");
		endDate = DateUtil.parseDateOrNull(currentTimeStr, "yyyy-MM-dd");
		return userAccountDetailDao.insertPointTranRec(jifengAmt,pointAmt,userId,endDate);
	}

	@Override
	public int insertPointTranRecJjd(BigDecimal jifengAmt, BigDecimal pointAmt,
			Long userId,AccountMsg opt) {
		return userAccountDetailDao.insertPointTranRecJjd(jifengAmt,pointAmt,userId,opt.toString());
	}


}
