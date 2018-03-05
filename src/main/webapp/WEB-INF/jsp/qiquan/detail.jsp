<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>微交易</title>

	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<script type="text/javascript" src="<c:url value ='/res-qiquan/js/echarts.min.js'/>"></script>
<style>
* {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}
*{ margin:0; padding:0;}
body{ font-size: 1.4rem;background-color: #333;}
table {border-collapse:collapse;border-spacing:0;} 
fieldset,img {border:0} 
ol,ul{list-style:none} 
ol li,ul li,li{list-style:none} 
a {
	text-decoration: none
}
.container{margin-top:10px;}


.detailtitle {
    width: 100%;
    height: 40px;
    line-height: 40px;
    color:#fff;
    background-color:#333;
    border-bottom:1px blue solid ;
}

.detailtitle li{
    width: 100%;
    height:100%;
    line-height: 100%;
    padding-top:8px;
    text-align: center;
}



.goodslst {
    width: 100%;
    height: 40px;
    line-height: 40px;
    color:#fff;
    background-color:#333;
    border-bottom:1px blue solid ;
}

.goodslst li{
    width: 33%;
    float: left;
    height:100%;
    line-height: 100%;
    padding-top:8px;
    text-align: center;
}

.goodslst .title {
	background-color: #03A9F4;
    font-size: 1.2rem;
    border-radius: 3px;
    text-align: center;
    padding-right: 8px;
    padding-left: 8px;
    padding-bottom: 5px;
    padding-top: 5px;
}
.goodslst .goodsName {
     padding-left:10px;
     text-align: left;
}

.mzmd {
    width: 100%;
    height: 80px;
    line-height: 80px;
    color:#fff;
    background-color:#333;
}

.mzmd li:FIRST-CHILD{
    width: 48%;
    float: left;
    height:80%;
    line-height: 100%;
    margin-left:2%;
    margin-top:15px;
    margin-bottom:15px;
    padding-top:18px;
    text-align: center;
    border-radius: 10px;
    background-color: red
}

.mzmd li:last-child{
    width: 48%;
    float: left;
    height:80%;
    line-height: 100%;
    margin-top:15px;
    margin-bottom:15px;
    margin-left:2%;
    padding-top:18px;
    text-align: center;
    border-radius: 10px;
    background-color: green;
}

.tzjeul {
    width: 100%;
    height: 50px;
    line-height: 40px;
    color:#fff;
    background-color:#333;
}


.tzjeul li{
    width: 25%;
    float: left;
    height:100%;
    line-height: 100%;
    padding-top:8px;
    text-align: center;
}

.tzjeul .title{
	background-color: #03A9F4;
    font-size: 1.2rem;
    border-radius: 3px;
    text-align: center;
    padding-right: 8px;
    padding-left: 8px;
    padding-bottom: 5px;
    padding-top: 5px;
}
.tzjeul .goodsName {
     padding-left:10px;
     text-align: left;
     font-size:1rem;
}

</style>

</head>
<body>

	<div class="container">
	    <div>
			<ul class="detailtitle">
				<li>
					<span>欧元兑美元</span>
				</li>
			</ul>
			<ul class="goodslst">
				<li class="goodsName">
					<span>高：${maxMin.maxCurr}</span>
				</li>
				<li style="border-left:1px blue solid;border-right: 1px blue solid; ">
					<span></span>
				</li>
				<li>
					<span>低：${maxMin.minCurr}</span>
				</li>
			</ul>
		</div>		
		<ul class="mzmd">
			<li class="mzmdspan" cdata="up">
				<span >买涨</span>
			</li>
			<li class="mzmdspan" cdata="down">
				<span >买跌</span>
			</li>
		</ul>
		
		<ul class="tzjeul" onclick="javascript:;">
			<li class="goodsName" >
				<span>投资金额</span>
			</li>
			<li>
				<span class="title" cdata="20">20元</span>
			</li>
			<li>
				<span class="title" cdata="200">200元</span>
			</li>
			<li>
				<span class="title" cdata="500">500元</span>
			</li>
			<input type="hidden" id="buyToken" value="${buyToken}"/>
			<input type="hidden" id="buyDirect" value="${buyDirect}"/>
		</ul>
	</div>
	
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width:100%;height:400px;"></div>

<script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
<script type="text/javascript">
	function getHuilv(){
		$.post('<c:url value="/qiquan/getHuilv.html"/>', {}, function(retData) {
			
			if(retData.retCode=="1"){
				alert("初始化失败");
			}else if(retData.retCode=="0"){
				var option = myChart.getOption();
				//option.xAxis.data = retData.xdate;
				option.xAxis["data"]=retData.xdate;
				//option.xAxis.data = ['周一','周二','周三','周四','周五','周六','周日'];
				xdate=retData.xdate;
				option.series[0].data = retData.yData;
				myChart.setOption(option);   
			}
		},"json");
	}
	
	$(".title").on("click",function(){
		var amt = $(this).attr("cdata");
		var mdirect = $("#buyDirect").val();
		var tipmsg="买涨";
		if("down"===mdirect){
			tipmsg="买跌";
		}
		if(confirm("你确定要购买"+amt+"股"+tipmsg+"期权")){
			$.post('<c:url value="/qiquan/buyQiquan.html"/>', {"amount":amt,"token":$("#buyToken").val(),"buyDirect":mdirect}, function(retData) {
				
				$("#buyToken").val(retData.buyToken);
				if(retData.retCode=="1"){
					alert(retData.msg );
				}else if(retData.retCode=="0"){
					alert(amt+"买涨期权，购买成功"); 
				}
			},"json");
		}
	});

	$(".mzmdspan").on("click",function(){
		var mdirect = $(this).attr("cdata");
		$("#buyDirect").val(mdirect);
	});
</script>	
<script type="text/javascript">
	
//var base = +new Date(1968, 9, 3);
//var oneDay = 24 * 3600 * 1000;
var xdate = [];
var data = [];

option = {
    tooltip: {
        trigger: 'axis',
        position: function (pt) {
            return [pt[0], '10%'];
        }
    },
    title: {
        left: 'center',
        text: '欧元兑美元',
    },
    legend: {
        top: 'bottom',
        data:['意向']
    },
    toolbox: {
        feature: {
            dataZoom: {
                yAxisIndex: 'none'
            },
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: xdate,
        //data : ['周一','周二','周三','周四','周五','周六','周日'],
        splitLine: {show: false},
        splitNumber: 20,
        min: 'dataMin',
        max: 'dataMax',
        scale: false,
        axisLabel:{
              textStyle:{
                 color:"white", //刻度颜色
                 fontSize:5  //刻度大小
            }
          }
    },
    yAxis: {
    	scale: true,
        type: 'value',
        boundaryGap: [0, '100%'],
        axisLabel:{
            textStyle:{
               color:"white", //刻度颜色
               fontSize:5  //刻度大小
          }
        }
    },
    dataZoom: [{
        type: 'inside',
        start: 0,
        end: 10
    }, {
        start: 0,
        end: 10,
        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
        handleSize: '80%',
        handleStyle: {
            color: '#fff',
            shadowBlur: 3,
            shadowColor: 'rgba(0, 0, 0, 0.6)',
            shadowOffsetX: 2,
            shadowOffsetY: 2
        }
    }],
    series: [
        {
            name:'汇率',
            type:'line',
            smooth:true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            },
            data: data
        }
    ]
};
	

	//基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    getHuilv();
    
</script>


</body>
</html>