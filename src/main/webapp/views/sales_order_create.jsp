<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/manager" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<t:generic title="创建销售单">
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
        <!-- AdminLTE App -->
        <spring:url value="/resources/dist/js/app.min.js" var="app_js"/>
        <script src="${app_js}"></script>
        <!-- SlimScroll 1.3.0 -->
        <spring:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" var="jquery_slimscroll_js"/>
        <script src="${jquery_slimscroll_js}"></script>
        <!-- AdminLTE for demo purposes -->
        <spring:url value="/resources/dist/js/demo.js" var="demo_js"/>
        <script src="${demo_js}"></script>

        <spring:url value="/resources/js/sales_order_create.js" var="sales_order_create_js"/>
        <script src="${sales_order_create_js}"></script>
    </jsp:attribute>

    <jsp:body>
        <section class="content-header">
            <h1>
                创建销售单
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-plane"></i> 销售管理</a></li>
                <li class="active">创建销售单</li>
            </ol>
        </section>

        <section class="content">
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
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="salesSource">销售渠道</label>
                                        <select class="form-control" id="salesSource"></select>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="customer">客户</label>
                                        <select class="form-control" id="customer"></select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <!-- 订单的商品列表 -->
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">销售商品列表</h3>
                            <button class="btn btn-primary" data-toggle="modal" data-target="#salesModal" id="addSalesOrderItem">添加商品</button>
                            <button class="btn btn-danger remove">一键清空</button>
                            <div class="box-tools" style="top:10px">
                                <div class="input-group" style="width: 350px;">
                                    <input type="text" name="table_search" class="form-control pull-right" placeholder="输入条形码">
                                    <div class="input-group-btn">
                                        <button class="btn btn-primary btn-default" id="searchBySerialiezedId">添加</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="box-body  table-responsive no-padding with-border">
                            <table class="table table-hover table-bordered" id="orderItems">
                                <tbody>
                                <tr>
                                    <th>系统编号</th>
                                    <th>单品名称</th>
                                    <th>单价/元</th>
                                    <th>数量</th>
                                    <th>单品总价</th>
                                    <th>操作</th>
                                </tr>
                                <%--<tr sid="123123">--%>
                                    <%--<td>123123</td>--%>
                                    <%--<td data-toggle="tooltip" data-placement="top" title="Tooltip on top">飘花伊人男士打底袜asdfsadfasdfsdafasd</td>--%>
                                    <%--<td class="singlePrice">--%>
                                        <%--<input type="text" name="" value="12.5" placeholder="输入商品数量" class="form-control">--%>
                                    <%--</td>--%>
                                    <%--<td class="singleQuantity">--%>
                                        <%--<input type="text" name="" value="1" placeholder="输入商品数量" class="form-control">--%>
                                    <%--</td>--%>
                                    <%--<td class="singleSum">12.5</td>--%>
                                    <%--<td>--%>
                                        <%--<button class="btn btn-danger remove">删除</button>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>

                                </tbody>
                            </table>
                        </div>
                        <div class="box-footer">
                            <div class="row">
                                <div class="col-md-4">
                                    <p class="lead">总金额：<span class="text-green" id="sum">0</span>元</p>
                                </div>
                                <div class="col-md-4">
                                    <p class="lead">已经支付:<input type="text" name="hasPaid" id="hasPaid" value="0">元</p>
                                </div>
                                <div class="col-md-4">
                                    <p class="lead">还需支付：<span class="text-red" id="remain">0</span>元</p>
                                </div>
                            </div>

                            <div class="row col-md-12">
                                <button class="btn btn-success btn-lg">快速支付</button>
                                <small>仅适用于顾客一次性付完该笔账单的场景</small>
                            </div>
                            <div class="row col-md-12">
                                <button class="btn btn-primary btn-lg">分批支付</button>
                                <button class="btn btn-primary btn-lg disabled">支付宝支付 </button>
                                <button class="btn btn-primary btn-lg disabled">微信支付</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!-- end of 商品列表 -->
        </section>

        <!-- 商品列表模态框 -->
        <div class="modal fade" id="salesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"> 销售单品列表</h4>
                    </div>

                    <div class="modal-body">
                        <div class="row">
                            <div class="form-inline col-md-12">
                                <div class="form-group col-md-6">
                                    <label class="sr-only" for="keyword">关键词</label>
                                    <input type="text" class="form-control" id="keyword" placeholder="关键词">
                                    <button type="submit" class="btn btn-primary" id="searchByKeyword">搜索</button>
                                    <button class="btn btn-default" id="showAllBriefSalesItems">显示所有商品</button>

                                </div>

                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <table class="table table-hover table-bordered col-sm-12 col-md-9" id="briefSalesItems">
                                <tbody>
                                <!-- 此处需要记录商品详情的编号 -->
                                <tr>
                                    <th>商品编号</th>
                                    <th>商品名称</th>
                                    <th>商品还有剩余</th>
                                    <th>操作</th>
                                </tr>
                                <%--<tr sid="21321321">--%>
                                    <%--<td>8806123123123-1123</td>--%>
                                    <%--<td>飘花伊人男士打底袜</td>--%>
                                    <%--<th>否</th>--%>
                                    <%--<td><button class="btn btn-primary addToSalesOrder">添加</button></td>--%>
                                <%--</tr>--%>
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
        <!-- end of modal -->
    </jsp:body>
</t:generic>