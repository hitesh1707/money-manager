//package com.example.moneymanager.service;
//
//import com.example.moneymanager.dto.TransactionDto;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.stereotype.Service;
//import java.io.ByteArrayOutputStream;
//import java.util.List;
//
//@Service
//public class ExcelService {
//
//    public byte[] generateExcel(List<TransactionDto> transactions) throws Exception {
//
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Transactions");
//
//        Row header = sheet.createRow(0);
//        header.createCell(0).setCellValue("Date");
//        header.createCell(1).setCellValue("Category");
//        header.createCell(2).setCellValue("Type");
//        header.createCell(3).setCellValue("Amount");
//
//        int rowNum = 1;
//        for (TransactionDto tx : transactions) {
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(tx.getDate().toString());
//            row.createCell(1).setCellValue(tx.getCategoryName());
//            row.createCell(2).setCellValue(tx.getType());
//            row.createCell(3).setCellValue(tx.getAmount());
//        }
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        workbook.write(out);
//        workbook.close();
//
//        return out.toByteArray();
//    }
//}