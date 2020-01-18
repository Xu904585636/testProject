package com.kingleadsw.ysm.po.banner;

import com.baomidou.mybatisplus.annotations.TableName;
import com.kingleadsw.ysm.base.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("cms_banner")
public class BannerPO extends BasePO {

    private String imageUrl;

    private Integer sort;

}
