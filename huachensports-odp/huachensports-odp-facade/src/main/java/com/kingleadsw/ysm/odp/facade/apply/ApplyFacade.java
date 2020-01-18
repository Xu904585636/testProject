package com.kingleadsw.ysm.odp.facade.apply;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.export.ExcelExportServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Lists;
import com.kingleadsw.ysm.api.activity.IApplyService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dto.activity.ApplyDTO;
import com.kingleadsw.ysm.odp.vo.activity.ApplyLogExcelVO;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.utils.Dates;
import com.kingleadsw.ysm.utils.ObjectCopys;

@Service
public class ApplyFacade {

    @Autowired
    private IApplyService applyService;




    /**
     * 获取报名列表
     * @param paginationVO
     * @return
     */
    public PageDTO<ApplyDTO> getApplyList(PaginationDTO<ApplyDTO, VoidEnum> paginationVO) {
        return applyService.getApplyList(paginationVO);
    }

    public  Workbook exportExcels(List<Map<String, Object>> list, ExcelType type) {
        Workbook workbook;
        if (ExcelType.HSSF.equals(type)) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        for (Map<String, Object> map : list) {
            ExcelExportServer server = new ExcelExportServer();
            ExportParams params = (ExportParams) map.get("params");
            Class<?> entry = (Class<?>) map.get("entity");
            Collection<?> data = (Collection<?>) map.get("data");
            server.createSheet(workbook, params,entry ,data);
        }
        return workbook;
    }

    List<ApplyLogExcelVO> getVaule(){
        PaginationDTO<ApplyDTO, VoidEnum> pagination=new PaginationDTO<>();
        pagination.setCurrent(1);
        pagination.setSize(10000);
        PageDTO<ApplyDTO> page=this.getApplyList(pagination);
        List<ApplyLogExcelVO> logs=null;
        if(page==null||Asserts.isNull(page.getRecords())) {
        	logs = new ArrayList<ApplyLogExcelVO>();
        }else {
        	List<ApplyDTO> dtos=page.getRecords();
        	logs=Lists.newArrayList();
        	for(ApplyDTO dto:dtos) {
        		ApplyLogExcelVO vo= ObjectCopys.maping(dto, ApplyLogExcelVO.class);
        		vo.setCreateTimeStr(Dates.format(dto.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        		logs.add(vo);
        	}
        }
        return  logs;
    }

    private Map<String, Object> getSheetMap() {
        Map<String, Object> map1 = new LinkedHashMap<String, Object>();
        List<ApplyLogExcelVO> list = getVaule();//导出记录
        map1.put(NormalExcelConstants.CLASS, ApplyLogExcelVO.class);//列名定义
        map1.put(NormalExcelConstants.FILE_NAME, "用户导出测试");//文件名称
        ExportParams ep = new ExportParams("报名信息", "记录" + (10 * Math.random()));//表头，及sheet名称
       // ep.setExclusions(new String[]{"年龄"});// 这里填替换后的
     
        map1.put(NormalExcelConstants.PARAMS, ep);
        map1.put(NormalExcelConstants.DATA_LIST, list);
        return map1;
    }

    /**
     * 模板导出
     * @param
     */
    public void exportExcel(HttpServletResponse resp, HttpServletRequest req) throws UnsupportedEncodingException {

        Map<String, Object> map1 = getSheetMap();
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
        list1.add(map1);
        Workbook workbook = exportExcels(list1, ExcelType.HSSF);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/x-download");
        String filedisplay = "applyLog.xls";
        filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
        resp.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);

        try {
            OutputStream out = resp.getOutputStream();
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    
    

}
