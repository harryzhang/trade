package com.redpack.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redpack.base.controller.BaseController;
import com.redpack.goods.IGoodsService;
import com.redpack.goods.model.CartDo;
import com.redpack.goods.model.GoodsDo;
import com.redpack.utils.ResponseUtils;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController extends BaseController {

	private static final Logger logger = Logger.getLogger(GoodsController.class);
	
	@Autowired
	private IGoodsService goodsService;

	
	/**
	 * 商品明细
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/select",method = RequestMethod.GET)
	public String  goodsSelect( HttpServletRequest request,
						   		HttpServletResponse response,
						        Model model) {
		logger.debug("----GoodsController.select;----");
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("gcode", "G_JIFENG");
		parameterMap.put("status", "1");
		List<GoodsDo> goodsLst = goodsService.selectGoods(parameterMap);
		model.addAttribute("goodsLst", goodsLst);
		//清空购物车
		request.getSession().removeAttribute("mycart");
		
		return getLocalPath(request,"goods/goodsSelect");
	}
	
	/**
	 * 商品明细
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/goodsDetail/{goodsId}",method = RequestMethod.GET)
	public String  goodsDetail(@PathVariable("goodsId") String goodsId , 
								HttpServletRequest request,
								HttpServletResponse response,
								Model model) {
		logger.debug("----GoodsController.goodsDetail;----goodsId:"+goodsId);
		GoodsDo goods = goodsService.getById(Long.valueOf(goodsId));
		model.addAttribute("goods", goods);
		return "goods/goodsDetail";
	}

	
	/**
	 * 商品明细
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/searchLoading")
	public void  searchLoading(HttpServletRequest request,HttpServletResponse response) {
		logger.debug("----GoodsController.searchLoading;----");
		JSONObject jsonObject = new JSONObject();
		//jsonObject.put("result", 0);
		ResponseUtils.renderText(response, null, jsonObject.toString());
	}
	
	
}
