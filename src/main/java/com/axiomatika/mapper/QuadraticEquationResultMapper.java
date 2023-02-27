package com.axiomatika.mapper;

import com.axiomatika.model.dto.QuadraticEquationRequest;
import com.axiomatika.model.dto.QuadraticEquationResponse;
import com.axiomatika.model.entity.QuadraticEquationResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuadraticEquationResultMapper {

    QuadraticEquationResult toEntity(QuadraticEquationRequest request, QuadraticEquationResponse response);
}
