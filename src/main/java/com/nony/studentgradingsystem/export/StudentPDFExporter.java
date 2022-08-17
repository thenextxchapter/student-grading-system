package com.nony.studentgradingsystem.export;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nony.studentgradingsystem.AbstractExporter;
import com.nony.studentgradingsystem.entity.Student;

public class StudentPDFExporter extends AbstractExporter {
	public void export(List<Student> listStudents, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/pdf", ".pdf", "students_");

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);

		Paragraph paragraph = new Paragraph("List of Students", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] {3.6f, 3.6f, 3.5f, 3.0f, 3.0f});

		writeTableHeader(table);
		writeTableData(table, listStudents);

		document.add(table);

		document.close();
	}

	private void writeTableData(PdfPTable table, List<Student> listStudents) {
		for (Student student : listStudents) {
			table.addCell(student.getMatricNumber());
			table.addCell(student.getFirstName() + " " + student.getLastName());
			table.addCell(student.getEmail());
			table.addCell(String.valueOf(student.getDateOfBirth()));
			table.addCell(String.valueOf(student.getPhoneNumber()));
		}
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Matric No", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Full Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("D.O.B", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Tel No", font));
		table.addCell(cell);
	}
}
