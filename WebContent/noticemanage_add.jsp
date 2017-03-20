<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="en">
    <%@ taglib uri="/struts-tags" prefix="s"%>
        <%@page import="java.sql.*"%>

            <head>

                <!-- Basic -->
                <meta charset="UTF-8" />

                <title>DCMS MANAGEMENT</title>

                <!-- Mobile Metas -->
                <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />


                <!-- Favicon and touch icons -->
                <link rel="shortcut icon" href="assets/ico/favicon.ico" type="image/x-icon" />
                <link rel="apple-touch-icon" href="assets/ico/apple-touch-icon.png" />
                <link rel="apple-touch-icon" sizes="57x57" href="assets/ico/apple-touch-icon-57x57.png" />
                <link rel="apple-touch-icon" sizes="72x72" href="assets/ico/apple-touch-icon-72x72.png" />
                <link rel="apple-touch-icon" sizes="76x76" href="assets/ico/apple-touch-icon-76x76.png" />
                <link rel="apple-touch-icon" sizes="114x114" href="assets/ico/apple-touch-icon-114x114.png" />
                <link rel="apple-touch-icon" sizes="120x120" href="assets/ico/apple-touch-icon-120x120.png" />
                <link rel="apple-touch-icon" sizes="144x144" href="assets/ico/apple-touch-icon-144x144.png" />
                <link rel="apple-touch-icon" sizes="152x152" href="assets/ico/apple-touch-icon-152x152.png" />

                <!-- start: CSS file-->

                <!-- Vendor CSS-->
                <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
                <link href="assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
                <link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />

                <!-- Plugins CSS-->
                <link href="assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />
                <link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
                <link href="assets/plugins/bootstrap-datepicker/css/datepicker-theme.css" rel="stylesheet" />
                <link href="assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.css" rel="stylesheet" />
                <link href="assets/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.css" rel="stylesheet" />
                <link href="assets/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" />

                <!-- Theme CSS -->
                <link href="assets/css/jquery.mmenu.css" rel="stylesheet" />

                <!-- Page CSS -->
                <link href="assets/css/style.css" rel="stylesheet" />

                <!-- end: CSS file-->


                <!-- Head Libs -->
                <script src="assets/plugins/modernizr/js/modernizr.js"></script>

                <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
                <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
                <!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

            </head>

            <body>

                <!-- Start: Header -->
                <div class="navbar" role="navigation">
                    <div class="container-fluid container-nav">
                        <!-- Navbar Action -->
                        <ul class="nav navbar-nav navbar-actions navbar-left">
                            <li class="visible-md visible-lg"><a href="noticemanage_add.jsp#" id="main-menu-toggle"><i class="fa fa-th-large"></i></a></li>
                            <li class="visible-xs visible-sm"><a href="noticemanage_add.jsp#" id="sidebar-menu"><i class="fa fa-navicon"></i></a></li>
                        </ul>
                        <!-- Navbar Left -->
                        <div class="navbar-left">
                            <!-- Search Form -->
                            <form class="search navbar-form">
                                <div class="input-group input-search">
                                    <input type="text" class="form-control" name="q" id="q" placeholder="Search...">
                                    <span class="input-group-btn">
								<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
							</span>
                                </div>
                            </form>
                        </div>
                        <!-- Navbar Right -->
                        <div class="navbar-right">
                            <!-- Notifications -->
                            <ul class="notifications hidden-sm hidden-xs">
                                <li>
                                    <a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
                                        <i class="fa fa-tasks"></i>
                                        <span class="badge">10</span>
                                    </a>
                                    <ul class="dropdown-menu update-menu" role="menu">
                                        <li><a href="#"><i class="fa fa-database bk-fg-primary"></i> Database </a></li>
                                        <li><a href="#"><i class="fa fa-bar-chart-o bk-fg-primary"></i> Connection </a></li>
                                        <li><a href="#"><i class="fa fa-bell bk-fg-primary"></i> Notification </a></li>
                                        <li><a href="#"><i class="fa fa-envelope bk-fg-primary"></i> Message </a></li>
                                        <li><a href="#"><i class="fa fa-flash bk-fg-primary"></i> Traffic </a></li>
                                        <li><a href="#"><i class="fa fa-credit-card bk-fg-primary"></i> Invoices </a></li>
                                        <li><a href="#"><i class="fa fa-dollar bk-fg-primary"></i> Finances </a></li>
                                        <li><a href="#"><i class="fa fa-thumbs-o-up bk-fg-primary"></i> Orders </a></li>
                                        <li><a href="#"><i class="fa fa-folder bk-fg-primary"></i> Directories </a></li>
                                        <li><a href="#"><i class="fa fa-users bk-fg-primary"></i> Users </a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
                                        <i class="fa fa-envelope"></i>
                                        <span class="badge">5</span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li class="dropdown-menu-header">
                                            <strong>Messages</strong>
                                            <div class="progress progress-xs  progress-striped active">
                                                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                    60%
                                                </div>
                                            </div>
                                        </li>
                                        <li class="avatar">
                                            <a href="page-inbox.html">
                                                <img class="avatar" src="assets/img/avatar1.jpg" alt="" />
                                                <div>
                                                    <div class="point point-primary point-lg"></div>New message</div>
                                                <span><small>1 minute ago</small></span>
                                            </a>
                                        </li>
                                        <li class="avatar">
                                            <a href="page-inbox.html">
                                                <img class="avatar" src="assets/img/avatar2.jpg" alt="" />
                                                <div>
                                                    <div class="point point-primary point-lg"></div>New message</div>
                                                <span><small>3 minute ago</small></span>
                                            </a>
                                        </li>
                                        <li class="avatar">
                                            <a href="page-inbox.html">
                                                <img class="avatar" src="assets/img/avatar3.jpg" alt="" />
                                                <div>
                                                    <div class="point point-primary point-lg"></div>New message</div>
                                                <span><small>4 minute ago</small></span>
                                            </a>
                                        </li>
                                        <li class="avatar">
                                            <a href="page-inbox.html">
                                                <img class="avatar" src="assets/img/avatar4.jpg" alt="" />
                                                <div>
                                                    <div class="point point-primary point-lg"></div>New message</div>
                                                <span><small>30 minute ago</small></span>
                                            </a>
                                        </li>
                                        <li class="avatar">
                                            <a href="page-inbox.html">
                                                <img class="avatar" src="assets/img/avatar5.jpg" alt="" />
                                                <div>
                                                    <div class="point point-primary point-lg"></div>New message</div>
                                                <span><small>1 hours ago</small></span>
                                            </a>
                                        </li>
                                        <li class="dropdown-menu-footer text-center">
                                            <a href="page-inbox.html">View all messages</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
                                        <i class="fa fa-bell"></i>
                                        <span class="badge">3</span>
                                    </a>
                                    <ul class="dropdown-menu list-group">
                                        <li class="dropdown-menu-header">
                                            <strong>Notifications</strong>
                                            <div class="progress progress-xs  progress-striped active">
                                                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                    60%
                                                </div>
                                            </div>
                                        </li>
                                        <li class="list-item">
                                            <a href="page-inbox.html">
                                                <div class="pull-left">
                                                    <i class="fa fa-envelope-o bk-fg-primary"></i>
                                                </div>
                                                <div class="media-body clearfix">
                                                    <div>Unread Message</div>
                                                    <h6>You have 10 unread message</h6>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="list-item">
                                            <a href="#">
                                                <div class="pull-left">
                                                    <i class="fa fa-cogs bk-fg-primary"></i>
                                                </div>
                                                <div class="media-body clearfix">
                                                    <div>New Settings</div>
                                                    <h6>There are new settings available</h6>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="list-item">
                                            <a href="#">
                                                <div class="pull-left">
                                                    <i class="fa fa-fire bk-fg-primary"></i>
                                                </div>
                                                <div class="media-body clearfix">
                                                    <div>Update</div>
                                                    <h6>There are new updates available</h6>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="list-item-last">
                                            <a href="#">
                                                <h6>Unread notifications</h6>
                                                <span class="badge">15</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <!-- End Notifications -->
                            <!-- Userbox -->
                            <div class="userbox">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <div class="profile-info">
                                        <span class="name">赵小明</span>
                                        <span class="role">管理员</span>
                                    </div>
                                    <i class="fa custom-caret"></i>
                                </a>
                                <div class="dropdown-menu">
                                    <ul class="list-unstyled">
                                        <li class="dropdown-menu-header bk-bg-white bk-margin-top-15">
                                            <div class="progress progress-xs  progress-striped active">
                                                <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                                    60%
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <a href="page-profile.html"><i class="fa fa-user"></i> Profile</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-wrench"></i> Settings</a>
                                        </li>
                                        <li>
                                            <a href="page-invoice"><i class="fa fa-usd"></i> Payments</a>
                                        </li>
                                        <li>
                                            <a href="#"><i class="fa fa-file"></i> File</a>
                                        </li>
                                        <li>
                                            <a href="page-login.html"><i class="fa fa-power-off"></i> Logout</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- End Userbox -->
                        </div>
                        <!-- End Navbar Right -->
                    </div>
                </div>
                <!-- End: Header -->

                <!-- Start: Content -->
                <div class="container-fluid content">
                    <div class="row">

                        <!-- Sidebar -->
                        <div class="sidebar">
                            <div class="sidebar-collapse">
                                <!-- Sidebar Header Logo-->
                                <div class="sidebar-header">
                                    <img src="assets/img/logo.png" class="img-responsive" alt="" />
                                </div>
                                <!-- Sidebar Menu-->
                                <div class="sidebar-menu">
                                    <nav id="menu" class="nav-main" role="navigation">
                                        <ul class="nav nav-sidebar">
                                            <div class="panel-body text-center">
                                                <div class="bk-avatar">
                                                    <img src="assets/img/avatar.jpg" class="img-circle bk-img-60" alt="" />
                                                </div>
                                                <div class="bk-padding-top-10">
                                                    <i class="fa fa-circle text-success"></i> <small>管理员</small>
                                                </div>
                                            </div>
                                            <div class="divider2"></div>
                                            <li>
                                                <a href="index_manage.jsp">
                                                    <i class="icon fa fa-home" aria-hidden="true"></i><span>首页</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="">
                                                    <span class="pull-right label label-primary">3</span>
                                                    <i class="fa fa-envelope" aria-hidden="true"></i><span>消息</span>
                                                </a>
                                            </li>
                                            <li class="nav-parent">
                                                <a>
                                                    <i class="fa fa-copy" aria-hidden="true"></i><span>公告信息</span>
                                                </a>
                                                <ul class="nav nav-children">
                                                    <li><a href="noticemanage_add.jsp"><span class="text"> 公告录入</span></a></li>
                                                    <li><a href="listnotice.action"><span class="text"> 公告管理</span></a></li>
                                                </ul>
                                            </li>
                                            <li class="nav-parent nav-expanded active">
                                                <a>
                                                    <i class="fa fa-list-alt" aria-hidden="true"></i><span>新闻信息</span>
                                                </a>
                                                <ul class="nav nav-children">
                                                    <li><a href="newsManage_add.jsp"><span class="text"> 新闻录入</span></a></li>
                                                    <li><a href="showNews.action"><span class="text"> 新闻管理</span></a></li>
                                                </ul>
                                            </li>
                                            <li class="nav-parent">
                                                <a>
                                                    <i class="fa fa-random" aria-hidden="true"></i><span>学科竞赛</span>
                                                </a>
                                                <ul class="nav nav-children">
                                                    <li><a href="Competition-add.jsp"><span class="text"> 竞赛录入</span></a></li>
                                                    <li><a href="Competition-index.jsp"><span class="text"> 竞赛管理</span></a></li>
                                                </ul>
                                            </li>
                                            <li class="nav-parent">
                                                <a>
                                                    <i class="fa fa-tasks" aria-hidden="true"></i><span>参赛学生</span>
                                                </a>
                                                <ul class="nav nav-children">
                                                    <li><a href="joinComQuery"><span class="text"> 参赛查询</span></a></li>
                                                </ul>
                                            </li>
                                            <li>
                                                <a href="">
                                                    <i class="fa fa-life-bouy" aria-hidden="true"></i><span>账户设置</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                                <!-- End Sidebar Menu-->
                            </div>
                            <!-- Sidebar Footer-->
                            <div class="sidebar-footer">
                                <ul class="sidebar-terms">
                                    <li><a href="index.html#">Terms</a></li>
                                    <li><a href="index.html#">Privacy</a></li>
                                    <li><a href="index.html#">Help</a></li>
                                    <li><a href="index.html#">About</a></li>
                                </ul>
                                <div class="copyright text-center">
                                    <small><a href="http://http://www.njxzc.edu.cn/" target="_blank">南京晓庄学院</a>信息工程学院 <a href="http://http://www.njxzc.edu.cn/" target="_blank"><br>学科竞赛管理系统</a> </small>
                                </div>
                            </div>
                            <!-- End Sidebar Footer-->
                        </div>
                        <!-- End Sidebar -->

                        <!-- Main Page -->
                        <div class="main">
                            <!-- Page Header -->
                            <div class="page-header">
                                <div class="pull-left">
                                    <ol class="breadcrumb visible-sm visible-md visible-lg">
                                        <li><a href="index_manage.jsp"><i class="icon fa fa-home"></i>首页</a></li>
                                        <li><a href="noticemanage_add.jsp"><i class="icon fa fa-home"></i>公告录入</a></li>
                                    </ol>
                                </div>
                                <div class="pull-right">
                                    <h2>ADD NOTICE</h2>
                                </div>
                            </div>
                            <!-- End Page Header -->
                            <!-- 录入公告信息 开始 -->
                            <div class="row bk-margin-top--15">
                                <div class="col-md-12 bk-padding-0">
                                    <div class="pannel pannel-heading bk-bg-very-light-gray">
                                        <div class="row">
                                            <div class="col-lg-8 col-lg-offset-2  text-center bk-vcenter ">
                                                <h3 class="border-bottom-primary text-primary  bk-padding-top-10 bk-padding-bottom-15">公告信息录入</h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-body bk-bg-very-light-gray">
                                        <form class="form-horizontal" role="form" action="addnotice.action" method="post" enctype="multipart/form-data">
                                            <div class="form-group">
                                                <label class="col-md-3 control-lable text-right" for="text-input">发布时间：</label>
                                                <div class="col-md-3 bk-padding-0">
                                                    <div class="input-group">
                                                        <input id="time" type="text" data-plugin-datepicker name="notice.noticeDate" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-lable text-right" for="text-input">公告正文内容：</label>
                                                <div class="col-md-7 bk-padding-0">
                                                    <div class="class="col-md-offset-3 col-lg-7 col-md-7 bk-padding-0"">
                                                        <textarea name="notice.noticeContent" class="form-control" rows="12"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row text-align-center">
                                               <div class="col-md-12">
                                                   <button type="submit" class="btn btn-success ">提交</button>
                                               </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- 录入公告信息 结束 -->
                        </div>
                        <!-- End Main Page -->


                    </div>
                </div>
                <!--/container-->


                <!-- Modal Dialog -->
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title bk-fg-primary">Modal title</h4>
                            </div>
                            <div class="modal-body">
                                <p class="bk-fg-danger">Here settings can be configured...</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Modal Dialog -->

                <div class="clearfix"></div>


                <!-- start: JavaScript-->

                <!-- Vendor JS-->
                <script src="assets/vendor/js/jquery.min.js"></script>
                <script src="assets/vendor/js/jquery-2.1.1.min.js"></script>
                <script src="assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
                <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
                <script src="assets/vendor/skycons/js/skycons.js"></script>

                <!-- Plugins JS-->
                <script src="assets/plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
                <script src="assets/plugins/touchpunch/js/jquery.ui.touch-punch.min.js"></script>
                <script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
                <script src="assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
                <script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
                <script src="assets/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.js"></script>
                <script src="assets/plugins/maskedinput/js/jquery.maskedinput.js"></script>
                <script src="assets/plugins/moment/js/moment.min.js"></script>
                <script src="assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>
                <script src="assets/plugins/summernote/js/summernote.js"></script>
                <script src="assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
                <script src="assets/plugins/bootstrap-markdown/js/markdown.js"></script>
                <script src="assets/plugins/bootstrap-markdown/js/to-markdown.js"></script>

                <!-- Theme JS -->
                <script src="assets/js/jquery.mmenu.min.js"></script>
                <script src="assets/js/core.min.js"></script>

                <!-- Pages JS -->
                <script src="assets/js/pages/form-elements.js"></script>
                <script src="assets/js/pages/form-editors.js"></script>
                <script src="assets/js/getautotime.js"></script>
				<script src="assets/js/checknotice.js"></script>
                <!-- end: JavaScript-->

            </body>

    </html>










    <!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css">
