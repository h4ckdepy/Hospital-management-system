<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
    <title>添加医生</title>
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
 	<script type="text/javascript" src="../Js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../Js/jquery.validate.js"></script>
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
    <!--  
    <script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
				window.location.href="${path}doctor?method=findDoctorByPage";
		 }); 
		
		////sfz 
		 $("#cardno").blur(function () {	 //失去焦点
			 $("#cardno_msg").text("")
		     var cardno = $(this).val();
		     var cardnoReg=/^[1-9][0-9]{5}([1][9][0-9]{2}|[2][0][0|1][0-9])([0][1-9]|[1][0|1|2])([0][1-9]|[1|2][0-9]|[3][0|1])[0-9]{3}([0-9]|[X])$/;
		     if(!cardnoReg.test(cardno)){		    	 
			       $("#cardno_msg").text("身份证信息有误").css({"color":"red"});
			       return ;
		      }else{
		      		 $("#cardno_msg").text("✔").css({"color":"green"});
		      }
	    });
		
		//返回列表
		$("#backid").click(function(){
			<!--window.location.href="${pageContext.request.contextPath}/doctor/index.jsp";
			window.history.go(-1);//从哪里到哪去
		});
		
    });
    
    </script>
-->
	<script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
				window.history.go(-1);
		 });
		//对身份证号进行验证
		$("#cardno").blur(function(){//失去焦点
			var cardno = $(this).val();
		if(cardno == null || cardno == ""){
			$("#cardno_msg").text("身份证号不能为空").css({"color":"red","font-size":"14px","padding-left":"10px"});
			return ;
		}
			var cardnoReg = /^[1-9][0-9]{5}([1][9][0-9]{2}|[2][0][0|1][0-9])([0][1-9]|[1][0|1|2])([0][1-9]|[1|2][0-9]|[3][0|1])[0-9]{3}([0-9]|[X])$/;
			if(!cardnoReg.test(cardno)){
				$("#cardno_msg").text("身份证号有误").css({"color":"red","font-size":"14px","padding-left":"10px"});;
				return ;
			}
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/doctor",
				data:{"method":"checkCardno","cardno":cardno},
				success:function(msg){
					var jsonObj = JSON.parse(msg);
					if(jsonObj.statusCode == 200){
						$("#cardno_msg").text(jsonObj.message).css({"color":"green","font-size":"14px","padding-left":"5px"})
					}else{
						$("#cardno_msg").text(jsonObj.message).css({"color":"red","font-size":"14px","padding-left":"5px"})
					}
				}
			});
		});
		//对手机号码进行验证
		$("#phone").blur(function(){//失去焦点
			var phone = $(this).val();
		if(phone == null || phone == ""){
			$("#phone_msg").text("手机号不能为空").css({"color":"red","font-size":"14px","padding-left":"10px"});
			return ;
		}
			var phoneReg = /^[1][3,4,5,7,8,9][0-9]{9}$/;
			if(!phoneReg.test(phone)){
				$("#phone_msg").text("手机格式有误").css({"color":"red","font-size":"14px","padding-left":"10px"});;
				return ;
			}
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/doctor",
				data:{"method":"checkPhone","phone":phone},
				success:function(msg){
					var jsonObj = JSON.parse(msg);
					if(jsonObj.statusCode == 200){
						$("#phone_msg").text(jsonObj.message).css({"color":"green","font-size":"14px","padding-left":"5px"})
					}else{
						$("#phone_msg").text(jsonObj.message).css({"color":"red","font-size":"14px","padding-left":"5px"})
					}
				}
			});
		});
		//验证邮箱
		$("#email").blur(function(){
			var email = $(this).val();
			//1.前端非空验证
			if(email == null || email == ""){
				$("#email_msg").text("邮箱不能为空").css({"color":"red","font-size":"14px","padding-left":"5px"});
				return;
			}
			//2.前端邮箱格式验证 正则表达式
			var emailReg=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				$("#email_msg").text("邮箱格式不正确").css({"color":"red","font-size":"14px","padding-left":"5px"});
				return;
			}
			//3.后端唯一性验证
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/doctor",
				data:{"method":"checkEmail","email":email},
				success:function(msg){
					var jsonObj = JSON.parse(msg);
					if(jsonObj.statusCode == 200){
						$("#email_msg").text(jsonObj.message).css({"color":"green","font-size":"14px","padding-left":"5px"})
					}else{
						$("#email_msg").text(jsonObj.message).css({"color":"red","font-size":"14px","padding-left":"5px"})
					}
				}
			});
		});
		
		$("#form").validate({
			rules: {
				name: {
					required: true
				},
		        age: {
					required: true
				}
			},
			messages: {
				name: {
					required: "姓名不能为空"
				},
				age: {
					required: "年龄不能为空"
				}
			}
		});
    });
    </script>



</head>
<body>

<form id="form" action="${pageContext.request.contextPath }/doctor" 
method="post" class="definewidth m20">
<input type="hidden" name="method" value="add">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="name" value=""/></td>
    </tr>   
    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td><input type="text" id = "cardno" name="cardno" value=""/>
        <span id="cardno_msg"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td><input type="text" name="phone" value="" id="phone"/><span id="phone_msg"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="sex" value="0" checked/>男&nbsp;&nbsp;&nbsp;
        <input type="radio" name="sex"  value="1"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td><input id="d12" type="text" name="birthday"/>
			<img onclick="WdatePicker({el:'d12',skin:'twoer' ,dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'%y-%M-%d %H:%m:%s'})" src="../Js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
		</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" name="age" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td><input type="text" name="email" value="" id="email"/><span id="email_msg"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td>
        	<select name="department">
        		<option value="1">急诊科</option>
        		<option value="2">儿科</option>
        		<option value="3">妇科</option>
        		<option value="4">皮肤科</option>
        		<option value="5">内分泌科</option>
        		<option value="6">牙科</option>
        	</select>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td>
        	<select name="education">
        		<option value="1">专科</option>
        		<option value="2">本科</option>
        		<option value="3">研究生</option>
        		<option value="4">博士</option>
        	</select>
        </td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="remark"></textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
			<input name="save" id="save" type="submit" class="btn btn-primary" value="保存"/>
			 &nbsp;&nbsp;
			 <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>