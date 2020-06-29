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

<!DOCTYPE html>
<html>
<head>
<base href="<%=this.getServletContext().getContextPath() %>/register/">
    <title>查看</title>
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
				window.history.back(-1);
		 });
    });
    </script>
</head>
<body>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td>${register.rid }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${register.name } </td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td>${register.idCard } </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号费</td>
        <td>${register.registerMoney } 元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td>${register.siNumber }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td>${register.phone }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td>
        	 <c:if test="${register.ispay == true}">否</c:if>
        	 <c:if test="${register.ispay == false}">是</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td>
	         <c:if test="${register.sex == true}">男</c:if>
        	 <c:if test="${register.sex == false}">女</c:if>
       	</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td>${register.age }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td>
        	 <c:if test="${register.consultation == true}">出诊</c:if>
        	 <c:if test="${register.consultation == false}">复诊</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td>
        	 <c:if test="${register.department == 1}">急诊</c:if>
        	 <c:if test="${register.department == 2}">儿科</c:if>
        	 <c:if test="${register.department == 3}">妇科</c:if>
        	 <c:if test="${register.department == 4}">皮肤科</c:if>
        	 <c:if test="${register.department == 5}">内分泌科</c:if>
        	 <c:if test="${register.department == 6}">牙科</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
              <sql:query dataSource="${db}" var="rsss">
						SELECT * from doctor where did="${register.did}";
				</sql:query>
              <td>
              <c:forEach items="${rsss.rows}" var="d">
              ${d.name}
              </c:forEach>
              </td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${register.remark }</td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</body>
</html>