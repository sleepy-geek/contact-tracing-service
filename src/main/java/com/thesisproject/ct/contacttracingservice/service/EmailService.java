package com.thesisproject.ct.contacttracingservice.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.thesisproject.ct.contacttracingservice.model.Subject;
import com.thesisproject.ct.contacttracingservice.util.QRCodeUtility;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	SubjectService subjectService;

	public void sendFormUrlEmail(String email, String formUrl) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("no-reply-contact-tracing@gmail.com");
		message.setTo(email);
		message.setSubject("Contact Tracing Form URL");
		message.setText(formUrl);

		emailSender.send(message);
	}

	public void sendPersonalQRCodeEmail(Subject subject) {
		MimeMessage message = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("no-reply-contact-tracing@gmail.com");
			helper.setTo(subject.getEmail());
			helper.setSubject("Contact Tracing Identification QR Code");
			helper.setText(
					"Attached is your personal identification QR Code. Please use your id number as password to access the document.");

			PDDocument document = new PDDocument();
			PDPage page1 = new PDPage();
			document.addPage(page1);
			PDPageContentStream textStream = new PDPageContentStream(document, page1);
			textStream.setFont(PDType1Font.COURIER, 10);
			textStream.beginText();

			textStream.setLeading(20f);
			textStream.newLineAtOffset(25, 620);
			textStream.showText("Dear " + StringUtils.capitalize(subject.getFirstName()) + ",");
			textStream.newLine();
			textStream.newLine();
			textStream.newLine();
			textStream.showText(
					"We are happy to inform you that you have successfully registered your data to the Contact");
			textStream.newLine();
			textStream.showText(
					"Tracing Service. You may now use the system facilities for contactless tracking. Please use");
			textStream.newLine();
			textStream.showText(
					"the QR Code on this document for your personal identification at each contact tracing area.");
			textStream.newLine();
			textStream.newLine();
			textStream.newLine();
			textStream.showText("Thank you and take care.");
			textStream.newLine();
			textStream.newLine();
			textStream.newLine();
			textStream.newLine();
			textStream.newLine();
			textStream.newLine();
			textStream.showText("Sincerely,");
			textStream.newLine();
			textStream.showText("Your Contact Tracing Team");
			textStream.endText();

			File attachmentImage = new File(subject.getSubjectId() + "attachment.png");
			FileUtils.writeByteArrayToFile(attachmentImage,
					QRCodeUtility.generateQRCode(subject.getSubjectId().toString(), 200, 200));
			PDImageXObject image = PDImageXObject.createFromFile(subject.getSubjectId() + "attachment.png", document);
			textStream.drawImage(image, 8, 80);
			textStream.close();

			AccessPermission accessPermission = new AccessPermission();
			accessPermission.setCanModify(false);
			accessPermission.setCanPrint(true);
			StandardProtectionPolicy standardProtectionPolicy = new StandardProtectionPolicy(subject.getIdNumber(),
					subject.getIdNumber(), accessPermission);
			document.protect(standardProtectionPolicy);
			document.save(subject.getSubjectId() + "attachment.pdf");
			document.close();

			File attachmentFile = new File(subject.getSubjectId() + "attachment.pdf");
			FileSystemResource file = new FileSystemResource(attachmentFile);
			helper.addAttachment("attachment.pdf", file);
			emailSender.send(message);

			FileUtils.forceDelete(attachmentImage);
			FileUtils.forceDelete(attachmentFile);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendSubjectRecordsReport() {
		MimeMessage message = emailSender.createMimeMessage();
		File file = new File("report.csv");
		try(FileOutputStream fos = new FileOutputStream(file); ByteArrayOutputStream out = new ByteArrayOutputStream(); CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL))) {

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("no-reply-contact-tracing@gmail.com");
			helper.setTo("cts.service.2021@gmail.com");
			helper.setSubject("Contact Tracing Daily Report");
			helper.setText("Attached is the updated report of the registered contact tracing subjects.");

			List<Subject> subjectList = subjectService.getSubjects();
			for (Subject subject : subjectList) {
				List<String> fields = Arrays.asList(String.valueOf(subject.getSubjectId()), 
													subject.getFirstName(),
													subject.getMiddleName(), 
													subject.getLastName(), 
													subject.getIdNumber(),
													subject.getContactNumber(), 
													subject.getEmail(), 
													subject.getPosition(), 
													subject.getDepartment(),
													String.valueOf(subject.isAgreedDataPrivacyConsent()));
				csvPrinter.printRecord(fields);
			}
			
			csvPrinter.flush();
			helper.addAttachment("attachment.csv", new ByteArrayDataSource(out.toByteArray(), "application/csv"));
			emailSender.send(message);
			
			fos.close();
			FileUtils.forceDelete(file);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}