package com.iweb.util;

import java.util.Date;

public class LateFiveSecondUtil {


  public static void lateTime() {
      long timeStampStart = new Date().getTime();
      int beforeDiff = 0;
      while (true) {
          long timeStampEnd = new Date().getTime();
          if ((timeStampEnd - timeStampStart) % 1000 == 0 && beforeDiff != (5 - (timeStampEnd - timeStampStart) / 1000)) {
              System.out.println("还有" + (5 - (timeStampEnd - timeStampStart) / 1000) + "秒返回");
              beforeDiff = (int) (5 - (timeStampEnd - timeStampStart) / 1000);

          }
          if ((5 - (timeStampEnd - timeStampStart) / 1000) == 0) {
              return;
          }
      }
  }


}
