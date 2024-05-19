package br.com.felipemira.api.domain.pagiable;

import br.com.felipemira.api.domain.response.AccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Schema
@Getter
@Setter
public class PageAccountResponse extends PageResponse<AccountResponse> implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public PageAccountResponse(PageResponse p) {
        super(p.getNumber(), p.size, p.totalPages, p.numberOfElements, p.totalElements, p.hasContent, p.first, p.last, p.nextPage, p.previousPage, p.content);
    }

}
