<%--
  Created by IntelliJ IDEA.
  User: rale
  Date: 5/24/17
  Time: 2:51 PM
--%>

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
            <h1>
                商品上架
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-plane"></i> 销售管理</a></li>
                <li class="active">商品上架</li>
            </ol>
        </section>
        <section class="content">

            <!-- 商品基本信息 -->
            <div class="row">
                <div class="col-md-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">基本信息</h3>
                            <div class="box-tools pull-right">
                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                            </div>
                        </div>

                        <div class="box-body">
                            <div class="row">
                                <%--@Todo--%>
                                <%--<input type="text" id="token" value="${FASTSALES_SALES_ITEM_ADD_FORM_TOKEN}"/>--%>
                                <div class="col-md-5">
                                    <div class="form-group">
                                        <label>名称</label>
                                        <input type="text" name="name" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="form-group">
                                        <label>条形码号（不手动填写的话则由系统生成）</label>
                                        <input type="text" name="serializedId" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label>预定售价</label>
                                        <div class="input-group">
                                            <input type="text" name="defaultPrice" class="form-control">
                                            <div class="input-group-addon"><i class="fa fa-rmb fa-fw"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 显示已添加商品列表 -->
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">商品列表</h3>
                            <button class="btn btn-primary" data-toggle="modal" data-target="#productModal" id="showAddProduct">添加商品</button>
                            <button class="btn btn-danger" id="clearSelectedProduct">一键清空</button>
                        </div>
                        <div class="box-body  table-responsive no-padding">
                            <table class="table table-hover table-bordered" id="selectedProducts">
                                <tbody>
                                <tr>
                                    <th>系统编号</th>
                                    <th>商品货号</th>
                                    <th>商品名称</th>
                                    <th>商品属性</th>
                                    <th>数量</th>
                                    <th>操作</th>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="box-footer">
                            <button class="btn btn-primary" id="puton">
                                上架
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 商品列表模态框 -->
        <div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"> 商品列表</h4>
                    </div>

                    <div class="modal-body">
                        <div class="row">
                            <div class="form-inline col-md-12">
                                <div class="form-group col-md-3">
                                    <label for="category">商品种类</label>
                                    <select class="form-control" id="category" refresh="true">
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="sr-only" for="keyword">关键词</label>
                                    <input type="text" class="form-control" id="keyword" placeholder="关键词">
                                    <button type="submit" class="btn btn-primary" id="searchProduct">搜索</button>
                                </div>
                                <div class="form-group col-md-3">
                                    <button class="btn btn-default" id="showAllProducts">所有商品</button>
                                </div>

                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <table class="table table-hover table-bordered col-sm-12 col-md-9" id="briefProducts">
                                <tbody>
                                <!-- 此处需要记录商品详情的编号 -->
                                <tr>
                                    <th>商品货号</th>
                                    <th>商品名称</th>
                                    <th>商品属性</th>
                                    <th>剩余数量</th>
                                    <th>操作</th>
                                </tr>



                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">完成</button>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>
</t:generic>

