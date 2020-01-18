package com.kingleadsw.ysm.service.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.api.activity.IActivityService;
import com.kingleadsw.ysm.api.activity.IApplyService;
import com.kingleadsw.ysm.base.BaseService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dao.activity.IApplyMapper;
import com.kingleadsw.ysm.dto.activity.ActivityDTO;
import com.kingleadsw.ysm.dto.activity.ApplyDTO;
import com.kingleadsw.ysm.po.activity.ActivityPO;
import com.kingleadsw.ysm.po.activity.ApplyPO;
import com.kingleadsw.ysm.utils.ObjectCopys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyService extends BaseService<ApplyPO> implements IApplyService {

    @Autowired
    private IApplyMapper applyMapper;

    @Override
    public Integer addApplyInfo(ApplyDTO applyDTO) {
        ApplyPO applyPO = ObjectCopys.maping(applyDTO, ApplyPO.class);
        applyPO.allEx(userId());
        return applyMapper.insert(applyPO);
    }

    @Override
    public List<ApplyDTO> selectApplyList(ApplyDTO applyDTO) {
        List<ApplyPO> applyPOList = applyMapper.selectApplyList(ObjectCopys.maping(applyDTO, ApplyPO.class));
        return ObjectCopys.mappingAll(applyPOList,ApplyDTO.class);
    }

    @Override
    public PageDTO<ApplyDTO> getApplyList(PaginationDTO<ApplyDTO, VoidEnum> paginationDTO) {
        Page<ApplyPO> poPage = this.getPage(paginationDTO);

        List<ApplyPO> list = applyMapper.getApplyList(poPage, poPage.getCondition());

        return this.getPage(poPage, ObjectCopys.mappingAll(ObjectCopys.mappingAll(list, ApplyDTO.class), ApplyDTO.class));
    }
}
