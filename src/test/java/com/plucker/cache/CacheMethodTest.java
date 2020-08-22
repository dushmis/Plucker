package com.plucker.cache;

import com.plucker.annotation.Cached;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class CacheMethodTest {
  @Cached(duration = 2, durationUnit = TimeUnit.HOURS, maximumSize = 100L)
  public Void aVoid(String item) {
    //noinspection MismatchedQueryAndUpdateOfCollection
    List<String> strings = new ArrayList<>();
    strings.add(item);
    return null;
  }

  @Cached(maximumSize = 2000L, duration = 1, durationUnit = TimeUnit.HOURS, recordStats = true)
  public List<?> getListOfStuff(boolean b, String name, Test test) {
    try {
      System.out.println("working");
      TimeUnit.SECONDS.sleep(1);
      System.out.println("working-ed");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }


  @Cached(maximumSize = 2000L, duration = 5, expireAfterWrite = true, recordStats = true)
  public List<?> getListOfStuff(String name) {
    try {
      System.out.println("working = " + new Date());
      TimeUnit.SECONDS.sleep(10);
      System.out.println("working-ed = " + new Date());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void name() {
    Assert.assertTrue(true);
  }
}