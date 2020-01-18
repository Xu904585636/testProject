package com.kingleadsw.ysm.mtp.controller.banner;

import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.caches.RedisUtil;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.constants.ServerMsg;
import com.kingleadsw.ysm.dto.banner.BannerDTO;
import com.kingleadsw.ysm.dto.customer.CustomerDTO;
import com.kingleadsw.ysm.mtp.facade.banner.BannerFacade;
import com.kingleadsw.ysm.need.token.Tokens;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.result.ResultVO;
import com.kingleadsw.ysm.service.customer.CustomerService;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.wechat.WechatUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
@Api(tags = "轮播图相关")
public class BannerController extends BaseController {

    @Autowired
    BannerFacade bannerFacade;

    @ApiOperation(value = "轮播图列表", notes = "轮播图列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @GetMapping("banner/list")
    public ResultVO<List<BannerDTO>> getBannerList() {
        List<BannerDTO> list = bannerFacade.getBannerList();
        return success(list);
    }


}
