package com.example.J2EE_project.services;

import com.example.J2EE_project.DTOs.CertificationDTO;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGenerationService {

    public byte[] generatePdf(CertificationDTO certificationDTO) throws IOException {
        // Output stream to hold the PDF content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String fontPath1 = "static/fonts/GreatVibes-Regular.ttf";
        String fontPath2 = "static/fonts/NotoSerif-Regular.ttf";
        String fontPath3 = "static/fonts/NotoSerif-Bold.ttf";

        PdfFont font1 = PdfFontFactory.createFont(fontPath1, PdfEncodings.IDENTITY_H);
        PdfFont font2 = PdfFontFactory.createFont(fontPath2, PdfEncodings.IDENTITY_H);
        PdfFont font3 = PdfFontFactory.createFont(fontPath3, PdfEncodings.IDENTITY_H);

        // PdfWriter writes the content to the output stream
        PdfWriter pdfWriter = new PdfWriter(outputStream);

        // PdfDocument is the container for the PDF structure
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4.rotate());

        // Document is used to add content to the PDF
        Document document = new Document(pdfDocument);

        // Load the background image (make sure to replace the path to your image)
        ImageData backgroundImage = ImageDataFactory.create("src/main/resources/static/images/v1_2.png");

        // Draw the image on the background
        PdfCanvas canvas = new PdfCanvas(pdfDocument.addNewPage());
        float x = 0; // X-position of the image
        float y = 0; // Y-position of the image
        float width = pdfDocument.getDefaultPageSize().getWidth();
        float height = pdfDocument.getDefaultPageSize().getHeight();
        Rectangle rect = new Rectangle(x, y, width, height);

        // Set the image to cover the entire page
        canvas.addImageFittedIntoRectangle(backgroundImage, rect, false);

        // Add title
        Paragraph title = new Paragraph("Giấy chứng nhận")
                .setFontSize(60)
                .setFont(font1)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);

        // Subtitle
        Paragraph subtitle = new Paragraph("được trao cho")
                .setFontSize(12)
                .setFont(font2)
                .setTextAlignment(TextAlignment.CENTER);

        // Recipient name
        Paragraph recipientName = new Paragraph(certificationDTO.getFullName())
                .setFontSize(48)
                .setFont(font1)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);

        // Program participation details
        Paragraph details = new Paragraph("đã tham gia đóng góp ủng hộ chương trình")
                .setFontSize(12)
                .setFont(font3)
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph eventDetails = new Paragraph(certificationDTO.getEvent())
                .setFontSize(12)
                .setFont(font2)
                .setItalic()
                .setTextAlignment(TextAlignment.CENTER);

        // Donation amount
        Paragraph donation_label = new Paragraph("với số tiền")
                .setFontSize(12)
                .setFont(font3)
                .setTextAlignment(TextAlignment.CENTER);

        // Donation amount
        Paragraph donation = new Paragraph(certificationDTO.getDonation() + "đ")
                .setFontSize(12)
                .setFont(font3)
                .setTextAlignment(TextAlignment.CENTER);

        // Footer information
        Paragraph representation_label = new Paragraph("Đơn vị gây quỹ")
                .setFontSize(12)
                .setFont(font3)
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginLeft(250);

        Paragraph representation = new Paragraph("Cơ sở giáo dục")
                .setFontSize(12)
                .setFont(font2)
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginLeft(250);

        // Footer information
        Paragraph date = new Paragraph(formatDate(certificationDTO.getTime()))
                .setFontSize(12)
                .setFont(font2)
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginLeft(250);

        // Add content to the document
        document.add(title);
        document.add(subtitle);
        document.add(recipientName);
        document.add(details);
        document.add(eventDetails);
        document.add(donation_label);
        document.add(donation);
        document.add(representation_label);
        document.add(representation);
        document.add(date);

        // Close the document
        document.close();

        // Return the generated PDF as a byte array
        return outputStream.toByteArray();
    }

    private String formatDate(String preDate) {
        String[] preDateSplit = preDate.split("/");
        return String.format("ngày %s tháng %s năm %s", preDateSplit[0], preDateSplit[1], preDateSplit[2]);
    }


}

