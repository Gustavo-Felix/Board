package com.dio.gustavo.dto;

import com.dio.gustavo.persistence.entity.BoardColumnKindEnum;

public record BoardColumnInfoDTO(Long id, int order, BoardColumnKindEnum kind) {

}