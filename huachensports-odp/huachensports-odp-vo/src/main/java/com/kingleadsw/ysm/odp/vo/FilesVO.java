package com.kingleadsw.ysm.odp.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "文件上传VO")
public class FilesVO {

    private  String file;
}
