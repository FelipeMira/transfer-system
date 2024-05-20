package br.com.felipemira.common.domain.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AppPageable {

    private int page;
    private int size;

}
