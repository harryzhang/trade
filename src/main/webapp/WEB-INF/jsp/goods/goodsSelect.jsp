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
			    result += '积分：' + item.repeat_prize;
			}else if(item.pay_type=="2"){
			     result += '￥' + item.shop_price ;
			     result += '+';
                             result += '积分：' + item.repeat_prize;
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
				    result += '积分：' + item.repeat_prize;
				}else if(item.pay_type=="2"){
				    result += '￥' + item.shop_price ;
				    result += '+';
				    result += '积分：' + item.repeat_prize;
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
		    <b onclick="javascript:window.location.href=&#39;<c:url value='/redPack/personalCenter.html'/>&#39;"></b>
		    <dd style="color:#333;width:50%">积分兑换商品</dd>
		    <span></span>
		</div>
<div class="main">
    <div class="shopList" style="display: block;">
    </div>
    <div class="goodsList" style="display: block;">
        <div class="list"> 
        	<c:if test="${!empty goodsLst}">
				<c:forEach   var="goods" items="${goodsLst}" >
		        	<a href="<c:url value='/goods/goodsDetail'/>/${goods.goodsId}">  
			        	<dl class="hleft"> 
			        		<dt><img width="250" height="220"  src="<c:url value ='/res-kuangji/goodsImg/images/${goods.imageSrc}'/>"></dt> 
			        		<dd class="name">${goods.gname}</dd>
			        		<dd class="price">
				        		<font class="fleft">积分：${goods.price}</font>
				        		<font class="fright">去购买</font>
			        		</dd>
			        		<dd class="price">
			        			<font class="f"></font>
			        		</dd> 
			        	</dl> 
		        	</a>
	        	</c:forEach>
        	</c:if>
        	</div>
    </div>
</div>
<div class="backtop" style="display: block;">
    <a href="javascript:scroll(0,0)" hidefocus="true"></a>
</div>

</body></html>