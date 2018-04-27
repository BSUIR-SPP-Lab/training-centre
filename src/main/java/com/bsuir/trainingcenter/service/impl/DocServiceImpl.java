package com.bsuir.trainingcenter.service.impl;


import com.bsuir.trainingcenter.entity.User;
import com.bsuir.trainingcenter.entity.view.CertificateInfoView;
import com.bsuir.trainingcenter.entity.view.CourseWithInfoView;
import com.bsuir.trainingcenter.entity.view.SolutionWithTaskView;
import com.bsuir.trainingcenter.entity.view.TaskWIthInfoView;
import com.bsuir.trainingcenter.service.*;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class DocServiceImpl implements DocService {

    private final CertificateService certificateService;
    private final UserService userService;
    private final CourseService courseService;
    private final SolutionService solutionService;
    private final TaskService taskService;

    @Autowired
    public DocServiceImpl(CertificateService certificateService, UserService userService, CourseService courseService, SolutionService solutionService, TaskService taskService) {
        this.certificateService = certificateService;
        this.userService = userService;
        this.courseService = courseService;
        this.solutionService = solutionService;
        this.taskService = taskService;
    }

    @Override
    public Resource generatePdfCertificate(long id) {
        CertificateInfoView info = certificateService.getCertificateInfo(id);
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
    public Resource generateXLSCertificate(long id) {
        CertificateInfoView info = certificateService.getCertificateInfo(id);
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
    public Resource generateCSVCertificate(long id) {
        CertificateInfoView info = new CertificateInfoView(1, "Влад", "Белых", "Java", "1", "2"); // certificateService.getCertificateInfo(id);
        if (info == null) {
            return null;
        }
        return createCSV(() -> String.format("%s;%s;%s;%s;%s;", info.getFirstName(), info.getLastName(), info.getName(), info.getStart(), info.getEnd()));
    }

    @Override
    public Resource generatePdfUsersOnCourse(long id, boolean finish) {
        List<User> list = userService.findUsersByCourseId(id, finish);
        CourseWithInfoView course = courseService.findCourseWithInfo(id);
        if (course == null) {
            return null;
        }
        return createPdf(((Document doc) -> {
            try {
                PdfFont font = PdfFontFactory.createFont("src/main/resources/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Paragraph paragraph = null;
                if (finish) {

                    paragraph = new Paragraph(String.format("Пользователи прошедшие курс \"%s\"", course.getName()))
                            .setItalic()
                            .setMarginBottom(20)
                            .setTextAlignment(TextAlignment.CENTER)
                            .setFont(font)
                            .setFontSize(40);
                    doc.add(paragraph);

                } else {

                    paragraph = new Paragraph(String.format("Пользователи обучающиеся на курсе \"%s\"", course.getName()))
                            .setItalic()
                            .setMarginBottom(20)
                            .setTextAlignment(TextAlignment.CENTER)
                            .setFont(font)
                            .setFontSize(40);
                    doc.add(paragraph);

                }
                paragraph = new Paragraph()
                        .setFont(font)
                        .setFontSize(20)
                        .add("Имя Фамилия\n");
                for (User user : list) {
                    paragraph.add(user.getFirstName() + " " + user.getLastName() + "\n");
                }
                doc.add(paragraph);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    @Override
    public Resource generateXLSUsersOnCourse(long id, boolean finish) {
        List<User> list = userService.findUsersByCourseId(id, finish);
        return createXLS((sheet, style) -> {
            HSSFRow[] rows = new HSSFRow[list.size() + 1];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = sheet.createRow(i);
            }
            createCell(rows[0], 0, "Имя", style);
            createCell(rows[0], 1, "Фамилия", style);

            for (int i = 0; i < list.size(); i++) {
                createCell(rows[i + 1], 0, list.get(i).getFirstName(), style);
                createCell(rows[i + 1], 1, list.get(i).getLastName(), style);

            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
        });
    }

    @Override
    public Resource generateCSVUsersOnCourse(long id, boolean finish) {
        List<User> list = userService.findUsersByCourseId(id, finish);
        return createCSV(() -> {
            StringBuffer buf = new StringBuffer();
            for (User user : list) {
                buf.append(user.getFirstName() + " " + user.getLastName() + ";");
            }
            return buf.toString();
        });
    }

    @Override
    public Resource generatePdfUserSolutions(long userId, long courseId) {
        List<SolutionWithTaskView> list = solutionService.findSolutionsByUserIdAndCourseId(userId, courseId);
        CourseWithInfoView course = courseService.findCourseWithInfo(courseId);
        User user = userService.findUser(userId);
        if (course == null || user == null) {
            return null;
        }
        return createPdf(((Document doc) -> {
            try {
                PdfFont font = PdfFontFactory.createFont("src/main/resources/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Paragraph paragraph = null;
                paragraph = new Paragraph(String.format("Список решений по курсу \"%s\" пользователя %s %s", course.getName(), user.getFirstName(), user.getLastName()))
                        .setItalic()
                        .setMarginBottom(20)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFont(font)
                        .setFontSize(40);
                doc.add(paragraph);

                for (SolutionWithTaskView solution : list) {
                    paragraph = new Paragraph()
                            .setFont(font)
                            .setFontSize(20)
                            .add(new Paragraph()
                                    .setItalic()
                                    .setMarginBottom(20)
                                    .setTextAlignment(TextAlignment.CENTER)
                                    .setFont(font)
                                    .setFontSize(30)
                                    .add(solution.getName()))
                            .add("Задача: " + solution.getBody() + "\n\n")
                            .add("Решение: " + solution.getNotes() + "\n\n")
                            .add("Заметки учителя: " + solution.getTeacherNotes() + "\n\n")
                            .add("Оценка: " + solution.getMark() + "\n\n");

                    doc.add(paragraph);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    @Override
    public Resource generateXLSUserSolution(long userId, long courseId) {
        List<SolutionWithTaskView> list = solutionService.findSolutionsByUserIdAndCourseId(userId, courseId);
        CourseWithInfoView course = courseService.findCourseWithInfo(courseId);
        User user = userService.findUser(userId);
        if (course == null || user == null) {
            return null;
        }
        return createXLS((sheet, style) -> {
            HSSFRow[] rows = new HSSFRow[list.size() * 8];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = sheet.createRow(i);
            }

            int i = 0;
            int j = 0;

            for (SolutionWithTaskView solution : list) {

                createCell(rows[i++], 0, "Курс", style);
                createCell(rows[i++], 0, "Имя Фамилия", style);
                createCell(rows[i++], 0, "Название", style);
                createCell(rows[i++], 0, "Задание", style);
                createCell(rows[i++], 0, "Решение", style);
                createCell(rows[i++], 0, "Заметки", style);
                createCell(rows[i++], 0, "Оценка", style);
                i++;

                createCell(rows[j++], 1, course.getName(), style);
                createCell(rows[j++], 1, user.getFirstName()+""+user.getLastName(), style);
                createCell(rows[j++], 1, solution.getName(), style);
                createCell(rows[j++], 1, solution.getBody(), style);
                createCell(rows[j++], 1, solution.getNotes(), style);
                createCell(rows[j++], 1, solution.getTeacherNotes(), style);
                createCell(rows[j++], 1, solution.getMark().toString(), style);
                j++;

            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
        });
    }

    @Override
    public Resource generateCSVUserSolution(long userId, long courseId) {
        List<SolutionWithTaskView> list = solutionService.findSolutionsByUserIdAndCourseId(userId, courseId);
        CourseWithInfoView course = courseService.findCourseWithInfo(courseId);
        User user = userService.findUser(userId);
        if (course == null || user == null) {
            return null;
        }
        return createCSV(() -> {
            StringBuffer buf = new StringBuffer();
            buf.append(course.getName()+";"+user.getFirstName()+" "+user.getLastName()+";");
            for (SolutionWithTaskView solution : list) {
                buf.append(String.format("%s;%s;%s;%s;%s;", solution.getName(),solution.getBody(), solution.getNotes(),solution.getTeacherNotes(),solution.getMark()));
            }
            return buf.toString();
        });
    }

    @Override
    public Resource generatePdfTasksByGroup(long groupId) {
        List<TaskWIthInfoView> tasks = taskService.findTasks(groupId);
        return createPdf(((Document doc) -> {
            try {
                PdfFont font = PdfFontFactory.createFont("src/main/resources/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Paragraph paragraph = null;
                paragraph = new Paragraph(String.format("Список заданий %n",  groupId))
                        .setItalic()
                        .setMarginBottom(20)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFont(font)
                        .setFontSize(40);
                doc.add(paragraph);

                for (TaskWIthInfoView task : tasks) {
                    paragraph = new Paragraph()
                            .setFont(font)
                            .setFontSize(20)
                            .add(new Paragraph()
                                    .setItalic()
                                    .setMarginBottom(20)
                                    .setTextAlignment(TextAlignment.CENTER)
                                    .setFont(font)
                                    .setFontSize(30)
                                    .add(task.getName()))
                            .add("Задача: " + task.getBody() + "\n\n").add("Срок сдачи:" + task.getUploadTime()+"\n\n");

                    doc.add(paragraph);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    @Override
    public Resource generateXLSTasksByGroup(long groupId) {
        List<TaskWIthInfoView> tasks = taskService.findTasks(groupId);
        return createXLS((sheet, style) -> {
            HSSFRow[] rows = new HSSFRow[(tasks.size()) * 4];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = sheet.createRow(i);
            }

            int i = 0;
            int j = 0;

            for (TaskWIthInfoView task : tasks) {

                createCell(rows[i++], 0, "Название", style);
                createCell(rows[i++], 0, "Задание", style);
                createCell(rows[i++],0,"Срок сдачи",style);
                i++;

                createCell(rows[j++], 1, task.getName(), style);
                createCell(rows[j++], 1, task.getBody(), style);
                createCell(rows[j++],1,task.getUploadTime(),style);
                j++;

            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
        });
    }

    @Override
    public Resource generateCSVTasksByGroup(long groupId) {
        List<TaskWIthInfoView> tasks = taskService.findTasks(groupId);
        return createCSV(() -> {
            StringBuffer buf = new StringBuffer();
            for (TaskWIthInfoView task : tasks) {
                buf.append(String.format("%s;%s;%s;", task.getName(),task.getBody(),task.getUploadTime()));
            }
            return buf.toString();
        });
    }

    @Override
    public Resource generatePdfUsersInGroup(long groupId) {
        List<User> list = userService.findUsersByGroupId(groupId);
        return createPdf(((Document doc) -> {
            try {
                PdfFont font = PdfFontFactory.createFont("src/main/resources/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Paragraph paragraph = null;
                paragraph = new Paragraph(String.format("Список пользователей группы %n", groupId))
                        .setItalic()
                        .setMarginBottom(20)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFont(font)
                        .setFontSize(40);
                doc.add(paragraph);
                paragraph = new Paragraph()
                        .setFont(font)
                        .setFontSize(20)
                        .add("Имя Фамилия\n");
                for (User user : list) {
                    paragraph.add(user.getFirstName() + " " + user.getLastName() + "\n");
                }
                doc.add(paragraph);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    @Override
    public Resource generateXLSUsersInGroup(long groupId) {
        List<User> list = userService.findUsersByGroupId(groupId);
        return createXLS((sheet, style) -> {
            HSSFRow[] rows = new HSSFRow[list.size() + 1];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = sheet.createRow(i);
            }

            createCell(rows[0], 1, "Фамилия", style);
            createCell(rows[0], 0, "Имя", style);

            for (int i = 0; i < list.size(); i++) {
                createCell(rows[i + 1], 0, list.get(i).getFirstName(), style);
                createCell(rows[i + 1], 1, list.get(i).getLastName(), style);

            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
        });
    }

    @Override
    public Resource generateCSVUsersInGroup(long groupId) {
        List<User> list = userService.findUsersByGroupId(groupId);
        return createCSV(() -> {
            StringBuffer buf = new StringBuffer();
            for (User user : list) {
                buf.append(String.format("%s %s;", user.getFirstName(), user.getLastName()));
            }
            return buf.toString();
        });
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