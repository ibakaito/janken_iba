package oit.is.z1082.kaizi.janken.model;

public class Janken {
  String cpu = "Gu";
  String result;

  // gu = 1;ch=2;pa=3;
  public Janken(String player) {
    int p = 0;
    int c = 0;
    p = te(player);
    c = te(cpu);
    if (p == c) {
      result = "Draw!";
    } else if (p == 1 && c == 2 || p == 2 && c == 3 || p == 3 && c == 1) {
      result = "You Win!";
    } else {
      result = "You Lose!";
    }
  }

  public int te(String te) {
    if (te == "Gu") {
      return 1;
    }
    if (te == "Choki") {
      return 2;
    }
    if (te == "Pa") {
      return 3;
    }
    return 0;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getCpu() {
    return cpu;
  }

  public void setCpu(String cpu) {
    this.cpu = cpu;
  }

}
