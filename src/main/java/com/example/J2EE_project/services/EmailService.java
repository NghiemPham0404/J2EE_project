package com.example.J2EE_project.services;

import com.example.J2EE_project.DTOs.CertificationDTO;
import com.example.J2EE_project.models.CharityEvent;
import com.example.J2EE_project.models.TransferSession;
import com.example.J2EE_project.repositories.CharityEventRepository;
import com.example.J2EE_project.repositories.TransferRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private TransferRepository transferRepository;


    public void sendDonationCertification(String recipientAddress, String transferSessionId) {
        try {
            UUID transferId = UUID.fromString(transferSessionId);
            TransferSession transferSession = transferRepository.findById(transferId).get();
            CharityEvent charityEvent = transferSession.getCharityEvent();

            CertificationDTO certificationDTO = new CertificationDTO();
            certificationDTO.setFullName(transferSession.getName());
            certificationDTO.setDonation(transferSession.getAmount().toString());
            certificationDTO.setTime(new SimpleDateFormat("dd/MM/yyyy").format(transferSession.getTime()));
            certificationDTO.setEvent(charityEvent.getName());

            // Generate PDF
            byte[] pdfContent = pdfGenerationService.generatePdf(certificationDTO);

            // Prepare MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipientAddress);
            helper.setSubject("Minh chứng tham gia hoạt động gây quỹ từ thiện");
            helper.setText("Cảm ơn bạn đã ủng hộ chương trình từ thiệt \n"+
                        "Dưới đây là minh chứng của bạn", true);

            // Attach PDF
            InputStreamSource attachment = new ByteArrayResource(pdfContent);
            helper.addAttachment(transferSessionId + ".pdf", attachment);


            // Send Email
            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
