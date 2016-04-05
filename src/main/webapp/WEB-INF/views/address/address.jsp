<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/common/global.jsp"%>
	<title>接口地址</title>
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
					<td colspan="13" style="text-align: center;background-color: gray;">接口地址列表</td>
				</tr>
				<tr>
					<th>序号</th>
					<th>地址</th>
					<th>操作</th>
				</tr>
				<tr>
					<td>1</td>
					<td>http://localhost:8088/firstCS/message/getMessage?type=complainQuery</td>
					<td>
						<a href="${ctx }/message/getMessage?type=complainQuery">投诉查询</a>
					</td>
				</tr>
				<tr>
					<td>2</td>
					<td>http://localhost:8088/firstCS/message/getMessage?type=complainArchive</td>
					<td>
						<a href="${ctx }/message/getMessage?type=complainArchive">投诉归档</a>
					</td>
				</tr>
				<tr>
					<td>3</td>
					<td>http://localhost:8088/firstCS/message/getMessage?type=complainReply</td>
					<td>
						<a href="${ctx }/message/getMessage?type=complainReply">投诉回复</a>
					</td>
				</tr>
				<tr>
					<td>4</td>
					<td>http://localhost:8088/firstCS/message/getMessage?type=complainDispatch</td>
					<td>
						<a href="${ctx }/message/getMessage?type=complainDispatch">投诉派发</a>
					</td>
				</tr>
				<tr>
					<td>5</td>
					<td>http://localhost:8088/firstCS/message/getMessage?type=middleAdvice</td>
					<td>
						<a href="${ctx }/message/getMessage?type=middleAdvice">中途意见</a>
					</td>
				</tr>
			</table>
		</td>
	</table>
</body>
</html>
