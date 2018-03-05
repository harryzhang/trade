package com.redpacket.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.redpack.account.dao.IUserDao;
import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.faced.IKangjiIncomeService;
import com.redpack.account.faced.IUserInfoService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.account.model.UserInfoDo;
import com.redpack.base.result.IResult;
import com.redpack.bet.IBetResultService;
import com.redpack.constant.WebConstants;
import com.redpack.group.IGroupUserService;
import com.redpack.order.IOrderService;
import com.redpack.order.model.OrderDo;
import com.redpack.utils.DateUtil;

public class UserInfoServiceTest extends BaseTestCase {

	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGroupUserService groupUserService;
	
	
	@Autowired
	public IKangjiIncomeService kangjiIncomeService;
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IBizUserAccountService bizuserAccountService;
	
	@Autowired
	private IBetResultService betResultService;
	
	@Test
	public void testAuditTempUserOne(){
//			int result  = userService.autidTempUser(Long.valueOf(314));
	}
	
	@Test
	public void testJob111(){
		kangjiIncomeService.doJob();
	}
	
	
	@Test
	public void testAuditTempUser(){
		int[] userArray = new int[] {
				236
				,238
				,239
				,240
				,241
				,242
				,243
				,244
				,245
				,246
				,247
				,248
				,249
				,250
				,251
				,252
				,253
				,254
				,255
				,256
				,257
				,258
				,259
				,260
				,261
				,262
				,263
				,264
				,265
				,266
				,267
				,268
				,269
				,270
				,271
				,272
				,273
				,276
				,277
				,278
				,279
				,280
				,281
				,285
				,287
				,288
				,291
				,292
				,293
				,294
				,295
				,297
				,298
				,299
				,300
				,302
				,303
				,304
				,307
				,308
				,309
				,310
				,311
				,312
				,313
				,314
				,315
				,319
				,320
				,321
				,322
				,323
				,325
				,328
				,329
				,331
				,333
				,334
				,338
				,342
				,346
				,347
				,349
				,353
				,354
				,355
		};
		for(int k = 0 ; k< userArray.length ; k++){
//			int result  = userService.autidTempUser(Long.valueOf(userArray[k]));
		}
	}
	
	
	@Test
	public void testGetParent(){
		long currentUserId = 1;
		UserDo user = userService.getParent(currentUserId);
		Assert.assertNull(user);
	}
	/*
	@Test
	public void testAddOneUserDo(){
		Long userId = 1L;
		String mobilePhone = "13900000000";
		String pwdMd5 = "371821df11665276e5b9b74fec86226a";
		
		
			UserDo userDo =new UserDo();
			userDo.setCreateUser(userId);                 //当前用户是记录创建者
			userDo.setUserName(mobilePhone);
			userDo.setPassword(pwdMd5);
			userDo.setTwoLevelPwd(pwdMd5);
			userDo.setGrade(0);								//当前等级
			userDo.setOrgan("");								//组织机构
			userDo.setEnabled("");								//状态
			userDo.setTreeNode("");								//业务方向
			userDo.setName(mobilePhone);
			userDo.setCreateTime(new Date());
			userDao.saveUser(userDo);
			UserInfoDo userInfoDo = new UserInfoDo();
			userInfoDo.setRealName(mobilePhone);
			userInfoDo.setMobile(mobilePhone);
			userInfoDo.setUserId(userDo.getId());
			userInfoService.saveUserInfo(userInfoDo);
			//IResult<Long> result = userService.saveUser(userDo);
			//System.out.println(result.getResultCode());
			//System.out.println(result.getErrorMessage());
		
	}
	*/
	
	@Test
	public void testmoveSort(){
		String user1Mobile="18275505414";
		int level = 4;
		Long position = 15L;
		String groupName="GA2016032000001";
		userService.moveSort(user1Mobile, level, position, groupName);
	}
	
	@Test
	public void testDelGroupUserByUserId(){
		String user1Mobile="18275505414";
		groupUserService.delGroupUserByUserId(user1Mobile);
		//userService.moveSort(user1Mobile, level, position, groupName);
	}
	
	String[] n={"","丁山","王琼","王送","董杰","张洁","李春"};
	String[] tel={"15869827514","13925298555","15012749200","137982951197",
			"13556872342","13426564739","13714730595"};
	String[] cztel={"13873567990" };
	// 18173587679
	
	private String getPwd(String tel){
		return tel.substring(5);
	}
	
	public static void main(String[] args) {
		System.out.println("13514621901".substring(5));
	}

