/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import zanimaux.Service.PanierService;
import zanimaux.Service.ProduitService;
import zanimaux.entities.ContenuPanier;
import zanimaux.entities.Panier;
import zanimaux.entities.Produit;
import zanimaux.entities.User;

/**
 *
 * @author macbookpro
 */
public class CreatePDF {
        //public static  String DEST = "result/table/simple_table6.pdf";
 
    /*public static void main(String[] args) throws IOException,
            DocumentException,
            SQLException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CreatePDF().createPdf(DEST);
    }*/
    public void createPdf(String dest) throws IOException, DocumentException, SQLException {
        User u = Session.getLoggedInUser();
        PanierService ps = new PanierService();
        List<ContenuPanier> list= ps.rechercheCommande("09625522");
        ProduitService prodSer = new ProduitService();
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        Font font = new Font(FontFamily.TIMES_ROMAN , 10, Font.NORMAL);
        Font fontTitreTableau = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        document.open();
        Phrase retourAlaLigne = new Phrase("\n");
        Paragraph detail = new Paragraph(u.getPrenom()+" "+ u.getNom()+"\n"+u.getCodePostale()+" ,"+u.getAdresse()+" "+u.getVille()+"\nTunis", font);
        document.add(new Phrase("FACTURE\n"));
        document.add( new Phrase("zanimo.esprit@gmail.com\n",font));
        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));
         document.add(retourAlaLigne);

        document.add(new Phrase("Details Client\n"));
        document.add(detail);
        document.add(new Chunk(ls));
        PdfPCell cell;
        // a table with three columns
        PdfPTable table = new PdfPTable(new float[] { 20, 100,70,70,70 });
        
           cell = new PdfPCell(new Phrase("ID",fontTitreTableau));
           BaseColor myColor = WebColors.getRGBColor("#F3F3F3");
           BaseColor borderColor = WebColors.getRGBColor("#ECEEF4");
           //cell.setBorder(Rectangle.NO_BORDER);
           cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBorderColor(borderColor);
           cell.setFixedHeight(30);
            table.addCell(cell);
           cell = new PdfPCell(new Phrase("Designation",fontTitreTableau));
           cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBorderColor(borderColor);
           cell.setFixedHeight(30);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Quantité",fontTitreTableau));
           cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBorderColor(borderColor);
           cell.setFixedHeight(30);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("Prix unitaire",fontTitreTableau));
           cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setFixedHeight(30);
           cell.setBorderColor(borderColor);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("Prix Total",fontTitreTableau));
           cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setFixedHeight(30);
           cell.setBorderColor(borderColor);
            table.addCell(cell);
        
      
        for(int i=0;i<list.size();i++)
        {
            if(i%2==0){
                myColor = WebColors.getRGBColor("#F3F3F3");
            }
            else{
                myColor = WebColors.getRGBColor("#FFFFFF");
            }
        Produit prod = prodSer.rechercheProduitMagasin(list.get(i).getIdProduit());
         cell = new PdfPCell(new Phrase(String.valueOf(list.get(i).getIdContenuPanier()),font));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(myColor);
         cell.setBorderColor(borderColor);
         cell.setFixedHeight(20);
         table.addCell(cell);
         cell = new PdfPCell(new Phrase(String.valueOf(prod.getLibelle()),font));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(myColor);
         cell.setBorderColor(borderColor);
         cell.setFixedHeight(20);
         table.addCell(cell);
         cell = new PdfPCell(new Phrase(String.valueOf(list.get(i).getQuantite()),font));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(myColor);
         cell.setBorderColor(borderColor);
         cell.setFixedHeight(20);
         table.addCell(cell);
         cell = new PdfPCell(new Phrase(String.valueOf(prod.getPrix()),font));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(myColor);
         cell.setBorderColor(borderColor);
         cell.setFixedHeight(20);
         table.addCell(cell);
         cell = new PdfPCell(new Phrase(String.valueOf(prod.getPrix()*list.get(i).getQuantite()),font));
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setBackgroundColor(myColor);
         cell.setBorderColor(borderColor);
         cell.setFixedHeight(20);
         table.addCell(cell);
     
        }
        Panier p = ps.recherchePanier(u.getCin());
        document.add(table);
        document.add(new Chunk(ls));
        document.add(retourAlaLigne);
        document.add(new Phrase("Total: "+p.getSommeCommande()+" DT\n"));
        document.add(new Chunk(ls));
        document.add(retourAlaLigne);
        document.add(new Phrase("Livraison: 10DT\n"));
        document.add(new Chunk(ls));
        document.add(retourAlaLigne);
        Double somme=p.getSommeCommande()+10;
        document.add(new Phrase("Montant à payer: "+ somme+" DT\n"));
        document.add(new Chunk(ls));
        document.add(retourAlaLigne);
        document.add(new Phrase("Important:\n" +
"1. les produits livrés ne peuvent pas faire l'objet d'un retour\n" +
"2. vous ne pouvez pas annuler la commande aprés 3jours de la date de sa passation\n" +
"3. Si le livreur ne vous trouve pas dans l'adresse de livraison, alors vous trouverez votre commande dans la poste la plus proche.\n" +
"        "));
        
        document.close();
    }
}
