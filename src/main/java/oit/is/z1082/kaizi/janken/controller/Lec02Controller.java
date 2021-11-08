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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z1082.kaizi.janken.model.Janken;
import oit.is.z1082.kaizi.janken.model.Entry;
import oit.is.z1082.kaizi.janken.model.User;
import oit.is.z1082.kaizi.janken.model.UserMapper;
import oit.is.z1082.kaizi.janken.model.MatchMapper;
import oit.is.z1082.kaizi.janken.model.Match;
import oit.is.z1082.kaizi.janken.model.MatchInfo;
import oit.is.z1082.kaizi.janken.model.MatchInfoMapper;
import oit.is.z1082.kaizi.janken.service.AsyncKekka;

@Controller
@RequestMapping("/lec02")
public class Lec02Controller {

  @Autowired
  MatchMapper matchMapper;
  @Autowired
  UserMapper userMapper;
  @Autowired
  MatchInfoMapper matchinfoMapper;
  @Autowired
  AsyncKekka asynckekka;

  @GetMapping("step2")
  public SseEmitter pushMatch() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.asynckekka.asyncShowMatchList(sseEmitter);
    return sseEmitter;
  }

  @GetMapping("step3")
  @Transactional
  public String sample4(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    String lgu = "Hi " + loginUser;
    User un1 = userMapper.selectByName(loginUser);
    ArrayList<User> user3 = userMapper.selectAllUser();
    ArrayList<Match> match1 = matchMapper.selectAllMatch();
    ArrayList<MatchInfo> match3 = matchinfoMapper.selectTrueMatchInfo();
    model.addAttribute("player", un1);
    model.addAttribute("login_user", lgu);
    model.addAttribute("userinfo", user3);
    model.addAttribute("match1", match1);
    model.addAttribute("match3", match3);
    return "lec02.html";
  }

  @GetMapping("step1")
  public String back() {
    return "lec02.html";
  }

  @GetMapping("match")
  public String match1(@RequestParam Integer id, @RequestParam Integer id2, Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    model.addAttribute("login_user", loginUser);
    User un1 = userMapper.selectById(id);
    User un2 = userMapper.selectById(id2);
    model.addAttribute("un1", un1);
    model.addAttribute("un2", un2);
    return "match.html";
  }

  @GetMapping("match2")
  @Transactional
  public String match2(@RequestParam Integer id1, @RequestParam Integer id2, String te, Principal prin,
      ModelMap model) {
    String loginUser = prin.getName();
    String logu = "Hi " + loginUser;
    model.addAttribute("logu", logu);
    int sep = 0;
    for (MatchInfo m : matchinfoMapper.selectTrueMatchInfo()) {
      if (m.getUser2() == id2) {
        sep++;
      }
    }

    boolean a = true;
    MatchInfo m = new MatchInfo();
    m.setUser1(id2);
    m.setUser2(id1);
    m.setUser1Hand(te);
    m.setIsActive(a);
    model.addAttribute("m", m);

    if (sep != 0) {
      MatchInfo mi = matchinfoMapper.selectByUser2(id1);
      Match m4 = new Match();
      m4.setUser1(mi.getUser1());
      m4.setUser2(mi.getUser2());
      m4.setUser1Hand(mi.getUser1Hand());
      m4.setUser2Hand(te);
      m4.setIsActive(true);
      model.addAttribute("match001", m4);
      matchMapper.insertMatch(m4);
    } else {
      matchinfoMapper.insertMatchInfo(m);
    }
    return "wait.html";
  }

  @GetMapping("match1")
  public String janken(@RequestParam Integer id, @RequestParam Integer id2, @RequestParam String te, ModelMap model) {
    String cpu = "Gu";
    User un = userMapper.selectById(id);
    model.addAttribute("un", un);
    User unyn = userMapper.selectByNotId(id2);
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
    return "wait.html";
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
