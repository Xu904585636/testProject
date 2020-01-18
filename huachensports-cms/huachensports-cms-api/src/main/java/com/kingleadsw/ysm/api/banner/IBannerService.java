package com.kingleadsw.ysm.api.banner;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dto.activity.ActivityCategoryDTO;
import com.kingleadsw.ysm.dto.banner.BannerDTO;

import java.util.List;

public interface IBannerService {

    /**
     * 轮播图
     * @param
     * @return
     */
    List<BannerDTO> getBannerList();

    /**
     * 分页获取轮播图
     * @param paginationVO
     * @return
     */
    PageDTO<BannerDTO> getBannerList(PaginationDTO<BannerDTO, VoidEnum> paginationVO);

    /**
     * 修改轮播图信息
     * @param bannerDTO
     */
    void updateBanner(BannerDTO bannerDTO);

    /**
     * 新增轮播图信息
     * @param bannerDTO
     * @return
     */
    void insertBanner(BannerDTO bannerDTO);

    /**
     * 删除轮播图
     * @param bannerId
     */
    void delBanner(Long bannerId);
}
