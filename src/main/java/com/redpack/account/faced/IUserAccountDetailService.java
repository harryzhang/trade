/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.account.faced;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.base.result.IResult;
import com.redpack.constant.AccountMsg;


public interface IUserAccountDetailService{

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
	 * 截止到当前日期可转换的积分
	 * @param userId
	 * @return
	 */
	public BigDecimal selectUserTransPoint(Long userId);

	/**
	 * 转换记录
	 * @param jifengAmt
	 * @param pointAmt
	 * @param userId
	 * @return
	 */
	public int insertPointTranRec(BigDecimal jifengAmt, BigDecimal pointAmt,
			Long userId);

	/**
	 * 奖金豆转换记录
	 * @param jifengAmt
	 * @param pointAmt
	 * @param userId
	 * @param type33 操作类型
	 * @return
	 */
	public int insertPointTranRecJjd(BigDecimal jifengAmt, BigDecimal pointAmt,
			Long userId, AccountMsg type33);
	

}
