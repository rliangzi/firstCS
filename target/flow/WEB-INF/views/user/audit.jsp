<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/common/global.jsp"%>
	<title>审批列表</title>
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
	function selectUserGroup(ty){
		var vl = window.showModalDialog(ctx + "/user/select-user-group?type=" + ty);  
		if(ty =='user'){
			$("#user_id").val(vl);
		}else if(ty =='group'){
			$("#group_id").val(vl);
		}
	}
	var processInstId = "${taskMap.processInstId}";
	var taskId = "${taskMap.taskId}";
	var businessKey = "${taskMap.businessKey}";
	function audit(){
		var url = ctx + '/wf/audit?processInstId=' + processInstId + "&taskId=" + taskId + "&businessKey=" + businessKey + "&hr=" + $("#user_id").val();
		alert(url)
		$.getJSON(url, function(infos) {
			if(infos == "0000"){
				alert("处理成功");
			}else{
				alert(infos);
			}
	    });
	}
	</script>
</head>
<body>
	<table>
		<td style="width: 50%">
			<table style="width: 100%">
				<tr>
					<td style="text-align: center;background-color: gray;">用户信息</td>
				</tr>
				<tr>
					<td>用户id：${user.user_id }</td>
				</tr>
				</tr>
				<tr>
					<td>用户名：${user.user_name }</td>
				</tr>
				<tr>
					<td>系统id：${user.system_id }</td>
				</tr>
				<tr>
					<td>
						选择处理人：<input id="user_id" name="user_id" type="text"> <input type="button" onclick="selectUserGroup('user')" value="选择">
					</td>
				</tr>
				<tr>
					<td>
						选择处理组织机构：<input id="group_id" name="group_id" type="text"> <input type="button" onclick="selectUserGroup('group')" value="选择">
					</td>
				</tr>
			</table>
			<input type="button" onclick="javascript:history.go(-1)" value="返回">
			<input type="button" onclick="audit()" value="审批">
			<input type="button"class="trace" pid="${taskMap.processInstId }" value="点击查看流程图">
		</td>
		<td>
			<table style="width: 100%">
				<tr>
					<td colspan="10" style="text-align: center;background-color: gray;">审批信息</td>
				</tr>
				<tr>
					<th>节点id</th>
					<th>节点名称</th>
					<th>节点处理人</th>
					<th>操作编码</th>
					<th>操作名称</th>
					<th>处理时间</th>
					<th>下步节点id</th>
					<th>下步节点名称</th>
					<th>下步处理人</th>
					<th>下步处理组</th>
				</tr>
				<c:forEach items="${taskHistory }" var="task">
				<tr>
					<td>${task.linkId }</td>
					<td>${task.linkName }</td>
					<td>${task.person }</td>
					<td>${task.operCode }</td>
					<td>${task.operName }</td>
					<td>${task.operDate }</td>
					<td>${task.nextLinkId }</td>
					<td>${task.nextLinkName }</td>
					<td>${task.dealPerson }</td>
					<td>${task.dealGroup }</td>
				</tr>
				</c:forEach>
			</table>
		</td>
	</table>
</body>

<script type="text/javascript">
// 获取图片资源
var myUrl = ctx + "/wf/resource/process-instance?processInstId=" + ${taskMap.processInstId } + "&resourceType=image";

$(function() {
	// 跟踪
    $('.trace').click(graphTrace);
});

function graphTrace(options) {
    var _defaults = {
        srcEle: this,
        pid: $(this).attr('pid')
    };
    var opts = $.extend(true, _defaults, options);
    // 获取图片资源
    var imageUrl = ctx + "/wf/resource/process-instance?processInstId=" + opts.pid + "&resourceType=image";
    $.getJSON(ctx + '/wf/process/trace?processInstId=' + opts.pid , function(infos) {
        var positionHtml = "";
        $.each(infos, function(i, v) {
            var $positionDiv = $('<div/>', {
            	'linkId' : v.linkId,
                'class': 'activity-attr'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 2),
                height: (v.height - 2),
                backgroundColor: 'black',
                opacity: 0,
                zIndex: $.fn.qtip.zindex - 1
            });
            // 节点边框
            var $border = $('<div/>', {
                'class': 'activity-attr-border'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 4),
                height: (v.height - 3),
                zIndex: $.fn.qtip.zindex - 2
            });
            if (v.currentActiviti) {
                $border.addClass('ui-corner-all-12').css({
                    border: '3px solid red'
                });
            }
            positionHtml += $positionDiv.outerHTML() + $border.outerHTML();
        });
        if ($('#workflowTraceDialog').length == 0) {
            $('<div/>', {
                id: 'workflowTraceDialog',
                title: '查看流程（按ESC键可以关闭）',
                html: "<div><img src='" + imageUrl + "' style='position:absolute; left:0px; top:0px;' />" +
                "<div id='processImageBorder'>" +
                positionHtml +
                "</div>" +
                "</div>"
            }).appendTo('body');
        } else {
            $('#workflowTraceDialog img').attr('src', imageUrl);
            $('#workflowTraceDialog #processImageBorder').html(positionHtml);
        }
        // 打开对话框
        $('#workflowTraceDialog').dialog({
            modal: true,
            resizable: false,
            dragable: false,
            open: function() {
                $('#workflowTraceDialog').dialog('option', 'title', '查看流程（按ESC键可以关闭）');
                $('#workflowTraceDialog').css('padding', '0.2em');
                $('#workflowTraceDialog .ui-accordion-content').css('padding', '0.2em').height($('#workflowTraceDialog').height() - 75);
                // 此处用于显示每个节点的信息，如果不需要可以删除
                $('.activity-attr').on('click', function(){
                	showView($(this).attr("linkId"));
                });
            },
            close: function() {
                $('#workflowTraceDialog').remove();
            },
            width: document.documentElement.clientWidth * 0.95,
            height: document.documentElement.clientHeight * 0.95
        });
    });
}

function showView(linkId){
	alert(linkId)
}

</script>

</html>
