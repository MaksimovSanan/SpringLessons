package ru.maksimov.RESTApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RentRequestDTO {
    @NotNull(message = "Missing 'borrowerId' in the request body")
    private Integer borrowerId;
    @NotNull(message = "Missing 'code' in the request body")
    private Integer code;

    @NotNull(message = "Missing 'bookId' in the request body")
    private Integer bookId;

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
