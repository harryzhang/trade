package com.redpack.notice.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redpack.base.controller.BaseController;
import com.redpack.notice.INoticeService;


@Controller
@RequestMapping(value = "/notice")
public class NoticeController extends BaseController {
	
	
	private static final Logger logger = Logger.getLogger(NoticeController.class);
	
	@Autowired
	private INoticeService noticeSevice;
	
	/**
	 * 公告
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/notice")
	public String toNotice(HttpServletRequest request,Model model) {
		List<Map<String, Object>>  noticeList = noticeSevice.queryNotice();
//		if(noticeList !=null && noticeList.size()> 0){
//			Map<String,Object> map = noticeList.get(0);
//			String noticeContent = (String) map.get("notice_content");
//			model.addAttribute("noticeContent", noticeContent);
//		}
		model.addAttribute("noticeList", noticeList);
		logger.debug("----NoticeController.toNotice;----");
		return "notice/notice";
	}
	
	/**
	 * 公告
	 * 
	 * @return
	 * @author: zhangyunhua
	 * @date 2015-3-29 上午3:36:11
	 */
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request,Model model) {
		String Id = request.getParameter("noticeId");
		List<Map<String,Object>> noticeList = noticeSevice.queryNoticeById(Id);
		if(noticeList !=null && noticeList.size()>0){
			Map notice = noticeList.get(0);
			model.addAttribute("noticeContent", notice.get("notice_content"));
		}
		logger.debug("----NoticeController.detail;----");
		return "notice/noticeDetail";
	}
	

}
