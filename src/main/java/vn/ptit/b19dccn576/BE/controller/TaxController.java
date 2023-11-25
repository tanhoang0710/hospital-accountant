package vn.ptit.b19dccn576.BE.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import vn.ptit.b19dccn576.BE.common.BaseResponse;
import vn.ptit.b19dccn576.BE.dto.CategoryPercentageResDto;
import vn.ptit.b19dccn576.BE.dto.TaxByTypeAndYearDto;
import vn.ptit.b19dccn576.BE.helper.ExcelHelper;
import vn.ptit.b19dccn576.BE.usecase.ITaxUsecase;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/taxes")
@CrossOrigin(origins = "http://localhost:3000")
public class TaxController {
    private ITaxUsecase taxUsecase;

    public TaxController(ITaxUsecase taxUsecase) {
        this.taxUsecase = taxUsecase;
    }

    @GetMapping("/type/{type}/year/{year}/month/{month}")
    public BaseResponse<Double> getTaxByTypeAndMonth(@PathVariable String type, @PathVariable String year, @PathVariable String month){
        return BaseResponse.ofSucceeded(taxUsecase.getTaxByTypeAndMonth(month, year, type));
    }

    @GetMapping("/get-categories/type/{type}/year/{year}/month/{month}")
    public BaseResponse<Integer> getNumberOfCategoryNeedToTaxByTypeAndMonth(@PathVariable String type, @PathVariable String year, @PathVariable String month){
        return BaseResponse.ofSucceeded(taxUsecase.getNumberOfCategoryNeedToTaxByTypeAndMonth(month, year, type));
    }

    @GetMapping("/get-category-percentage")
    public BaseResponse<List<CategoryPercentageResDto>> getCategoryPercentageNeedToTaxByTypeAndYearAndMonth(@RequestParam String type, @RequestParam String year, @RequestParam String month){
        return BaseResponse.ofSucceeded(taxUsecase.getCategoryPercentage(month, year, type));
    }

    @GetMapping("/get-by-year")
    public BaseResponse<List<TaxByTypeAndYearDto>> getTaxByTypeAndYear(@RequestParam String type){
        return BaseResponse.ofSucceeded(taxUsecase.getTaxByTypeAndYear(type));
    }

    @GetMapping("/get-total-by-year")
    public BaseResponse<Double> getTotalTaxByTypeAndYear(@RequestParam String type){
        return BaseResponse.ofSucceeded(taxUsecase.getTotalTaxByCurrentYearAndType(type));
    }

    @GetMapping("/get-export-pdf")
    public void getExportPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        taxUsecase.export(response);
    }

    @PostMapping(value = "/upload")
    public BaseResponse<String> uploadFile(@RequestParam("file") MultipartFile file){
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                taxUsecase.importCategories(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return BaseResponse.ofSucceeded(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return BaseResponse.ofSucceeded(message);
            }
        }

        message = "Please upload an excel file!";
        return BaseResponse.ofSucceeded(message);
    }
}
