package br.com.lab.webapi.controllers;

import br.com.lab.webapi.models.Log;
import br.com.lab.webapi.repositories.LogRepository;

import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
@RequestMapping("/logs")
public class LogController {

  private final LogRepository logRepository;

  public LogController(LogRepository logRepository) {
    this.logRepository = logRepository;
  }

  @PostMapping
  public String processarArquivo(@RequestParam("arquivo") MultipartFile arquivo) {
    try (InputStream inputStream = arquivo.getInputStream()) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String row;
      while ((row = reader.readLine()) != null) {
        System.out.println("O valor da é: " + row);
        Log log = new Log();
        log.setName(row);
        logRepository.save(log);
      }
    } catch (IOException e) {
      // e.printStackTrace();
      System.out.println(e);
    }
    return "redirect:logs";
  }

  @GetMapping
  public String getAllLogs(Model model) {
    Iterable<Log> logs = logRepository.findAll();
    model.addAttribute("logs", logs);
    return "views/logs";
  }


}
