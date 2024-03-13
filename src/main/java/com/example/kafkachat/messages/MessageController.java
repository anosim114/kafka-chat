package com.example.kafkachat.messages;

import com.example.kafkachat.messages.Message;
import com.example.kafkachat.messages.Store;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@AllArgsConstructor
@Controller
@Slf4j
public class MessageController {
  Store msgStore;

  @GetMapping("/messages")
  public String allMessages(Model model, @RequestParam(defaultValue="10") int limit) {
    List<Message> messages = msgStore.readMessages(limit);
    model.addAttribute("messages", messages);

    return "messages/index";
  }

  @PostMapping("/messages")
  public String createMessage(Model model, @ModelAttribute Message msg) {
    log.info("New message from {}: '{}'", msg.getAuthor(), msg.getMessage());

    msgStore.save(msg.getMessage());

    return "redirect:/";
  }
}
