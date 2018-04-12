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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    private CertificateInfoService certificateInfoService;


    @Override
    public Resource generatePdfCertificate(int id) {
        CertificateInfoView info =new CertificateInfoView(1,"Влад","Белых","Java","1","2"); // certificateInfoService.getCertificateInfo(id);
        if(info==null){
            return null;
        }
        return find((doc -> {
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
                        .add(String.format("Сертификат о том что %s %s прошел курсы %s c %s по %s",info.getFirstName(),info.getLastName(),info.getName(),info.getStart(),info.getEnd()));
                doc.add(paragraph);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    public Resource find(Consumer<Document> c) {

        Document document;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(stream);
        PdfDocument pdf = new PdfDocument(writer);
        document = new Document(pdf, PageSize.A4);
        createPdfTemplate(document);
        c.accept(document);
        document.close();
        return new ByteArrayResource(stream.toByteArray());
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

//    public void createPdf(Document document) {
//
//        try {
//            //BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            //Font standardFont = new Font(bf, 14, 0, BaseColor.BLACK);
//
//            paragraph = new Paragraph("Email: " + resume.getEmail() + "\r\n")
//                    .setFont(russian)
//                    .setFontSize(20)
//                    .add("Career objective: " + resume.getCareerObjective() + "\r\n")
//                    .add("Skills: " + resume.getSkills() + "\r\n")
//                    .add("Sex: " + resume.getSex() + "\r\n")
//                    .add("Phone number: " + resume.getPhoneNumber() + "\r\n")
//                    .add("Education: " + resume.getEducation() + "\r\n")
//                    .add("English level: " + resume.getEnglishLevel() + "\r\n")
//                    .add("Is relocation possible: " + resume.getIsRelocationPossible() + "\r\n")
//                    .add("Is trip possible: " + resume.getIsTripPossible() + "\r\n")
//                    .add("Date Of Birth: " + resume.getDateOfBirth() + "\r\n")
//                    .add("Salary: " + resume.getSalary() + "\r\n")
//                    .add("About me: " + resume.getAboutMe() + "\r\n");
//            document.add(paragraph);

//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


}