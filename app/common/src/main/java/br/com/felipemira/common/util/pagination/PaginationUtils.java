package br.com.felipemira.common.util.pagination;

import br.com.felipemira.common.domain.pagination.AppPage;
import br.com.felipemira.common.domain.pagination.AppPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaginationUtils {

    public static <T, U> AppPage<U> convertToAppPage(Page<T> page, Function<T, U> converter) {
        AppPage<U> appPage = new AppPage<>();
        appPage.setContent(page.getContent().stream().map(converter).collect(Collectors.toList()));
        appPage.setNumber(page.getNumber());
        appPage.setSize(page.getSize());
        appPage.setTotalPages(page.getTotalPages());
        appPage.setTotalElements(page.getTotalElements());
        return appPage;
    }

    public static Pageable convertToPageable(AppPageable appPageable) {
        return PageRequest.of(appPageable.getPage(), appPageable.getSize());
    }

    public static AppPageable convertToAppPageable(Pageable pageable) {
        AppPageable appPageable = new AppPageable();
        appPageable.setPage(pageable.getPageNumber());
        appPageable.setSize(pageable.getPageSize());
        return appPageable;
    }

    public static <T, U> AppPage<U> convertToAppPage(AppPage<T> appPage, Function<T, U> converter) {
        List<U> content = appPage.getContent().stream()
                .map(converter)
                .collect(Collectors.toList());
        Integer nextPage = (appPage.getNumber() >= appPage.getTotalPages() - 1) ? null : appPage.getNumber() + 1;
        Integer previousPage = (appPage.getNumber() > 0) ? appPage.getNumber() - 1 : null;
        return new AppPage<>(content, appPage.getNumber(), appPage.getSize(), appPage.getTotalPages(), appPage.getTotalElements(), nextPage, previousPage);
    }

    public static <T> AppPage<T> paginate(List<T> list, AppPageable appPageable) {
        int totalElements = list.size();
        int totalPages = (int) Math.ceil((double) totalElements / appPageable.getSize());

        if (appPageable.getPage() >= totalPages) {
            return new AppPage<>(new ArrayList<>(), appPageable.getPage(), appPageable.getSize(), totalPages, totalElements, null, null);
        }

        int start = appPageable.getPage() * appPageable.getSize();
        int end = Math.min((start + appPageable.getSize()), list.size());
        List<T> sublist = list.subList(start, end);
        Integer nextPage = (appPageable.getPage() >= totalPages - 1) ? null : appPageable.getPage() + 1;
        Integer previousPage = (appPageable.getPage() > 0) ? appPageable.getPage() - 1 : null;
        return new AppPage<>(sublist, appPageable.getPage(), appPageable.getSize(), totalPages, totalElements, nextPage, previousPage);
    }
}
