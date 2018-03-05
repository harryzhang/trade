/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.basedata.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.basedata.IBannerService;
import com.redpack.basedata.dao.IBannerDao;
import com.redpack.basedata.model.BannerDo;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


@Service("bannerService")
public class BannerServiceImpl implements IBannerService {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
    private IBannerDao  bannerDao;
	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	@Override
	public BannerDo getById(int id){
	  return bannerDao.getById(id);
	}
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<BannerDo> selectBanner(Map<String,Object> parameterMap){
		return bannerDao.selectBanner(parameterMap);
	}
	
	/**
	 * 更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateBannerById(BannerDo newBannerDo){
		logger.debug("updateBanner(BannerDo: "+newBannerDo);
		return bannerDao.updateBannerById(newBannerDo);
	}
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addBanner(BannerDo newBannerDo){
		logger.debug("addBanner: "+newBannerDo);
		return bannerDao.addBanner(newBannerDo);
		
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int deleteById(int id){
		logger.debug("deleteByIdBanner:"+id);
		return bannerDao.deleteById(id);
	}

}
