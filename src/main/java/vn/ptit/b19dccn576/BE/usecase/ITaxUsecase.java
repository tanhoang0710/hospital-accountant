package vn.ptit.b19dccn576.BE.usecase;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import vn.ptit.b19dccn576.BE.dto.CategoryPercentageResDto;
import vn.ptit.b19dccn576.BE.dto.TaxByTypeAndYearDto;

import java.io.IOException;
import java.util.List;

public interface ITaxUsecase {
    Double getTaxByTypeAndMonth(String month, String year, String type);

    Integer getNumberOfCategoryNeedToTaxByTypeAndMonth(String month, String year, String type);

    List<CategoryPercentageResDto> getCategoryPercentage(String month, String year, String type);

    List<TaxByTypeAndYearDto> getTaxByTypeAndYear(String type);

    Double getTotalTaxByCurrentYearAndType(String type);

    void export(HttpServletResponse response) throws IOException;

    void importCategories(MultipartFile file);
}
