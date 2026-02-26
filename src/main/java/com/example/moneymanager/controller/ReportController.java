//package com.example.moneymanager.controller;
//
//import com.example.moneymanager.dto.TransactionDto;
//import com.example.moneymanager.service.ExcelService;
//import com.example.moneymanager.service.EmailService;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/report")
//public class ReportController {
//
//    private final ExcelService excelService;
//    private final EmailService emailService;
//    private final TransactionService transactionService;
//
//    public ReportController(
//            ExcelService excelService,
//            EmailService emailService,
//            TransactionService transactionService) {
//
//        this.excelService = excelService;
//        this.emailService = emailService;
//        this.transactionService = transactionService;
//    }
//
//    // =========================
//    // DOWNLOAD EXCEL
//    // =========================
//    @GetMapping("/excel")
//    public ResponseEntity<byte[]> downloadExcel() throws Exception {
//
//        List<TransactionDto> data =
//                transactionService.getAllTransactions();
//
//        byte[] file = excelService.generateExcel(data);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=transactions.xlsx")
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(file);
//    }
//
//    // =========================
//    // EMAIL EXCEL
//    // =========================
//    @PostMapping("/email")
//    public ResponseEntity<?> sendEmail(
//            @RequestParam String email) throws Exception {
//
//        List<TransactionDto> data =
//                transactionService.getAllTransactions();
//
//        byte[] file = excelService.generateExcel(data);
//
//        emailService.sendExcel(email, file);
//
//        return ResponseEntity.ok("Email sent successfully");
//    }
//}
