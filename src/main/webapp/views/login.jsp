<%--
  Created by IntelliJ IDEA.
  User: rale
  Date: 4/30/17
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="boostrap_css"/>
    <spring:url value="/resources/boostrap/js/boostrap.min.js" var="bootstrap_js"/>
    <spring:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" var="jquery_js"/>
    <link href="${boostrap_css}" rel="stylesheet"/>
    <title>登录</title>
</head>
<body>

    <div class="container" style="margin-top: 100px">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <form:form class="form-signin" action="login" method="post" modelAttribute="loginBean">

                <h2 class="form-signin-heading" style="text-align: center">登录系统</h2>
                <label for="identifier" class="sr-only">用户名</label>
                <form:input type="text" id="identifier" path="identifier" class="form-control" placeholder="请输入用户名或手机号或编号" required="" autofocus="" value="${loginBean.identifier}" />
                <label for="password" class="sr-only">密码</label>
                <form:input type="password" id="password" path="password" class="form-control" placeholder="请输入密码" required="" value="${loginBean.password}"/>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> 记住密码
                    </label>
                </div>
                <c:if test="${!empty FASTSALES_MESSAGE}">
                    <div class="alert alert-danger" role="alert">${FASTSALES_MESSAGE}</div>
                </c:if>
                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            </form:form>
        </div>

    </div>


    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${jquery_js}"/>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${bootstrap_js}"/>
</body>
</html>
