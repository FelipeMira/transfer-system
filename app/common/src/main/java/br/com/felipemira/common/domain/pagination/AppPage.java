package br.com.felipemira.common.domain.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor
public class AppPage<T> {

    private List<T> content;
    private int number;
    private int size;
    private int totalPages;
    private long totalElements;
    private Integer nextPage;
    private Integer previousPage;

    public AppPage(List<T> content, int number, int size, int totalPages, long totalElements) {
        this.content = content;
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.nextPage = (number >= totalPages - 1) ? null : number + 1;
        this.previousPage = (number > 0) ? number - 1 : null;
    }

}