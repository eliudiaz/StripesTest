/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author edcracken
 */
public class PageableResultDto<T> {

    private Integer pagesCount;
    private List<T> content;
    private Integer pageSize;
    private Integer totalElements;

    public static PageableResultDto getFrom(Page page) {
        return new PageableResultDto(page.getTotalPages(),
                page.getContent(), page.getSize(),
                page.getNumberOfElements());
    }

    public PageableResultDto(Integer pagesCount, List<T> content, Integer pageSize, Integer totalElements) {
        this.pagesCount = pagesCount;
        this.content = content;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public PageableResultDto() {
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

}
