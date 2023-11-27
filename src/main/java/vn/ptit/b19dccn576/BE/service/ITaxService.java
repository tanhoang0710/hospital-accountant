package vn.ptit.b19dccn576.BE.service;

import jakarta.servlet.http.HttpServletResponse;
import vn.ptit.b19dccn576.BE.dto.CategoryPercentageResDto;
import vn.ptit.b19dccn576.BE.model.Item;

import java.io.IOException;
import java.util.List;

public interface ITaxService {
    Double getTaxByTypeAndMonth(String month, String year, String type);
    Integer getNumberOfCategoryNeedToTaxByTypeAndMonth(String month, String year, String type);
    List<Item> getCategoryPercentage(String month, String year, String type);
    List<Item> getItemsByYear(String type, String year);
    void export(HttpServletResponse response) throws IOException;
}
