<%--
  Created by IntelliJ IDEA.
  User: rale
  Date: 5/2/17
  Time: 9:09 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/manager" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<t:generic title="首页">
    <jsp:attribute name="css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

        <!-- jvectormap -->
        <spring:url value="/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.css" var="jquery_jvectormap_css"/>
        <link rel="stylesheet" href="${jquery_jvectormap_css}"/>

        <!-- Theme style -->
        <spring:url value="/resources/dist/css/AdminLTE.min.css" var="AdminLTE_css"/>
        <link rel="stylesheet" href="${AdminLTE_css}">

        <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
        <spring:url value="/resources/dist/css/skins/_all-skins.min.css" var="all_skins_css"/>
        <link rel="stylesheet" href="${all_skins_css}">

        <spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrap_css"/>
        <link rel="stylesheet" href="${bootstrap_css}">


	</jsp:attribute>
    <jsp:attribute name="header"><t:header></t:header></jsp:attribute>
    <jsp:attribute name="leftside"><t:leftside indexActive="active"></t:leftside></jsp:attribute>
    <jsp:attribute name="rightside"><t:rightside></t:rightside></jsp:attribute>
    <jsp:attribute name="js">
        <spring:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" var="jquery_js"/>
        <script src="${jquery_js}"></script>
        <!-- Bootstrap 3.3.5 -->
        <spring:url value="/resources/bootstrap/js/bootstrap.min.js" var="bootstrap_js"/>
        <script src="${bootstrap_js}"></script>
        <!-- FastClick -->
        <spring:url value="/resources/plugins/fastclick/fastclick.min.js" var="fastclick_js"/>
        <script src="${fastclick_js}"></script>
        <!-- AdminLTE App -->
        <spring:url value="/resources/dist/js/app.min.js" var="app_js"/>
        <script src="${app_js}"></script>
        <!-- Sparkline -->
        <spring:url value="/resources/plugins/sparkline/jquery.sparkline.min.js" var="jquery_sparkline_js"/>
        <script src="${jquery_sparkline_js}"></script>
        <!-- jvectormap -->
        <spring:url value="/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" var="jquery_jvectormap_js"/>
        <spring:url value="/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" var="jquery_jvectormap_world_mill_js"/>
        <script src="${jquery_jvectormap_js}"></script>
        <script src="${jquery_jvectormap_world_mill_js}"></script>
        <!-- SlimScroll 1.3.0 -->
        <spring:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" var="jquery_slimscroll_js"/>
        <script src="${jquery_slimscroll_js}"></script>
        <!-- ChartJS 1.0.1 -->
        <spring:url value="/resources/plugins/chartjs/Chart.min.js" var="char_js"></spring:url>
        <script src="${char_js}"></script>
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <spring:url value="/resources/dist/js/pages/dashboard2.js" var="dashboard2_js"/>
        <script src="${dashboard2_js}"></script>
        <!-- AdminLTE for demo purposes -->
        <spring:url value="/resources/dist/js/demo.js" var="demo_js"/>
        <script src="${demo_js}"></script>

    </jsp:attribute>

</t:generic>
