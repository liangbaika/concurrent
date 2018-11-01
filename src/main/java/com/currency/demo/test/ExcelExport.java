package com.currency.demo.test;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: LQ
 * @Date: 2018/11/1 21:26
 * @Description:excel导出演示  首行统计数据 后边才是列表数据 为了演示 就直接硬编码了哈
 */
public class ExcelExport {


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        consExcel();
        long end = System.currentTimeMillis();
        System.out.println(" cost : "+(end-start));

    }

    private static void consExcel() {
        List<Person> peoples = init();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生表");
        HSSFRow row = sheet.createRow(2);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFRow row0 = sheet.createRow(0);
        HSSFCell row0Cell0 = row0.createCell(0);
        HSSFCell row0Cell2 = row0.createCell(2);
        row0Cell0.setCellValue("idSum");
        row0Cell0.setCellStyle(style);
        row0Cell2.setCellValue("ageSum");
        row0Cell2.setCellStyle(style);

        int idsum = peoples.stream().mapToInt(Person::getId).sum();
        int agesum = peoples.stream().mapToInt(Person::getAge).sum();

        HSSFRow row1 = sheet.createRow(1);
        HSSFCell row1Cell0 = row1.createCell(0);
        HSSFCell row1Cell2 = row1.createCell(2);

        row1Cell0.setCellValue(idsum);
        row1Cell0.setCellStyle(style);
        row1Cell2.setCellValue(agesum);
        row1Cell2.setCellStyle(style);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("id");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("name");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("age");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("desc");
        cell.setCellStyle(style);

        for (int i = 0; i < peoples.size(); i++) {
            row = sheet.createRow(i + 3);
            Person person = peoples.get(i);
            row.createCell(0).setCellValue(person.getId());
            row.createCell(1).setCellValue(person.getName());
            HSSFCell cell1 = row.createCell(2);
            cell1.setCellStyle(style);
            cell1.setCellValue(person.getAge());
            row.createCell(3).setCellValue(person.getDesc());
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("E:/students611.xls");
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static List init() {
        List<Person> linkedList = Lists.newLinkedList();
//        List<Person> linkedList = Lists.newArrayList();
        for (int i = 0; i < 50000; i++) {
            Person person = new Person(i, "zhangsan" + i, 1, "desc" + i);
            linkedList.add(person);
        }
        return linkedList;
    }
}
