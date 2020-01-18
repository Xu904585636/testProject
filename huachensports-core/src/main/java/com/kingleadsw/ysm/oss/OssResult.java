package com.kingleadsw.ysm.oss;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/27 14:14
 * @Description:

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OssResult {

    private String url;
    private String uri;
    private String domain;
    private String eTag;
    private long size;
    private String name;
    private String originalName;
    private String bucket;
    private String endpoint;

    public OssResult(String eTag)
    {
        this.eTag = eTag;
    }

}
 */