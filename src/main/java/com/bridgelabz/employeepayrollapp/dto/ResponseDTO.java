package com.bridgelabz.employeepayrollapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Purpose : To demonstrate fields of response message in DTO
 *
 * @author : Sampriti Roy Chowdhury
 * @version : 0.0.1
 * @since : 15-12-2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String message;
    private Object data;
}
