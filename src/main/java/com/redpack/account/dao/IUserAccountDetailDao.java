/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.redpack.account.model.UserAccountDetailDo;
@Repository
public interface IUserAccountDetailDao {

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public UserAccountDetailDo getById(int id);
	
	/**
	 *根据条件查询列表
	 */
	public List<UserAccountDetailDo> selectUserAccountDetail(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateUserAccountDetailById(UserAccountDetailDo newUserAccountDetailDo);
	
	/**
	 * 新增
	 */
	public int addUserAccountDetail(UserAccountDetailDo newUserAccountDetailDo);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);

	/**
	 * 取最后一次转换记录
	 * @param userId
	 * @return
	 */
	public Map<String, Object> selectLastTransPoint(Long userId);

	/**
	 * 查询可转换金额
	 * @param paraMap
	 * @return
	 */
	public BigDecimal selectUserTransPoint(Map<String, Object> paraMap);

	/**
	 * 积分释放记录
	 * @param jifengAmt
	 * @param pointAmt
	 * @param userId
	 * @param endDate 转换截止日期
	 * @return
	 */
	public int insertPointTranRec(@Param("tranPoint") BigDecimal jifengAmt, @Param("totalPoint")BigDecimal pointAmt,
			@Param("userId")Long userId, @Param("endDate") Date endDate);

	/**
	 * 奖金豆转换记录
	 * @param jifengAmt
	 * @param pointAmt
	 * @param userId
	 * @param optType 
	 * @return
	 */
	public int insertPointTranRecJjd(@Param("tranPoint")BigDecimal jifengAmt,  @Param("totalPoint")BigDecimal pointAmt,
			@Param("userId")Long userId, @Param("optType")String optType);

	

}
