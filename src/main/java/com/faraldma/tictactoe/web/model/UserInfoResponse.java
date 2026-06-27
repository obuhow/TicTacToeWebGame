package com.faraldma.tictactoe.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private UUID userId;
    private String login;
}
