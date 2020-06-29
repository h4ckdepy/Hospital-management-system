<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://39.97.117.52:3306/hospital?characterEncoding=utf8"
     user="hospital"  password="qq123456"/>
<sql:query dataSource="${db}" var="rs">
SELECT * from doctor;
</sql:query>

<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=this.getServletContext().getContextPath() %>/register/">
    <title>门诊查询</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/hospitals/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/hospitals/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/hospitals/Css/style.css" />
    <script type="text/javascript" src="/hospitals/Js/jquery.js"></script>
    <script type="text/javascript" src="/hospitals/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="/hospitals/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/hospitals/Js/ckform.js"></script>
    <script type="text/javascript" src="/hospitals/Js/common.js"></script>

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
    $(function(){
    	//清空
    	$("#ret").click(function(){
    		$("#rid").val("");
    		$("#name").val("");
    		$("#department").val("0");
    	});
    	//去添加页面
    	$("#newNav").click(function(){
    		window.location.href="${pageContext.request.contextPath}/register/add.jsp"
    	});
    	//全选全不选
    	$("#checkall").click(function(){
    		var result = $("#checkall").prop("checked");//获取状态
    		$("tbody input").prop("checked",result);//设置状态
    	});
    	//批量删除
    	$("#delRegister").click(function(){
    		var ids="";
    		$("tbody input:checked").each(function(index,item){
    			var rid=$(item).val();
    			ids=ids+rid+",";
    		});
    		ids = ids.substring(0,ids.length-1);
    		if(ids == null || ids==""){
    			alert("请选择要删除的挂号信息");
    		}
    		else{
    			if(confirm("您确定要删除这些挂号信息")){
    				//请求后端批量删除
    				window.location.href="${pageContext.request.contextPath}/register?method=delAll&ids="+ids;
    			}		
    	}
    	});
    });
		
    </script>
</head>
<body>

<form action="${pageContext.request.contextPath }/register?method=getRegisterList" method="post" class="definewidth m20">
<input name="method" value="findRegisterByPage" type="hidden"/>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号：</td>
        <td><input type="text" id="rid" name="rid" value="${rid }"/></td>
		
        <td width="10%" class="tableleft">姓名：</td>
        <td><input type="text" id="name" name="name" value="${name }"/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td>
        	<select name="department" id="department">
	        	<option value="0" >==请选择==</option>
	        	<option value="1" <c:if test="${department ==1}">selected='selected'</c:if>>急诊科</option>
	        	<option value="2" <c:if test="${department ==2}">selected='selected'</c:if>>儿科</option>
	        	<option value="3" <c:if test="${department ==3}">selected='selected'</c:if>>妇科</option>
	        	<option value="4" <c:if test="${department ==4}">selected='selected'</c:if>>皮肤科</option>
	        	<option value="5" <c:if test="${department ==5}">selected='selected'</c:if>>内分泌科</option>
	        	<option value="6" <c:if test="${department ==6}">selected='selected'</c:if>>牙科</option>
        	</select>
        </td>
    </tr>
    <tr>
		  <td colspan="6">
		  <center>
            <input id="find" name="find" type="submit" class="btn btn-primary" value="查询"/>
			<input name="ret" id="ret" type="button" class="btn btn-primary" value="清空"/>
            </center>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>病例号</th>
        <th>病人姓名</th>
        <th>主治医生</th>
        <th>挂号时间</th>
        <th>挂号科室</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	<c:forEach items="${page.list }" var="register">
    	    <tr>
    	      <td><input type="checkbox" id="checkall" onChange="checkall();" value="${register.rid }"></td>
              <td>${register.rid }</td>
              <td>${register.name }</td>
              <sql:query dataSource="${db}" var="rsss">
						SELECT * from doctor where did="${register.did}";
				</sql:query>
              <td>
              <c:forEach items="${rsss.rows}" var="d">
              ${d.name}
              </c:forEach>
              </td>
              <td><fmt:formatDate value="${register.registerDate }" pattern="yyyy-MM-dd"/></td>
              <td>
                   <c:if test="${register.department == 1 }">急诊</c:if>
                   <c:if test="${register.department == 2 }">儿科</c:if>
                   <c:if test="${register.department == 3 }">妇科</c:if>
                   <c:if test="${register.department == 4 }">皮肤科</c:if>
                   <c:if test="${register.department == 5 }">内分泌科</c:if>
                   <c:if test="${register.department == 6 }">牙科</c:if>
              </td>
              <td>
                   <c:if test="${register.status ==1 }">挂号</c:if>
                   <c:if test="${register.status ==2 }">已住院</c:if>
                   <c:if test="${register.status ==3 }">已出院</c:if>
                   <c:if test="${register.status ==4 }">已结算</c:if>
              </td>
              <td>
              <a href="${pageContext.request.contextPath}/register?method=detail&rid=${register.rid}">详情</a> | 
              <a href="${pageContext.request.contextPath}/register?method=edit&rid=${register.rid}">修改</a>
              </td>
    	    </tr>
    	</c:forEach>
		        <!-- 1-急诊  2-儿科 3-妇科 4-皮肤科 5-内分泌科 6-牙科  -->
     </tbody>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  
  		<div class="inline pull-right page">
	          <a href="${pageContext.request.contextPath}/register?method=getRegisterList&pageNum=1"${queryStr} >首页</a> 
	          <a href="${pageContext.request.contextPath}/register?method=getRegisterList&pageNum=${page.currentPage-1 }${queryStr}">上一页</a>     
	          <a href="${pageContext.request.contextPath}/register?method=getRegisterList&pageNum=${page.currentPage+1 }${queryStr}" >下一页</a> 
	          <a href="${pageContext.request.contextPath}/register?method=getRegisterList&pageNum=${page.totalPage}${queryStr}" >尾页</a>
			  &nbsp;&nbsp;&nbsp;
			     共<span class='current'>${page.totalRecord } </span>条记录
			     <span class='current'>${page.currentPage } / ${page.totalPage} </span>页
		</div>
		<div>
		   <button type="button" class="btn btn-success" id="newNav">门诊挂号</button>&nbsp;&nbsp;&nbsp;
		   <button type="button" class="btn btn-success" id="delRegister" onclick="delAll()">批量删除</button>
		</div>
	</th></tr>
  </table>
  
</body>
</html>
