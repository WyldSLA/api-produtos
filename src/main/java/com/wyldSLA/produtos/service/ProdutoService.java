package com.wyldSLA.produtos.service;

import com.wyldSLA.produtos.dto.ProdutoCreateDto;
import com.wyldSLA.produtos.dto.ProdutoResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoService {
    public ProdutoResponseDTO save(ProdutoCreateDto produtoDto);

    public Optional<ProdutoResponseDTO> getProduto(UUID id);

    public ProdutoResponseDTO update(UUID id, ProdutoCreateDto createDto);

    public ProdutoResponseDTO updateStatus(UUID id, boolean status);

    public List<ProdutoResponseDTO> findAll();

    public void delete(UUID id);
}
