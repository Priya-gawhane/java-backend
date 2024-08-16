package com.pdf.java_backend.utility;

import ch.qos.logback.core.rolling.helper.CompressionMode;
import com.itextpdf.kernel.pdf.*;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfUtils {

    // Compress PDF
    public static byte[] compressPdf(byte[] pdfBytes) throws IOException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            PdfReader reader = new PdfReader(inputStream);
            PdfWriter writer = new PdfWriter(outputStream, new WriterProperties().setCompressionLevel(CompressionConstants.BEST_COMPRESSION));
            PdfDocument pdfDoc = new PdfDocument(reader, writer);

            pdfDoc.close();
            return outputStream.toByteArray();
        }
    }

    // Edit PDF (e.g., adding a watermark or modifying text)
    public static byte[] editPdf(byte[] pdfBytes) throws IOException {
        PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();
        return outputStream.toByteArray();
    }

    // Merge PDFs
    public static byte[] mergePdfs(List<MultipartFile> files) throws IOException {
        PDDocument mergedDocument = new PDDocument();
        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        pdfMerger.setDestinationStream(new ByteArrayOutputStream());

        for (MultipartFile file : files) {
            PDDocument document = PDDocument.load(file.getInputStream());
            pdfMerger.appendDocument(mergedDocument, document);
            document.close();
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mergedDocument.save(outputStream);
        mergedDocument.close();
        return outputStream.toByteArray();
    }

    // Convert PDF to Word
    public static byte[] convertPdfToWord(byte[] pdfBytes) throws IOException {
        PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes));
        XWPFDocument wordDocument = new XWPFDocument();
        // Conversion logic here
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        wordDocument.write(outputStream);
        document.close();
        return outputStream.toByteArray();
    }
}

