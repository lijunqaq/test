package com.lj.excel.utils;



import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/12/11
 */

@Slf4j
public class ExcelUtil {

//    public static void exportExcel(HttpServletResponse response, ExcelData data) {
//        log.info("导出解析开始，fileName:{}",data.getFileName());
//        try {
//            //实例化HSSFWorkbook
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            //创建一个Excel表单，参数为sheet的名字
//            HSSFSheet sheet = workbook.createSheet("sheet");
//            //设置表头
//            setTitle(workbook, sheet, data.getHead());
//            //设置单元格并赋值
//            setData(sheet, data.getData());
//            //设置浏览器下载
//            setBrowser(response, workbook, data.getFileName());
//            log.info("导出解析成功!");
//        } catch (Exception e) {
//            log.info("导出解析失败!");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 方法名：setTitle
//     * 功能：设置表头
//     * 描述：
//     * 创建人：typ
//     * 创建时间：2018/10/19 10:20
//     * 修改人：
//     * 修改描述：
//     * 修改时间：
//     */
//    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
//        try {
//            HSSFRow row = sheet.createRow(0);
//            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
//            for (int i = 0; i <= str.length; i++) {
//                sheet.setColumnWidth(i, 15 * 256);
//            }
//            //设置为居中加粗,格式化时间格式
//            HSSFCellStyle style = workbook.createCellStyle();
//            HSSFFont font = workbook.createFont();
//            font.setBold(true);
//            style.setFont(font);
//            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
//            //创建表头名称
//            HSSFCell cell;
//            for (int j = 0; j < str.length; j++) {
//                cell = row.createCell(j);
//                cell.setCellValue(str[j]);
//                cell.setCellStyle(style);
//            }
//        } catch (Exception e) {
//            log.info("导出时设置表头失败！");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 方法名：setData
//     * 功能：表格赋值
//     * 描述：
//     * 创建人：typ
//     * 创建时间：2018/10/19 16:11
//     * 修改人：
//     * 修改描述：
//     * 修改时间：
//     */
//    private static void setData(HSSFSheet sheet, List<String[]> data) {
//        try{
//            int rowNum = 1;
//            for (int i = 0; i < data.size(); i++) {
//                HSSFRow row = sheet.createRow(rowNum);
//                for (int j = 0; j < data.get(i).length; j++) {
//                    row.createCell(j).setCellValue(data.get(i)[j]);
//                }
//                rowNum++;
//            }
//            log.info("表格赋值成功！");
//        }catch (Exception e){
//            log.info("表格赋值失败！");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 方法名：setBrowser
//     * 功能：使用浏览器下载
//     * 描述：
//     * 创建人：typ
//     * 创建时间：2018/10/19 16:20
//     * 修改人：
//     * 修改描述：
//     * 修改时间：
//     */
//    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
//        try {
//            //清空response
//            response.reset();
//            //设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
//            OutputStream os = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/vnd.ms-excel;charset=gb2312");
//            //将excel写入到输出流中
//            workbook.write(os);
//            os.flush();
//            os.close();
//            log.info("设置浏览器下载成功！");
//        } catch (Exception e) {
//            log.info("设置浏览器下载失败！");
//            e.printStackTrace();
//        }
//
//    }
//

    /**
     * 方法名：importExcel
     * 功能：导入
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 11:45
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    public static List<Object[]> importExcel(String fileName) {
        log.info("导入解析开始，fileName:{}",fileName);
        try {
            List<Object[]> list = new ArrayList<>();
            InputStream inputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                //过滤表头行
                if (i == 0) {
                    continue;
                }
                //获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {
                    if (cell.getCellType()==0) {
                        objects[index] = (int) cell.getNumericCellValue();
                    }
                    if (cell.getCellType()==1) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellType()==4) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellType()==5) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            log.info("导入文件解析成功！");
            return list;
        }catch (Exception e){
            log.info("导入文件解析失败！");
            e.printStackTrace();
        }
        return null;
    }

//    //测试导入
//    public static void main(String[] args) {
//        try {
//            String fileName = "d:/麻栗坡县单位.xlsx";
//            List<Object[]> list = importExcel(fileName);
//            List<SysApiRoleAccount> result = new ArrayList<>();
//
//            for (int i = 0; i < list.size(); i++) {
//                SysApiRoleAccount user = new SysApiRoleAccount();
////                user.setId((Integer) list.get(i)[0]);
//                user.setAccountId(19);
//                user.setUnitId((String) list.get(i)[1]);
//                user.setUnitName((String) list.get(i)[2]);
//                user.setValid(1);
//                result.add(user);
//            }
//            System.out.println(result.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
