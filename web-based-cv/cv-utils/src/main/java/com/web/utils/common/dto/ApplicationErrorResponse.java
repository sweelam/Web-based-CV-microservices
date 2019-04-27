package com.web.utils.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auth Sweelam
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationErrorResponse {
    private String message;
    private String devErrorMessage;
    private String errorCode;
}
