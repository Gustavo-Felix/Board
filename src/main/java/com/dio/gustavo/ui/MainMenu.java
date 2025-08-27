package com.dio.gustavo.ui;

import com.dio.gustavo.persistence.entity.BoardColumnEntity;
import com.dio.gustavo.persistence.entity.BoardColumnKindEnum;
import com.dio.gustavo.persistence.entity.BoardEntity;
import com.dio.gustavo.service.BoardService;
import com.dio.gustavo.service.BoardQueryService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.dio.gustavo.persistence.config.ConnectionConfig.getConnection;
import static com.dio.gustavo.persistence.entity.BoardColumnKindEnum.*;

public class MainMenu {

    private final Scanner sc = new Scanner(System.in);

    public void execute() throws SQLException {
        System.out.println("Bem vindo ao gerenciador de boards, escolha a opção desejada");
        var option = -1;
        while (true){
            System.out.println("1 - Criar um novo board");
            System.out.println("2 - Selecionar um board existente");
            System.out.println("3 - Excluir um board");
            System.out.println("4 - Sair");
            option = sc.nextInt();
            switch (option){
                case 1 -> createBoard();
                case 2 -> selectBoard();
                case 3 -> deleteBoard();
                case 4 -> System.exit(0);
                default -> System.out.println("Opção inválida, informe uma opção do menu: ");
            }
        }
    }

    private void createBoard() throws SQLException {
        var entity = new BoardEntity();
        sc.nextLine();
        System.out.println("Informe o nome do seu board: ");
        entity.setName(sc.nextLine());

        System.out.println("Seu board terá colunas além dos 3 padrões? Se sim, informe quantas, senão digite '0': ");
        var additionalColumns = sc.nextInt();

        List<BoardColumnEntity> columns = new ArrayList<>();

        System.out.println("Informe o nome da coluna inicial do board: ");
        sc.nextLine();
        var initialColumnName = sc.nextLine();
        var initialColumn = createColumn(initialColumnName, INITIAL, 0);
        columns.add(initialColumn);

        for (int i = 0; i < additionalColumns; i++) {
            System.out.println("Informe o nome da coluna de tarefa pendente do board: ");
            var pendingColumnName = sc.nextLine();
            var pendingColumn = createColumn(pendingColumnName, PENDING, i + 1);
            columns.add(pendingColumn);
        }

        System.out.println("Informe o nome da coluna final: ");
        var finalColumnName = sc.nextLine();
        var finalColumn = createColumn(finalColumnName, FINAL, additionalColumns + 1);
        columns.add(finalColumn);

        System.out.println("Informe o nome da coluna de cancelamento do baord: ");
        var cancelColumnName = sc.nextLine();
        var cancelColumn = createColumn(cancelColumnName, CANCEL, additionalColumns + 2);
        columns.add(cancelColumn);

        System.out.println(entity);

        entity.setBoardColumns(columns);
        try(var connection = getConnection()){
            var service = new BoardService(connection);
            service.insert(entity);
        }

    }

    private void selectBoard() throws SQLException {
        System.out.println("Informe o id do board que deseja selecionar: ");
        var id = sc.nextLong();
        try(var connection = getConnection()){
            var queryService = new BoardQueryService(connection);
            var optional = queryService.findById(id);
            optional.ifPresentOrElse(
                    b -> new BoardMenu(b).execute(),
                    () -> System.out.printf("Não foi encontrado um board com id %s!\n", id)
            );
        }
    }

    private void deleteBoard() throws SQLException {
        System.out.println("Informe o id do board que será excluido: ");
        var id = sc.nextLong();
        try(var connection = getConnection()){
            var service = new BoardService(connection);
            if (service.delete(id)){
                System.out.printf("O board %s foi excluido!\n", id);
            } else {
                System.out.printf("Não foi encontrado um board com id %s!\n", id);
            }
        }
    }

    private BoardColumnEntity createColumn(final String name, final BoardColumnKindEnum kind, final int order){
        var boardColumn = new BoardColumnEntity();
        boardColumn.setName(name);
        boardColumn.setKind(kind);
        boardColumn.setOrder(order);
        return boardColumn;
    }
}
