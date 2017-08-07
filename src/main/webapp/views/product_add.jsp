<%--
  Created by IntelliJ IDEA.
  User: rale
  Date: 5/24/17
  Time: 2:51 PM
--%>
<%-- TODO 将删除选项从disply：none改变为删掉元素节点--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/manager" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<t:generic title="创建商品">
    <jsp:attribute name="css">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Select2 -->
        <spring:url value="/resources/plugins/select2/select2.min.css" var="select2_css"/>
        <link rel="stylesheet" href="${select2_css}">


        <spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrap_css"/>
        <link rel="stylesheet" href="${bootstrap_css}">

        <!-- Theme style -->
        <spring:url value="/resources/dist/css/AdminLTE.min.css" var="AdminLTE_css"/>
        <link rel="stylesheet" href="${AdminLTE_css}">

        <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
        <spring:url value="/resources/dist/css/skins/_all-skins.min.css" var="all_skins_css"/>
        <link rel="stylesheet" href="${all_skins_css}">

	</jsp:attribute>
    <jsp:attribute name="header"><t:header></t:header></jsp:attribute>
    <jsp:attribute name="leftside"><t:leftside productActive="active"></t:leftside></jsp:attribute>
    <jsp:attribute name="rightside"><t:rightside></t:rightside></jsp:attribute>
    <jsp:attribute name="js">
        <spring:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" var="jquery_js"/>
        <script src="${jquery_js}"></script>
        <!-- Bootstrap 3.3.5 -->
        <spring:url value="/resources/bootstrap/js/bootstrap.min.js" var="bootstrap_js"/>
        <script src="${bootstrap_js}"></script>
        <!-- Select2 -->
        <spring:url value="/resources/plugins/select2/select2.full.min.js" var="select2_js"/>
        <script src="${select2_js}"></script>
        <!-- FastClick -->
        <spring:url value="/resources/plugins/fastclick/fastclick.min.js" var="fastclick_js"/>
        <script src="${fastclick_js}"></script>
        <%--ImageUpload--%>
        <spring:url value="/resources/plugins/jQueryFileupload/js/vendor/jquery.ui.widget.js" var="jquery_ui_widget_js"/>
        <spring:url value="/resources/plugins/jQueryFileupload/js/jquery.iframe-transport.js" var="jquery_iframe_transport_js"/>
        <spring:url value="/resources/plugins/jQueryFileupload/js/jquery.fileupload.js" var="jquery_fileupload_js"/>
        <script src="${jquery_ui_widget_js}"></script>
        <script src="${jquery_iframe_transport_js}"></script>
        <script src="${jquery_fileupload_js}"></script>
        <!-- AdminLTE App -->
        <spring:url value="/resources/dist/js/app.min.js" var="app_js"/>
        <script src="${app_js}"></script>
        <!-- SlimScroll 1.3.0 -->
        <spring:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" var="jquery_slimscroll_js"/>
        <script src="${jquery_slimscroll_js}"></script>
        <!-- AdminLTE for demo purposes -->
        <spring:url value="/resources/dist/js/demo.js" var="demo_js"/>
        <script src="${demo_js}"></script>

        <spring:url value="/resources/js/product.js" var="product_js"/>
        <script src="${product_js}"></script>


    </jsp:attribute>

    <jsp:body>
        <section class="content-header">
            <h1>添加商品
                <small>含有 * 必填</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-database"></i> 库存管理</a></li>
                <li class="active"> 添加商品</li>
            </ol>
        </section>
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">商品信息</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <input type="hidden" name="token" value="${token}" />
                    <div class="form-group col-md-12">
                        <label>产品名称*</label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-file-text fa-fw"></i>
                            </div>
                            <input type="text" class="form-control" name="name" value="${FASTSALES_PRODUCT_ADD_BEAN.name}"/>
                        </div>
                    </div>
                    <div class="form-group col-md-3">
                        <label>编号（货号）*</label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-info  fa-fw"></i>
                            </div>
                            <input type="text" class="form-control" name="shortId" value="${FASTSALES_PRODUCT_ADD_BEAN.shortId}"/>
                        </div>
                    </div>
                    <div class="form-group col-md-3">
                        <label>序列号（条形码号）如果没有可以不填写</label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-info fa-fw"></i>
                            </div>
                            <input type="text" class="form-control" name="serializedId" value="${FASTSALES_PRODUCT_ADD_BEAN.serializedId}"/>
                        </div>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="category">类别*</label>
                        <select class="form-control" id="category" name="categoryId">
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="tags">标签</label>
                        <select class="form-control" id="tags" multiple="multiple" name="tags">
                        </select>
                    </div>
                    <div class="form-group col-md-12">
                        <label>备注</label>
                        <textarea class="form-control" rows="3" name="comment">${FASTSALES_PRODUCT_ADD_BEAN.comment}</textarea>
                    </div>
                </div>
            </div>
            <div class="box box-primary" >
                <div class="box-header with-border">
                    <h3 class="box-title">规格明细*（至少填写一个）</h3>
                    <button id="addSpec" class="btn btn-primary"><i class="fa fa-plus"></i> 添加</button>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    </div>
                </div>

                <div class="box-body" id="specs">
                    <c:forEach items="${FASTSALES_PRODUCT_ADD_BEAN.productSpecBeans}" var="productSpecBean">
                        <div class="box box-widget spec">
                            <div class="box-header with-border">
                                <div class="form-group col-md-5">
                                    <input class="form-control" type="text" name = "properties" placeholder="请填写商品属性" value="${productSpecBean.properties}"/>
                                </div>
                                <div class="form-group col-md-3">
                                    <input class="form-control" type="text" name = "quantity" placeholder="商品数量" value="${productSpecBean.quantity}"/>
                                </div>
                                <div class="form-group col-md-3">
                                    <input class="form-control" type="text" name = "unit" placeholder="每一包装中的商品数量" value="${productSpecBean.unit}">
                                </div>
                                <div class="form-group col-md-1 remove">
                                    <button class="btn btn-primary remove">删除</button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>

                <div class="box-footer">
                    <button class="btn btn-primary" id="createProduct">提交商品数据</button>
                </div>
            </div>

        </section>
    </jsp:body>
</t:generic>

