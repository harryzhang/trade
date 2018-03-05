<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>团队结构树</title>
<meta name="viewport"
	content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=nos" />
<link rel="stylesheet"
	href='<c:url value="/res/css/mainsm.css?v="/>${cssversion}' />
<link
	href='<c:url value="/res/js/plugins/modal/modal.css?"/>${jsversion}'
	type="text/css" rel="stylesheet">
<link href='<c:url value="/res/css/main.min.css?"/>${jsversion}'
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src='<c:url value="/res/js/libs/zepto.min.js?v="/>${jsversion}'></script>
<script type="text/javascript"
	src='<c:url value="/res/js/global.js?v="/>${jsversion}'></script>

<script type="text/javascript"
	src='<c:url value="/res/js/main.min.js?v="/>${jsversion}'></script>
<script type="text/javascript"
	src='<c:url value="/res/js/organization.js?v="/>${jsversion}'></script>

<style type="text/css">
.OrgBox {
	font-size: 12px;
	padding: 5px 5px 5px 5px;
	clear: left;
	float: left;
	text-align: center;
	position: absolute; //
	background-image: url(${fileServerUrl}/res/images/org.jpg);
	width: 48px;
	height: 56px;
}

.OrgBox img {
	width: 30px;
	height: 30px;
}

.OrgBox div {
	color: #FFA500;
	font-weight: 800;
}

.top-bar {
	position: relative;
	height: 1.95rem;
	width: 100%;
	font-size: 1.1rem;
	color: #fff;
	background: url(/res/images/head-bg.jpg) 0 0 no-repeat;
	line-height: 1.95rem;
	background-color: #20abad
}

.top-bar .icon-back, .top-bar .icon-back2 {
	position: absolute;
	height: 1.25rem;
	width: 1.25rem
}

.top-bar .btn {
	display: inline-block;
	width: 3rem;
	height: 1.1rem;
	background: #E74C3C;
	color: #fff;
	font-size: 0.6rem;
	position: absolute;
	line-height: 1.1rem;
	text-align: center;
	border-radius: 0.23rem;
	right: 0.25rem;
	top: .5rem;
}

.top-bar .icon-back {
	background: url(/res/images/icon-back.png) 50% 50% no-repeat;
	background-size: 30%;
	left: .5rem;
	top: .4rem
}

.top-bar .icon-back2 {
	background: url(/res/images/icon-back2.png) 50% 50% no-repeat;
	background-size: 30%;
	left: .5rem;
	top: .4rem
}


.custRedBox{
	border: 1px solid black;
    width: 30px;
    height: 30px;
    margin: 0;
    -webkit-border-radius: 15px;
    -moz-border-radius: 15px;
    -o-border-radius: 15px;
    border-radius: 15px;    
    text-align: center; 
	background-color: red;
}
.custYellowBox{
	border: 1px solid black;
    width: 30px;
    height: 30px;
    margin: 0;
    -webkit-border-radius: 15px;
    -moz-border-radius: 15px;
    -o-border-radius: 15px;
    border-radius: 15px;    
    text-align: center; 
	background-color: green;
}
.custGreyBox{ 
	border: 1px solid black;
    width: 30px;
    height: 30px;
    margin: 0;
    -webkit-border-radius: 15px;
    -moz-border-radius: 15px;
    -o-border-radius: 15px;
    border-radius: 15px;    
    text-align: center;
	background-color: grey;
}
</style>


</head>
<body>
	<div class="wrap flex">
		<header class="header">
			<a class="header-left"
				href="<c:url value='/redPack/personalCenter.html'/>"><img
				src="<c:url value="/res/images/icon-back.png"/>" alt=""></a>
			<h1 class="header-title">查看团队结构树</h1>
		</header>		
	</div>
</body>
</html>

<script>
	// var box = document.getElementById('OrgBox');
	//box.onclick
	function CookieGroup() {
		//alert(123)	
	}

	function zeroTree(prant) {
		var zeroTree = new OrgNode()
		// alert(oneTreeData[i].name)
		zeroTree.customParam.EmpName = "暂无";
		zeroTree.customParam.department = "";

		prant.Nodes.Add(zeroTree);
	}

	function initTree(rootData, grade, prant,userId) {
		var oneTreeData = eval(rootData.childList);
		var gradeText = "等级4";
		

		for (var i = 0; i < oneTreeData.length; i++) {
			var boxClass = "custGreyBox";
			// alert(oneTreeData[i].name+" ") 
			var treeChild = oneTreeData[i];

			var ontTree = new OrgNode()
			var treeGrade = oneTreeData[i].grade;
			var memberStatus = oneTreeData[i].status;
			var childId =  oneTreeData[i].id;
			
			
			if(oneTreeData[i].status == 1){
				boxClass="custYellowBox";
			}
			
			if(userId == childId){
				boxClass = "custRedBox";
			}
			
			//alert(treeGrade)
			/* if (3 == treeGrade) {
				gradeText = "等级3";
				if (memberStatus == 0) {
					photo = "00.png";
				} else {
					photo = "03.png";
				}
			}
			if (2 == treeGrade) {
				gradeText = "等级2";
				if (memberStatus == 0) {
					photo = "00.png";
				} else {
					photo = "02.png";
				}
			}
			if (1 == treeGrade) {
				gradeText = "等级1";
				if (memberStatus == 0) {
					photo = "00.png";
				} else {
					photo = "01.png";
				}
			}
			if (0 == treeGrade) {
				gradeText = "等级0";
				photo = "00.png";
			} */

			// alert(oneTreeData[i].name)
			//ontTree.customParam.EmpName = oneTreeData[i].name;
			ontTree.customParam.EmpName = oneTreeData[i].name;
			ontTree.customParam.department = "";
			ontTree.customParam.custmBoxClass = boxClass;			

			prant.Nodes.Add(ontTree);
			if (grade > 1) {
				initTree(treeChild, grade - 1, ontTree, userId);

			}

		}
	}
</script>

<script language="javascript">
	var root = new OrgNode()

	//init data
	var json = {};

	var options = {
		type : "post",
		url : "<c:url value='/userTree/getTreeData.do?netWork=${netWork}'/>&treeDataUserId=${treeDataUserId}"
	};
	ajaxRequest(
			options,
			function(data) {
				if (data.result == 1) {
					popWindow("权限受限！");
				} else {
					treeData = data.dataTree;
					userId = data.userId;
					var  boxclass= "custYellowBox";
					if(treeData.id && userId == treeData.id){
						boxclass = "custRedBox";
					}
					
					root.customParam.EmpName = ""
					root.customParam.department = treeData.name;
					root.customParam.custmBoxClass = boxclass;
					initTree(treeData, 3, root,userId);

					var OrgShows = new OrgShow(root);
					OrgShows.Top = 80;
					OrgShows.Left = 20;
					OrgShows.IntervalWidth = 12;
					OrgShows.IntervalHeight = 30;
					//OrgShows.ShowType=2;
					//OrgShows.BoxHeight=100;
					//OrgShows.BoxTemplet = "<div id=\"{Id}\" class=\"OrgBox\" onclick=\"CookieGroup()\"><img src=\"{EmpPhoto}\" /><span>{EmpName}</span><div>{department}</div></div>"
					OrgShows.BoxTemplet = "<div id=\"{Id}\" class=\"OrgBox\" onclick=\"CookieGroup()\"><div class=\"{custmBoxClass}\" /></div><div><font color=\"black\">{EmpName}</font></div><div><font color=\"black\">{department}</font></div></div>"
					OrgShows.Run()
				}
			});
</script>