package oit.is.z1082.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1082.kaizi.janken.model.Janken;
import oit.is.z1082.kaizi.janken.model.Entry;
import oit.is.z1082.kaizi.janken.model.User;
import oit.is.z1082.kaizi.janken.model.UserMapper;
import oit.is.z1082.kaizi.janken.model.MatchMapper;
import oit.is.z1082.kaizi.janken.model.Match;

@Controller
@RequestMapping("/lec02")
public class Lec02Controller {

  @Autowired
  MatchMapper matchMapper;
  @Autowired
  UserMapper userMapper;
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

  @GetMapping("step3")
  public String sample4(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    String lgu = "Hi " + loginUser;
    ArrayList<User> user3 = userMapper.selectAllUser();
    ArrayList<Match> match1 = matchMapper.selectAllMatch();
    model.addAttribute("login_user", lgu);
    model.addAttribute("userinfo", user3);
    model.addAttribute("match", match1);
    return "lec02.html";
  }

  @GetMapping("step1")
  public String lec02() {
    return "lec02.html";
  }

  @GetMapping("match")
  public String lec002(@RequestParam Integer id, ModelMap model) {
    User un = userMapper.selectById(id);
    model.addAttribute("un", un);
    User unyn = userMapper.selectByNotId(id);
    model.addAttribute("unyn", unyn);
    return "match.html";
  }

  @GetMapping("match1")
  public String janken(@RequestParam String te, ModelMap model) {
    String cpu = "Gu";
    int id = 2;
    String name = "ほんだ";
    User un = userMapper.selectById(id);
    model.addAttribute("un", un);
    User unyn = userMapper.selectByNotId(id);
    model.addAttribute("unyn", unyn);
    String Win = "You Win!";
    String Lose = "You Lose";
    String Draw = "Draw";
    model.addAttribute("te", te);
    model.addAttribute("cpu", cpu);
    if (te.equals("Pa")) {
      model.addAttribute("result", Win);
    } else if (te.equals("Choki")) {
      model.addAttribute("result", Lose);
    } else {
      model.addAttribute("result", Draw);
    }

    Match match = new Match(); // int id = match.getId1(); // match.setId(id);
    match.setUser1(un.getId());
    match.setUser2(unyn.getId());
    match.setUser1Hand(te);
    match.setUser2Hand(cpu);
    matchMapper.insertMatch(match);
    return "match.html";
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
