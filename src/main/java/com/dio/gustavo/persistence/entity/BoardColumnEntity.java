package com.dio.gustavo.persistence.entity;

import lombok.Data;

@Data
public class BoardColumnEntity {

    private Long id;
    private String name;
    private int order;
    private BoardColumnKindEnum kind;
    private BoardEntity board = new BoardEntity();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BoardColumnKindEnum getKind() {
        return kind;
    }

    public void setKind(BoardColumnKindEnum kind) {
        this.kind = kind;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }
}
