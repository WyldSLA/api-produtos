package com.wyldSLA.produtos.service.impl;

import com.wyldSLA.produtos.dto.ProdutoCreateDto;
import com.wyldSLA.produtos.dto.ProdutoResponseDTO;
import com.wyldSLA.produtos.mapper.ProdutoMapper;
import com.wyldSLA.produtos.model.ProdutoModel;
import com.wyldSLA.produtos.repository.ProdutoRepository;
import com.wyldSLA.produtos.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoServiceImpl implements ProdutoService {


    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoResponseDTO save(ProdutoCreateDto produtoDto) {
        ProdutoModel newProduto = produtoMapper.toModel(produtoDto);
        return produtoMapper.toDTO(produtoRepository.save(newProduto));
    }

    @Override
    public Optional<ProdutoResponseDTO> getProduto(UUID id) {
        return produtoRepository.findById(id)
                .map(produtoMapper::toDTO);
    }

    @Override
    public ProdutoResponseDTO update(UUID id, ProdutoCreateDto createDto) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoMapper.updateModelFromDTO(createDto, produto);

        produto.setDataAtualizacao(LocalDateTime.now(ZoneId.of("America/Recife")));

        var updateProduto = produtoRepository.save(produto);

        return produtoMapper.toDTO(updateProduto);
    }

    @Override
    public ProdutoResponseDTO updateStatus(UUID id, boolean status) {

        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setAtivo(status);
        produto.setDataAtualizacao(LocalDateTime.now(ZoneId.of("America/Recife")));

        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toDTO)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoRepository.deleteById(produto.getId());
    }
}
