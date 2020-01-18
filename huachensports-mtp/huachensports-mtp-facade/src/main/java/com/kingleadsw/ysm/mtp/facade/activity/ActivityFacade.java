package com.kingleadsw.ysm.mtp.facade.activity;

import com.kingleadsw.ysm.api.activity.*;
import com.kingleadsw.ysm.api.collection.ICollectionService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.dto.activity.*;
import com.kingleadsw.ysm.dto.collection.CollectionDTO;
import com.kingleadsw.ysm.exception.UnknowException;
import com.kingleadsw.ysm.mtp.vo.activity.ActivityVO;
import com.kingleadsw.ysm.need.token.Token;
import com.kingleadsw.ysm.need.token.Tokens;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.ObjectCopys;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityFacade {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IActivityCategoryService activityCategoryService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IApplyService applyService;

    @Autowired
    private IScoreService scoreService;

    @Autowired
    private ICollectionService collectionService;

    /**
     * 获取活动列表
     * @param paginationVO
     * @return
     */
    public PageDTO<ActivityDTO> getByActivityPage(PaginationDTO<ActivityVO, VoidEnum> paginationVO) {

        PaginationDTO<ActivityDTO, VoidEnum> paginationDTO = ObjectCopys.transferPagination(paginationVO, ActivityDTO.class);

        PageDTO<ActivityDTO> pageDTO = activityService.getByPage(paginationDTO);

        return pageDTO;
    }

    /**
     * 获取sort正序的分类列表
     * @return
     */
    public List<ActivityCategoryDTO> getCategoryList(){
        return activityCategoryService.getCategoryList();
    }

    /**
     * 获取活动详情
     * @param activityId
     * @return
     */
    public ActivityDTO getActivityInfo(Long activityId){
        return activityService.getActivityInfo(activityId);
    }

    /**
     * 获取活动小组列表
     * @param activityId
     * @return
     */
    public List<GroupDTO> getGroupList(Long activityId){
        return groupService.getGroupList(activityId);
    }

    /**
     * 保存报名信息
     * @param applyDTO
     * @return  返回活动信息
     */
    public ActivityDTO addApplyInfo(ApplyDTO applyDTO){
        Long customerId = RequestCache.userId();
//        Long customerId = 11L;
        applyDTO.setCustomerId(customerId);         //设置报名人Id
        //查询活动详情
        ActivityDTO activityInfo = activityService.getActivity(applyDTO.getActivityId().longValue());
        if(!applyDTO.getActivityCode().equals(activityInfo.getActivityCode())){
            throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID,"活动报名码不正确");
        }
        Integer groupNumber = activityInfo.getGroupNumber();
        //判断活动人数是否已满
        List<ApplyDTO> applyDTOS = applyService.selectApplyList(applyDTO);
        if (applyDTOS != null){
            applyDTOS.forEach(applyDTOInfo->{
                if(applyDTO.getCustomerId() == applyDTOInfo.getCustomerId()){
                    throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID,"您已经报名了此活动");
                }
            });
        }
        //报名人数大于等于小组人数的时候 且小组人数不为无限制的时候,无法继续报名
        if (applyDTOS != null && applyDTOS.size() >= groupNumber && groupNumber != 0 && activityInfo.getIsLimit() != 2) {
            throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID,"该小组报名人数已满");
        }else{
           applyService.addApplyInfo(applyDTO);
        }
        return activityInfo;
    }


    /**
     * 获取成绩详情
     * @param activityId
     * @return
     */
    public ScoreDTO getScoreInfo(Long activityId){

        //先判断活动是否已经开始或者结束,否则没有成绩信息
        ActivityDTO activity = activityService.getActivity(activityId);
        long nowTime = System.currentTimeMillis();
        long startTime = activity.getActivityStartTime();
        if (startTime > nowTime) {
            throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID, "活动还未开始,没有成绩信息");
        } else {
            ScoreDTO scoreInfo = scoreService.getScoreInfo(activityId);
            //判断是否有发布成绩信息
            if(scoreInfo == null || scoreInfo.getId() == null){
                throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID, "活动还未发布成绩信息");
            }else{
                return scoreInfo;
            }
        }
    }

    /**
     * 获取我报名的活动
     * @return
     */
    public PageDTO<ActivityDTO> getApplyActivity(PaginationDTO<ActivityVO, VoidEnum> paginationVO){
        Long customerId = RequestCache.userId();
//        Long customerId = 11L;
        PaginationDTO<ActivityDTO, VoidEnum> paginationDTO = ObjectCopys.transferPagination(paginationVO, ActivityDTO.class);
        paginationDTO.getCondition().setCustomerId(customerId);     //设置查询条件
        PageDTO<ActivityDTO> list = activityService.getApplyActivity(paginationDTO);
        return list;
    }

    /**
     * 查询收藏的活动
     * @param paginationVO
     * @return
     */
    public PageDTO<ActivityDTO> getCollectionActivity(PaginationDTO<ActivityVO, VoidEnum> paginationVO){
        Long customerId = RequestCache.userId();
//        Long customerId = 11L;

        PaginationDTO<ActivityDTO, VoidEnum> paginationDTO = ObjectCopys.transferPagination(paginationVO, ActivityDTO.class);
        paginationDTO.getCondition().setCustomerId(customerId);     //设置查询条件
        PageDTO<ActivityDTO> list = activityService.getCollectionActivity(paginationDTO);
        return list;
    }

    /**
     * 查询是否已经报名该活动
     * @param activityId
     * @return 1-已报名,0-未报名
     */
    public Map<String,Object> getHasApplyAndCollection(Long activityId){
        Long customerId = RequestCache.userId();
//        Long customerId = 11L;
        Integer hasApply = activityService.getHasApply(ApplyDTO.builder().activityId(activityId).customerId(customerId).build());
        Integer hasCollection = collectionService.getHasCollection(CollectionDTO.builder().activityId(activityId).customerId(customerId).build());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hasApply",hasApply);
        map.put("hasCollection",hasCollection);
        return map;
    }

    /**
     * 用户收藏活动
     * @param activityId
     */
    public void collectionActivity(Long activityId) {
        Long customerId = RequestCache.userId();
//        Long customerId = 11L;
        CollectionDTO collectionDTO = CollectionDTO.builder().activityId(activityId).customerId(customerId).build();
        Integer hasCollection = collectionService.getHasCollection(collectionDTO);
        if(hasCollection == 0){
            collectionService.collectionActivity(collectionDTO);
        }
    }

    /**
     * 用户取消收藏
     * @param collectionId
     */
    public void cancelCollection(Long collectionId) {
        collectionService.cancelCollection(collectionId);
    }
}
