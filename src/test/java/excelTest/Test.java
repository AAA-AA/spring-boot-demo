package excelTest;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import ren.com.cn.domain.entity.User;
import ren.com.cn.exception.BizException;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/7/21 15:53
 * Email: renhongqiang1397@gmail.com
 */
public class Test {

    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("nanali");
        user1.setId(1L);
        user1.setAge(20);

        User user2 = new User();
        user2.setName("nanali");
        user2.setId(1L);
        user2.setAge(20);

        List<User> list = Lists.newArrayList(user1, user2);

    }

    public static void exportProjectExcel(List<User> projectList, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new BizException("文件不存在！");
            }

            OutputStream fos = new FileOutputStream(file);
            // 1、创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 1.1、创建合并单元格对象
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0,
                    12);// 起始行号，结束行号，起始列号，结束列号
            CellRangeAddress cellRangeAddress1 = new CellRangeAddress(1, 1, 0,
                    12);// 起始行号，结束行号，起始列号，结束列号
            // 1.2、头标题样式
            HSSFCellStyle style1 = workbook.createCellStyle();
            style1.setAlignment((short) 18);

            HSSFCellStyle style2 = workbook.createCellStyle();
            style1.setAlignment((short) 12);

            // 1.3、列标题样式
            // 2、创建工作表
            HSSFSheet sheet = workbook.createSheet("年度新增人防工程项目汇总表");
            // 2.1、加载合并单元格对象
            sheet.addMergedRegion(cellRangeAddress);
            sheet.addMergedRegion(cellRangeAddress1);
            // 设置默认列宽
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeightInPoints(22);
            // 3、创建行
            // 3.1、创建头标题行；并且设置头标题
            HSSFRow row1 = sheet.createRow(0);
            row1.setHeightInPoints(50);
            HSSFCell cell1 = row1.createCell(0);
            // 加载单元格样式
            cell1.setCellStyle(style1);
            cell1.setCellValue("年度新增人防工程项目汇总表");
            // 3.1、创建副标题行；并且设置
            HSSFRow row2 = sheet.createRow(1);
            row2.setHeightInPoints(20);
            HSSFCell cell2 = row2.createCell(0);
            // 加载单元格样式
            style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平居中
            cell2.setCellStyle(style2);
            cell2.setCellValue("填报单位：");
            // 3.2、创建列标题行；并且设置列标题
            HSSFRow row3 = sheet.createRow(2);
            String[] titles = {"序号", "项目名称", "建设单位", "建设地点", "设计单位",
                    "监理单位", "审图单位", "防护设备单位", "防化设备单位", "建筑面积(┫)",
                    "使用面积(┫)", "战时用途", "防护等级"};
            for (int i = 0; i < titles.length; i++) {
                HSSFCell cell5 = row3.createCell(i);
                // 加载单元格样式
                cell5.setCellStyle(style2);
                cell5.setCellValue(titles[i]);
            }
            // 4、操作单元格；将巡查记录列表写入excel
            if ((!"".equals(projectList) && projectList != null)) {
                if (!projectList.isEmpty()) {
                    for (int j = 0; j < projectList.size(); j++) {
                        HSSFRow row = sheet.createRow(j + 3);
                        cell2.setCellValue("填报单位：" + projectList.get(0).getId());
                        int i = j + 1;
                        HSSFCell c1 = row.createCell(0);
                        c1.setCellValue(i);
                        HSSFCell c2 = row.createCell(1);
                        c2.setCellValue(projectList.get(j).getName());
                    }
                }
            }
            // 5、输出
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void exportProjectExcel(List<User> projectList,
                                          ServletOutputStream outputStream) {
        try {
            // 1、创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 1.1、创建合并单元格对象
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0,
                    12);// 起始行号，结束行号，起始列号，结束列号
            CellRangeAddress cellRangeAddress1 = new CellRangeAddress(1, 1, 0,
                    12);// 起始行号，结束行号，起始列号，结束列号
            // 1.2、头标题样式
            HSSFCellStyle style1 = workbook.createCellStyle();
            style1.setAlignment((short) 18);

            HSSFCellStyle style2 = workbook.createCellStyle();
            style1.setAlignment((short) 12);

            // 1.3、列标题样式
            // 2、创建工作表
            HSSFSheet sheet = workbook.createSheet("年度新增人防工程项目汇总表");
            // 2.1、加载合并单元格对象
            sheet.addMergedRegion(cellRangeAddress);
            sheet.addMergedRegion(cellRangeAddress1);
            // 设置默认列宽
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeightInPoints(22);
            // 3、创建行
            // 3.1、创建头标题行；并且设置头标题
            HSSFRow row1 = sheet.createRow(0);
            row1.setHeightInPoints(50);
            HSSFCell cell1 = row1.createCell(0);
            // 加载单元格样式
            cell1.setCellStyle(style1);
            cell1.setCellValue("年度新增人防工程项目汇总表");
            // 3.1、创建副标题行；并且设置
            HSSFRow row2 = sheet.createRow(1);
            row2.setHeightInPoints(20);
            HSSFCell cell2 = row2.createCell(0);
            // 加载单元格样式
            style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平居中
            cell2.setCellStyle(style2);
            cell2.setCellValue("填报单位：");
            // 3.2、创建列标题行；并且设置列标题
            HSSFRow row3 = sheet.createRow(2);
            String[] titles = {"序号", "项目名称", "建设单位", "建设地点", "设计单位",
                    "监理单位", "审图单位", "防护设备单位", "防化设备单位", "建筑面积(┫)",
                    "使用面积(┫)", "战时用途", "防护等级"};
            for (int i = 0; i < titles.length; i++) {
                HSSFCell cell5 = row3.createCell(i);
                // 加载单元格样式
                cell5.setCellStyle(style2);
                cell5.setCellValue(titles[i]);
            }
            // 4、操作单元格；将巡查记录列表写入excel
            if ((!"".equals(projectList) && projectList != null)) {
                if (!projectList.isEmpty()) {
                    for (int j = 0; j < projectList.size(); j++) {
                        HSSFRow row = sheet.createRow(j + 3);
                        cell2.setCellValue("填报单位：" + projectList.get(0).getId());
                        int i = j + 1;
                        HSSFCell c1 = row.createCell(0);
                        c1.setCellValue(i);
                        HSSFCell c2 = row.createCell(1);
                        c2.setCellValue(projectList.get(j).getName());
                    }
                }
            }
            // 5、输出
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
