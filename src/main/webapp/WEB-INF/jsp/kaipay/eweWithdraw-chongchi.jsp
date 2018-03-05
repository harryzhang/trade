<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>提现列表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  		<form name="form1" action="eweWithdrawController.do?postChongzhi" method="post" onsubmit="validate()">
		<input type="hidden" name="serverUrl" value="https://pg.openepay.com/gateway/index.do?" />
		<input type="hidden" name="key" value="zxcvbnm1" />
		<input type="hidden" name="inputCharset" value="1" />
		<input type="hidden" name="pickupUrl" value="http://lvcplat.com/api-demo/pickup.jsp" />
		<input type="hidden" name="receiveUrl" value="http://lvcplat.com/api-demo/receive.jsp" />
		<input type="hidden" name="version" value="v1.0" />
		<input type="hidden" name="language" value="1" />
		<input type="hidden" name="signType" value="1" />
		<input type="hidden" name="merchantId" value="103610161205001" />
		<input type="hidden" name="payerName" value="平台冲值" />
		<input type="hidden" name="orderCurrency" value="156" />
		<input type="hidden" id="orderDatetime" name="orderDatetime" value="20161129133632" />
		<input type="hidden" name="productPrice" value="100" />
		<input type="hidden" name="productNum" value="1" />
		<input type="hidden" name="payType" value="0" />
		<table class="table_box" width="100%">
			<tr>
			<td style="padding-left:20px;">
			<table class="table_box" width="90%" style="color:#000">
				<tr>
					<td colspan="4" class="tit_bar">平台充值</td>
				</tr>
			
				<tr>
					<td>1</td>
					<td>商户系统订单号:</td>
					<td><input type="text" name="orderNo" id="orderNo" value=""/> <input type="button" value="刷新订单号" onclick="setOrderNo();"/></td>
					<td class="comment">刷新获取</td>
				</tr>
				<tr>
					<td>2</td>
					<td>订单金额(精确到分):</td>
					<td><input type="text" name="orderAmount" id="orderAmount" value="100"/></td>
					<td class="comment">不可空，整型，以分为单位</td>
				</tr>
				<tr>
					<td>3</td>
					<td>商品名称:</td>
					<td><input type="text" name="productName" id="productName" value="笔记本电脑"/></td>
					<td class="comment">不可空，英文或中文字符串</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>19</td> -->
<!-- 					<td>商品代码:</td> -->
<!-- 					<td><input type="text" name="productId" id="productId" value="P1005001"/></td> -->
<!-- 					<td class="comment">字母、数字或 - 、_ 的组合。用于使用产品数据中心的产品数据，或用于市场活动的优惠 -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>20</td> -->
<!-- 					<td>商品描述:</td> -->
<!-- 					<td><input type="text" name="productDesc" id="productDesc" value="商品描述中文测试"/></td> -->
<!-- 					<td class="comment">英文或中文字符串</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>21</td> -->
<!-- 					<td>附加参数1:</td> -->
<!-- 					<td><input type="text" name="ext1" id="ext1" value="附加参数1"/></td> -->
<!-- 					<td class="comment">英文或中文字符串支付完成后，按照原样返回给商户</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>22</td> -->
<!-- 					<td>附加参数2:</td> -->
<!-- 					<td><input type="text" name="ext2" id="ext2" value="附加参数2"/></td> -->
<!-- 					<td class="comment">英文或中文字符串支付完成后，按照原样返回给商户</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>23</td> -->
<!-- 					<td>支付方式:</td> -->
<!-- 					<td><input type="text" name="payType" id="payType" value="0"/></td> -->
<!-- 					<td class="comment">不可空，固定选择值：0、1、2、3。0代表不指定支付方式；1代表网银支付；31代表银联认证支付。 -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>24</td> -->
<!-- 					<td>发卡行机构号:</td> -->
<!-- 					<td><input type="text" name="issuerId" id="issuerId" value="" /> -->
<!-- 					<td class="comment">银行或机构代码，用于指定支付使用的付款方机构，例如直连</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>25</td> -->
<!-- 					<td>业务扩展字段:</td> -->
<!-- 					<td><input type="text" name="extTL" id="extTL" value=""/></td> -->
						
