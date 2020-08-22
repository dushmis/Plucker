package com.plucker.cache;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author dushyantmistry
 */
public class Utils {
  static final String SUFFIX_CLASS_NAME = "CacheService";
  static final String _CACHE_ = "_cache_";
  static final Map<String, Object[]> CLASS_MAP = new WeakHashMap<>();
  /**
   *
   */
  static final Map<String, Boolean> DEFAULT_CONS_CHEAP_CACHE = new WeakHashMap<>();
  /**
   *
   */
  static Map<String, Boolean> GETINST_METH_CHEAP_CACHE = new WeakHashMap<>();
  /**
   *
   */
  static Map<String, Boolean> CHEAP_CLASSNAME_CACHE = new WeakHashMap<>();

  static {
    CLASS_MAP.put("boolean", new Object[]{"Boolean", boolean.class, Boolean.class});
    CLASS_MAP.put("char", new Object[]{"Character", char.class, Character.class});
    CLASS_MAP.put("byte", new Object[]{"Byte", byte.class, Byte.class});
    CLASS_MAP.put("short", new Object[]{"Short", short.class, Short.class});
    CLASS_MAP.put("int", new Object[]{"Integer", int.class, Integer.class});
    CLASS_MAP.put("long", new Object[]{"Long", long.class, Long.class});
    CLASS_MAP.put("float", new Object[]{"Float", float.class, Float.class});
    CLASS_MAP.put("double", new Object[]{"Double", double.class, Double.class});
    CLASS_MAP.put("void", new Object[]{"Void", void.class, Void.class});
  }
}
