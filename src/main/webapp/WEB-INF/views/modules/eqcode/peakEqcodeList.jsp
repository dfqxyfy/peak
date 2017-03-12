<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查看错误日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/eqcode/peakEqcode/">查看错误日志列表</a></li>
		<shiro:hasPermission name="eqcode:peakEqcode:edit"><li><a href="${ctx}/eqcode/peakEqcode/form">查看错误日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="peakEqcode" action="${ctx}/eqcode/peakEqcode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>open_id：</label>
				<form:input path="openId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>eqcode_text：</label>
				<form:input path="eqcodeText" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>create_time：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${peakEqcode.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${peakEqcode.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div>
		<form:form enctype="multipart/form-data"  id="importForm" modelAttribute="peakEqcode" action="${ctx}/eqcode/peakEqcode/fileUpload" method="post" class="breadcrumb form-search">
		<input type="file" name="file"/>
		<input type="submit" class="btn" value="上传文件提交文件"/>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>open_id</th>
				<th>eqcode_text</th>
				<th>create_time</th>
				<th>update_time</th>
				<shiro:hasPermission name="eqcode:peakEqcode:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="peakEqcode">
			<tr>
				<td><a href="${ctx}/eqcode/peakEqcode/form?id=${peakEqcode.id}">
					${peakEqcode.id}
				</a></td>
				<td>
					${peakEqcode.openId}
				</td>
				<td>
					${peakEqcode.eqcodeText}
				</td>
				<td>
					<fmt:formatDate value="${peakEqcode.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${peakEqcode.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="eqcode:peakEqcode:edit"><td>
    				<a href="${ctx}/eqcode/peakEqcode/form?id=${peakEqcode.id}">修改</a>
					<a href="${ctx}/eqcode/peakEqcode/delete?id=${peakEqcode.id}" onclick="return confirmx('确认要删除该查看错误日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>