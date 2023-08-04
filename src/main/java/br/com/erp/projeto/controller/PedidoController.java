package br.com.erp.projeto.controller;

import br.com.erp.projeto.dtos.mapper.PedidoMapper;
import br.com.erp.projeto.dtos.request.MessageResponseDTO;
import br.com.erp.projeto.dtos.request.PedidoDTO;
import br.com.erp.projeto.exceptions.ResourceNotFoundException;
import br.com.erp.projeto.model.Pedido;
import br.com.erp.projeto.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createOrder(@RequestHeader("Authorization") String authHeader,
                                                          @RequestBody PedidoDTO pedidoDto) throws ResourceNotFoundException {
        var pedido = pedidoMapper.toPedidoCreate(pedidoDto);
        var message = pedidoService.createPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll(@RequestHeader("Authorization") String authHeader){
        var pedidoList = pedidoService.findAll();
        return ResponseEntity.ok().body(pedidoList);
    }
}
