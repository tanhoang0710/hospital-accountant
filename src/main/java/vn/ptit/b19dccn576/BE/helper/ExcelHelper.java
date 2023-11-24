package vn.ptit.b19dccn576.BE.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import vn.ptit.b19dccn576.BE.model.Category;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "Tutorials";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

//    public static ByteArrayInputStream tutorialsToExcel(List<Tutorial> tutorials) {
//
//        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
//            Sheet sheet = workbook.createSheet(SHEET);
//
//            // Header
//            Row headerRow = sheet.createRow(0);
//
//            for (int col = 0; col < HEADERs.length; col++) {
//                Cell cell = headerRow.createCell(col);
//                cell.setCellValue(HEADERs[col]);
//            }
//
//            int rowIdx = 1;
//            for (Tutorial tutorial : tutorials) {
//                Row row = sheet.createRow(rowIdx++);
//
//                row.createCell(0).setCellValue(tutorial.getId());
//                row.createCell(1).setCellValue(tutorial.getTitle());
//                row.createCell(2).setCellValue(tutorial.getDescription());
//                row.createCell(3).setCellValue(tutorial.isPublished());
//            }
//
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//        } catch (IOException e) {
//            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
//        }
//    }
//
    public static List<Category> excelToCategories(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Category> categories = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Category category = new Category();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0 -> category.setId((long) currentCell.getNumericCellValue());
                        case 1 -> category.setName(currentCell.getStringCellValue());
                        case 3 -> {

                        }
                        default -> {
                        }
                    }
                }
                categories.add(category);
            }

            workbook.close();

            return categories;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
