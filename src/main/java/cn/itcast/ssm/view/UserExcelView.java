package cn.itcast.ssm.view;

import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.utils.Constants;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by zm on 2017/8/8.
 */
public class UserExcelView extends AbstractExcelView {
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 从model对象中获取userList
        List<User> userList = (List<User>) model.get("userList");
        // 创建Excel的sheet
        HSSFSheet sheet = workbook.createSheet("用户列表");

        if (userList!=null&&userList.size()!=0){
            // 创建标题行
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("用户名");
            header.createCell(2).setCellValue("姓名");
            header.createCell(3).setCellValue("年龄");
            header.createCell(4).setCellValue("性别");
            header.createCell(5).setCellValue("出生日期");
            header.createCell(6).setCellValue("创建时间");
            header.createCell(7).setCellValue("更新时间");

            for (User u :userList) {
                int lastRowNum = sheet.getLastRowNum();
                HSSFRow row = sheet.createRow(lastRowNum+1);
                row.createCell(0).setCellValue(u.getId());
                row.createCell(1).setCellValue(u.getUserName());
                row.createCell(2).setCellValue(u.getName());
                row.createCell(3).setCellValue(u.getAge());
                String sexStr;
                if (u.getSex() == 1) {
                    sexStr = "男";
                } else if (u.getSex() == 2) {
                    sexStr = "女";
                } else {
                    sexStr = "未知";
                }
                row.createCell(4).setCellValue(sexStr);
                row.createCell(5).setCellValue(new DateTime(u.getBirthday()).toString(Constants.DATE));
                row.createCell(6).setCellValue(new DateTime(u.getCreated()).toString(Constants.DATE_TIME));
                row.createCell(7).setCellValue(new DateTime(u.getUpdated()).toString(Constants.DATE_TIME));

            }
            // 设置相应头信息，以附件形式下载并且指定文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(),"ISO-8859-1"));

        }

    }
}
