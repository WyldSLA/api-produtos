package com.wyldSLA.produtos.dto;

import java.math.BigDecimal;

public record ProdutoCreateDto(
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidadeEstoque,
        String categoria,
        Boolean ativo
) {
}
