package com.kingleadsw.ysm.odp.controller.banner;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.constants.ServerMsg;
import com.kingleadsw.ysm.dto.banner.BannerDTO;
import com.kingleadsw.ysm.need.Need;
import com.kingleadsw.ysm.odp.facade.banner.BannerFacade;
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
@Api(tags = "轮播图相关")
public class BannerContorller extends BaseController {

    @Autowired
    private BannerFacade bannerFacade;

    @ApiOperation(value = "轮播图列表", notes = "轮播图列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("banner/list")
    public ResultVO<PageDTO<BannerDTO>> getBannerList(@RequestBody PaginationDTO<BannerDTO, VoidEnum> paginationDTO){
        PageDTO<BannerDTO> bannerList = bannerFacade.getBannerList(paginationDTO);
        return success(bannerList);
    }

    @Need(token = true, repeat = true)
    @ApiOperation(value = "新增或者修改", notes = "通过轮播图Id区分是新增还是修改", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("banner/save")
    public ResultVO<BannerDTO> saveBanner(@RequestBody BannerDTO bannerDTO) {
        bannerFacade.editBanner(bannerDTO);
        return success(bannerDTO);
    }


    @ApiOperation(value = "删除分类", notes = "通过分类id删除分类", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @DeleteMapping("banner/delById/{id}")
    public ResultVO<BannerDTO> delCategory(@PathVariable("id") Long categoryId) {
        bannerFacade.delBanner(categoryId);
        return success();
    }

}
