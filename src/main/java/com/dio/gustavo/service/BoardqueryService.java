package com.dio.gustavo.service;

import com.dio.gustavo.persistence.dao.BoardColumnDAO;
import com.dio.gustavo.persistence.dao.BoardDAO;
import com.dio.gustavo.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardqueryService {

    private final Connection connection;

    public Optional<BoardEntity> findById(final Long id) throws SQLException {
        var dao = new BoardDAO();
        var option = dao.findById(id);
        var boardColumnDAO = new BoardColumnDAO(connection);
        if (option.isPresent()) {
            var entity = option.get();
            entity.setBoardColumns(boardColumnDAO.findByBoardId(entity.getId()));

            return Optional.of(entity);
        }
        return Optional.empty();
    }

}
