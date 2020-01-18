package com.kingleadsw.ysm.odp.controller.system;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.constants.ServerMsg;
import com.kingleadsw.ysm.odp.facade.system.FileFacade;
import com.kingleadsw.ysm.odp.vo.FilesVO;
import com.kingleadsw.ysm.result.ResultVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 *
 */
@Api("文件上传")
@RestController
public class FileuploadController extends BaseController {

    @Autowired
    FileFacade fileuploadFacade;

    @ApiOperation(value = "上传图片", notes = "上传图片", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("upload/img")
    public ResultVO<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        return success(fileuploadFacade.updateFile(file));
    }

    @ApiOperation(value = "上传Base64", notes = "上传Base64", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("upload/img/str")
    public ResultVO<String> uploadImage(@RequestBody FilesVO file) throws IOException {

        return success(fileuploadFacade.updateBaseStr(file.getFile()));
    }

    @ApiOperation(value = "批量上传图片/文件/视频 返回批量上传列表", notes = "上传图片/文件/视频 返回批量上传列表", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = ServerCode.OK.OK_DEFAULT, message = ServerMsg.OK.OK_DEFAULT),
            @ApiResponse(code = ServerCode.BadRequest.BAD_REQUEST_INVALID, message = ServerMsg.BadRequest.BAD_REQUEST_INVALID)})
    @PostMapping("upload/files")
    public ResultVO< List<String> > uploadfile(@RequestParam("file") List<MultipartFile> file) throws IOException {

        return success(fileuploadFacade.updateFile(file));
    }
}
