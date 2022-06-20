package com.example.diplomaprojectgeneticcode.service.client;

import com.example.diplomaprojectgeneticcode.dto.filter.PriceFilter;
import com.example.diplomaprojectgeneticcode.enums.CourseLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SharedService {

    public List<Integer> getPageNumbers(int totalPages) {
        if (totalPages <= 0) {
            return Collections.emptyList();
        }
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
        log.info("pageNumbers: {}", pageNumbers);
        return pageNumbers;
    }


    public <T> Page<T> findPaginated(List<T> listGiven, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<T> list;

        if (listGiven.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listGiven.size());
            list = listGiven.subList(startItem, toIndex);
        }

        Page<T> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), listGiven.size());

        log.info("page: {}", page);

        return page;
    }


    public Set<String> getLanguages() {
        return Arrays.stream(Locale.getISOLanguages())
                .map(Locale::new)
                .map(l -> l.getDisplayLanguage(Locale.ENGLISH))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Set<PriceFilter> getPriceFilters() {
        return Set.of(
                new PriceFilter("From cheapest", "price,asc"),
                new PriceFilter("From the most expensive", "price,desc")
                );
    }

    public Set<String> getCurrencies() {
        return Set.of(
             "USD", "KZT", "RUB"
        );
    }

    public Set<String> getContentTypes() {
        return Set.of(
                "Lecture", "Video", "Download"
        );
    }


    public Set<String> getLevels() {
        return CourseLevel.levels();
    }




}
