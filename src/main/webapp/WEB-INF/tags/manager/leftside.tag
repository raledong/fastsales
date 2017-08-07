<%@ tag language="java" pageEncoding="UTF-8"%>
<%-- 首页 --%>
<%@ attribute name="indexActive"%>
<%-- 库存 --%>
<%@ attribute name="productActive"%>
<%-- 客户--%>
<%@ attribute name="customerActive"%>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>用户名</p>
                <a href="#"><i class="fa fa-circle text-success"></i> 职位</a>
            </div>
        </div>
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header ${indexActive}">首页</li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>首页</span></i>
                </a>
            </li>

            <%-- 库存管理 --%>
            <li class="header ${productActive}">库存管理</li>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-database"></i>
                    <span>商品信息</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/product/add"><i class="fa  fa-plus"></i> 添加商品 </a></li>
                    <li><a href="pages/layout/boxed.html"><i class="fa fa-circle-o"></i> 商品列表</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-sticky-note"></i>
                    <span>进/退货单管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="pages/layout/top-nav.html"><i class="fa  fa-plus"></i> 新建进货单 </a></li>
                    <li><a href="pages/layout/top-nav.html"><i class="fa  fa-plus"></i> 新建退货单 </a></li>

                    <li><a href="pages/layout/boxed.html"><i class="fa fa-circle-o"></i> 进货单列表</a></li>
                </ul>
            </li>
            <%-- END OF 库存管理 --%>

            <%-- 客户和供应商管理 --%>
            <li class="header ${customerActive}">客户和供应商</li>
            <li class="treeview">
                <a href="#">
                    <i class="fa  fa-users"></i>
                    <span>客户管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="pages/layout/top-nav.html"><i class="fa fa-plus"></i> 添加客户</a></li>
                    <li><a href="#"><i class="fa fa-list-ul"> 查看所有客户</i></a> </li>
                    <li><a href="#"><i class="fa fa-pie-chart"> 客户信息统计</i></a> </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa  fa-plane"></i>
                    <span>供应商管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href=""><i class="fa fa-plus"></i> 添加供应商 </a></li>
                    <li><a href=""><i class="fa fa-list-ul"></i> 供应商列表</a></li>
                    <li><a href=""><i class="fa fa-pie-chart"></i> 供应商信息统计</a></li>
                </ul>
            </li>
            <%-- END OF 供应商管理 --%>

            <li class="header">订单管理</li>
            <li class="header">销售管理</li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
