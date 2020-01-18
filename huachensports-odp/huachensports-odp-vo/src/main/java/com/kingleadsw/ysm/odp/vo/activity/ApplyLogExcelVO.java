package com.kingleadsw.ysm.odp.vo.activity;

import org.jeecgframework.poi.excel.annotation.Excel;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "报名导出记录VO")
public class ApplyLogExcelVO {
	
	@Excel(name = "活动名称",width=50)
	private String activityName;
	
	@Excel(name = "分组名称",width=30)
	private String groupName;
	
	@Excel(name = "报名人",width=30)
	private String applyName;
	
	@Excel(name = "手机号码",width=30)
	private String mobile;
	
	@Excel(name = "报名时间",width=30)
	private String createTimeStr;

}
