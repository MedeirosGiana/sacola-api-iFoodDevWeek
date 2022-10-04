package dio.me.sacola.service.impl;

import dio.me.sacola.enumeration.FormaPagamento;
import dio.me.sacola.model.Item;
import dio.me.sacola.model.Restaurante;
import dio.me.sacola.model.Sacola;
import dio.me.sacola.repository.ItemRepository;
import dio.me.sacola.repository.ProdutoRepository;
import dio.me.sacola.repository.SacolaRepository;
import dio.me.sacola.resource.dto.ItemDto;
import dio.me.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());


        if (sacola.isFechada()) {
            throw new RuntimeException("Sacola está fechada!");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Essa produto não existe!");
                        }
                ))
                .build();

        List<Item> itensDaSacola = sacola.getItens();
        if (itensDaSacola.isEmpty()) {
            itensDaSacola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)){
                itensDaSacola.add( itemParaSerInserido);
            }else{
                throw new RuntimeException("Não é possível inserir na sacola produtos de restaurantes diferentes, feche a sacola ou esvazie!");
            }
        }

        List<Double> valorDosItens = new ArrayList<Double>();

        for (Item itemDaSacola : itensDaSacola) {
            Double valorTotalItem =
            itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }
        double valorTotalSacola = valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();

        sacola.setValorTotal(valorTotalSacola);
        sacolaRepository.save(sacola);
        return itemParaSerInserido;
    }

        @Override
        public Sacola verSacola (Long id){
            return sacolaRepository.findById(id).orElseThrow(
                    () -> {
                        throw new RuntimeException("Essa sacola não existe!");
                    }
            );
        }

        @Override
        public Sacola fecharSacola (Long id,int numeroformaPagamento){
            Sacola sacola = verSacola(id);
            if (sacola.getItens().isEmpty()) {
                throw new RuntimeException("Inclua itens na sacola!");
            }
            FormaPagamento formaPagamento =
                    numeroformaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;
            sacola.setFormaPagamento(formaPagamento);
            sacola.setFechada(true);
            return sacolaRepository.save(sacola);
        }

    }

