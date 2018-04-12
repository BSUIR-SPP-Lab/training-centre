package com.bsuir.trainingcenter.service.impl;


import com.bsuir.trainingcenter.entity.view.CertificateInfoView;
import com.bsuir.trainingcenter.service.CertificateInfoService;
import com.bsuir.trainingcenter.service.DocService;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.BaseFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    private CertificateInfoService certificateInfoService;


    @Override
    public Resource generatePdfCertificate(int id) {
        CertificateInfoView info = certificateInfoService.getCertificateInfo(id);
        if (info == null) {
            return null;
        }
        return createPdf((doc -> {
            try {
                PdfFont font = PdfFontFactory.createFont("src/main/resources/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                Paragraph paragraph = new Paragraph("Certificate")
                        .setItalic()
                        .setMarginBottom(20)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFont(font)
                        .setFontSize(40);
                doc.add(paragraph);

                paragraph = new Paragraph()
                        .setFont(font)
                        .setFontSize(20)
                        .add(String.format("Сертификат о том что %s %s прошел курсы %s c %s по %s", info.getFirstName(), info.getLastName(), info.getName(), info.getStart(), info.getEnd()));
                doc.add(paragraph);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    @Override
    public Resource generateXLSCertificate(int id) {
        CertificateInfoView info = certificateInfoService.getCertificateInfo(id);
        if (info == null) {
            return null;
        }
        return createXLS((sheet, style) -> {
            HSSFRow[] rows = new HSSFRow[5];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = sheet.createRow(i);
            }

            int i = 0;
            createCell(rows[i++], 0, "Имя", style);
            createCell(rows[i++], 0, "Фамилия", style);
            createCell(rows[i++], 0, "Курс", style);
            createCell(rows[i++], 0, "Начало", style);
            createCell(rows[i++], 0, "Конец", style);

            i = 0;
            createCell(rows[i++], 1, info.getFirstName(), style);
            createCell(rows[i++], 1, info.getLastName(), style);
            createCell(rows[i++], 1, info.getName(), style);
            createCell(rows[i++], 1, info.getStart(), style);
            createCell(rows[i++], 1, info.getEnd(), style);

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
        });
    }

    @Override
    public Resource generateCSVCertificate(int id) {
        CertificateInfoView info = new CertificateInfoView(1, "Влад", "Белых", "Java", "1", "2"); // certificateInfoService.getCertificateInfo(id);
        if (info == null) {
            return null;
        }
        return createCSV(()-> String.format("%s;%s;%s;%s;%s;",info.getFirstName(),info.getLastName(),info.getName(),info.getStart(),info.getEnd()));
    }

    public Resource createPdf(Consumer<Document> c) {

        Document document;
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(stream);
            PdfDocument pdf = new PdfDocument(writer);
            document = new Document(pdf, PageSize.A4);
            createPdfTemplate(document);
            c.accept(document);
            document.close();
            return new ByteArrayResource(stream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Resource createCSV(Supplier<String> c) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream();
             Writer writer = new OutputStreamWriter(stream)) {
            writer.append(c.get());
            writer.flush();
            return new ByteArrayResource(stream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public Resource createXLS(BiProcedure<HSSFSheet, CellStyle> p) {

        try (HSSFWorkbook workbook = new HSSFWorkbook();
             ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

            HSSFSheet sheet = workbook.createSheet("FirstSheet");
            CellStyle style = createStyle(workbook, sheet);
            p.accept(sheet, style);
            workbook.write(stream);
            return new ByteArrayResource(stream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void createPdfTemplate(Document document) {
        try {
            Image logo = new Image(ImageDataFactory.create("src/main/resources/logo.jpg"));
            Paragraph paragraph = new Paragraph().add(logo);
            document.add(paragraph);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private CellStyle createStyle(HSSFWorkbook workbook, HSSFSheet sheet) {

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("Times New Roman");
        style.setFont(font);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        return style;
    }



}