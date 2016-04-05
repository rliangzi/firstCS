<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/common/global.jsp"%>
	<title>选择列表</title>
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
		function selectChk(){
			var chk_value =[]; 
			$('input[name="chk"]:checked').each(function(){ 
				chk_value.push($(this).val()); 
			}); 
			//进行字符串转换
			window.returnValue = chk_value+"";
		}
	</script>
</head>
<body>
	<table>
		<td>
			<table id="selectTb" style="width: 100%">
				<tr>
					<td colspan="3" style="text-align: center;background-color: gray;">选择列表</td>
				</tr>
				<c:if test="${type=='user' }">
					<tr>
						<th>操作</th>
						<th>用户id</th>
						<th>用户名称</th>
					</tr>
					<c:forEach items="${list }" var="ls">
						<tr>
							<td>
								<input type="checkbox" name="chk" value="${ls.userId }"> 
							</td>
							<td>${ls.userId }</td>
							<td>${ls.userName }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${type=='group' }">
					<tr>
						<th>操作</th>
						<th>组织id</th>
						<th>组织名称</th>
					</tr>
					<c:forEach items="${list }" var="ls">
						<tr>
							<td>
								<input type="checkbox" name="chk" value="${ls.groupId }"> 
							</td>
							<td>${ls.groupId }</td>
							<td>${ls.groupName }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<input type="button" value="确定" onclick="selectChk();window.close();">
		</td>
	</table>
</body>
</html>
