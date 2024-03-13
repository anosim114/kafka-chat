package com.example.kafkachat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.kafkachat.messages.Message;

@Controller
public class IndexController {
  Logger logger = LoggerFactory.getLogger(IndexController.class);

  @GetMapping("")
  public String index(Model model) {
    logger.info("index was accessed");

    model.addAttribute("newMessage", new Message("", ""));

    return "index";
  }
}
