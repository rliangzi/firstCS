<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/common/global.jsp"%>
	<title>用户申请</title>
	<%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link href="${ctx }/js/common/plugins/jui/extends/timepicker/jquery-ui-timepicker-addon.css" type="text/css" rel="stylesheet" />
    <link href="${ctx }/js/common/plugins/qtip/jquery.qtip.min.css" type="text/css" rel="stylesheet" />
    <%@ include file="/common/include-custom-styles.jsp" %>
    <script src="${ctx }/js/common/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="${ctx }/js/common/plugins/jui/jquery-ui-${tdemeVersion }.min.js" type="text/javascript"></script>
    <script src="${ctx }/js/common/plugins/jui/extends/timepicker/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/extends/i18n/jquery-ui-date_time-picker-zh-CN.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/jquery.validate.pack.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/qtip/jquery.qtip.pack.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/html/jquery.outerhtml.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/blockui/jquery.blockUI.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script type="text/javascript"></script>
</head>
<body>
	<form action="${ctx }/user/save">
		<table>
			<tr>
				<td>用户id：<input type="text" id="user_id" name="user_id"> </td>
			</tr>
			<tr>
				<td>用户名：<input type="text" id="user_name" name="user_name"> </td>
			</tr>
			<tr>
				<td>系统id：<input type="text" id="" name="system_id"> </td>
			</tr>
			<tr>
				<td><input type="submit" value="提交"> </td>
			</tr>
		</table>
	</form>
</body>
</html>