<!-- 					<td class="comment">规则说明:<br> -->
<!-- 					1)	按固定金额分账时，总金额不超过该笔订单金额；按比例分账时，总百分比不超过100%；<br> -->
<!-- 					2)	最多可配置10个开联账户号；<br> -->
<!-- 					3)	因分账配置不合法导致无法正常分账的，该笔订单金额将划至商户的余额账户中<br> -->
<!-- 					<br> -->
<!-- 					范例： -->
<!-- 					<br> -->
<!-- 					1)	&lt;A00001&gt;1|10203040506070809001,1000|10203040506070809002,200&lt;/A00001&gt; <br> -->
<!-- 					表示按固定金额分账，开联账户10203040506070809001分10元，开联账户10203040506070809002分2元。<br> -->
<!-- 					<br> -->
<!-- 					2)	&lt;A00001&gt;2|10203040506070809001,10|10203040506070809002,0.5&lt;/A00001&gt;<br> -->
<!-- 					表示按比例分账，开联账户10203040506070809001分10%，开联账户10203040506070809002分0.5%<br> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td colspan="4" align="center"><input type="submit" name="确认充值" value="确认充值"/></td>
				</tr>
			</table>
			</td></tr>
		</table>
		</form>
	</body>
	<script language="javascript">
		setOrderNo();	
		function setOrderNo() {
			var curr = new Date();
			var m = curr.getMonth() + 1;
			if (m < 10) {m = '0' + m;}
			var d = curr.getDate();
			if (d < 10) {d = '0' + d;}
			var h = curr.getHours();
			if (h < 10) {h = '0' + h;}
			var mi = curr.getMinutes();
			if (mi < 10) {mi = '0' + mi;}
			var s = curr.getSeconds();
			if (s < 10) {s = '0' + s;}		
			var strDatetime = '' + curr.getFullYear() + m + d + h + mi + s;		
			document.getElementById("orderDatetime").value = strDatetime;
			document.getElementById("orderNo").value = 'NO' + strDatetime;
		}
		function validate() {
// 			var version=document.getElementById("version").value;
// 			if(null==version || ''==version){
// 				alert('错误提示：版本号不能为空');
// 				return false;
// 			}
// 			var merchantId=document.getElementById("merchantId").value;
// 			if(null==merchantId || ''==merchantId){
// 				alert('错误提示：商户号不能为空');
// 				return false;
// 			}
// 			var receiveUrl=document.getElementById("receiveUrl").value;
// 			if(receiveUrl==null || receiveUrl==''){
// 				alert('错误提示：receiveUrl不能为空');
// 				return false;
// 			}
// 			var payType=document.getElementById("payType").value;
// 			if(null==payType&&''==payType){
// 				alert('错误提示：支付方式不能为空');
// 				return false;
// 			}
// 			var signType=document.getElementById("signType").value;
// 			if(null==signType && ''==signType){
// 				alert('错误提示：签名方式不能为空');
// 				return false;
// 			}
			var orderNo=document.getElementById("orderNo").value;
			if(null==orderNo && ''==orderNo){
				alert('错误提示：订单号不能为空');
				return false;
			}
			var orderAmount=document.getElementById("orderAmount").value;
			if(null==orderAmount && ''==orderAmount){
				alert('错误提示：订单金额不能为空');
				return false;
			}
// 			var orderDatetime=document.getElementById("orderDatetime").value;
// 			if(null==orderDatetime && ''==orderDatetime){
// 				alert('错误提示：订单创建时间不能为空');
// 				return false;
// 			}
	  }
	</script>
 </body>
</html>