<%@page import="cn.edu.nyist.bookmanv1.vo.BookVo"%>
<%@page import="cn.edu.nyist.bookmanv1.vo.TypeVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%--
//权限拦截
		if(session.getAttribute("loginSuccess")==null||!session.getAttribute("loginSuccess").equals("1")) {
			response.sendRedirect("login.jsp");
			return;
		}
--%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书籍添加</title>
<!-- 告诉浏览器不要缩放 -->
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
					</button> <a class="navbar-brand" href="#">图书管理系统</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">书籍管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="bookList">查看</a>
								</li>
								<li>
									<a href="bookAdd.jsp">添加</a>
								</li>
							</ul>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="#">修改密码</a>
						</li>
						<li>
							<a href="exit.jsp">退出</a>
						</li>
					</ul>
				</div>
				
			</nav>
		</div>
	</div>
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" role="form" method="post"
					action="doBookEdit" id="bookEditFrm" enctype="multipart/form-data">
					<%
						if (request.getAttribute("msg") != null) {
					%>
					<div class="alert alert-warning" role="alert"><%=request.getAttribute("msg")%></div>
					<%
						}
					BookVo bookVo=(BookVo)request.getAttribute("bookVo");
					%>
					<input type="hidden" name="id" value="<%=bookVo.getId()%>">
					<div class="form-group">

						<label for="inputName" class="col-sm-2 control-label"> 书名
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputName"
								name="name"
								value="<%=bookVo==null||bookVo.getName()==null ? "" : bookVo.getName()%>" />
						</div>
					</div>
					<div class="form-group">

						<label for="textAreaDescri" class="col-sm-2 control-label">
							描述 </label>
						<div class="col-sm-10">
							<textarea class="form-control" name="descri" id="textAreaDescri">
							<%=bookVo==null||bookVo.getDescri()==null ? "" : bookVo.getDescri()%>
							</textarea>
						</div>
					</div>
					<div class="form-group">

						<label for="inputPhoto" class="col-sm-2 control-label"> 图片
						</label>
						<% 
						if(bookVo!=null&&bookVo.getPhoto()!=null){
						%>
						<div class="col-sm-6">
						<img alt="" src="upload/<%=bookVo.getPhoto()%>">
						</div>
						<% }%>
						<div class="col-sm-4">
							<input type="file" class="form-control" id="inputPhoto"
								name="photo" />
						</div>
					</div>
					<div class="form-group">

						<label for="inputPrice" class="col-sm-2 control-label"> 价格
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPrice"
								name="price" placeholder="请输入数字" 
								value="<%=bookVo==null? "" : bookVo.getPrice()%>"/>
						</div>
					</div>
					<div class="form-group">

						<label for="inputPubDate" class="col-sm-2 control-label">
							出版时间 </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPubDate"
								name="pubDate" value="<%=bookVo==null||bookVo.getPubDate()==null ? "" : bookVo.getPubDate()%>"/>
						</div>
					</div>
					<div class="form-group">

						<label for="inputAuthor" class="col-sm-2 control-label">
							作者 </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputAuthor"
								name="author" value="<%=bookVo==null||bookVo.getAuthor()==null ? "" : bookVo.getAuthor()%>"/>
						</div>
					</div>
					<div class="form-group">

						<label for="selectTid" class="col-sm-2 control-label"> 类别
						</label>
						<div class="col-sm-10">
							<select name="tid" class="form-control" id="selectTid">
								
							</select>
						</div>
					</div>
					<div class="form-group">

						<label for="inputVcode" class="col-sm-2 control-label">
							验证码 </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="inputVcode"
								name="vcode" maxlength="4" />
						</div>
						<div class="col-sm-4">
							<img alt="" src="vcode.png" id="vcodeImg" title="单击换图片">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">

							<button type="submit" class="btn btn-default">修改</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="bower_components/jquery/dist/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/bootstrap/dist/js/bootstrap.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/jquery-validation/dist/jquery.validate.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/jquery-validation-bootstrap-tooltip/jquery-validate.bootstrap-tooltip.min.js">
		
	</script>
	<script type="text/javascript">
		$(function() {
			$("#vcodeImg").click(function(evt) {
				//通过随机数更改新的验证码图片的值让每次点击验证码图片都能更新
				this.src = "vcode.png?t=" + Math.random();
			});
			//日期控件
			$('#inputPubDate').datepicker({
				format : 'yyyy-mm-dd',//日期格式
				language : 'zh-CN',//提示中文界面
				autoclose : true//自动关闭
			});
			//添加验证
			$( "#bookEditFrm" ).validate( {
				rules: {
					name: "required",
					price: {
						required: true,
						number: true
					}
				},
				messages: {
					name: "书名必填",
					username: {
						required: "价格必填",
						number: "必须输入数字"
					}
				},
				errorElement: "em",
				errorPlacement: function ( error, element ) {
					error.addClass( "alert-warning" );
					if ( element.prop( "type" ) === "checkbox" ) {
						error.insertAfter( element.parent( "label" ) );
					} else {
						error.insertAfter( element );
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-sm-6" ).addClass( "has-error" ).removeClass( "has-success" );
				},
				unhighlight: function (element, errorClass, validClass) {
					$( element ).parents( ".col-sm-6" ).addClass( "has-success" ).removeClass( "has-error" );
				}
			});
		});
	</script>
	<script type="text/javascript">
		function fillSel(){
			var tid = <%=bookVo.getTid()%>;
			var sel = document.getElementById("selectTid");
			for(var i=0;i<types.length;i++){
				var op=new Option(types[i].name,types[i].id);
				sel.appendChild(op);
				if(tid==types[i].id){
					op.selected=true;
					}
				}
			}
	</script>
	<script type="text/javascript" src="findAllTypes" onload="fillSel()"></script>
</body>
</html>