<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://127.0.0.1:3306/hospitaldb?characterEncoding=utf8"
     user="root"  password="root"/>
<sql:query dataSource="${db}" var="rs">
SELECT * from doctor;
</sql:query>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=this.getServletContext().getContextPath() %>/register/">
    <title>挂号</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/hospitals/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/hospitals/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/hospitals/Css/style.css" />
    <script type="text/javascript" src="/hospitals/Js/jquery.js"></script>
    <script type="text/javascript" src="/hospitals/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="/hospitals/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/hospitals/Js/ckform.js"></script>
    <script type="text/javascript" src="/hospitals/Js/common.js"></script>
    <script type="text/javascript" src="/hospitals/Js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="/hospitals/Js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="/hospitals/Js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="/hospitals/Js/jquery.validate.js"></script>
    <script type="text/javascript" src="/hospitals/Js/messages_zh.js"></script>
             <script type="text/javascript" src="/hospitals/Js/My97DatePicker/WdatePicker.js"></script>
    
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
        .error{
		    display:inline;
		    padding-left:10px;
			color: red;
			font-size: 14px;
		}


    </style>
    <script type="text/javascript">
    $(function () {
    	$('#backid').click(function(){
			window.history.go(-1);
	 });
    	//对病历号进行验证
    	//对身份证号进行验证
		$("#rid").blur(function(){//失去焦点
			var rid = $(this).val();
		if(rid == null || rid == ""){
			$("#rid_msg").text("病历号不能为空").css({"color":"red","font-size":"14px","padding-left":"10px"});
			return ;
		}
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/register",
				data:{"method":"checkRid","rid":rid},
				success:function(msg){
					var jsonObj = JSON.parse(msg);
					if(jsonObj.statusCode == 200){
						$("#rid_msg").text(jsonObj.message).css({"color":"green","font-size":"14px","padding-left":"5px"})
					}else{
						$("#rid_msg").text(jsonObj.message).css({"color":"red","font-size":"14px","padding-left":"5px"})
					}
				}
			});
		});
    	//对身份证号进行验证
		$("#idCard").blur(function(){//失去焦点
			var idCard = $(this).val();
		if(idCard == null || idCard == ""){
			$("#idCard_msg").text("身份证号不能为空").css({"color":"red","font-size":"14px","padding-left":"10px"});
			return ;
		}
			var idCardReg = /^[1-9][0-9]{5}([1][9][0-9]{2}|[2][0][0|1][0-9])([0][1-9]|[1][0|1|2])([0][1-9]|[1|2][0-9]|[3][0|1])[0-9]{3}([0-9]|[X])$/;
			if(!idCardReg.test(idCard)){
				$("#idCard_msg").text("身份证号有误").css({"color":"red","font-size":"14px","padding-left":"10px"});;
				return ;
			}
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/register",
				data:{"method":"checkIdCard","idCard":idCard},
				success:function(msg){
					var jsonObj = JSON.parse(msg);
					if(jsonObj.statusCode == 200){
						$("#idCard_msg").text(jsonObj.message).css({"color":"green","font-size":"14px","padding-left":"5px"})
					}else{
						$("#idCard_msg").text(jsonObj.message).css({"color":"red","font-size":"14px","padding-left":"5px"})
					}
				}
			});
		});
    	//对社保号进行验证
		//对身份证号进行验证
		$("#siNumber").blur(function(){//失去焦点
			var siNumber = $(this).val();
		if(siNumber == null || siNumber == ""){
			$("#siNumber_msg").text("社保号不能为空").css({"color":"red","font-size":"14px","padding-left":"10px"});
			return ;
		}
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/register",
				data:{"method":"checkSiNumber","siNumber":siNumber},
				success:function(msg){
					var jsonObj = JSON.parse(msg);
					if(jsonObj.statusCode == 200){
						$("#siNumber_msg").text(jsonObj.message).css({"color":"green","font-size":"14px","padding-left":"5px"})
					}else{
						$("#siNumber_msg").text(jsonObj.message).css({"color":"red","font-size":"14px","padding-left":"5px"})
					}
				}
			});
		});
		$("#form4").validate({
			rules: {
				name: {
					required: true
				},
		        age: {
					required: true
				},
				registerMoney: {
					required: true
				},
				phone: {
					required: true
				}
			},
			messages: {
				name: {
					required: "姓名不能为空"
				},
				age: {
					required: "年龄不能为空"
				},
				registerMoney: {
					required: "挂号费不能为空"
				},
				phone: {
					required: "联系电话不能为空"
				}
			}
		});
    });
    
    </script>
</head>
<body>
<form id="form4" action="${pageContext.request.contextPath}/register" method="post" class="definewidth m20">
	<input name="method" value="add" type="hidden">
<table class="table table-bordered table-hover definewidth m10">
	<tr>
        <td width="10%" class="tableleft">病历号</td>
        <td><input type="text" id="rid" name="rid" value=""/><span id="rid_msg"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text"  name="name" value=""/></td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td><input type="text" id="idCard" name="idCard" value=""/><span id="idCard_msg"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td><input type="text" id="siNumber" name="siNumber" value=""/><span id="siNumber_msg"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号费</td>
        <td><input type="text"  name="registerMoney" value=""/>元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td><input type="text" id="phone" name="phone" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td><input type="radio" name="isPay" value="0" checked/>否&nbsp;&nbsp;&nbsp;
            <input type="radio" name="isPay" value="1"/>是</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="sex" value="0" checked/>男&nbsp;&nbsp;&nbsp;
            <input type="radio" name="sex" value="1"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" name="age" value=""/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td><input type="radio" name="consultation" value="0" checked/>初诊&nbsp;&nbsp;&nbsp;
            <input type="radio" name="consultation" value="1"/>复诊</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td>
        	<select name="department" id="department">
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
        <td width="10%" class="tableleft">指定医生</td>
        <td>
        	<select name="did" id="doctor">
	        	<c:forEach items="${rs.rows}" var="d">
	        		<option value="${d.did }">${d.name}</option>
	        	</c:forEach>
	        </select>
	     </td>  
    </tr>
    
        <tr>
        <td width="10%" class="tableleft">挂号日期</td>
        <td>
            <input id="d12" type="text" name="birthday" value="<fmt:formatDate value="${register.registerDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			<img onclick="WdatePicker({el:'d12',skin:'twoer' ,dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'%y-%M-%d %H:%m:%s'})" src="../Js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
		</td>
    </tr>
    
    
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="remark"></textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>

</html>