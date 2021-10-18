package oit.is.z1082.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1082.kaizi.janken.model.Janken;
import oit.is.z1082.kaizi.janken.model.Entry;

@Controller
@RequestMapping("/lec02")
public class Lec02Controller {

  @Autowired
  private Entry entry;

  @GetMapping("step2")
  public String sample38(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    String lgu = "Hi " + loginUser;
    model.addAttribute("login_user", lgu);
    model.addAttribute("entry", this.entry);
    return "lec02.html";
  }

  @GetMapping("step1")
  public String lec02() {
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
