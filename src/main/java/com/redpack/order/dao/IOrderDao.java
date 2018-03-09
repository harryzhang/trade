/*
 * Powered By  huangzl QQ: 272950754
 * Web Site:  
 * Since 2008 - 2016
 */

package com.redpack.order.dao;

/**
 * @author  huangzl QQ: 272950754
 * @version 1.0
 * @since 1.0
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.redpack.order.model.OrderDo;
@Repository
public interface IOrderDao {

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
	 *积分订单查询
	 */
	public List<OrderDo> selectPointOrder(Map<String,Object> parameterMap);
	
	/**
	 * 更新
	 */
	public int  updateOrderById(OrderDo newOrderDo);
	
	/**
	 * 新增
	 */
	public int addOrder(OrderDo newOrderDo);
	
	/**
	 * 删除
	 */
	public int deleteById(Long id);

	/**
	 * 查询公告
	 * @return
	 */
	public List<Map<String, Object>> queryNotice();

	public List<Map<String, Object>> queryNoticeById(String id);

	/**
	 * 更改匹配的订单状态为交易中
	 * zhangyunhmf
	 *
	 */
    public void updateOrderStatusByOldStatus(Map<String, Object> paraMap);

	/**
	 * 更新支付状态
	 * zhangyunhmf
	 *
	 */
    public void updateOrderPayStatus(Map<String, Object> paraMap);

}
