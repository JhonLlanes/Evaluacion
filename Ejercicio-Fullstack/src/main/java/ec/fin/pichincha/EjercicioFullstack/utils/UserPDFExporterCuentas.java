package ec.fin.pichincha.EjercicioFullstack.utils;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserPDFExporterCuentas {
    List<Map<String, String>> respFin;

    public UserPDFExporterCuentas(List<Map<String, String>> respFin) {
        this.respFin = respFin;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("FECHA".toUpperCase(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cliente".toUpperCase(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Numero Cuenta".toUpperCase(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tipo".toUpperCase(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Estado".toUpperCase(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Saldo Inicial".toUpperCase(), font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Movimiento".toUpperCase(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Saldo Disponible".toUpperCase(), font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Map<String, String> map : respFin) {
            table.addCell(map.get("fecha"));
            table.addCell(map.get("cliente"));
            table.addCell(map.get("numerocuenta"));
            table.addCell(map.get("tipo"));
            table.addCell(map.get("estado"));
            table.addCell(map.get("saldoinicial"));
            table.addCell(map.get("movimiento") );
            table.addCell(map.get("saldodisponible"));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("TRANSACCIONES", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{2.5f, 3.5f, 3.0f, 3.0f,3.5f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();

    }
}
