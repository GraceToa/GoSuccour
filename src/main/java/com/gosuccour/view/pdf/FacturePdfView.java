package com.gosuccour.view.pdf;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.gosuccour.entity.Facture;
import com.gosuccour.entity.ItemFacture;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("facture/seeFacture")
public class FacturePdfView extends AbstractPdfView {
	
	 public static final String IMG = "src/main/resources/static/images/logoBlack.png";
	 private String FILE ="src/main/resources/static/pdf/facture.pdf";
	 
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest arg3, HttpServletResponse arg4) throws Exception {
		Facture facture = (Facture) model.get("facture");	
		PdfWriter.getInstance(document, new FileOutputStream(FILE));
		document.open();
		Image image = Image.getInstance(IMG);
		image.scaleToFit(270, 123);
		image.setAbsolutePosition( 150f, 200f);
	
		image.setSpacingAfter(250);
		

		PdfPTable tableSpace = new PdfPTable(1);
		tableSpace.setSpacingBefore(550);
		PdfPCell cell=null;
		
		
		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingBefore(550);
		cell= new PdfPCell(new Phrase("About Facture:"));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		table2.addCell(cell);
		table2.addCell("Facture:" + facture.getId());
		table2.addCell("Date: " + facture.getCreateAt());

		PdfPTable table = new PdfPTable(1);
		table.setSpacingAfter(20);
		cell= new PdfPCell(new Phrase("About Client:"));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		table.addCell(cell);
		table.addCell(facture.getCar().getClient().getSurname() + " " + facture.getCar().getClient().getLastname());
		table.addCell(facture.getCar().getClient().getEmail());

		PdfPTable table3 = new PdfPTable(1);
		table3.setSpacingAfter(20);
		cell= new PdfPCell(new Phrase("About Car:"));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		table3.addCell(cell);
		table3.addCell("Model:" + " " + facture.getCar().getModel() + "" + "Matriculation:" + " "
				+ facture.getCar().getMatriculation());

		PdfPTable table4 = new PdfPTable(1);
		table4.setSpacingAfter(20);
		cell= new PdfPCell(new Phrase("Services Contract:"));
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setPadding(8f);
		cell.setColspan(3);
		table4.addCell(cell);
		for (ItemFacture item : facture.getItems()) {
			if (item.getMaintenance() != null) {
				table4.addCell(item.getMaintenance().toString());
			}
			if (item.getRevision() != null) {
				table4.addCell(item.getRevision().toString());
			}
			if (item.getItv() != null) {
				table4.addCell(item.getItv().toString());
			}
		}
	
		cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	
		table4.addCell("TOTAL"+" "+" "+facture.getTotal().toString()+"€");	
		
	
		document.add(image);
		document.add(tableSpace);
		document.add(table2);
		document.add(table);
		document.add(table3);
		document.add(table4);
		document.close();
	}
}
