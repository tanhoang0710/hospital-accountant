package vn.ptit.b19dccn576.BE.usecase;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.ptit.b19dccn576.BE.dto.CategoryPercentageResDto;
import vn.ptit.b19dccn576.BE.dto.TaxByTypeAndYearDto;
import vn.ptit.b19dccn576.BE.model.Item;
import vn.ptit.b19dccn576.BE.service.ITaxService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class TaxUsecase implements ITaxUsecase {
    private ITaxService taxService;

    public TaxUsecase(ITaxService taxService) {
        this.taxService = taxService;
    }

    @Override
    public Double getTaxByTypeAndMonth(String month, String year, String type) {
        return taxService.getTaxByTypeAndMonth(month, year, type);
    }

    @Override
    public Integer getNumberOfCategoryNeedToTaxByTypeAndMonth(String month, String year, String type) {
        return taxService.getNumberOfCategoryNeedToTaxByTypeAndMonth(month, year, type);
    }

    @Override
    public List<CategoryPercentageResDto> getCategoryPercentage(String month, String year, String type) {
        log.info("[TaxUsecase] getCategoryPercentage with month {}, year {}, type {}", month, year, type);
        var items = taxService.getCategoryPercentage(month, year, type);
        var categoryPercentage = new ArrayList<CategoryPercentageResDto>();
        if (items != null && items.size() > 0) {
            var x = items.stream()
                    .collect(groupingBy(item -> item.getCategory().getName()));
            for (Map.Entry<String, List<Item>> entry : x.entrySet()) {
                double totalTax = 0.0;
                Long id = 0L;
                for (Item item : entry.getValue()) {
                    totalTax += item.getPrice() * item.getQuantity() * item.getCategory().getPercentage() / 100;
                    id = item.getCategory().getId();
                }
                categoryPercentage.add(CategoryPercentageResDto.builder()
                        .name(entry.getKey())
                        .totalTax(totalTax)
                        .id(id).build());
            }
        }
        return categoryPercentage;
    }

    @Override
    public List<TaxByTypeAndYearDto> getTaxByTypeAndYear(String type) {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int year = today.getYear();
        var taxByTypeAndYearList = new ArrayList<TaxByTypeAndYearDto>();
        List<Double> taxThisYear = new ArrayList<>();
        List<Double> taxLastYear = new ArrayList<>();
        for (int i = 1; i <= month; i++) {
            var tax1 = taxService.getTaxByTypeAndMonth(String.valueOf(i), String.valueOf(year), type);
            taxThisYear.add(tax1);
            var tax2 = taxService.getTaxByTypeAndMonth(String.valueOf(i), String.valueOf(year - 1), type);
            taxLastYear.add(tax2);
        }
        for(int i = month + 1; i <= 12; i++){
            taxThisYear.add(0.0);
            taxLastYear.add(0.0);
        }
        var taxByTypeAndThisYear = TaxByTypeAndYearDto.builder()
                .tax(taxThisYear)
                .year("Năm nay");
        var taxByTypeAndLastYear = TaxByTypeAndYearDto.builder()
                .tax(taxLastYear)
                .year("Năm trước");
        taxByTypeAndYearList.add(taxByTypeAndThisYear.build());
        taxByTypeAndYearList.add(taxByTypeAndLastYear.build());
        return taxByTypeAndYearList;
    }

    @Override
    public Double getTotalTaxByCurrentYearAndType(String type) {
        LocalDate today = LocalDate.now();
        int year = today.getYear() - 2;
        var items = taxService.getItemsByYear(type, String.valueOf(year));
        AtomicReference<Double> totalTax = new AtomicReference<>(0.0);
        items.forEach(item -> {
            totalTax.updateAndGet(v -> v + item.getPrice() * item.getQuantity() * item.getCategory().getPercentage() / 100);
        });
        return totalTax.get();
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        taxService.export(response);
    }

    @Override
    public void importCategories(MultipartFile file) {

    }
}