<title>添加公告</title>
<style type="text/css">
.nig{
  color:gray;
}
</style>
</head>
<body>
<div class="top">
		<img src="imgs/logo.png" alt="logo" class="logo">

		<div class="aside">
			<input type="button" class="login" value="登录">
			<span class="manager"><a href="noticemanage.jsp">管理员</a></span>
			<div>
				<input type="text" class="text"><input type="button"
					value="搜索" class="search">
			</div>
		</div>
		<div class="noticenav" style="width: 100%;
	height: 100px;
	background: #09386b;
	margin-bottom: -20px;color:white;text-align:center;line-height:90px;
	font-size:32px;
	font-family: 'Microsoft YaHei';">
			公告管理中心
		</div>
	</div>
	<div style="height:400px;width:1000px;margin-top:100px;">
	<div style="position:absolute;top:200px;left:40%"><img src="imgs/4.png" ></div>
	<span class="back1"><a href="noticemanage.jsp">&nbsp;返回&nbsp;</a></span>
	<span class="back"><a href="notice/listnotice.action">&nbsp;查询&nbsp;</a></span>
	<div class="notice-a" style="position:absolute;left:400px;top:400px;">
	
	
<form action="notice/addnotice.action" method="post" >

<table style="border-collapse:separate;border-spacing:10px;"><!-- 边框分开 ,行间距-->

    <!-- <tr><td style="width:50px;height:50px;background:#09386b;color:white;text-align:center;line-height:30px;
	font-size:18px;
	font-family: 'Microsoft YaHei';">内容</td><td><input id="con" type="text" 
	name="notice.noticeContent" style="width:350px;height:50px"  placeholder="请输入内容，不能为空！"></td></tr>
<tr><td style="width:50px;height:50px;background:#09386b;color:white;text-align:center;line-height:30px;
	font-size:18px;
	font-family: 'Microsoft YaHei';">日期</td><td><input type="text" id="time" name="notice.noticeDate" style="width:350px;height:50px"></td></tr>

<tr><td><input type="submit" value="提交" style="font-family: 'Microsoft YaHei';width:70px;height:35px;background:#09386b;color:white;text-align:center;line-height:30px;position:relative;top:20px;left:170px;border-radius:30%"></td> </tr>
</table>

</form>
</div>
</div>

<br><br>
	<div class="foot">
		<pre>方山校区：南京市江宁区弘景大道3601号	邮编：211171	莫愁校区：南京市建邺区北圩路41号	邮编：210017
版本所有  2013 南京晓庄学院   苏ICP备16013351号	 苏公网安备 32011502010053号</pre>
	</div>
</body>
</html>-->