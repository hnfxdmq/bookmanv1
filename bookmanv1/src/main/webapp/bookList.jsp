<%@page import="cn.edu.nyist.bookmanv1.vo.TypeVo"%>
<%@page import="cn.edu.nyist.bookmanv1.vo.BookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%--jsp注释
//权限拦截
		if(session.getAttribute("loginSuccess")==null||!session.getAttribute("loginSuccess").equals("1")) {
			response.sendRedirect("login.jsp");
			return;
		}
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>图书列表</title>
<!-- 告诉浏览器不要缩放 -->
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">

						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">图书管理系统</a>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">书籍管理<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="bookList">查看</a></li>
									<li><a href="bookAdd.jsp">添加</a></li>
								</ul></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#">修改密码</a></li>
							<li><a href="exit.jsp">退出</a></li>
						</ul>
					</div>

				</nav>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover table-condensed table-bordered">
					<thead>
						<tr>
							<td colspan="9"><form class="form-inline" action="bookList" id="searchFrm">
									<div class="form-group">
										<label for="inputName">书名</label> <input type="text"
											class="form-control" id="inputName"
											name="name" value='<%=request.getAttribute("name")==null?"":request.getAttribute("name")%>'>
									</div>
									<div class="form-group">
										<label for="selTid">类别</label> 
										<select name="tid" id="selTid" class="form-control">
										<option value="-1">--请选择--</option>
										<% 
										List<TypeVo> lis = (List<TypeVo>)request.getAttribute("types");
												int tid = (Integer)request.getAttribute("tid");
												for(TypeVo typeVo:lis){
													if(tid==typeVo.getId()){
													%>
													<option value="<%=typeVo.getId()%>" selected="selected"><%=typeVo.getName()%></option>
													<% 
												}else{
													%>
													<option value="<%=typeVo.getId()%>"><%=typeVo.getName()%></option>
													<% 
												}}
										%>
										</select>
									</div>
									<button type="submit" class="btn btn-default">搜索</button>
								</form></td>
						</tr>
						<tr>
							<th>编号</th>
							<th>书名</th>
							<th>描述</th>
							<th>类别</th>
							<th>图片</th>
							<th>价格</th>
							<th>作者</th>
							<th>出版时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<BookVo> ls = (List<BookVo>) request.getAttribute("ls");
							for (BookVo bookVo : ls) {
						%>
						<tr>
							<td><%=bookVo.getId()%></td>
							<td><%=bookVo.getName()%></td>
							<td><%=bookVo.getDescri()%></td>
							<td><%=bookVo.getTid()%></td>
							<td><img alt="" src="upload/<%=bookVo.getPhoto()%>" style="max-height: 100px"></td>
							<td><%=bookVo.getPrice()%></td>
							<td><%=bookVo.getAuthor()%></td>
							<td><%=bookVo.getPubDate()%></td>
							<td>
							<a href="bookDel?id=<%=bookVo.getId()%>" class="glyphicon glyphicon-remove" title="删除" onclick="confirmDel(event)"></a>&nbsp;
							<a href="toBookEdit?id=<%=bookVo.getId()%>" class="glyphicon glyphicon-pencil" title="修改"></a>
							</td>
						</tr>
						<%
							}
						%>
						<tr>
							<td colspan="9" style="padding-top: 0px;padding-bottom: 0px" class="text-center">
								<ul class="pagination" style="margin: 0px">
								<%
								int pageNo=(Integer)request.getAttribute("pageNo");
								if(pageNo==1){
									%>
									<li class="disabled"><a href="#">&lt;&lt;</a></li>
									<% 
								} else{%>
								<li><a href="bookList?pageNo=<%=pageNo-1%>">&lt;&lt;</a></li>
								<% 
								}
								%>
									<% 
									int totalPage=(Integer)request.getAttribute("totalPage");
									if(totalPage<=5){
										for(int i=1;i<=totalPage;i++){
											if(i==pageNo){
											%>
											<li class="active"><a href="bookList?pageNo=<%=i%>"><%=i%></a></li>
											<%}
											else{
												%>
												<li><a href="bookList?pageNo=<%=i%>"><%=i%></a></li>
												<%
											}
										}
									} else if(pageNo<=3){
										%>
										<li><a href="bookList?pageNo=1">1</a></li>
									<li><a href="bookList?pageNo=2">2</a></li>
									<li><a href="bookList?pageNo=3">3</a></li>
									<li><a href="bookList?pageNo=4">4</a></li>
									<li><a href="bookList?pageNo=<%=totalPage%>">...<%=totalPage%></a></li>
										<% 
									} else if(pageNo>=totalPage-2){
										%>
										<li><a href="bookList?pageNo=1">1...</a></li>
									<li><a href="bookList?pageNo=<%=totalPage-3%>"><%=totalPage-3%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage-2%>"><%=totalPage-2%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage-1%>"><%=totalPage-1%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage%>"><%=totalPage%></a></li>
										<% 
									} else{
										%>
										<li><a href="bookList?pageNo=1">1...</a></li>
									<li><a href="bookList?pageNo=<%=pageNo-1%>"><%=pageNo-1%></a></li>
									<li><a href="bookList?pageNo=<%=pageNo%>"><%=pageNo%></a></li>
									<li><a href="bookList?pageNo=<%=pageNo+1%>"><%=pageNo+1%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage%>">...<%=totalPage%></a></li>
										<% 
									}
									%>
									<%
								if(pageNo==totalPage){
									%>
									<li class="disabled"><a href="#">&gt;&gt;</a></li>
									<% 
								} else{
									%>
									<li><a href="bookList?pageNo=<%=pageNo+1%>">&gt;&gt;</a></li>
									<%
								}%>
								</ul>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h2>Heading</h2>
				<p>&copy;加版权</p>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="bower_components/jquery/dist/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="bower_components/bootstrap/dist/js/bootstrap.min.js">
		
	</script>
	<script type="text/javascript">
	$(function(){
		$("a[href='bookList?pageNo=<%=pageNo%>']").parent("li").addClass("active");
		//修改分页链接，追加name和tid
		//href^筛选以bookList?pageNo=为开头的标签，属性用中括号选中
		$(".pagination a[href^='bookList?pageNo=']").click(function(){
			//用序列化表单，解决分页时传参数问题
			this.href+="&"+$("#searchFrm").serialize();
			})
		});
	function confirmDel(event){
		if(!confirm("确认删除")){
			//取消默认行为
			event.preventDefault();
			}
		}
	</script>
</body>
</html>