package com.kingleadsw.ysm.mtp.facade.banner;

import com.kingleadsw.ysm.api.banner.IBannerService;
import com.kingleadsw.ysm.api.customer.ICustomerService;
import com.kingleadsw.ysm.dto.banner.BannerDTO;
import com.kingleadsw.ysm.dto.customer.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BannerFacade {
    @Autowired
    private IBannerService bannerService;

    /**
     * 获取轮播图列表
     * @return
     */
    public List<BannerDTO> getBannerList(){
        return bannerService.getBannerList();
    }
}
