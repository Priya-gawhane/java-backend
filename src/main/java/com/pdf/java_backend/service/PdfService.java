package com.pdf.java_backend.service;

import com.pdf.java_backend.utility.PdfUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PdfService {
    public byte[] compressPdf(MultipartFile file) throws IOException {
        return PdfUtils.compressPdf(file.getBytes());
    }

    public byte[] editPdf(MultipartFile file) throws IOException {
        return PdfUtils.editPdf(file.getBytes());
    }

    public byte[] mergePdfs(List<MultipartFile> files) throws IOException {
        return PdfUtils.mergePdfs(files);
    }

    public byte[] convertPdfToWord(MultipartFile file) throws IOException {
        return PdfUtils.convertPdfToWord(file.getBytes());
    }
}
