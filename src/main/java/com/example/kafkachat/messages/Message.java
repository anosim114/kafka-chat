package com.example.kafkachat.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {
  private String author;
  private String message;
}
