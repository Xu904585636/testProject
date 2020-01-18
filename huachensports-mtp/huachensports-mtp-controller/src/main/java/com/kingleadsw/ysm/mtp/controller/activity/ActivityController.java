package com.kingleadsw.ysm.mtp.controller.activity;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.constants.ServerMsg;
import com.kingleadsw.ysm.dto.activity.*;
import com.kingleadsw.ysm.mtp.facade.activity.ActivityFacade;
import com.kingleadsw.ysm.mtp.vo.activity.ActivityVO;
import com.kingleadsw.ysm.need.Need;
import com.kingleadsw.ysm.result.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "活动相关")
public class ActivityController extends BaseController {

    @Autowired
    ActivityFacade activityFacade;


    @ApiOperation(value = "首页活动列表", notes = "首页活动列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/listPage")
    public ResultVO<PageDTO<ActivityDTO>> getByPage(@RequestBody PaginationDTO<ActivityVO, VoidEnum> paginationDTO) {
        PageDTO<ActivityDTO> pageDTO = activityFacade.getByActivityPage(paginationDTO);
        return success(pageDTO);
    }

    @ApiOperation(value = "活动分类列表", notes = "活动列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @GetMapping("activity/categoryList")
    public ResultVO<List<ActivityCategoryDTO>> getCategoryList() {
        List<ActivityCategoryDTO> list = activityFacade.getCategoryList();
        return success(list);
    }

    @ApiOperation(value = "活动详情", notes = "通过活动Id获取活动详情", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/activityInfo")
    public ResultVO<ActivityDTO> getActivityInfo(@RequestBody Long activityId) {
        ActivityDTO activityDTO = activityFacade.getActivityInfo(activityId);
        return success(activityDTO);
    }

    @ApiOperation(value = "活动分组列表", notes = "通过活动Id获取活动分组列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/groupList")
    public ResultVO<List<GroupDTO>> getGroupList(@RequestBody Long activityId) {
        List<GroupDTO> groupList = activityFacade.getGroupList(activityId);
        return success(groupList);
    }

    @ApiOperation(value = "活动报名", notes = "活动报名", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/addApplyInfo")
    @Need(token = true, repeat = true)
    public ResultVO<ActivityDTO> addApplyInfo(@RequestBody @Validated ApplyDTO applyDTO) {
        ActivityDTO activityDTO = activityFacade.addApplyInfo(applyDTO);
        return success(activityDTO);
    }

    @ApiOperation(value = "活动成绩", notes = "活动成绩", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/scoreInfo")
    public ResultVO<ScoreDTO> addApplyInfo(@RequestBody Long activityId) {
        ScoreDTO scoreDTO = activityFacade.getScoreInfo(activityId);
        return success(scoreDTO);
    }

    @ApiOperation(value = "已经报名的活动列表", notes = "已经报名的活动列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/applyList")
    @Need(token = true, repeat = true)
    public ResultVO<PageDTO<ActivityDTO>> getApplyActivity(@RequestBody PaginationDTO<ActivityVO, VoidEnum> paginationVO) {
        PageDTO<ActivityDTO> pageDTO = activityFacade.getApplyActivity(paginationVO);
        return success(pageDTO);
    }

    @ApiOperation(value = "收藏的活动列表", notes = "收藏的活动列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/collectionList")
    @Need(token = true, repeat = true)
    public ResultVO<PageDTO<ActivityDTO>> getCollectionActivity(@RequestBody PaginationDTO<ActivityVO, VoidEnum> paginationVO) {
        PageDTO<ActivityDTO> pageDTO = activityFacade.getCollectionActivity(paginationVO);
        return success(pageDTO);
    }

    @ApiOperation(value = "是否报名或收藏活动", notes = "是否报名或收藏", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/hasApplyOrCollection")
    @Need(token = true, repeat = true)
    public ResultVO<Map<String,Object>> hasApplyAndCollection(@RequestBody Long activityId) {
        Map<String, Object> map = activityFacade.getHasApplyAndCollection(activityId);
        return success(map);
    }

    @ApiOperation(value = "收藏活动", notes = "用户收藏该活动", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/collection")
    @Need(token = true, repeat = true)
    public ResultVO<Integer> collectionActivity(@RequestBody Long activityId) {
        activityFacade.collectionActivity(activityId);
        return success();
    }

    @ApiOperation(value = "取消收藏活动", notes = "用户取消收藏该活动", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/cancelCollection")
    public ResultVO<Integer> cancelCollection(@RequestBody Long collectionId) {
        activityFacade.cancelCollection(collectionId);
        return success();
    }
}
