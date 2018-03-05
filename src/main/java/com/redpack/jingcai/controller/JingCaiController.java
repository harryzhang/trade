package com.redpack.jingcai.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.Thread.ToJingCai;
import com.redpack.account.faced.IBizUserAccountService;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.bet.IBetResultService;
import com.redpack.bet.IJingpaiService;
import com.redpack.bet.model.BetResultDo;
import com.redpack.bet.model.JingpaiDo;
import com.redpack.constant.WebConstants;
import com.redpack.goods.IGoodsService;
import com.redpack.goods.model.GoodsDo;
import com.redpack.member.IMemberService;
import com.redpack.member.model.KuangjiUserAccountDo;
import com.redpack.utils.ResponseUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/jingcai")
public class JingCaiController extends BaseController {

	private static final Logger logger = Logger.getLogger(JingCaiController.class);

	@Autowired
	private IBetResultService betResultService;

	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IJingpaiService jingpaiService;
	@Autowired
	private IBizUserAccountService bizUserAccountService;

	/**
	 * 竞猜页面
	 * 
	 * @return
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/tojingcai")
	public String tojingcai(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("----获取开盘时间----");
		// JSONObject jsonObject = new JSONObject();

		long openTime = ToJingCai.liveTime;
		int period = ToJingCai.period;
		// jsonObject.put("result", "成功");
		// jsonObject.put("openTime", openTime);
		// jsonObject.put("period", period);

		model.addAttribute("openTime", openTime);
		model.addAttribute("period", period);

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("fetch", 20);
		List<BetResultDo> betList = betResultService.selectBetResult(parameterMap);
		// List<GoodsDo> goodsLst = goodsService.selectGoods(parameterMap);

		if (betList.size() > 0) {
			BetResultDo brd = betList.get(0);
			model.addAttribute("numImage", "/res-kuangji/jingcaiImg/num_" + brd.getBetNum() + ".jpg");
			model.addAttribute("bigImage", "/res-kuangji/jingcaiImg/big_" + brd.getBetNum() + ".jpg");
		}

		for (BetResultDo betResultDo : betList) {
			String betNum = betResultDo.getBetNum();
			betResultDo.setBetNum("/res-kuangji/jingcaiImg/num_" + betNum + ".jpg");
			betResultDo.setStatus("/res-kuangji/jingcaiImg/big_" + betNum + ".jpg");
		}
		model.addAttribute("betList", betList);

		// 用户投注结果
		UserDo currentUser = (UserDo) request.getSession().getAttribute(WebConstants.SESSION_USER);
		long userId = currentUser.getId();
		parameterMap.put("userId", userId);
		List<Map<String, Object>> userList = betResultService.selectUserResult(parameterMap);
		model.addAttribute("userList", userList);

		return getLocalPath(request, "jingcai/tojingcai");
	}

	/**
	 * 竞猜页面
	 * 
	 * @return
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/toxiazu")
	public String toxiazu(HttpServletRequest request, Model model) {
		Long userId = this.getUserId(request);
//		KuangjiUserAccountDo userAccountDo = memberService.getByUserid(userId);	
		
		BigDecimal account =bizUserAccountService.getAccountTypeAmount(WebConstants.RMB_ACCOUNT, userId);
		model.addAttribute("account", account);
		
		return getLocalPath(request, "jingcai/toxiazu");
	}

	/**
	 * 竞拍页面
	 * 
	 * @return
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/jingpai")
	public String jingpai(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("----商品竞拍-------");
		Map param = new HashMap<String, Object>();
		param.put("status", 1);
		List<Map<String,Object>> jingpaiList = jingpaiService.selectGoodsJingpai(param);
		model.addAttribute("jingpaiList", jingpaiList);

		return getLocalPath(request, "jingpai/jingpai");
	}
	
	/**
	 * 获取竞拍信息
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @param changeName
	 */
	@RequestMapping("getJingpai")
	public void setUnvalidUser(HttpServletRequest request,
			HttpServletResponse response, Model model,String userId,String changeName) {
		logger.info("----获取竞拍信息----");
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("result", "成功"); 
		Map param = new HashMap<String, Object>();
		List<Map<String,Object>> jingpaiList = jingpaiService.selectGoodsJingpai(param);
		model.addAttribute("jingpaiList", jingpaiList);
		jsonObject.put("jingpaiList", jingpaiList); 
		ResponseUtils.renderText(response, "UTF-8", jsonObject.toString());
		return;
	}

}
