package ec.fin.pichincha.EjercicioFullstack.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.Color;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;

public class UserPDFExporter {
    List<Map<String, String>> respFin;

    public UserPDFExporter(List<Map<String, String>> respFin) {
        this.respFin = respFin;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("FECHA", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("tipoMovimiento".toUpperCase(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("valor".toUpperCase(), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("saldo".toUpperCase(), font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Map<String, String> map : respFin) {
            table.addCell(map.get("fecha"));
            table.addCell(map.get("tipoMovimiento"));
            table.addCell(map.get("valor"));
            table.addCell(map.get("saldo"));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Lista Movimientos", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();

    }
}
