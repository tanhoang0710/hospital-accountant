package vn.ptit.b19dccn576.BE.service.impl;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import vn.ptit.b19dccn576.BE.model.Type;
import vn.ptit.b19dccn576.BE.service.IPdfExporter;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class PdfExporter implements IPdfExporter {
    private List<Type> types = List.of(Type.builder().id(1L).name("123").build(), Type.builder().id(2L).name("456").build());

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("STT"));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Nhà cung cấp"));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Mặt hàng"));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Giá trị hàng hóa"));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Thuế suất"));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Thuế giá trị gia tăng"));

        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        table.addCell("1");
        table.addCell("Pfizer");
        table.addCell("Vắc xin");
        table.addCell("100.000.000");
        table.addCell("5%");
        table.addCell("5.000.000");
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.setDocumentLanguage("UTF-8");

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, "UTF-8");
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("BẢNG KÊ DỊCH VỤ MUA VÀO");
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);
        Paragraph kyTinhThue = new Paragraph("Kỳ tính thuế: tháng 11 năm 2023");
        kyTinhThue.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(kyTinhThue);

        Paragraph tenNguoiNop = new Paragraph("Tên người nộp thuế: Accountant1");
        tenNguoiNop.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(tenNguoiNop);

        Paragraph tenDaiLy = new Paragraph("Tên đại lý thuế: Bệnh viện A");
        tenDaiLy.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(tenDaiLy);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 2.5f, 3.0f, 3.0f, 1.5f, 2.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
