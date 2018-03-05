<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0">
        <meta name="apple-touch-fullscreen" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">


        <title>商品</title>

		<link href="<c:url value ='/res-kuangji/css/global.css'/>" rel="stylesheet" type="text/css">
        <link href="<c:url value ='/res-kuangji/css/select.css'/>" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/jquery-2.1.1.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value ='/res-kuangji/js/top.js'/>"></script>
		<script type="text/javascript" src="<c:url value ='/res-kuangji/js/select.js'/>"></script>
<%-- 
<script>
        //滑动加载...
	var page = 2;
	var finished = 0;
	var sover = 0;
    $(function () {
        loadgoods();
        
        if ($(".top dd").text() == "商品") {
            $(".hotgoods").show();
        } else {
            $(".hotgoods").hide();
        }
        $(".goods").click(function () {
            $(".hotgoods").show();
        });
        $(".shops").click(function () {
            $(".hotgoods").hide();
        });

        //载入分词
        $.get("http://yingzc.net/assets/xml" + '/keywords.xml', function (d) {
            if($('#search').val()!=null){
            $(d).find('keyword').each(function () {
                var $keyword = $(this);
                var title = $keyword.attr("title");
//                alert(title);
                    var html = '<li>' + title + '</li>';
                $('.top ol').append($(html));
            });}
        });
        //分词索引
        $("#search").keyup(function () {
            if ($("#search").val() && $("#search").val() != '') {
                var allOl = $(".top ol").children();
                //alert(allOl);
                $(allOl).each(function (i) {
                    //alert($(allOl[i]).html());
                    if ($(allOl[i]).html().indexOf($("#search").val()) >= 0) {
                        $(this).show();
                    }
                });
                $(allOl).each(function (i) {
                    //alert($(allOl[i]).html());
                    if ($(allOl[i]).html().indexOf($("#search").val()) <= -1) {
                        $(this).hide();
                    }
                });
            }
        });

        var cat_id = 0;
        function loadgoods() {
           
            $(this).parents("div.ssbox").siblings("ol").hide();
			$(".goodsList").show().siblings("div.shopList").show();
            var search = $("#search").val() == "请输入搜索内容" ? "" : $("#search").val();
            var orderby = $("#orderby").val();
            var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
	    var page = 1;
            $.ajax({
                type: "Post",
                url: "http://yingzc.net/mobile/select/showGoodsList",
                data: {cat_id: cat_id, orderby: orderby, search: search, _token: token,page:page},
                dataType: 'json',
                success: function (data) {
                    var result = '';
                    var flag = 0;
                    var url = "http://yingzc.net/mobile/goodsDetail";
                    //var uploads_url = "http://yingzc.net/assets/img/tu.png";
                    var uploads_url = "http://image.yutu365.org/";
                    $.each(data.data, function (index, item) {
                        if (flag % 2 == 0) {
                            result += ' <a href="' + url + '/' + item.goods_id + '">  <dl class="hleft">';
                        } else {
                            result += ' <a href="' + url + '/' + item.goods_id + '">  <dl class="hright">';
                        }
                        result += ' <dt><img src="' + uploads_url + '/' + item.goods_thumb + '" /></dt>';
                        result += ' <dd class="name">' + item.goods_name + '</dd>';
                        result += '<dd class="price"><font class="fleft">';
			if(item.pay_type=="0"){
			     result += '￥' + item.shop_price ;
			}else if(item.pay_type=="1"){
			    result += '宝石：' + item.repeat_prize;
			}else if(item.pay_type=="2"){
			     result += '￥' + item.shop_price ;
			     result += '+';
                             result += '宝石：' + item.repeat_prize;
			}
                        result += '</font><font class="fright">已售' + item.sale_count + '件</font></dd>';
                        result += '<dd class="price"><font class="f">';
                        result += '</font></dd>';
                        result += ' </dl>';
                        result += ' </a> ';
                        flag++;
                    });
                    $(".goodsList .list").empty();
                    $('.goodsList .list').append(result);
                },
                error: function () {
                    alert('Ajax error!');
                }
            });
        }
        $(".hotgoods a").click(function () {
            var search = $(this).text();
            $("#search").val(search);
            $(".goodsList").show().siblings("div.shopList").hide();
            loadgoods();
        });
        $(".goodsList ul li").click(function () {
            var orderby = "zh";//综合
            page = 2;
            finished = 0;
            sover = 0;
            if ($(this).text() == "销量") {
                orderby = "xl";
                $("#orderby").val(orderby);
                $(".goodsList").show().siblings("div.shopList").hide();
                loadgoods();
            }
            if ($(this).text() == "最新") {
                orderby = "zx";
                $("#orderby").val(orderby);
                $(".goodsList").show().siblings("div.shopList").hide();
                loadgoods();
            }
        });
        $(".goodsList ol li").click(function () {
            if ($(this).text() == "综合排序") {
                var orderby = "zh";
            }
            if ($(this).text() == "价格递增") {
                var orderby = "jgdz";
            }
            if ($(this).text() == "价格递减") {
                var orderby = "jgdj";
            }
            if ($(this).text() == "积分换购") {
                var orderby = "qejf";
            }
            page = 2;
            finished = 0;
            sover = 0;
            $("#orderby").val(orderby);
            $(".goodsList").show().siblings("div.shopList").hide();
            loadgoods();
        });
        $(".ssbox dt").click(function () {
            var search = $("#search").val() == "请输入搜索内容" ? "" : $("#search").val();
            if ($(this).parents("div.ssbox").siblings("dd").text() == "商品") {
                $(this).parents("div.ssbox").siblings("ol").hide();
                $(".goodsList").show().siblings("div.shopList").hide();
                loadgoods();
            } else {
                $(this).parents("div.ssbox").siblings("ol").hide();
                $(".shopList").show().siblings("div.goodsList").hide();
                var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";
                var url = "http://yingzc.net/mobile/storeDetail";
                $.ajax({
                    type: "Post",
                    url: "http://yingzc.net/mobile/select/showSupplierList",
                    data: {search: search, _token: token},
                    dataType: 'json',
                    success: function (data) {
                        var result = '';
                        var flag = 0;
                        $.each(data.data, function (index, item) {
                            result += ' <div class="shop"><a href="' + url + '/' + item.user_id + '">  <dl>';
                            result += ' <dt><img src="' + item.photo + '" /></dt>';
                            result += ' <dd class="sname">' + item.nick_name + '</dd>';
                            result += ' <dd class="saddr">' + item.m_province_name + item.m_city_name + item.m_district_name + item.address + '</dd>';
                            result += ' <dd class="snumber">' + item.gcount + '款商品</dd>';
                            result += ' </dl>';
                            result += ' </a> </div>';
                            flag++;
                        });
                        $(".shopList").empty();
                        $('.shopList').append(result);
                    },
                    error: function () {
                        alert('Ajax error!');
                    }
                });
            }

        });

//	//如果屏幕未到整屏自动加载下一页补满
//	var setdefult = setInterval(function () {
//	    if (sover == 1) {
//		clearInterval(setdefult);
//	    } else if ($(".recommand").height() < $(window).height()) {
//		loadmore($(window));
//	    } else {
//		clearInterval(setdefult);
//	    }
//	}, 500);
	//加载完
	function loadover() {
	    if (sover == 1) {
		var text = '<div class="nomore" style="text-align:center;"><span class="loading"></span>没有更多啦~</div>';
		$("body").append(text);
	    }
	}
	function loadmore(obj) {
	    if (finished == 0 && sover == 0) {

		var scrollTop = $(obj).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(obj).height();
		if (scrollTop + windowHeight >= scrollHeight) {

		    //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
		    var txt = '<div class="loadmore" style="text-align:center;><span class="loading"></span>加载中..</div>';
		    $("body").append(txt);
		    //防止未加载完再次执行
		    finished = 1;
		    $(this).parents("div.ssbox").siblings("ol").hide();
		    $(".goodsList").show().siblings("div.shopList").show();
		    var search = $("#search").val() == "请输入搜索内容" ? "" : $("#search").val();
		    var orderby = $("#orderby").val();
		    var token = "ZvL0EsRqGaHp6eIMAedm7QqkKFohqpu5KDMH77cP";

		    $.ajax({
			type: "Post",
			url: "http://yingzc.net/mobile/select/showGoodsList",
			data: {cat_id: cat_id, orderby: orderby, search: search, _token: token, page: page},
			dataType: 'json',
			success: function (data) {
			    if (page >= 10 || page >= data.last_page ||data == ''){
				sover = 1;
				loadover();
			    }
			    var result = '';
			    var flag = 0;
			    var url = "http://yingzc.net/mobile/goodsDetail";
			    //var uploads_url = "http://yingzc.net/assets/img/tu.png";
			    var uploads_url = "http://image.yutu365.org/";
			    $.each(data.data, function (index, item) {
				if (flag % 2 == 0) {
				    result += ' <a href="' + url + '/' + item.goods_id + '">  <dl class="hleft">';
				} else {
				    result += ' <a href="' + url + '/' + item.goods_id + '">  <dl class="hright">';
				}
				result += ' <dt><img src="' + uploads_url + '/' + item.goods_thumb + '" /></dt>';
				result += ' <dd class="name">' + item.goods_name + '</dd>';
				result += '<dd class="price"><font class="fleft">';
				if(item.pay_type=="0"){
				    result += '￥' + item.shop_price ;
				}else if(item.pay_type=="1"){
				    result += '宝石：' + item.repeat_prize;
				}else if(item.pay_type=="2"){
				    result += '￥' + item.shop_price ;
				    result += '+';
				    result += '宝石：' + item.repeat_prize;
				}
			    result += '</font><font class="fright">已售' + item.sale_count + '件</font></dd>';
				result += '<dd class="price"><font class="f">';
				result += '</font></dd>';
				result += ' </dl>';
				result += ' </a> ';
				flag++;
			    });
			    $(".loadmore").remove();
			    //                    $(".goodsList .list").empty();
			    $('.goodsList .list').append(result);

			    page += 1;
			    finished = 0;
			},
			error: function () {
			    alert('Ajax error!');
			}
		    });
		}
	    }
	}
	//页面滚动执行事件
	$(window).scroll(function () {
	    loadmore($(this));
	});
	//--滑动加载结束

    });

