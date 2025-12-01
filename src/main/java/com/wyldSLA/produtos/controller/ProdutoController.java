package com.wyldSLA.produtos.controller;

import com.wyldSLA.produtos.dto.ProdutoCreateDto;
import com.wyldSLA.produtos.service.impl.ProdutoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    private final ProdutoServiceImpl produtoService;

    public ProdutoController(ProdutoServiceImpl produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody ProdutoCreateDto createDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(createDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduto(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.getProduto(id));
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody ProdutoCreateDto createDto, @PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.update(id, createDto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> updateStatus(
            @PathVariable UUID id,
            @RequestParam boolean ativo) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.updateStatus(id, ativo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
