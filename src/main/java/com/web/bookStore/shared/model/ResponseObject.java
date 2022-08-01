package com.web.bookStore.shared.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ResponseObject {
    private String message;
    private Object data;

    public ResponseObject(String message) {
        this.message = message;
    }
}
