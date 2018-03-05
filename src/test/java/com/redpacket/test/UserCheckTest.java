package com.redpacket.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.account.dao.IUserAccountIncomeDao;
import com.redpack.account.dao.IUserDao;
import com.redpack.account.dao.IUserInfoDao;
import com.redpack.account.faced.IKangjiIncomeService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserAccountIncomeDo;
import com.redpack.account.model.UserDo;
import com.redpack.group.IGroupUserService;
import com.redpack.member.dao.IMemberDao;
import com.redpack.member.model.KuangjiUserAccountDo;

public class UserCheckTest extends BaseTestCase {

	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGroupUserService groupUserService;
	
	@Autowired
    private IUserAccountIncomeDao  userAccountIncomeDao;
	@Autowired
	public IKangjiIncomeService kangjiIncomeService;
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Autowired
	private IMemberDao memberDao;
	
	int i = 0;
	int leveal = 0;
	
	@Test
	public void testFindAllChild(){
		Long userId =1902L;
		UserDo user = userService.getById(userId);
	
		printUserInfo(user);
		allChild(userId);
		
	}
	
	//寻找所有子用户
	void allChild(Long userId){
		leveal ++ ;
		List<UserDo> userList =  userService.getChildList(userId);
		for (UserDo user : userList) {
			printUserInfo(user);
			Long cuserId = user.getId();
			UserAccountIncomeDo uaiDo = userAccountIncomeDao.getById(cuserId);
			printUserAccount(uaiDo);
			allChild(cuserId);
		}
	}
	
	void printUserInfo(UserDo user){
		System.out.println( i + ":"+user.getId() + " ====" + user.getName() +
				"=======" + user.getUserName() + "===推荐人数：" + user.getParentId());
		i++;
		
	}
	
	void printUserAccount(UserAccountIncomeDo uaiDo){
		if(null != uaiDo){
			System.out.println( i + ":"+uaiDo.getUserId() + " ====" + uaiDo.getAmount() +
					"=======" + uaiDo.getPoint() + "===总销费" +uaiDo.getConsumeTotalAmount());
		}
		
	}
	
	@Test
	public void testFindAllParent(){
		
		Map<Integer,Double> map = new HashMap<Integer,Double>();
		map.put(1009,120.0);
		map.put(1094,3656.0);
		map.put(1843,2132.94);
		map.put(1861,90.0);
		map.put(1871,68.01);
		map.put(1901,60.0);
		map.put(1905,240.0);
		map.put(1952,0.13);
		map.put(1954,0.13);
		map.put(1956,67.2);
		map.put(1957,75.6);
		map.put(1958,50.4);
		map.put(1959,30.0);
		map.put(1960,20.48);
		map.put(1961,0.06);
		map.put(1972,62.0);
		map.put(1973,1020.0);
		map.put(2016,601.0);
		map.put(2019,240.0);
		map.put(2022,120.0);
		map.put(2025,135.4);
		map.put(2027,274.0);
		map.put(2035,124.0);
		map.put(2037,120.0);
		map.put(2083,4.4);
		map.put(2091,12.88);
		map.put(2108,30.0);
		map.put(2396,300.0);
		map.put(4780,60.0);

		for (Map.Entry<Integer, Double> entry : map.entrySet()) {  
			  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		    Integer key = entry.getKey();
		    Double value = entry.getValue();
		 
		    
		    Long userId =key.longValue();
		    double amountD = 2 * value;
		    		UserDo user = userService.getById(userId);
		    		if( null != user){
			    		Long refferId = user.getReferrerId() == null ? -1l :user.getReferrerId() ;
			    		UserDo oneUserDo = userService.getById(refferId);
			    		//5 增加团队业绩
			    		if( null != oneUserDo){
			    			teamAmount(refferId, new BigDecimal(amountD));
			    		}
		    		}
		}  
		
//		
		
	}
	
	
	//团队业绩汇总
	private void teamAmount(long refferId, BigDecimal amount) {
		//更新用户业绩金额
		KuangjiUserAccountDo userAccount = new KuangjiUserAccountDo();
		userAccount.setUserId(refferId);
		userAccount.setTeamAmount(amount);
		userAccount.setUpdateTime(new Date());
		memberDao.updateAmountById(userAccount);
		
		//给上级添加团队业绩 
		UserDo userDo = userDao.getById(refferId);
		if(null == userDo){
			return;
		}
		Long parentId = userDo.getReferrerId();
		if( null != parentId && 0 != parentId.intValue() ){
			teamAmount(parentId,amount );
		}
		
	}
}
