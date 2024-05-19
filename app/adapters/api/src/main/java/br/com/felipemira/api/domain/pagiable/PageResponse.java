package br.com.felipemira.api.domain.pagiable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    @JsonInclude(ALWAYS)
    public int number;

    @JsonInclude(ALWAYS)
    public int size;

    @JsonInclude(ALWAYS)
    public int totalPages;

    @JsonInclude(ALWAYS)
    public int numberOfElements;

    @JsonInclude(ALWAYS)
    public long totalElements;

    @JsonInclude(ALWAYS)
    public boolean hasContent;

    @JsonInclude(ALWAYS)
    public boolean first;

    @JsonInclude(ALWAYS)
    public boolean last;

    @JsonInclude(ALWAYS)
    public int nextPage;

    @JsonInclude(ALWAYS)
    public int previousPage;

    public List<T> content;
}

