package cz.palivtom.httpmonitoring.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper
public abstract class PageableMapper {

    public Pageable map(Integer pageNumber, Integer pageSize, String order, String sortingColumn) {
        Sort sort = Sort.by(map(order), sortingColumn);
        return PageRequest.of(pageNumber, pageSize, sort);
    }

    protected abstract Sort.Direction map(String order);
}