/**   
* @Title: TotalAccountServiceImpl.java 
* @Package com.redpack.account.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhangyunhmf
* @date 2018年3月10日 上午2:03:41 
* @version V1.0   
*/
package com.redpack.account.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.redpack.account.dao.IBizUserAccountDao;
import com.redpack.account.faced.ITotalAccountService;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.account.model.UserDo;

/** 
 * @ClassName: TotalAccountServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhangyunhmf
 * @date 2018年3月10日 上午2:03:41 
 *  
 */
@Service("totalAccountService")
public class TotalAccountServiceImpl implements ITotalAccountService{
	
	@Autowired
	IBizUserAccountDao bizUserAccountDao;
	
	/**
	 * 
	 * 累加下级额度
	 * zhangyunhmf
	 *
	 * @see com.redpack.account.faced.IBizUserAccountService#totalAmt(com.redpack.account.model.UserDo, java.lang.String)
	 *
	 */
    @Override
    public BigDecimal totalAmt(UserDo userDo, String accountType) {
    	
    	BigDecimal result = BigDecimal.ZERO;
    	Map<String, Object> map = new HashMap<String,Object>();
    	map.put("userId", userDo.getId());
    	map.put("accountType", accountType);
		BizUserAccountDo userAccount = bizUserAccountDao.getByUserIdAndAccount(map);
		if(userAccount != null){
			result.add(userAccount.getAmount());
		}
		
		List<UserDo> childList = userDo.getChildList();
		if(CollectionUtils.isEmpty(childList)){
			return result;
		}
		
		for(UserDo user : childList){
			BigDecimal tmp = totalAmt(user,accountType);
			result.add(tmp);
		}
		
	    return result;
    }

	/**
	 * 
	 *
	 * zhangyunhmf
	 *
	 * @see com.redpack.account.faced.IBizUserAccountService#totalReferAmt(java.lang.Long, java.lang.String)
	 *
	 */
    @Override
    public BigDecimal totalReferAmt(Long id, String accountType) {
	    
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("referrerId", id);
	    map.put("accountType", accountType);
	    List<Map<String,Object>> result = bizUserAccountDao.totalReferAmt(map );
	    if(CollectionUtils.isEmpty(result)){
	    	return BigDecimal.ZERO;
	    }
	    
	    return (BigDecimal)result.get(0).get("amount");
	    
    }

}
