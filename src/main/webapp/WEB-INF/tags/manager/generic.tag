<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true"%>
<%@ attribute name="css" fragment="true" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="leftside" fragment="true" %>
<%@ attribute name="rightside" fragment="true" %>
<%@ attribute name="popup" fragment="true" %>
<%@ attribute name="js" fragment="true" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>${title}</title>
    <jsp:invoke fragment="css"/>
</head>
<body class="sidebar-mini fixed sidebar-collapse skin-purple">

<div id="wrapper">

    <!-- header -->
    <jsp:invoke fragment="header"/>
    <jsp:invoke fragment="leftside"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <jsp:doBody/>

        <!-- Main content -->
    </div><!-- /.content-wrapper -->
    <jsp:invoke fragment="rightside"/>
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 2.3.0
        </div>
        <strong>Copyright © 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights reserved.
    </footer>
    <jsp:invoke fragment="popup"/>
</div>

<jsp:invoke fragment="js"/>

</body>
</html>