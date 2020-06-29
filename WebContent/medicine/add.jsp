<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
    <title>添加药品</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/ckeditor/ckeditor.js"></script>
 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
			window.location.href="${path}medicine?method=findMedicineByPage";
	 }); 
		//返回列表
		$("#backid").click(function(){
			//window.location.href="${pageContext.request.contextPath}/doctor/index.jsp";
			window.history.go(-1);//从哪里来到哪去
		});	
		$("#inPrice").blur(function(){  //失去焦点
			 $("#inPrice_msg").text("")
			var inPrice = $(this).val(); //获取username
			//前端非空验证
			if(inPrice == null || inPrice == ""){
				$("#inPrice_msg").text("进价不能为空").css({"color":"red","font-size":"14px","padding-left":"5px"});
				return ;
			}
		});
		$("#salPrice").blur(function(){  //失去焦点
			 $("#isalPrice_msg").text("")
			var salPrice = $(this).val(); //获取username
			//前端非空验证
			if(salPrice == null || salPrice == ""){
				$("#isalPrice_msg").text("售价不能为空").css({"color":"red","font-size":"14px","padding-left":"5px"});
				return ;
			}
		});
		$("#name").blur(function(){  //失去焦点
			 $("#name_msg").text("")
			var name = $(this).val(); //获取username
			//前端非空验证
			if(name == null || name == ""){
				$("#name_msg").text("药品名称不能为空").css({"color":"red","font-size":"14px","padding-left":"5px"});
				return ;
			}
		});
		$("#readme").blur(function(){  //失去焦点
			 $("#readme_msg").text("")
			var readme = $(this).val(); //获取username
			//前端非空验证
			if(readme == null || readme == ""){
				$("#readme_msg").text("进价不能为空").css({"color":"red","font-size":"14px","padding-left":"5px"});
				return ;
			}
		});
    });	

    </script>
</head>
<body>

<form id = "form3" action="${pageContext.request.contextPath }/medicine?method=add" 
 method="post" class="definewidth m20" enctype="multipart/form-data" >
<!-- enctype="multipart/form-data" -->
<input type="hidden" name="method" value="add">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">药品编号</td>
        <td><input type="text"  id = "mid" name="mid" value=""/><span id="mid_msg"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">图片</td>
        <td><input type="file"  id = " picture" name="picture" value=""/><span id="picture_msg"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">进价</td>
        <td><input type="text"    id ="inPrice"   name="inPrice" value=""/><span id="inPrice_msg"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">售价</td>
        <td><input type="text"   id = "salPrice"  name="salPrice" value=""/><span id="isalPrice_msg"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品名称</td>
        <td><input type="text"  id = "name"  name="name" value=""/><span id="name_msg"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品类型</td>
        <td>
        	<input type="radio" name="type" value="1" checked/>处方药&nbsp;&nbsp;&nbsp;
        	<input type="radio" name="type" value="2"/>中药&nbsp;&nbsp;&nbsp;
        	<input type="radio" name="type" value="3"/>西药</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">简单描述</td>
        <td><input type="text"  id = "descs"  name="descs" value=""/><span id="descs_msg"></td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">保质期</td>
        <td><input type="text"  id = "name"  name="qualityDate" value="36"/>月</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">详细描述</td>
        <td><textarea name="description"></textarea></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">生产厂商</td>
        <td><textarea name="produceFirm"></textarea></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">服用说明</td>
        <td><input type="text"  id = "readme"   name="readme" value=""/></td>
    </tr>
   
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="remark"></textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<!--<button id="save"  class="btn btn-primary"  type="button" >保存</button> &nbsp;&nbsp;-->
			 	<input name="save" id="save" type="submit" class="btn btn-primary" value="保存"/>
			 &nbsp;&nbsp;
			<!-- <input id="save" type="button" value="保存" name="save" class="btn btn-primary" style="width: 100px;"/>
        &nbsp;&nbsp;-->	
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>
