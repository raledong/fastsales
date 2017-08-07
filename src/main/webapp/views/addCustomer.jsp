<%--
  Created by IntelliJ IDEA.
  User: rale
  Date: 5/2/17
  Time: 9:09 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/manager" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<t:generic title="添加新的顾客">
    <jsp:attribute name="css">
        <spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrap_css"/>
        <link rel="stylesheet" href="${bootstrap_css}">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

        <!-- Ionicons -->
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

        <!-- Theme style -->
        <spring:url value="/resources/dist/css/AdminLTE.min.css" var="AdminLTE_css"/>
        <link rel="stylesheet" href="${AdminLTE_css}">

        <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
        <spring:url value="/resources/dist/css/skins/_all-skins.min.css" var="all_skins_css"/>
        <link rel="stylesheet" href="${all_skins_css}">




	</jsp:attribute>
    <jsp:attribute name="header"><t:header></t:header></jsp:attribute>
    <jsp:attribute name="leftside"><t:leftside customerActive="active"></t:leftside></jsp:attribute>
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
        <!-- SlimScroll 1.3.0 -->
        <spring:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" var="jquery_slimscroll_js"/>
        <script src="${jquery_slimscroll_js}"></script>
        <!-- AdminLTE for demo purposes -->
        <spring:url value="/resources/dist/js/demo.js" var="demo_js"/>
        <script src="${demo_js}"></script>

        <script>
            $('#name').keyup(function(){
                console.log($('#name').val() != '');
                if($('#name').val() != ''){
                    var parentNode = $('#name').parent('.form-group')
                    parentNode.removeClass('has-error')
                    parentNode.addClass('has-success');
                }else{
                    var parentNode = $('#name').parent('.form-group')
                    parentNode.removeClass('has-success');
                    parentNode.addClass('has-error');
                }
               console.log($('#name').val());
            });
            $('#addCustomer').bind('click', function (event) {
                event.preventDefault();
                var name = $('#name').val();
                if(name==null || name==''){
                    $('#name').addClass('has-error');
                    return;
                }
                var mobile = $('#mobile').val();
                var tele = $('#tele').val();
                var wechat = $('#wechat').val();
                var comment = $('#comment').val();
                $.ajax({
                    type : "POST",
                    url : 'add',
                    data : {
                        'name' : name,
                        'mobile' : mobile,
                        'tele' : tele,
                        'wechat' : wechat,
                        'comment' : comment
                    },
                    success : function (data) {
                        if(data.message == 'success'){
                            window.location.href = "/customer/index";
                        }
                        console.log(data);
                    }
                });

            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <section class="content-header">
            <h1>添加新的客户</h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-users"></i> 客户管理</a></li>
                <li class="active">添加新客户</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-md-12 col-lg-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">客户信息</h3>
                        </div>
                        <form:form role="form" method="post" modelAttribute="customerBean">
                            <div class="box-body">
                                <div class="form-group">
                                    <form:label for="name" path="name">客户姓名</form:label>
                                    <form:input type="text" class="form-control input-lg" id="name" placeholder="姓名" path="name"/>
                                </div>
                                <div class="form-group">
                                    <form:label for="mobile" path="mobile">客户手机</form:label>
                                    <form:input type="number" class="form-control input-lg" id="mobile" placeholder="手机号" path="mobile"/>
                                </div>
                                <div class="form-group">
                                    <form:label for="tele" path="tele">客户电话</form:label>
                                    <form:input type="number" class="form-control input-lg" id="tele" placeholder="电话号码" path="tele"/>
                                </div>
                                <div class="form-group">
                                    <form:label for="wechat" path="wechat">客户微信</form:label>
                                    <form:input type="text" class="form-control input-lg" id="wechat" placeholder="微信号" path="wechat"/>
                                </div>
                                <div class="form-group">
                                    <form:label for="comment" path="comment">客户备注</form:label>
                                    <form:textarea class="form-control" name="comment" id="comment" rows="3" path="comment"/>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button class="btn btn-primary" id="addCustomer">创建</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
    </jsp:body>
</t:generic>
