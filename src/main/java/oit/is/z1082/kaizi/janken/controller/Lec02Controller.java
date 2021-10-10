package oit.is.z1082.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1082.kaizi.janken.model.Janken;

@Controller
public class Lec02Controller {
  @PostMapping("/lec02")
  public String lec02(@RequestParam String na, ModelMap mod) {
    na = "Hi " + na;
    mod.addAttribute("n", na);
    return "lec02.html";
  }

  @GetMapping("ch")
  public String ch(ModelMap mod) {
    String player = "Choki";
    Janken JResult = new Janken(player);
    mod.addAttribute("player", player);
    mod.addAttribute("cpu", JResult.getCpu());
    mod.addAttribute("JResult", JResult.getResult());
    return "lec02.html";
  }

  @GetMapping("gu")
  public String gu(ModelMap mod) {
    String player = "Gu";
    Janken JResult = new Janken(player);
    mod.addAttribute("player", player);
    mod.addAttribute("cpu", JResult.getCpu());
    mod.addAttribute("JResult", JResult.getResult());
    return "lec02.html";
  }

  @GetMapping("pa")
  public String pa(ModelMap mod) {
    String player = "Pa";
    Janken JResult = new Janken(player);
    mod.addAttribute("player", player);
    mod.addAttribute("cpu", JResult.getCpu());
    mod.addAttribute("JResult", JResult.getResult());
    return "lec02.html";
  }
}
