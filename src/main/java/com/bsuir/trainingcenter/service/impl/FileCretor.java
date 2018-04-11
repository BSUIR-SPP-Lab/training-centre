package com.bsuir.trainingcenter.service.impl;


import com.itextpdf.io.font.PdfEncodings;
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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class FileCretor {

    public Resource find() {

        Document document;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(stream);
        PdfDocument pdf = new PdfDocument(writer);
        document = new Document(pdf, PageSize.A4);
        createPdfTemplate(document);
        createPdf(document);
        document.close();
        return new ByteArrayResource(stream.toByteArray());
    }

    private void createPdfTemplate(Document document) {
        try {
            Image logo = new Image(ImageDataFactory.create("src/main/resources/logo.jpg"));
            Paragraph paragraph = new Paragraph().add(logo).setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(paragraph);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createPdf(Document document) {

        try {

            Paragraph paragraph = new Paragraph()
                    //.setFont(russian)
                    .setFontSize(20)
                    .add("Career objective:"+ "\r\n");
            document.add(paragraph);
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


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}