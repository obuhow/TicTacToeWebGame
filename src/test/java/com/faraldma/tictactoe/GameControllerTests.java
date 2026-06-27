// package com.faraldma.tictactoe.web.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;

// import com.faraldma.tictactoe.datasource.repository.GameRepository;
// import com.faraldma.tictactoe.domain.model.Game;
// import com.faraldma.tictactoe.domain.model.GameBoard;
// import com.faraldma.tictactoe.domain.model.Move;
// import com.faraldma.tictactoe.domain.service.GameService;
// import com.faraldma.tictactoe.web.mapper.GameWebMapper;
// import com.faraldma.tictactoe.web.model.ErrorResponse;
// import com.faraldma.tictactoe.web.model.GameResponse;
// import com.faraldma.tictactoe.web.model.GameUpdateRequest;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.when;
// import static org.mockito.ArgumentMatchers.any;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// import java.util.Optional;
// import java.util.UUID;

// import org.junit.jupiter.api.Test;

// @WebMvcTest(SingleGameController.class)
// public class GameControllerTests {
//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private GameService service;

//     @MockBean
//     private GameRepository repository;

//     @MockBean
//     private GameWebMapper mapper;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @Test
//     void createGame_shouldReturn201() throws Exception {
//         UUID expectedId = UUID.randomUUID();
//         int[][] board = new int[3][3];
//         GameResponse mockResponse = new GameResponse(expectedId, board);

//         doNothing().when(repository).save(any());
//         when(mapper.toResponse(any())).thenReturn(mockResponse);

//         mockMvc.perform(post("/game?size=3"))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.gameId").exists())
//                 .andExpect(jsonPath("$.board").isArray());
//     }


//     @Test
//     void updateGame_shouldReturn200() throws Exception {
//         UUID gameId = UUID.randomUUID();   
//         int[][] currentBoard = {{0,0,0},{0,0,0},{0,0,0}};
//         GameBoard currentGameBoard = new GameBoard(currentBoard);
//         UUID userId = UUID.randomUUID(); 
//         Optional<Game> game = Optional.ofNullable(new Game(gameId, currentBoard, userId));

//         int[][] newBoard = {{0,1,0},{0,0,0},{0,0,0}};
//         GameUpdateRequest request = new GameUpdateRequest(newBoard);
//         GameBoard newGameBoard = new GameBoard(newBoard);
//         Move move = new Move(1, 1);

//         when(repository.findById(gameId)).thenReturn(game);
//         when(service.isGameOver(any())).thenReturn(false);
//         when(mapper.toDomainBoard(any())).thenReturn(newGameBoard);
//         when(service.validateGame(any(), any())).thenReturn(true);
//         when(service.getNextMove(any())).thenReturn(move);
        

//         mockMvc.perform(post("/game/{gameId}", gameId.toString())
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(request)))
//                 .andDo(print())
//                 .andExpect(status().isOk());
//     }
    
//     @Test
//     void updateGame_shouldReturnNotFound() throws Exception {
//         String NonExistendUuid = "9b0c8366-45ea-4944-a989-8fcb8999test";
//         int[][] board = {{0,1,0},{0,0,0},{0,0,0}};
//         GameUpdateRequest request = new GameUpdateRequest(board);

//         mockMvc.perform(post("/gameId/{gameId}", NonExistendUuid)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(request)))
//                 .andExpect(status().isNotFound());
//     }

//     @Test
//     void updateGame_shouldReturnEndGame() throws Exception {
//         String NonExistendUuid = "9b0c8366-45ea-4944-a989-8fcb8999test";
//         int[][] board = {{0,1,0},{0,0,0},{0,0,0}};
//         GameUpdateRequest request = new GameUpdateRequest(board);

//         mockMvc.perform(post("/gameId/{gameId}", NonExistendUuid)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(request)))
//                 .andExpect(status().isNotFound());
//     }
// }

