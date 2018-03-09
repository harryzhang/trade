/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.order;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.redpack.account.model.UserDo;
import com.redpack.order.model.OrderDo;


public interface IOrderService{

	/**
	 * 根据ID 查询
	 * @parameter id
	 */
	public OrderDo getById(Long id);
	
	/**
	 *根据条件查询列表
	 */
	public List<OrderDo> selectOrder(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateOrderById(OrderDo newOrderDo);
	
	/**
	 * 新增
	 */
	public int addOrder(OrderDo newOrderDo);
	/**
	 *积分订单查询
	 */
	public List<OrderDo> selectPointOrder(Map<String,Object> parameterMap);
	/**
	 * 新增
	 */
	public int addNewOrder(OrderDo newOrderDo);
	/**
	 * 不带逻辑新增
	 * @throws IOException 
	 */
	public JSONObject addOrderNotBuss(OrderDo newOrderDo) throws IOException;
	/**
	 * 新增
	 */
	public Boolean chongZhiOrder(Map<String,String> parameterMap);
	
	/**
	 * 删除
	 */
	public int deleteById(int id);
	
	
	/**
	 * 根据订单号查询订单支付状态: true 已支付， false 未支付
	 */
	public  boolean selectPayStatusByOrderNo(String orderNo);
	
	/**
	 * 根据订单号查询订单
	 */
	public  OrderDo selectOrderByOrderNo(String orderNo);
	
	/**
	 * 购买股权的时候计算支付金额
	 * @param newOrderDo
	 * @param openId
	 * @return
	 * @throws IOException
	 */
	public BigDecimal getPayAmount(OrderDo newOrderDo);
	
	public void giftTeamAmount(long refferId, BigDecimal amount);
	

	/**
	 * 购买证券各个账户金额比例
	 * @param string
	 * @param totalMoney
	 * @return
	 */
	Map<String, BigDecimal> splitBuyMoney(String string, BigDecimal totalMoney,Long userId);

	/**
	 * 匹配订单
	 *
	 * zhangyunhmf
	 *
	 */
    public void matchOrder(OrderDo newOrder);

	/**
	 * 
	 * 确认收款和付款
	 * zhangyunhmf
	 *
	 */
    public void confirmOrder(Long userId, Long orderId);

}
