package com.kingleadsw.ysm.api.activity;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dto.activity.ApplyDTO;
import com.kingleadsw.ysm.dto.banner.BannerDTO;

import java.util.List;

public interface IApplyService {

    /**
     * 保存报名信息
     * @param applyDTO
     * @return
     */
    Integer addApplyInfo(ApplyDTO applyDTO);

    /**
     * 查询活动的报名列表
     * @param applyDTO
     * @return
     */
    List<ApplyDTO> selectApplyList(ApplyDTO applyDTO);

    PageDTO<ApplyDTO> getApplyList(PaginationDTO<ApplyDTO, VoidEnum> paginationVO);
}
