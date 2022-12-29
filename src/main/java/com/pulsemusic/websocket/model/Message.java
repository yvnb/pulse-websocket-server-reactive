package com.pulsemusic.websocket.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Message {
    private String from;
    private String message;
    private LocalDateTime timeStamp;
    private String channel;

}
