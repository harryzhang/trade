/**  
 * @Project: deposit-biz-service
 * @Package com.hehenian.deposit.service.account
 * @Title: UserServiceImpl.java
 * @Description: TODO
 * @author: liuzgmf
 * @date 2015年3月5日 上午10:58:12
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.account.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.account.dao.IBizUserAccountDao;
import com.redpack.account.faced.IFeiHongService;
import com.redpack.account.model.BizUserAccountDo;

@Service("manualFeiHongService")
public class ManualFeiHongServiceImpl implements  IFeiHongService {
	
	private static final Logger LOGGER = Logger.getLogger(ManualFeiHongServiceImpl.class);
	@Autowired
	private IBizUserAccountDao bizUserAccountDao;

	

	@Override
	public List<BizUserAccountDo> selectFeiHongUserAccount(
			Map<String, Object> parameterMap) {
		return bizUserAccountDao.selectNotFeiHongUserAccount(parameterMap);
	}



	@Override
	public int selectFeiHongUserAccountCount(Map<String, Object> parameterMap) {
		return bizUserAccountDao.selectNotFeiHongUserAccountCount(parameterMap);
	}

	
	
}
