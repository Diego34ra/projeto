package br.com.erp.projeto.services;

import br.com.erp.projeto.dtos.request.MessageResponseDTO;
import br.com.erp.projeto.exceptions.ResourceNotFoundException;
import br.com.erp.projeto.model.Cliente;
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
    ClienteService clienteService;
    @Autowired
    ItemRepository itemRepository;


    public MessageResponseDTO createPedido(Pedido pedido) throws ResourceNotFoundException {
        pedido.setDataPedido(LocalDate.now());
        List<Item> itensPedido = getItemPedido(pedido.getItens(),pedido);
        Cliente cliente = clienteService.findById(pedido.getCliente().getId());
        pedido.setCliente(cliente);
        pedidoRepository.save(pedido);
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

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) throws ResourceNotFoundException {
        return verificaSeExiste(id);
    }


    public MessageResponseDTO deleteById(Integer id) throws ResourceNotFoundException {
        Pedido pedido = verificaSeExiste(id);
        pedidoRepository.delete(pedido);
        return MessageResponseDTO.builder()
                .status("Ok")
                .code(200)
                .message("Pedido com id "+id+" deletado com sucesso;")
                .build();
    }

    public Pedido verificaSeExiste(Integer id) throws ResourceNotFoundException {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido de id "+id+" não encontrdo."));
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
            Product product;
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
}
