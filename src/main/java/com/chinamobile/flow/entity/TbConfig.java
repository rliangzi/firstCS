
package com.chinamobile.flow.entity;

/**
 * @Description: 参数配置表
 * @ClassName TbConfig
 * @author: shosho
 * @Created 2016 2016年2月3日 上午11:43:56
 */
public class TbConfig {
	private String processDefinitionId;
	private String activityId;
	private String paramType;
	private String paramKey;
	private String paramValue;

	public String getActivityId() {

		return activityId;
	}

	public String getParamKey() {

		return paramKey;
	}

	public String getParamType() {

		return paramType;
	}

	public String getParamValue() {

		return paramValue;
	}

	public String getProcessDefinitionId() {

		return processDefinitionId;
	}

	public void setActivityId(String activityId) {

		this.activityId = activityId;
	}

	public void setParamKey(String paramKey) {

		this.paramKey = paramKey;
	}

	public void setParamType(String paramType) {

		this.paramType = paramType;
	}

	public void setParamValue(String paramValue) {

		this.paramValue = paramValue;
	}

	public void setProcessDefinitionId(String processDefinitionId) {

		this.processDefinitionId = processDefinitionId;
	}

}
