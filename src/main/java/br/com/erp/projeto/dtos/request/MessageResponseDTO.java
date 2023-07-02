package br.com.erp.projeto.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageResponseDTO {

    private final String status;
    private final int code;
    private final String message;
}
