package com.kingleadsw.ysm.odp.controller.activity;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.constants.ServerMsg;
import com.kingleadsw.ysm.dto.activity.ActivityCategoryDTO;
import com.kingleadsw.ysm.need.Need;
import com.kingleadsw.ysm.odp.facade.activity.CategoryFacade;
import com.kingleadsw.ysm.result.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "活动分类相关")
public class CategoryContorller extends BaseController {

    @Autowired
    private CategoryFacade categoryFacade;

    @ApiOperation(value = "分类列表", notes = "活动分类列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("category/list")
    public ResultVO<PageDTO<ActivityCategoryDTO>> getCategoryList(@RequestBody PaginationDTO<ActivityCategoryDTO, VoidEnum> paginationDTO){
        PageDTO<ActivityCategoryDTO> categoryList = categoryFacade.getCategoryList(paginationDTO);
        return success(categoryList);
    }

    @ApiOperation(value = "新增或者修改", notes = "通过分类Id区分是新增还是修改", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("category/save")
    @Need(token = true, repeat = true)
    public ResultVO<ActivityCategoryDTO> saveCategory(@RequestBody ActivityCategoryDTO activityCategoryDTO) {
        categoryFacade.editCategory(activityCategoryDTO);
        return success(activityCategoryDTO);
    }


    @ApiOperation(value = "删除分类", notes = "通过分类id删除分类", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @DeleteMapping("category/delById/{id}")
    public ResultVO<ActivityCategoryDTO> delCategory(@PathVariable("id") Long categoryId) {
        categoryFacade.delCategory(categoryId);
        return success();
    }

}
