package com.kingleadsw.ysm.odp.controller.apply;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.constants.ServerMsg;
import com.kingleadsw.ysm.dto.activity.ApplyDTO;
import com.kingleadsw.ysm.odp.facade.apply.ApplyFacade;
import com.kingleadsw.ysm.result.ResultVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "活动报名相关")
public class ApplyContorller extends BaseController {

    @Autowired
    private ApplyFacade applyFacade;

    @ApiOperation(value = "报名列表", notes = "活动报名列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("apply/list")
    public ResultVO<PageDTO<ApplyDTO>> getApplyList(@RequestBody PaginationDTO<ApplyDTO, VoidEnum> paginationDTO){
        PageDTO<ApplyDTO> applyList = applyFacade.getApplyList(paginationDTO);
        return success(applyList);
    }
    
    @ApiOperation(value = "Excel导出", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("apply/export")
    public void exportExcel(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
    	applyFacade.exportExcel(response,request);
    }

}
