package com.wyldSLA.produtos.mapper;

import com.wyldSLA.produtos.dto.ProdutoCreateDto;
import com.wyldSLA.produtos.dto.ProdutoResponseDTO;
import com.wyldSLA.produtos.model.ProdutoModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdutoMapper {

    ProdutoModel toModel(ProdutoCreateDto dto);

    ProdutoResponseDTO toDTO(ProdutoModel model);

    void updateModelFromDTO(ProdutoCreateDto dto, @MappingTarget ProdutoModel model);
}