	/**
	 * z
	 * @param request
	 * @param httpResponse
	 * @param model
	 * @throws ServletException
	 * @throws IOException
	 */
	private int chongzhi(BigDecimal payAmount,Long userId,String orderTradeNo) {
		logger.debug("----支付订单----");
		// 保存订单
		OrderDo newOrder = new OrderDo();
		newOrder.setCreateTime(new Date());
		newOrder.setGoodsId(1L);
		newOrder.setOrderStatus(2);
		newOrder.setQty(1l);
		newOrder.setPayStatus(0);
		newOrder.setUserId(userId);
		newOrder.setOrderType(0);
		newOrder.setPrice(payAmount);
		newOrder.setTotalPrice(payAmount);
		newOrder.setOrderCode(orderTradeNo);
		return orderService.addOrder(newOrder);
		
	}
	
	/**
	 * 充值通知
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @throws IOException 
	 * @date 2015-3-29 上午3:36:11
	 */
	private void notifyResult(String orderNo){
		logger.debug("----PayController.notifyResult;----");		
		Map map = new HashMap<String,Object>();		
		map.put("orderNo", orderNo);	
		Boolean payStatus = orderService.chongZhiOrder(map);
		
	}
	
	/**
	 * 购买证券商品明细
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	public void  submitOrder(Long userId, BigDecimal amt ) {
		
		logger.debug("----OrderController.submitOrder;----");
			
		String orderType = "1";
		OrderDo newOrder = new OrderDo();
		long qty = amt.divide(new BigDecimal("10")).longValue();
		long goodsId = 1l;
		newOrder.setCreateTime(new Date());
		newOrder.setGoodsId(goodsId);
		newOrder.setQty(qty);
		newOrder.setPayStatus(0);
		newOrder.setUserId(userId);
		newOrder.setOrderType(Integer.valueOf(orderType));
		newOrder.setPrice(new BigDecimal("10"));
		newOrder.setTotalPrice((new BigDecimal("10")).multiply(new BigDecimal(qty)));
		newOrder.setOrderCode("order_"+userId+DateUtil.getDate()+DateUtil.getThree());
		newOrder.setOrderStatus(3);
		orderService.addNewOrder(newOrder);
	}
	
	
	@Test
	public void testAddUser(){
        for(int i = 1; i< tel.length; i++){
			String name = n[i];
			String mobilePhone = tel[i];
			String pwd =  getPwd(mobilePhone);
			String referenceId =  tel[i-1];
			String netWork = "";
			Date d = new Date();
			//DateUtil.dateAddDay(d, i);
			if(StringUtils.isBlank(netWork)){
				netWork = "A";
			}
			
			
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userName", mobilePhone);
			// 获取登录用户userId
			UserDo temp = userService.getByUserDo(parameterMap);

			if(temp!=null&&temp.getId()!=null){
				System.out.println("输入的手机号已存在");
				return;
			}
			if (StringUtils.isBlank(name)) {
				System.out.println(  "请输入昵称");
				return;
			}
			if (StringUtils.isBlank(mobilePhone)) {
				System.out.println( "请输入手机号");
				return;
			}
			if (StringUtils.isBlank(referenceId)) {
				System.out.println(  "请输入推荐人手机号码");
				return;
			}
			
			
			//根据推荐人的手机号码查找推荐人
			parameterMap.clear();
			parameterMap.put("userName", referenceId);
			parameterMap.put("status", "1");
			UserDo refUser = userService.getByUserDo(parameterMap);
			if(refUser == null){
				System.out.println( "查找不到对应的推荐用户或者推荐用户为非正式会员");
				return;
			}
			
			String pwdMd5 =DigestUtils.md5Hex(pwd + WebConstants.PASS_KEY);
			UserDo userDo =new UserDo();
			userDo.setCreateUser(-1L);                 //当前用户是记录创建者
			userDo.setUserName(mobilePhone);
			userDo.setPassword(pwdMd5);
			userDo.setTwoLevelPwd(pwdMd5);
			userDo.setGrade(0);								//当前等级
			userDo.setOrgan("0");								//组织机构
			userDo.setEnabled("0");								//状态  默认激活
		
			Random ne=new Random();//实例化一个random的对象ne
	        String activeNum="" + (ne.nextInt(9999-1000+1)+1000);
			userDo.setRemark(activeNum);
			userDo.setReferrerId(refUser.getId());				//推荐人ID
			userDo.setParentId(0l);	
			userDo.setStatus(1);//接点人ID
			userDo.setTreeNode("");								//业务方向
			userDo.setName(name);
			UserInfoDo userInfoDo = new UserInfoDo();
			userInfoDo.setRealName(name);
			userInfoDo.setMobile(mobilePhone);

			userDo.setCreateTime(d);
			userDo.setUpdateTime(d);
			userDo.setUserInfoDo(userInfoDo);
			
			//开始保存
			IResult<Long> result = userService.saveUser(userDo);
			if (result.isSuccess()) {
				Long userId = userDo.getId();
				userInfoDo.setUserId(userId);
				userInfoService.saveUserInfo(userInfoDo);
				
				int j = ne.nextInt(10);
				//充值
				BigDecimal payAmount = new BigDecimal(30000);
				String orderTradeNo = "order_"+userId+DateUtil.getDate()+DateUtil.getThree();
				chongzhi(payAmount,userId,orderTradeNo);
				notifyResult(orderTradeNo);
				BigDecimal pet = bizuserAccountService.getAccountTypeAmount("pet", userId);
				submitOrder(userId, pet);				
				System.out.println("注册成功");
			} else {
				System.out.println("注册失败");
			}
        }			
	}
	
	
	@Test
	public void testUserChonzhi(){
        for(int i = 0; i< cztel.length; i++){
			String mobilePhone = cztel[i];
	
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userName", mobilePhone);
			// 获取登录用户userId
			UserDo userDo = userService.getByUserDo(parameterMap);
			Long userId = userDo.getId();
			//充值
			Random ne=new Random();//实例化一个random的对象ne
	        int activeNum=(1+ne.nextInt(9)) *10000;
			//int activeNum=3000;
			BigDecimal payAmount = new BigDecimal(activeNum);
			String orderTradeNo = "order_"+userId+DateUtil.getDate()+DateUtil.getThree();
			chongzhi(payAmount,userId,orderTradeNo);
			notifyResult(orderTradeNo);
			BigDecimal pet = bizuserAccountService.getAccountTypeAmount("pet", userId);
			submitOrder(userId, pet);				
			System.out.println("注册成功");
        }			
	}
	
	
	@Test
	public void testExchangePosition(){
		String user1Mobile = "18003799711";
		String user2Mobile = "15152072898";
		userService.exchangeEachOther(user1Mobile, user2Mobile);
		
	}
	
	/*
	@Test
	public void testAddTestUserDo(){
		//二级
		//Long userId = 1676L;
		//三级
		Long userId = 1689L;
		//一级
		//Long userId = 1093;
		String mobilePhone = "139000000";
		String pwdMd5 = "371821df11665276e5b9b74fec86226a";
	
		
		for(int i = 2 ; i < 11 ; i++){
			String userName ="健三";
			mobilePhone = "137000023";
			UserDo userDo =new UserDo();
			userDo.setCreateUser(userId);                 //当前用户是记录创建者
			if(i<10){
				mobilePhone = mobilePhone +"0"+String.valueOf(i);
			}else{
				mobilePhone = mobilePhone + String.valueOf(i);
			}
			userName = userName + i;
			userDo.setUserName(mobilePhone);
			userDo.setPassword(pwdMd5);
			userDo.setTwoLevelPwd(pwdMd5);
			userDo.setGrade(0);								//当前等级
			userDo.setOrgan("");								//组织机构
			userDo.setEnabled("");								//状态
			userDo.setReferrerId(userId);				//推荐人ID
			userDo.setTreeNode("");								//业务方向
			userDo.setName(userName);
			UserInfoDo userInfoDo = new UserInfoDo();
			
			userInfoDo.setRealName(mobilePhone);
			userInfoDo.setMobile(mobilePhone);
			userDo.setCreateTime(new Date());
			userDo.setUserInfoDo(userInfoDo);
			IResult<Long> result = userService.saveUser(userDo);
			System.out.println(result.getResultCode());
			System.out.println(result.getErrorMessage());
		}
		
	}
	*/
	
