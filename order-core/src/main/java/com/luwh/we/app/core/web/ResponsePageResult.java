package com.luwh.we.app.core.web;

import org.springframework.http.HttpStatus;

/**
 * @author lu.wh
 * @date 2023/09/12 11/01/17
 * @description
 */
public class ResponsePageResult<T> {
    private Long page;
    private Long pageSize;

    private Long total;
    private Integer code;
    private String message;
    private T data;
    private boolean success;

    public ResponsePageResult() {
    }

    public ResponsePageResult(Integer code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    /**
     *
     * @param code
     * @param page
     * @param pageSize
     * @param total
     * @param data
     * @param success
     */
    public ResponsePageResult(Integer code, Long page, Long pageSize, Long total, T data, boolean success) {
        this.code = code;
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
        this.success = success;
    }

    public static <T> ResponsePageResult success(Integer code, T data, String message) {
        return new ResponsePageResult(code, message, data, true);
    }

    /**
     *
     * @param page
     * @param pageSize
     * @param total
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponsePageResult success(Long page, Long pageSize, Long total, T data) {
        return new ResponsePageResult(HttpStatus.OK.value(), page, pageSize, total, data, true);
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
