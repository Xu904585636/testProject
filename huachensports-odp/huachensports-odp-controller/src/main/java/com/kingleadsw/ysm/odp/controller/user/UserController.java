package com.kingleadsw.ysm.odp.controller.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.constants.ServerMsg;
import com.kingleadsw.ysm.dto.user.UserDTO;
import com.kingleadsw.ysm.need.Need;
import com.kingleadsw.ysm.odp.facade.user.UserFacade;
import com.kingleadsw.ysm.odp.vo.user.LoginReqVO;
import com.kingleadsw.ysm.odp.vo.user.LoginVO;
import com.kingleadsw.ysm.odp.vo.user.UserVO;
import com.kingleadsw.ysm.result.ResultVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = "后台用户相关操作")
@RestController
public class UserController  extends BaseController{

	@Autowired
	private UserFacade userFacade;
	
    @ApiOperation(value = "用户登录", notes = "用户登录", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("user/login")
    public ResultVO<LoginVO> login(@RequestBody @Validated LoginReqVO loginDTO) {
        LoginVO token = userFacade.login(loginDTO);
        return success(token);
    }

    @ApiOperation(value = "用户列表", notes = "获取用户列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("user/list")
    public ResultVO<PageDTO<UserDTO>> userList(@RequestBody PaginationDTO<UserDTO, VoidEnum> paginationDTO) {
        PageDTO<UserDTO> pageDTO = userFacade.getUserList(paginationDTO);
        return success(pageDTO);
    }
    
    
    
    @Need(token = true,repeat=true)
    @ApiOperation(value = "重置密码", notes = "重置密码", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("user/resetPwd")
    public ResultVO<Void> resetPwd(@RequestBody @Validated UserVO user){
    	userFacade.resetPwd(user);
    	return success();
    }

    
}
