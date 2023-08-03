package br.com.erp.projeto.dtos.request;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
public class MessageResponseDTO {

    private final String status;
    private final int code;
    private final String message;

}
