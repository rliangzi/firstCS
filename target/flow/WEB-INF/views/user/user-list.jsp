<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/common/global.jsp"%>
	<title>用户列表</title>
	<%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <%@ include file="/common/include-jquery-ui-theme.jsp" %>
    <link href="${ctx }/js/common/plugins/jui/extends/timepicker/jquery-ui-timepicker-addon.css" type="text/css" rel="stylesheet" />
    <link href="${ctx }/js/common/plugins/qtip/jquery.qtip.min.css" type="text/css" rel="stylesheet" />
    <%@ include file="/common/include-custom-styles.jsp" %>
    <script src="${ctx }/js/common/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="${ctx }/js/common/plugins/jui/jquery-ui-${themeVersion }.min.js" type="text/javascript"></script>
    <script src="${ctx }/js/common/plugins/jui/extends/timepicker/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/extends/i18n/jquery-ui-date_time-picker-zh-CN.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/jquery.validate.pack.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/qtip/jquery.qtip.pack.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/html/jquery.outerhtml.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/blockui/jquery.blockUI.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	</script>
</head>
<body>
	<table>
		<td>
			<table style="width: 100%">
				<tr>
					<td colspan="4" style="text-align: center;background-color: gray;">用户列表</td>
				</tr>
				<tr>
					<th>用户id</th>
					<th>用户名</th>
					<th>系统id</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${result }" var="user">
				<tr>
					<td>${user.user_id }</td>
					<td>${user.user_name }</td>
					<td>${user.system_id }</td>
					<td>
						<c:if test="${type == 'wait' }">
						<a href="${ctx }/user/audit?user_id=${user.user_id}">审批</a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		</td>
	</table>
</body>
</html>
