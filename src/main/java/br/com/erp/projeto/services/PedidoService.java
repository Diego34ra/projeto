package br.com.erp.projeto.services;

import br.com.erp.projeto.dtos.request.MessageResponseDTO;
import br.com.erp.projeto.dtos.request.PedidoDTO;
import br.com.erp.projeto.exceptions.ResourceNotFoundException;
import br.com.erp.projeto.model.Item;
import br.com.erp.projeto.model.Pedido;
import br.com.erp.projeto.model.Product;
import br.com.erp.projeto.repository.ItemRepository;
import br.com.erp.projeto.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ItemRepository itemRepository;


    public MessageResponseDTO createPedido(Pedido pedido){
        pedido.setDataPedido(LocalDate.now());
        pedidoRepository.save(pedido);
        List<Item> itensPedido = getItemPedido(pedido.getItens(),pedido);
        itemRepository.saveAll(itensPedido);
        pedido.setValorTotal(calcularValorPedido(pedido.getItens()));
        pedidoRepository.save(pedido);
        return MessageResponseDTO
                .builder()
                .code(201)
                .status("Created")
                .message("Pedido criado com sucesso")
                .build();
    }

    public BigDecimal calcularValorPedido(List<Item> itens) {
        BigDecimal valorTotal = itens.stream()
                .map(item -> item.getProduto().getPrice().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return valorTotal;
    }

    public List<Item> getItemPedido(List<Item> itens, Pedido pedido){
        return itens.stream().map(item -> {
            Item itemPedido = new Item();
            Product product = null;
            try {
                product = productService.findById(item.getProduto().getId());
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
            itemPedido.setOrder(pedido);
            itemPedido.setProduto(product);
            itemPedido.setQuantidade(item.getQuantidade());
            return itemPedido;
        }).collect(Collectors.toList());
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }
}
