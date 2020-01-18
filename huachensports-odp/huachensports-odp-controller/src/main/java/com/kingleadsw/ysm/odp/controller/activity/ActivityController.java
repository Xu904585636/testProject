package com.kingleadsw.ysm.odp.controller.activity;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.constants.ServerMsg;

import com.kingleadsw.ysm.dto.activity.*;
import com.kingleadsw.ysm.need.Need;
import com.kingleadsw.ysm.odp.facade.activity.ActivityFacade;
import com.kingleadsw.ysm.odp.vo.activity.ActivityVO;
import com.kingleadsw.ysm.result.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "活动相关")
public class ActivityController extends BaseController {

    @Autowired
    ActivityFacade activityFacade;


    @ApiOperation(value = "活动列表", notes = "首页活动列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/listPage")
    @Need
    public ResultVO<PageDTO<ActivityDTO>> getByPage(@RequestBody PaginationDTO<ActivityVO, VoidEnum> paginationDTO) {
        PageDTO<ActivityDTO> pageDTO = activityFacade.getByActivityPage(paginationDTO);
        return success(pageDTO);
    }

    @ApiOperation(value = "活动分类列表", notes = "活动分类列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    public ResultVO<ActivityVO> getActivityInfo(@RequestBody Long activityId) {
        ActivityVO activityVO = activityFacade.getActivityInfo(activityId);
        return success(activityVO);
    }

    @ApiOperation(value = "活动新增或者修改", notes = "通过活动Id区分是新增活动还是修改", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/save")
    @Need(token = true,repeat =true)
    public ResultVO<ActivityVO> saveActivity(@RequestBody ActivityVO activityVO) {
        activityFacade.editActivity(activityVO);
        return success(activityVO);
    }

    @ApiOperation(value = "活动删除", notes = "通过活动Id删除活动", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @DeleteMapping("activity/delById/{id}")
    public ResultVO<ActivityVO> saveActivity(@PathVariable("id") Long activityId) {
        activityFacade.delActivity(activityId);
        return success();
    }

    @ApiOperation(value = "活动成绩", notes = "通过活动Id获取成绩", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/score")
    public ResultVO<ScoreDTO> getScore(@RequestBody Long activityId) {
        ScoreDTO scoreInfo = activityFacade.getScoreInfo(activityId);
        return success(scoreInfo);
    }

    @ApiOperation(value = "保存活动成绩", notes = "通过是否存在成绩Id判断新增还是修改", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/saveScore")
    @Need(token = true, repeat = true)
    public ResultVO<ScoreDTO> saveScore(@RequestBody ScoreDTO scoreDTO) {
        activityFacade.editScore(scoreDTO);
        return success();
    }

    @ApiOperation(value = "所有活动列表(带小组信息)", notes = "所有活动列表(带小组信息,查询报名信息使用)", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("activity/allList")
    public ResultVO<List<ActivityDTO>> getAllList() {
        List<ActivityDTO> list = activityFacade.getAllList();
        return success(list);
    }
}