	/*
	@Test
	public void testAddUserDo() {
		for (int i = 0; i < 27; i++) {
			Long userId = 477L;
			String mobilePhone = "137000000";
			String pwdMd5 = "371821df11665276e5b9b74fec86226a";
			UserDo userDo = new UserDo();
			if (i < 10) {
				mobilePhone = mobilePhone + "0" + String.valueOf(i);
			} else {
				mobilePhone = mobilePhone + String.valueOf(i);
			}
			userDo.setUserName(mobilePhone);
			userDo.setPassword(pwdMd5);
			userDo.setTwoLevelPwd(pwdMd5);
			userDo.setGrade(0); // 当前等级
			userDo.setOrgan(""); // 组织机构
			userDo.setEnabled(""); // 状态
			userDo.setReferrerId(userId); // 推荐人ID
			userDo.setTreeNode(""); // 业务方向
			userDo.setName(mobilePhone);
			UserInfoDo userInfoDo = new UserInfoDo();
			userInfoDo.setRealName(mobilePhone);
			userInfoDo.setMobile(mobilePhone);
			userDo.setCreateTime(new Date());
			userDo.setUserInfoDo(userInfoDo);
			IResult<Long> result = userService.saveUser(userDo);
			System.out.println(result.getResultCode());
			System.out.println(result.getErrorMessage());
		}
	}
	*/
	@Test
	public void testUpdateUserDo() {
		//String mobilePhone = "137000000";
		//String pwdMd5 = "371821df11665276e5b9b74fec86226a";
		long id = 1148L;
		UserDo oldUser = userDao.getById(id);
		oldUser.setName("王振杰");
		userDao.updateUser(oldUser);
	}
	
	
	/**
	 * 手工开奖
	 */
	@Test
	public void testOpenGame() {
		betResultService.openGame(55410);
	}
	
}
