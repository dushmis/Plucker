# AnnotationCached
Cache java methods with just annotation

![Java CI with Maven](https://github.com/dushmis/AnnotationCached/workflows/Java%20CI%20with%20Maven/badge.svg)

annotate any method with `@Cached`

```
  @Cached(duration = 1, durationUnit = TimeUnit.HOURS, maximumSize = 10000L)
  public Map<String, Object> methodThatTakesTooMuchTime(String objId) throws InterruptedException {
    final Map<String, Object> stringObjectMap = Collections.synchronizedMap(new HashMap<String, Object>());
    stringObjectMap.put("name", "steve");
    stringObjectMap.put("data", new HashSet<String>() {{
      add("one");
      add("two");
    }});
    TimeUnit.SECONDS.sleep(3);
    return stringObjectMap;
  }
```

use it as mentioned below

```
  public void test_methodThatTakesTooMuchTime() {
    assertTrue(TestCacheService.getInstance().getListOfStuff("key") != null);
    assertTrue(TestCacheService.getInstance().getListOfStuff("x") != null);
    System.out.println("one = " + TestCacheService.getInstance().methodThatTakesTooMuchTime("one"));
    System.out.println("one = " + TestCacheService.getInstance().methodThatTakesTooMuchTime("one"));
  }
```