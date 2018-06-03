package com.gosuccour.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.gosuccour.entity.Facture;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("facture/seeFacture")
public class FacturePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2, HttpServletRequest arg3,
			HttpServletResponse arg4) throws Exception {
			Facture facture = (Facture) model.get("facture");
			PdfPTable table = new PdfPTable(1);
			table.setSpacingAfter(20);
			table.addCell("About Client");
			table.addCell(facture.getCar().getClient().getSurname() +" "+ facture.getCar().getClient().getLastname());
			table.addCell(facture.getCar().getClient().getEmail());
			
			PdfPTable table2 = new PdfPTable(1);
			table2.setSpacingAfter(20);
			table2.addCell("About Facture");
			table2.addCell("Facture:" + facture.getId());
			table2.addCell("Date: " + facture.getCreateAt());
			

			
			document.add(table);
			document.add(table2);		
	
	}
}
