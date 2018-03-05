package com.redpack.account.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redpack.account.faced.IUserInfoService;
import com.redpack.account.faced.IUserService;
import com.redpack.account.model.UserDo;
import com.redpack.account.model.UserInfoDo;
import com.redpack.base.controller.BaseController;
import com.redpack.constant.WebConstants;
import com.redpack.utils.IDCardUtil;
import com.redpack.utils.IdCardUtils;
import com.redpack.utils.ResponseUtils;


/**
 * @Description 描述方法作用
 * @author huangzl QQ: 272950754
 * @date 2015年5月27日 下午5:33:52
 * @Project hehenian-lend-login
 * @Package com.hehenian.login.account 
 * @File CommonController.java
*/
@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(CommonController.class);
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserInfoService userInfoService;
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "imageCode")
	public String imageCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// 在内存中创建图象
		int width = 65, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(230, 255));
		g.fillRect(0, 0, 100, 25);
		// 设定字体
		g.setFont(new Font("Arial", Font.CENTER_BASELINE | Font.ITALIC, 18));
		// 产生0条干扰线，
		g.drawLine(0, 0, 0, 0);
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(getRandColor(100, 150));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 15 * i + 6, 16);
		}
		  for(int i=0;i<(random.nextInt(5)+5);i++){  
		        g.setColor(new Color(random.nextInt(255)+1,random.nextInt(255)+1,random.nextInt(255)+1));  
		        g.drawLine(random.nextInt(100),random.nextInt(30),random.nextInt(100),random.nextInt(30));  
		}
		String pageId = request.getParameter("pageId");
		String key = pageId + "_checkCode";
		// 将验证码存入页面KEY值的SESSION里面
		request.getSession().setAttribute(key, sRand);
		request.getSession().setAttribute("keys", sRand);
		// 图象生效
		g.dispose();
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", responseOutputStream);
		// 以下关闭输入流！
		responseOutputStream.flush();
		responseOutputStream.close();
		// 获得页面key值
		return null;
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	
	/**
	 * 验证身份证号码
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @author: chenzhpmf
	 * @date 2015-4-4 上午12:02:52
	 */
	@RequestMapping(value = "isIDNO")
	public void isIDNO(HttpServletRequest request,HttpServletResponse response) throws IOException, NumberFormatException, ParseException {
		JSONObject jsonObject = new JSONObject();
		String IDNO = request.getParameter("idNo");
		if (StringUtils.isBlank(IDNO)) {
			jsonObject.put("putStr", "0");
			ResponseUtils.renderText(response, null,jsonObject.toString());
			return;
		}
		long len = IDNO.length();
		// 验证身份证
		int sortCode = 0;
		int MAN_SEX = 0;
		if (len == 15) {
			sortCode = Integer.parseInt(IDNO.substring(12, 15));
		} else if(len == 18) {
			sortCode = Integer.parseInt(IDNO.substring(14, 17));
		}else{
			jsonObject.put("putStr", "0");
			ResponseUtils.renderText(response, null,jsonObject.toString());
			return;
		}
		if (sortCode % 2 == 0) {
			MAN_SEX = 2;// 女性身份证
		} else if (sortCode % 2 != 0) {
			MAN_SEX = 1;// 男性身份证
		} else {
			jsonObject.put("putStr", "1");// 身份证不合法
			ResponseUtils.renderText(response, null,jsonObject.toString());
			return;
		}
		String iDresutl = "";
		iDresutl = IDCardUtil.chekIdCard(MAN_SEX, IDNO);
		if (iDresutl != "") {
			jsonObject.put("putStr", "1");// 身份证不合法
			ResponseUtils.renderText(response, null,jsonObject.toString());
		}
	}
	
	
	public static void recursiveCode(String key,Map<String,Object> object,Map<String,Object> cityCodeMap){
		for (Map.Entry<String, Object> cityEntry : cityCodeMap.entrySet()) {  
			String cityKey = cityEntry.getKey();
			if(cityKey.contains(key+"_")){
				String code = cityKey.split("_")[1];
				object.put("c"+code, cityEntry.getValue());
			}
		}
	}
	
	/**
	 * 跳转首页
	 * @author  huangzl QQ:272950754
	 * @version 创建时间：2015-7-26 下午09:53:32 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcome(Model model, HttpSession session, HttpServletRequest request) {
		return "redPack/index";
	}

	/**
	 * 修改资料跳转方法
	 * 
	 * @author huangzl QQ:272950754
	 * @version 创建时间：2015-7-26 下午09:05:57
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyInfo")
	public String modifyInfo(Model model, HttpSession session, HttpServletRequest request) {
		logger.info("----------修改用户资料---------");
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		
		return "member/userInfo";
	}
	/**
	 * 修改资料保存方法
	 *  
	 * @author huangzl QQ:272950754
	 * @version 创建时间：2015-7-26 下午09:50:57
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyUpdata", method = RequestMethod.POST)
	public void modifyUpdata(@ModelAttribute UserInfoDo userInfoDo,Model model, HttpSession session, 
			HttpServletRequest request,
			HttpServletResponse response) {
//		userInfoService.updataUserInfo(userInfoDo);
		
		UserDo user = (UserDo) session.getAttribute(WebConstants.SESSION_USER);
		//更新用户表的name
		UserDo tempSave = new UserDo();
		tempSave.setId(user.getId());
		tempSave.setName(userInfoDo.getRealName());
		userService.updateUser(tempSave);
		
		UserInfoDo userInfo = userInfoService.getByUserId(user.getId());
		if(null == userInfo ){
			userInfo = new UserInfoDo();
			userInfo.setUserId(user.getId());
		}
		userInfo.setRealName(userInfoDo.getRealName());
		userInfo.setZfbNumber(userInfoDo.getZfbNumber());
		userInfo.setWeixiNumber(userInfoDo.getWeixiNumber());
		userInfo.setBankName(userInfoDo.getBankName());
		userInfo.setBankSubbranch(userInfoDo.getBankSubbranch());
		userInfo.setBankAccount(userInfoDo.getBankAccount());
		userInfo.setRealName(userInfoDo.getRealName());
		userInfo.setIdCardNo(userInfoDo.getIdCardNo());
		userInfo.setIdCardNoString(userInfoDo.getIdCardNoString());
		userInfo.setContactAddress(userInfoDo.getContactAddress());
		userInfo.setProvince(userInfoDo.getProvince());
		userInfo.setCity(userInfoDo.getCity());
		
		if(userInfo.getId() == null){
			userInfoService.saveUserInfo(userInfo);
		}else{
			userInfoService.updataUserInfo(userInfo);
		}
		
		user.setUserInfoDo(userInfo);
		session.setAttribute(WebConstants.SESSION_USER, user);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "修改成功");
		ResponseUtils.renderText(response, "UTF-8", JSONObject.fromObject(jsonObject).toString());
		return;
	}
	
}
