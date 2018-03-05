package com.redpack.quest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.account.model.UserAccountDetailDo;
import com.redpack.account.model.UserDo;
import com.redpack.base.controller.BaseController;
import com.redpack.constant.AccountMsg;
import com.redpack.constant.WebConstants;
import com.redpack.quest.IQuestionService;
import com.redpack.quest.model.QuestionDo;
import com.redpack.utils.ResponseUtils;

import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "/quest")
public class QuestController extends BaseController {
	
	
	private static final Logger logger = Logger.getLogger(QuestController.class);
	
	@Autowired
	private IQuestionService questionService;
	
	/**
	 * 反馈
	 * 
	 */
	@RequestMapping("/saveQuest")
	public String saveWithdraw(@ModelAttribute QuestionDo questionDo,Model model, HttpSession session, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		questionDo.setUserId(user.getId());
		questionDo.setCreateTime(new Date());
		questionService.addQuestion(questionDo);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "反馈成功");
		jsonObject.put("success", true);
		ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
		return "order/success";
	}
	
	/**
	 * 跳转收款页面
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015年8月2日 22:45:08
	 */
	@RequestMapping("/questView")
	public String quest(HttpServletRequest request,HttpServletResponse response,Model model) {
		logger.info("----跳转反馈页面----");
		
		long userId = getUserId(request);
		//用户资金明细
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("userId", userId);
		List<Map> feedbackList = questionService.selectFeedback(parameterMap);
		model.addAttribute("feedbackList", feedbackList);
		return getLocalPath(request,"quest/quest");
	}

}
