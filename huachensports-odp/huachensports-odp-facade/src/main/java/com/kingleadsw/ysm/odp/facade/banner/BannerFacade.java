package com.kingleadsw.ysm.odp.facade.banner;

import com.kingleadsw.ysm.api.activity.IActivityCategoryService;
import com.kingleadsw.ysm.api.banner.IBannerService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dto.activity.ActivityCategoryDTO;
import com.kingleadsw.ysm.dto.banner.BannerDTO;
import com.kingleadsw.ysm.utils.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerFacade {

    @Autowired
    private IBannerService bannerService;



    /**
     * 删除轮播图
     * @param bannerId
     */
    public void delBanner(Long bannerId){
        bannerService.delBanner(bannerId);
    }


    /**
     * 获取轮播图分类列表
     * @param paginationVO
     * @return
     */
    public PageDTO<BannerDTO> getBannerList(PaginationDTO<BannerDTO, VoidEnum> paginationVO) {
        PageDTO<BannerDTO> pageDTO = bannerService.getBannerList(paginationVO);
        return pageDTO;
    }

    /**
     * 根据是否存在分类Id修改或者新增轮播图
     * @param bannerDTO
     * @return
     */
    public void editBanner(BannerDTO bannerDTO){
        if(Asserts.isNotNullOrEmpty(bannerDTO.getId())){       //修改
            bannerService.updateBanner(bannerDTO);
        }else{                                                           //新增
            bannerService.insertBanner(bannerDTO);
        }
    }

}
