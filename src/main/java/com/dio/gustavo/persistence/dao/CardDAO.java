package com.dio.gustavo.persistence.dao;

import com.dio.gustavo.dto.CardDetails;
import lombok.AllArgsConstructor;

import java.sql.Connection;

@AllArgsConstructor
public class CardDAO {

    private Connection connection;

    public CardDetails findById(final Long id){
        return null;
    }

}
