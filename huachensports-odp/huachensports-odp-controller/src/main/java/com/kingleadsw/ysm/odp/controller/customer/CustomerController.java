package com.kingleadsw.ysm.odp.controller.customer;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.constants.ServerMsg;
import com.kingleadsw.ysm.dto.customer.CustomerDTO;
import com.kingleadsw.ysm.dto.user.UserDTO;
import com.kingleadsw.ysm.odp.facade.customer.CustomerFacade;
import com.kingleadsw.ysm.odp.facade.user.UserFacade;
import com.kingleadsw.ysm.odp.vo.user.LoginReqVO;
import com.kingleadsw.ysm.odp.vo.user.LoginVO;
import com.kingleadsw.ysm.result.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "微信用户相关操作")
@RestController
public class CustomerController extends BaseController{

	@Autowired
	private CustomerFacade customerFacade;

    @ApiOperation(value = "微信用户列表", notes = "获取微信用户列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("customer/list")
    public ResultVO<PageDTO<CustomerDTO>> getCustomerList(@RequestBody PaginationDTO<CustomerDTO, VoidEnum> paginationDTO) {
        PageDTO<CustomerDTO> pageDTO = customerFacade.getCustomerList(paginationDTO);
        return success(pageDTO);
    }
    
}
