/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.bet.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.bet.IBetWinService;
import com.redpack.bet.dao.IBetWinDao;
import com.redpack.bet.model.BetWinDo;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("betWinService")
public class BetWinServiceImpl implements IBetWinService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IBetWinDao  betWinDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public BetWinDo getById(int id){
	  return betWinDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<BetWinDo> selectBetWin(Map<String,Object> parameterMap){
		return betWinDao.selectBetWin(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> updateBetWinById(BetWinDo newBetWinDo){
		logger.debug("updateBetWin(BetWinDo: "+newBetWinDo);
		int i =betWinDao.updateBetWinById(newBetWinDo);
		if(i<1){
			return ResultSupport.buildResult(1, newBetWinDo+"更新失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> addBetWin(BetWinDo newBetWinDo){
		logger.debug("addBetWin: "+newBetWinDo);
		int i =betWinDao.addBetWin(newBetWinDo);
		if(i<1){
			return ResultSupport.buildResult(1, newBetWinDo+"新增失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public IResult<?> deleteById(int id){
		logger.debug("deleteByIdBetWin:"+id);
		int i =betWinDao.deleteById(id);
		if(i<1){
			return ResultSupport.buildResult(1, id+"删除失败");
		}
		IResult<Integer> result = ResultSupport.buildResult(0);
		result.setModel(Integer.valueOf(i));
		return result;
	}

}
