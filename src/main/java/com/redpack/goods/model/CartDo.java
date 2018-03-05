package com.redpack.goods.model;

import java.util.ArrayList;
import java.util.List;

import com.redpack.utils.CalculateUtils;


/**
 * 购物车
 * @author harry
 *
 */
public class CartDo {
	
	private List<GoodsDo>  chooseGoodsList ;

	public void addGoods(GoodsDo goods) {
		if(null == chooseGoodsList){
			chooseGoodsList = new ArrayList<GoodsDo>();
			chooseGoodsList.add(goods);
		}
		
	}

	/**
	 * 根据商品ID , 获取购物车中的物品
	 * @param recId
	 * @return
	 */
	public GoodsDo getGoodsById(long recId) {
		for(GoodsDo  g : chooseGoodsList){
			if(g.getGoodsId().longValue() == recId){
				return g;
			}
		}
		return null;
	}

	/**
	 * 根据商品ID ， 从购物车中删除物品
	 * @param recId
	 */
	public void delGoods(long recId) {
		GoodsDo delGoods = null;
		for(GoodsDo  g : chooseGoodsList){
			if(g.getGoodsId().longValue() == recId){
				delGoods= g;
				break;
			}
		}
		if(null != delGoods)
		chooseGoodsList.remove(delGoods);
		
	}

	/**
	 * 根据逗号分隔的商品ID  , 计算购物车里的总价
	 * @param recIds
	 * @return
	 */
	public double toTal(String recIds) {
		String[] ids = recIds.split(",");
		double total = 0 ;
		for(String idStr : ids){
			long id = Long.parseLong(idStr);
			GoodsDo  g = this.getGoodsById(id);
			if(g != null){
				double tmp=CalculateUtils.mul(g.getPrice().doubleValue(), new Double( g.getBuyQty()));
				total = CalculateUtils.add(total, tmp);
			}
		}
		return total;
	}

	/**
	 * 根据逗号分隔的商品ID  , 计算购物车里的总数量
	 * @param string
	 * @return
	 */
	public double toTalQty(String recIds) {

		String[] ids = recIds.split(",");
		double total = 0 ;
		for(String idStr : ids){
			long id = Long.parseLong(idStr);
			GoodsDo  g = this.getGoodsById(id);
			if(g != null){
				total = CalculateUtils.add(total, g.getBuyQty());
			}
		}
		return total;
	
	}
	

}