</script>
--%>
    </head>
    <body>
        <input id="orderby" type="hidden" value="zh">
<div class="top">
    <b
			onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
    <dd>商品</dd>
    <div class="ssbox">
        <dt></dt>
        <li></li>
        <input id="search" type="text" value="请输入搜索内容">
    </div>
    <ul>
        <li class="goods">商品</li>
<!--        <li class="shops">商家</li>-->
    </ul>
    <ol>
        <!--<li>a</li>
        <li>aa</li>-->
    <li>c</li><li>a</li><li>aa</li><li>aaa</li><li>化工</li><li>化妆品</li><li>化妆用品</li><li>bb</li><li>b</li></ol>
</div>
<div class="hotgoods">
        <a></a>
    </div>

<div class="main">
    <div class="shopList" style="display: block;">
    </div>
    <div class="goodsList" style="display: block;">
        <ul>
            <li class="sort cur">
                <dl>
                    <dt></dt>
                    <dd>综合排序</dd>
                </dl>
            </li>
            <li>销量</li>
            <li>最新</li>
        </ul>
        <ol>
            <li class="cur">综合排序</li>
            <li>积分换购</li>
            <li>价格递增</li>
            <li>价格递减</li>
        </ol>
        <div class="list"> 
        	<a href="<c:url value='/goods/goodsDetail'/>/295">  
	        	<dl class="hleft"> 
	        		<dt><img src="<c:url value ='/res-kuangji/images/295_thumb_G_1468506562066.jpg'/>"></dt> 
	        		<dd class="name">薄蚕丝被</dd>
	        		<dd class="price">
		        		<font class="fleft">宝石：680</font>
		        		<font class="fright">暂未上线</font>
	        		</dd>
	        		<dd class="price">
	        			<font class="f"></font>
	        		</dd> 
	        	</dl> 
        	</a>  
        	<a href="<c:url value='/goods/goodsDetail'/>/320">  
        	<dl class="hright"> 
        		<dt><img src="<c:url value ='/res-kuangji/images/320_thumb_G_1468668624497.jpg'/>"></dt> 
        		<dd class="name">红色经典炒锅</dd>
        		<dd class="price">
        			<font class="fleft">宝石：200</font>
        			<font class="fright">暂未上线</font>
        		</dd>
        		<dd class="price"><font class="f"></font></dd> 
        	</dl> 
        	</a>  
        	<a href="<c:url value='/goods/goodsDetail'/>/296">  
        		<dl class="hleft"> 
        		<dt><img src="<c:url value ='/res-kuangji/images/264_thumb_G_1468389955360.jpg'/>"></dt> 
        		<dd class="name">厚蚕丝被</dd><dd class="price"><font class="fleft">宝石：880</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/311">  <dl class="hright"> <dt><img src="<c:url value ='/res-kuangji/images/311_thumb_G_1468561551568.jpg'/>"></dt> <dd class="name">体重秤</dd><dd class="price"><font class="fleft">宝石：200</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/314">  <dl class="hleft"> <dt><img src="<c:url value ='/res-kuangji/images/314_thumb_G_1468564316581.jpg'/>"></dt> <dd class="name">蒸汽熨斗加美容</dd><dd class="price"><font class="fleft">宝石：200</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/317">  <dl class="hright"> <dt><img src="<c:url value ='/res-kuangji/images/317_thumb_G_1468566015925.jpg'/>"></dt> <dd class="name">车载吸尘器</dd><dd class="price"><font class="fleft">宝石：200</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/281">  <dl class="hleft"> <dt><img src="<c:url value ='/res-kuangji/images/281_thumb_G_1468483573992.jpg'/>"></dt> <dd class="name">伊菲特女士包</dd><dd class="price"><font class="fleft">宝石：598</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/291">  <dl class="hright"> <dt><img src="<c:url value ='/res-kuangji/images/291_thumb_G_1468505732611.jpg'/>"></dt> <dd class="name">床上四件套</dd><dd class="price"><font class="fleft">宝石：800</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/301">  <dl class="hleft"> <dt><img src="<c:url value ='/res-kuangji/images/301_thumb_G_1468507601548.jpg'/>"></dt> <dd class="name">男士名表</dd><dd class="price"><font class="fleft">宝石：680</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/315">  <dl class="hright"> <dt><img src="<c:url value ='/res-kuangji/images/315_thumb_G_1468564758699.jpg'/>"></dt> <dd class="name">香皂</dd><dd class="price"><font class="fleft">宝石：20</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/318">  <dl class="hleft"> <dt><img src="<c:url value ='/res-kuangji/images/318_thumb_G_1468569961932.jpg'/>"></dt> <dd class="name">枕头</dd><dd class="price"><font class="fleft">宝石：200</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/319">  <dl class="hright"> <dt><img src="<c:url value ='/res-kuangji/images/319_thumb_G_1468570102768.jpg'/>"></dt> <dd class="name">枕头</dd><dd class="price"><font class="fleft">宝石：200</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/341">  <dl class="hleft"> <dt><img src="<c:url value ='/res-kuangji/images/341_thumb_G_1469082658536.jpg'/>"></dt> <dd class="name">小麦胚芽茶</dd><dd class="price"><font class="fleft">宝石：200</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/264">  <dl class="hright"> <dt><img src="<c:url value ='/res-kuangji/images/264_thumb_G_1468389955360.jpg'/>"></dt> <dd class="name">破解财富密码课程</dd><dd class="price"><font class="fleft">￥1000.00  宝石：400</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/268">  <dl class="hleft"> <dt><img src="<c:url value ='/res-kuangji/images/268_thumb_G_1468541209081.jpg'/>"></dt> <dd class="name">威马堡罗女士包</dd><dd class="price"><font class="fleft">宝石：698</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/269">  <dl class="hright"> <dt><img src="<c:url value ='/res-kuangji/images/269_thumb_G_1468541143447.jpg'/>"></dt> <dd class="name">威马堡罗女士包</dd><dd class="price"><font class="fleft">宝石：698</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/270">  <dl class="hleft"> <dt><img src="<c:url value ='/res-kuangji/images/270_thumb_G_1468475895715.jpg'/>"></dt> <dd class="name">伊菲特女士包</dd><dd class="price"><font class="fleft">宝石：598</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/271">  <dl class="hright"> <dt><img src="<c:url value ='/res-kuangji/images/271_thumb_G_1468476061372.jpg'/>"></dt> <dd class="name">伊菲特女士包</dd><dd class="price"><font class="fleft">宝石：598</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  <a href="<c:url value='/goods/goodsDetail'/>/272">  <dl class="hleft"> <dt><img src="<c:url value ='/res-kuangji/images/272_thumb_G_1468476602593.jpg'/>"></dt> <dd class="name">威马堡罗女士包</dd><dd class="price"><font class="fleft">宝石：598</font><font class="fright">暂未上线</font></dd><dd class="price"><font class="f"></font></dd> </dl> </a>  
        		<a href="<c:url value='/goods/goodsDetail'/>/273">  
        			<dl class="hright"> 
		        		<dt>
		        			<img src="<c:url value ='/res-kuangji/images/273_thumb_G_1468477513682.jpg'/>">
		        		</dt> 
		        		<dd class="name">伊菲特女士包</dd>
		        		<dd class="price">
			        		<font class="fleft">宝石：598</font>
			        		<font class="fright">暂未上线</font>
		        		</dd>
		        		<dd class="price">
		        			<font class="f"></font>
		        		</dd> 
	        		</dl> 
        		</a>  
        		<a href="<c:url value='/goods/goodsDetail'/>/274">  
        			<dl class="hleft"> 
        				<dt>
        					<img src="http://image.yutu365.org//images/201607/thumb_img/274_thumb_G_1468477895730.jpg">
        				</dt>
			        	 <dd class="name">伊菲特女士包</dd>
			        	 <dd class="price">
			        	 	<font class="fleft">宝石：598</font>
			        	 	<font class="fright">暂未上线</font>
			        	 </dd>
			        	 <dd class="price">
			        	 	<font class="f"></font>
			        	 </dd> 
        	 		</dl>
        	 </a> 
        	</div>
    </div>
</div>
<div class="backtop" style="display: block;">
    <a href="javascript:scroll(0,0)" hidefocus="true"></a>
</div>

    


</body></html>