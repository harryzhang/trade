package sms;
//import com.bcloud.msg.http.HttpSender;

import com.bcloud.msg.http.HttpSender;

public class HttpSenderTest {
	public static void main(String[] args) {
//		String url = "http://222.73.117.158/msg/";// 应用地址
//		String account = "jiekou-clcs-09";// 账号
//		String pswd = "Tch123456CL";// 密码
		String url = "http://222.73.117.156/msg/index.jsp";// 应用地址
		String account = "ANhui123";// 账号
		String pswd = "Tch112233";// 密码
		String mobile = "13723410575";// 手机号码，多个号码使用","分割
		String msg = "亲爱的用户，您的验证码是123456，5分钟内有效。";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String product = null;// 产品ID
		String extno = null;// 扩展码

		try {
			String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, product, extno);
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}
}
