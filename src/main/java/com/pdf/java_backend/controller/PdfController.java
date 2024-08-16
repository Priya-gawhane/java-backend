package com.pdf.java_backend.controller;

import com.pdf.java_backend.service.PdfService;
import org.apache.tomcat.util.buf.UEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private static final Logger log = LoggerFactory.getLogger(PdfController.class);
    @Autowired
    private PdfService pdfService;

    @PostMapping("/compress")
    public ResponseEntity<byte[]> compressPdf(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] compressedPdf = pdfService.compressPdf(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=compressed.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(compressedPdf);
    }

    @PutMapping("/edit")
    public ResponseEntity<byte[]> editPdf(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] editedPdf = pdfService.editPdf(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=editedfile.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(editedPdf);
    }

    @PostMapping("/merge")
    public ResponseEntity<byte[]> mergePdf(@RequestParam("file") List<MultipartFile> file) throws IOException {
        byte[] mergePdf = pdfService.mergePdfs(file);
        return ResponseEntity.ok().body(mergePdf);
    }

    @PutMapping("convert-to-word")
    public ResponseEntity<byte[]> convertToWord(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] convertToWord = pdfService.convertPdfToWord(file);
        return ResponseEntity.ok().body(convertToWord);
    }
}
