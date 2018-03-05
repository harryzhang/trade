<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<script src="${fileServerUrl }/res/js/zepto.min.js"></script>
		<script src="${fileServerUrl }/res/js/main.min.js"></script>
		<link type="text/css" rel="stylesheet" href="${fileServerUrl }/res/css/main.min.css">
<script language="javascript" type="text/javascript" src="${fileServerUrl}/res/js/organization.js"></script>

<title>团队结构树</title>
<style type="text/css">
.OrgBox{
	font-size:12px;
	padding:5px 5px 5px 5px;
	clear:left;
	float:left;
	text-align:center;
	position:absolute;
	//background-image:url(${fileServerUrl}/res/images/org.jpg);
	width:40px;
	height:56px;
}
.OrgBox img{
	width:30px;
	height:30px;
}
.OrgBox div{
	color:#FFA500;
	font-weight:800;
}
.top-bar {
	position:relative;
	height:1.95rem;
	width:100%;
	font-size:1.1rem;
	color:#fff;
	background: url(/res/img/head-bg.jpg) 0 0 no-repeat;
	line-height:1.95rem;
	background-color:#20abad
}
.top-bar .icon-back,.top-bar .icon-back2 {
	position:absolute;
	height:1.25rem;
	width:1.25rem
}
.top-bar .btn{
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
	background:url(/res/images/icon-back.png)50% 50% no-repeat;
	background-size:30%;
	left:.5rem;
	top:.4rem
}
.top-bar .icon-back2 {
	background:url(/res/images/icon-back2.png)50% 50% no-repeat;
	background-size:30%;
	left:.5rem;
	top:.4rem
}

</style>
<script>
// var box = document.getElementById('OrgBox');
 //box.onclick
function CookieGroup(){
	//alert(123)	
}
 
function zeroTree(prant){
	 var zeroTree=new OrgNode()
	   // alert(oneTreeData[i].name)
	    zeroTree.customParam.EmpName="暂无";
	 zeroTree.customParam.department="";
	 zeroTree.customParam.EmpPhoto="${fileServerUrl}/rec/images/00.png";
	     
	    prant.Nodes.Add(zeroTree);
}

function initTree(rootData,grade,prant){
	var oneTreeData = eval(rootData.childList);
	var gradeText = "等级4";
	var photo ="04.png";
	
	
	
	  for(var i=0; i<oneTreeData.length; i++)  
	  {  
	    // alert(oneTreeData[i].name+" ") 
	    var treeChild = oneTreeData[i];
	    
	    var ontTree=new OrgNode()
	    var treeGrade = oneTreeData[i].grade;
	    var memberStatus = oneTreeData[i].status;
	    //alert(treeGrade)
	    if(3==treeGrade){
			 gradeText = "等级3";
			 if(memberStatus == 0){
				photo ="00.png";
			 }else{
			 	photo ="03.png";
			 }
		}
		if(2==treeGrade){
			 gradeText = "等级2";
			 if(memberStatus == 0){
				photo ="00.png";
			 }else{
				photo ="02.png";
			 }
		}
		if(1==treeGrade){
			 gradeText = "等级1";
			 if(memberStatus == 0){
				photo ="00.png";
			 }else{
				photo ="01.png";
			 }
		}
		if(0==treeGrade){
			 gradeText = "等级0";
			 photo ="00.png";
		}
		
	   // alert(oneTreeData[i].name)
	    ontTree.customParam.EmpName=oneTreeData[i].name;
	    ontTree.customParam.department=gradeText;
	    ontTree.customParam.EmpPhoto="${fileServerUrl}/res/images/" + photo;
	     
	    prant.Nodes.Add(ontTree);
		if(grade >1){
	    	initTree(treeChild,grade - 1,ontTree)
	    	
		}
		
	  }  
	  
	 // alert(oneTreeData.length +"=="+ grade);
		//if(oneTreeData.length < 3 && grade > 0 ){
  	//	alert('c');
  		//节点不足 补足节点
  		//var subTree = 3 - oneTreeData.length;
  	//	 for(var j=0; i<subTree; j++){  
  		//	zeroTree(prant);
  		// }
  	//}
	
}
</script>
</head>
<body class="bg-2"  style="padding-bottom:60px;" >
	<header class="top-bar">
		<a href="javascript:history.go(-1);">
			<span class="icon-back"></span>
		</a>
		<span>组织结构</span>
	</header>
<script language="javascript">
var root=new OrgNode()


	//init data
	var json = {};
	
	var options = {
		type : "post",
		url : "/userTree/getTreeData.do",
		data : {
			userId : '0001'
		}
	};
	ajaxRequest(options, function(data) {
		if (data.result == 1) {
			popWindow("用户ID不存在");
		} else {
			treeData = data.dataTree;
			root.customParam.EmpName=treeData.name
			root.customParam.department="等级1"
			root.customParam.EmpPhoto="${fileServerUrl}/res/images/04.png"
			initTree(treeData,3,root);
			  
			
			var OrgShows=new OrgShow(root);
			OrgShows.Top=40;
			OrgShows.Left=20;
			OrgShows.IntervalWidth=5;
			OrgShows.IntervalHeight=20;
			//OrgShows.ShowType=2;
			//OrgShows.BoxHeight=100;
			OrgShows.BoxTemplet="<div id=\"{Id}\" class=\"OrgBox\" onclick=\"CookieGroup()\"><img src=\"{EmpPhoto}\" /><span>{EmpName}</span><div>{department}</div></div>"
			OrgShows.Run()
		}
	});


</script>
</body>

</html>