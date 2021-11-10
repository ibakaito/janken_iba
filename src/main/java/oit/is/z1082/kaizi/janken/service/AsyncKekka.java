package oit.is.z1082.kaizi.janken.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z1082.kaizi.janken.model.Match;
import oit.is.z1082.kaizi.janken.model.MatchInfo;
import oit.is.z1082.kaizi.janken.model.MatchMapper;
import oit.is.z1082.kaizi.janken.model.MatchInfoMapper;

@Service
public class AsyncKekka {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  MatchMapper mMapper;
  @Autowired
  MatchInfoMapper miMapper;

  public ArrayList<Match> syncShowMatchList() {
    return mMapper.selectAllMatch();
  }

  public ArrayList<MatchInfo> syncShowMatchInfoList() {
    return miMapper.selectAllMatchInfo();
  }

  @Async
  @Transactional
  public void asyncShowMatchList(SseEmitter emitter) {
    dbUpdated = true;
    try {
      while (true) {
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        Match result = mMapper.selectByAllTrue();
        // mMapper.updateById(mMapper.selectId());
        // miMapper.updateById(miMapper.selectId());
        emitter.send(result);
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowMatchList complete");
  }

}
