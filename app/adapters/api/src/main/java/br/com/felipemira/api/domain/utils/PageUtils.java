package br.com.felipemira.api.domain.utils;


import br.com.felipemira.api.domain.pagiable.PageResponse;

public final class PageUtils {

    public static final <T> PageResponse<T> adjustPageResponse(PageResponse<T> pageResponse, Integer page, Integer size, Integer count) {

        pageResponse.setNumber(page);
        pageResponse.setSize(size);

        if(page==0) {
            pageResponse.setFirst(true);
            pageResponse.setPreviousPage(0);
        }else {
            pageResponse.setFirst(false);
            pageResponse.setPreviousPage(page-1);
        }

        pageResponse.setTotalElements(count);

        if(pageResponse.getContent()==null) {
            pageResponse.setNumberOfElements(0);
            pageResponse.setHasContent(false);
        }else {
            pageResponse.setNumberOfElements(pageResponse.getContent().size());
            pageResponse.setHasContent(true);
        }

        float div = Float.valueOf(count) / Float.valueOf(size);
        pageResponse.setTotalPages((int) Math.ceil(div));

        if(page==pageResponse.getTotalPages()-1) {
            pageResponse.setLast(true);
            pageResponse.setNextPage(page);
        }else {
            pageResponse.setLast(false);
            pageResponse.setNextPage(page+1);
        }

        return pageResponse;
    }

}

