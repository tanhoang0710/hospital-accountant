package vn.ptit.b19dccn576.BE.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.ptit.b19dccn576.BE.dto.CategoryPercentageResDto;
import vn.ptit.b19dccn576.BE.model.Item;
import vn.ptit.b19dccn576.BE.repository.TypeRepository;
import vn.ptit.b19dccn576.BE.service.ICategoryService;
import vn.ptit.b19dccn576.BE.service.IItemService;
import vn.ptit.b19dccn576.BE.service.IPdfExporter;
import vn.ptit.b19dccn576.BE.service.ITaxService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class TaxService implements ITaxService {
    private final TypeRepository typeRepository;
    private final ICategoryService categoryService;
    private final IItemService itemService;
    private final IPdfExporter pdfExporter;

    public TaxService(TypeRepository typeRepository, ICategoryService categoryService, IItemService itemService, IPdfExporter pdfExporter) {
        this.typeRepository = typeRepository;
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.pdfExporter = pdfExporter;
    }

    @Override
    public Double getTaxByTypeAndMonth(String month, String year, String type) {
        var items = itemService.getItemsByTypeAndMonth(month, year, type);
        AtomicReference<Double> tax = new AtomicReference<>(0.0);
        items.forEach(item -> {
            tax.updateAndGet(v -> v + item.getPrice() * item.getQuantity() * item.getCategory().getPercentage() / 100);
        });
        return tax.get();
    }

    @Override
    public Integer getNumberOfCategoryNeedToTaxByTypeAndMonth(String month, String year, String type) {
        var categories =  categoryService.getCategoryByTypeAndMonth(month, year, type);
        return categories.size();
    }

    @Override
    public List<Item> getCategoryPercentage(String month, String year, String type) {
        log.info("[TaxService] getCategoryPercentage with month {}, year {}, type {}", month, year, type);
        return itemService.getItemsByTypeAndMonth(month, year, type);
    }

    @Override
    public List<Item> getItemsByYear(String type, String year) {
        return itemService.getItemsByTypeAndYear(year, type);
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        pdfExporter.export(response);
    }
}
