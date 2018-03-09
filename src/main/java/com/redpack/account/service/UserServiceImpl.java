
/**  
 * @Project: deposit-biz-service
 * @Package com.hehenian.deposit.service.account
 * @Title: UserServiceImpl.java
 * @Description: TODO
 * @author:  zhangyunhua
 * @date 2015年3月5日 上午10:58:12
 * @Copyright: HEHENIAN Co.,Ltd. All rights reserved.
 * @version V1.0  
 */
package com.redpack.account.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.dao.IUserInfoDao;
import com.redpack.account.faced.ITotalAccountService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.ApplyAgentDo;
import com.redpack.account.model.UserDo;
import com.redpack.base.result.IResult;
import com.redpack.base.result.ResultSupport;
import com.redpack.constant.WebConstants;
import com.redpack.grade.model.GradeFeeDo;
import com.redpack.grade.model.GroupUserDo;
import com.redpack.group.IGroupUserService;
import com.redpack.param.IParamService;
import com.redpack.wallet.dao.IWalletDao;
import com.redpack.wallet.model.WalletDo;

/**
 * 
 * @author: zhangyunhua
 * @date 2015年3月5日 上午10:58:12
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired	
	private IUserInfoDao  userInfoDao;
	
	
	@Autowired
	private IGroupUserService groupUserService;
	@Autowired
	private ITotalAccountService totalAccountService;
	
	@Autowired	
    private IWalletDao  walletDao;
	
	@Autowired	
    private IParamService  paramService;
	
	
	@Value("#{config['system.name']}")
	private String system;
	
	/**
	 * 参 数 名 称 功 能 描 述 readOnly
	 * 该属性用于设置当前事务是否为只读事务，设置为true表示只读，false则表示可读写，默认值为false
	 * 。例如：@Transactional(readOnly=true)
	 * 
	 * rollbackFor 该属性用于设置需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，则进行事务回滚。例如：
	 * 指定单一异常类：@Transactional(rollbackFor=RuntimeException.class)
	 * 指定多个异常类：@Transactional(rollbackFor={RuntimeException.class,
	 * Exception.class})
	 * 
	 * rollbackForClassName
	 * 该属性用于设置需要进行回滚的异常类名称数组，当方法中抛出指定异常名称数组中的异常时，则进行事务回滚。例如：
	 * 指定单一异常类名称：@Transactional(rollbackForClassName="RuntimeException")
	 * 指定多个异常类名称
	 * ：@Transactional(rollbackForClassName={"RuntimeException","Exception"})
	 * 
	 * noRollbackFor 该属性用于设置不需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，不进行事务回滚。例如：
	 * 指定单一异常类：@Transactional(noRollbackFor=RuntimeException.class)
	 * 指定多个异常类：@Transactional(noRollbackFor={RuntimeException.class,
	 * Exception.class})
	 * 
	 * noRollbackForClassName
	 * 该属性用于设置不需要进行回滚的异常类名称数组，当方法中抛出指定异常名称数组中的异常时，不进行事务回滚。例如：
	 * 指定单一异常类名称：@Transactional(noRollbackForClassName="RuntimeException")
	 * 指定多个异常类名称：
	 * 
	 * @Transactional(noRollbackForClassName={"RuntimeException","Exception" )
	 * 
	 *                                                                       propagation
	 *                                                                       该属性用于设置事务的传播行为
	 * @Transactional(propagation=Propagation.REQUIRED) 如果有事务, 那么加入事务,
	 *                                                  没有的话新建一个(默认情况下)
	 * @Transactional(propagation=Propagation.NOT_SUPPORTED) 容器不为这个方法开启事务
	 * @Transactional(propagation=Propagation.REQUIRES_NEW) 
	 *                                                      不管是否存在事务,都创建一个新的事务,原来的挂起
	 *                                                      ,新的执行完毕,继续执行老的事务
	 * @Transactional(propagation=Propagation.MANDATORY) 必须在一个已有的事务中执行,否则抛出异常
	 * @Transactional(propagation=Propagation.NEVER) 
	 *                                               必须在一个没有的事务中执行,否则抛出异常(与Propagation
	 *                                               .MANDATORY相反)
	 * @Transactional(propagation=Propagation.SUPPORTS) 
	 *                                                  如果其他bean调用这个方法,在其他bean中声明事务
	 *                                                  ,那就用事务.如果其他bean没有声明事务,
	 *                                                  那就不用事务.
	 * 
	 *                                                  isolation
	 *                                                  该属性用于设置底层数据库的事务隔离级别
	 *                                                  ，事务隔离级别用于处理多事务并发的情况
	 *                                                  ，通常使用数据库的默认隔离级别即可
	 *                                                  ，基本不需要进行设置
	 * 
	 *                                                  timeout
	 *                                                  该属性用于设置事务的超时秒数，默认值为
	 *                                                  -1表示永不超时
	 * 
	 * @param userDo
	 * @return
	 */
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public IResult<Long> saveUser(UserDo userDo) {
		
		logger.info("新注册的user：-------------------"+userDo.toString());
		
		//保存用户注册信息
		long i = userDao.saveUser(userDo);
		if (i < 1) {
			return ResultSupport.buildResult(1, "保存用户信息失败");
		}
		//获取用户编号
		//setUserCode(userDo);
		
		
		//根据推荐人确定用户位置
		Long refUserId = userDo.getReferrerId();
		UserDo refUser = userDao.getById(refUserId);
		groupUserService.getAllChildRen(refUser);
		UserDo rightUser =refUser.getRightChild();
		UserDo leftUser = refUser.getLeftChild();
		
		
		UserDo  newUserParent = null;
		String leftRightFlag = "L";
		//左为空
		if(leftUser ==null){
			newUserParent = refUser;
		}else if(null == rightUser  ){ //右为空
			newUserParent = refUser;
			leftRightFlag = "R";
		}else{//左右都不为空
			BigDecimal rightAmt = totalAccountService.totalAmt(rightUser,WebConstants.SECURITY_ACCOUNT);
			BigDecimal leftAmt = totalAccountService.totalAmt(leftUser,WebConstants.SECURITY_ACCOUNT);
			UserDo  chooseUser = leftAmt.compareTo(rightAmt)>0?rightUser:leftUser;
			newUserParent = UserDo.getLastLeftUser(chooseUser);
		}
		
		GroupUserDo newGroupUserDo = new GroupUserDo();
		newGroupUserDo.setUserId(userDo.getId());
		newGroupUserDo.setParentId(newUserParent.getId());
		newGroupUserDo.setGroupName(newUserParent.getOrgan());
		newGroupUserDo.setStatus("T");
		
		//左右标记
		newGroupUserDo.setGroupuserIdx(leftRightFlag);
		groupUserService.addGroupUser(newGroupUserDo);
		
		
		
		IResult<Long> result= ResultSupport.buildResult(0, "保存用户信息成功");
		result.setModel(userDo.getId());
		return result;
		
		
	}
	
	
	
	/**
	 * 给平台打款
	 * @return
	 */
	private int saveFx(Long userId,String groupName){
		return saveFx(userId,groupName,"注册转正式用户给平台付款");
	}
	
	/**
	 * 给平台打款
	 * @return
	 */
	private int saveFx(Long userId,String groupName,String remark){
		WalletDo walletDo = new WalletDo();
		walletDo.setFkUserId(userId);
		walletDo.setSkUserId(0l);
		Double amt = paramService.getPayMoney(0,"A");
		walletDo.setAmt(amt);
		walletDo.setRemark(remark);
		walletDo.setType(2);
		walletDo.setSkStatus(0);
		walletDo.setFkStatus(0);
		walletDo.setGroupName(groupName);
		walletDo.setValid(1);
		int addInt = walletDao.addWallet(walletDo);
		return addInt;
	}
	
	
	
	
	@Override
	public UserDo getById(Long id) {
		UserDo userDo= userDao.getById(id);
		return userDo;
	}

	@Override
	public UserDo getByLoginInfo(String id) {
		return this.getById(Long.valueOf(id));
	}

	@Override
	public UserDo getByUserDo(Map<String, Object> parameterMap) {
		List<UserDo> userList =  userDao.getByUserDo(parameterMap);
		if(null != userList && userList.size() > 0){
			return userList.get(0);
		}
		return null;
	}
	
	
	
	/**
	 * 根据当前用户ID ,查询他的第一层下级， 放到当前对象的 childList属性里，
	 * @param userId
	 * @return
	 */
	@Override
	public UserDo getUserAndChild(long userId) {
		UserDo  currentUser = this.getById(userId);
		if(null != currentUser){
			List<UserDo> child = this.getChildList(userId);
			currentUser.setChildList(child);
		}
		return currentUser;
	}
	
	/**
	 * 据当前用户ID ,查询他的下级的下级， 直到没有下级， 放到当前对象的 childList属性里，
	 * @param userId
	 * @return
	 */
	@Override
	public UserDo getUserAndAllChild(long userId) {
		UserDo  currentUser = this.getById(userId);
		if(null != currentUser){
			getAllChildren(currentUser);
		}
		return currentUser;
	}
	
	/**
	 * 获取第一层下级
	 */
	@Override
	public List<UserDo> getChildList(long userId) {
		List<UserDo> child = this.selectChildByParentId(userId);
		return child;
	}
	
	
	/**
	 * 根据当前用户ID ,查询他的所有下级， 放到当前对象的 childList属性里， 递归查询到最后一级
	 * @param userId
	 * @return
	 */
	@Override
	public void getAllChildren(UserDo currentUser) {
		
		List<UserDo> child = this.getChildList(currentUser.getId());
		currentUser.setChildList(child);
		if(null == child){
			return;
		}
		
		for(UserDo user : child){
			getAllChildren(user);				
		}
		return;
	}
	
	/**
	 * 获取上级
	 */
	@Override
	public UserDo getParent(long currentUserId ){
		UserDo currentUser = this.getById(currentUserId);
		if(currentUser != null && currentUser.getParentId() != null){
			return this.getById(currentUser.getParentId());
		}
		return null;
	}
	
	/**
	 * 获取指定层数的上级
	 * @param currentUserId
	 * @param levelNum
	 * @return
	 */
	@Override
	public void getAllParent(UserDo currentUser, int levelNum ){
		if(levelNum <= 0){
			return;
		}
		
		--levelNum;
		
		if(currentUser != null && currentUser.getReferrerId() != null){
			UserDo parent = this.getById(currentUser.getReferrerId());
			if(null == parent){
				return;
			}else{
				currentUser.setParentDo(parent);
				this.getAllParent(parent, levelNum);
			}
		}
		return;
	}
	
	
	/**
	 *根据条件查询列表
	 */
	@Override
	public List<UserDo> selectChildByParentId(Long parentId){
		return this.userDao.selectChildByParentId(parentId);
	}
	
	/**
	 * 递归查询所有上级
	 */
	@Override
	public void  getAllParent(UserDo currentUser) {
		if(currentUser != null && currentUser.getParentId() != null){
			UserDo parent = this.getById(currentUser.getParentId());
			if(null == parent){
				return;
			}else{
				currentUser.setParentDo(parent);
				this.getAllParent(parent);
			}
		}
		return;
	}

	@Override
	public UserDo getAllParent(long userId, int levelNum) {
		UserDo user = this.getById(userId);
		if(user == null){return null;}
		this.getAllParent(user, levelNum);
		return user;		
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateUserGradeById(Long userid, Integer afterUpgrade) {
		return userDao.updateUserGradeById(userid,afterUpgrade);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateUser(UserDo tempSave) {
		return userDao.updateUser(tempSave);
	}


	@Override
	public List<UserDo> selectUser(Map<String, Object> queryMap) {
		return userDao.getByUserDo(queryMap);
	}


	private BigDecimal getBaseBonus(List<GradeFeeDo> gradeFeeList, int grade){
		for(GradeFeeDo gradeFee : gradeFeeList ){
			if(gradeFee.getBeforeUpgrade().intValue() == grade){
				return gradeFee.getGradeAmount();
			}
		}
		return BigDecimal.ZERO;
	}
	
	private BigDecimal getBonusFromLevelToLevel(List<GradeFeeDo> gradeFeeList, int from, int to){
		for(GradeFeeDo gradeFee : gradeFeeList ){
			if(gradeFee.getBeforeUpgrade().intValue() == from && gradeFee.getAfterUpgrade().intValue() == to){
				return gradeFee.getGradeAmount();
			}
		}
		return BigDecimal.ZERO;
	}
	

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void updateUserStatusById(Long tempUserId, int newStatus) {
		userDao.updateUserStatusById(tempUserId,newStatus);
	}

	

	@Override
	public int getRefUserCount(Long id) {
		return userDao.getRefUserCount(id);
	}

	@Override
	public int getTempMemberCount(long userId) {
		return userDao.getTempMemberCount(userId);
	}

	
	///////////////////////////////////////////////////////////////////////////
	
	private static long[][] getParentIds(){
		long[][] parentIds = new long[3][9];
		for(int i = 0 ; i < 3;i++){
			for(int k = 0; k<9; k++){
				parentIds[i][k] = 6+k+ i*9;
			}
		}
		return parentIds;
	}
	
     public  static void main(String[] args){
    	
    	 UserServiceImpl userServiceImpl = new UserServiceImpl();
    	 UserDo userDo = new UserDo();
    	 for (Long i = 0L; i < 1000; i++) {
        	 userDo.setId(i);
        	 userServiceImpl.setUserCode(userDo);
		}
    
//    	 splitGroup();
     }

	@Override
	public List<UserDo> getAllUser(Map paramMap) {
		// TODO Auto-generated method stub
		return userDao.getAllUser(paramMap);
	}

	@Override
	public List<UserDo> getAllGroupUser(Map paramMap) {
		// TODO Auto-generated method stub
		return userDao.getAllGroupUser(paramMap);
	}
	
	/**
	 * 生成用户编号 规划是字母 + 5位数字 顺序增加
	 * @param userDo
	 */
	private void setUserCode(UserDo userDo) {
		Long userId = userDo.getId();

		String str[] = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" };
		String userCode = "";
		Long code = userId / 100000;
		userCode = str[code.intValue()];

		Long num = userId % 100000;

		String numStr = num.toString();
		int numLength = numStr.length();
		// 根据值的长度补够长度
		if (1 == numLength) {
			userCode = userCode + "0000" + num;
		} else if (2 == numLength) {
			userCode = userCode + "000" + num;
		} else if (3 == numLength) {
			userCode = userCode + "00" + num;
		} else if (4 == numLength) {
			userCode = userCode + "0" + num;
		} else {
			userCode = userCode + num;
		}

		userDo.setUserCode(userCode);
		userDao.updateUser(userDo);
	}

	/**
	 * 交换位置
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void exchangeEachOther(String user1Mobile, String user2Mobile){
		Map<String, Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("userName", user1Mobile);
		List<UserDo> user1List = userDao.getByUserDo(parameterMap);
		
		parameterMap.clear();
		parameterMap.put("userName", user2Mobile);
		List<UserDo> user2List = userDao.getByUserDo(parameterMap);
		
		
		Long user1Id = user1List.get(0).getId();
		Long user2Id = user2List.get(0).getId();
		Long tmpId = -1000L;
		Map<String,Object>  idMap = new HashMap<String,Object>();
		idMap.put("oldId", user1Id);
		idMap.put("newId", tmpId);
		userDao.updateUserId(idMap);
		
		idMap.clear();
		idMap.put("oldId", user2Id);
		idMap.put("newId", user1Id);
		userDao.updateUserId(idMap);
		
		idMap.clear();
		idMap.put("oldId", tmpId);
		idMap.put("newId", user2Id);
		userDao.updateUserId(idMap);
		
		//推荐人也要修改
		parameterMap.clear();
		parameterMap.put("referrerId", user1Id);		
		List<UserDo> user1RefList = userDao.getByUserDo(parameterMap);
		for(UserDo refUser : user1RefList){
			refUser.setReferrerId(user2Id);
			userDao.updateUser(refUser);
		}
		
		parameterMap.clear();
		parameterMap.put("referrerId", user2Id);		
		List<UserDo> user2RefList = userDao.getByUserDo(parameterMap);
		for(UserDo refUser : user2RefList){
			refUser.setReferrerId(user1Id);
			userDao.updateUser(refUser);
		}
		
	}
	
	

	@Override
	public List<Map<String, Object>> listRef(Map<String, Object> queryMap) {
		return userDao.listRef(queryMap);
	}



	/**
	 * 保存代理申请
	 */
	@Override
	public IResult<Long> saveApplyAgent(ApplyAgentDo agentDo) {
		userDao.saveApplyAgent(agentDo);
		IResult ret = ResultSupport.buildResult(0);
		ret.setModel(agentDo.getId());
		return ret;
		
	}


	/**
	 * 用户申请记录
	 */
	@Override
	public List<Map<String, Object>> getApplyAgentUser(
			Map<String, Object> paraMap) {
		return userDao.getApplyAgentUser(paraMap);
	}



	@Override
	public IResult<Long> updateApplyAgent(ApplyAgentDo agentDo) {
		int ret = userDao.updateApplyAgent(agentDo);
		IResult retResult = ResultSupport.buildResult(0);
		retResult.setModel(ret);
		return retResult;
	}



	@Override
	public void saveLoginlog(Long id) {
		userDao.saveLoginlog(id);
		
	}
		
}
