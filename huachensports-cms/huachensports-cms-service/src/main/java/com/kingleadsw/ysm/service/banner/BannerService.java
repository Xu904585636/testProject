package com.kingleadsw.ysm.service.banner;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.api.activity.IScoreService;
import com.kingleadsw.ysm.api.banner.IBannerService;
import com.kingleadsw.ysm.base.BaseService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dao.activity.IScoreMapper;
import com.kingleadsw.ysm.dao.banner.IBannerMapper;
import com.kingleadsw.ysm.dto.activity.ActivityCategoryDTO;
import com.kingleadsw.ysm.dto.activity.ScoreDTO;
import com.kingleadsw.ysm.dto.banner.BannerDTO;
import com.kingleadsw.ysm.po.activity.ActivityCategoryPO;
import com.kingleadsw.ysm.po.activity.ScorePO;
import com.kingleadsw.ysm.po.banner.BannerPO;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.ObjectCopys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService extends BaseService<BannerPO> implements IBannerService {

    @Autowired
    private IBannerMapper bannerMapper;

    @Override
    public List<BannerDTO> getBannerList() {
        List<BannerPO> list = bannerMapper.selectList(this.wrapper().orderBy("sort", true));
        return ObjectCopys.mappingAll(list,BannerDTO.class);
    }

    @Override
    public PageDTO<BannerDTO> getBannerList(PaginationDTO<BannerDTO, VoidEnum> paginationDTO) {
        Page<BannerPO> poPage = this.getPage(paginationDTO);
        List<BannerPO> list = bannerMapper.getByPages(poPage, poPage.getCondition());
        return this.getPage(poPage, ObjectCopys.mappingAll(list, BannerDTO.class));
    }

    @Override
    public void updateBanner(BannerDTO bannerDTO) {
        BannerPO bannerPO = ObjectCopys.maping(bannerDTO, BannerPO.class);
        bannerPO.allEx(RequestCache.userId());
        bannerMapper.update(bannerPO,wrapper().eq("id",bannerPO.getId()));
    }

    @Override
    public void insertBanner(BannerDTO bannerDTO) {
        BannerPO bannerPO = ObjectCopys.maping(bannerDTO, BannerPO.class);
        bannerPO.allEx(RequestCache.userId());
        bannerMapper.insert(bannerPO);
    }

    @Override
    public void delBanner(Long bannerId) {
        bannerMapper.deleteById(bannerId);
    }
}
