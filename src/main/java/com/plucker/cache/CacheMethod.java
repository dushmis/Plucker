package com.plucker.cache;

import com.plucker.annotation.Cached;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;

/**
 * @author dushyantmistry
 */
public class CacheMethod {
  final Element element;
  final String methodName;
  final TypeMirror returnType;
  final Cached cached;
  final List<? extends TypeMirror> parameterTypes;
  final String cacheName;
  final String cacheFieldName;
  private String methodParameters;


  public CacheMethod(Element element, int i) {
    this.element = element;
    ExecutableType executableType = (ExecutableType) element.asType();
    String methodPackage = element.getEnclosingElement().toString();
    String methodPackageWithoutDots = methodPackage.replaceAll("\\.", "_") + "_";

    returnType = executableType.getReturnType();
    cached = element.getAnnotation(Cached.class);
    parameterTypes = executableType.getParameterTypes();
    methodName = element.getSimpleName().toString();
    cacheName = methodPackageWithoutDots + element.getSimpleName().toString() + "" + i;
    cacheFieldName = element.getSimpleName().toString() + Utils._CACHE_ + i;
  }

  @Override
  public String toString() {
    return "CacheMethod{" + "methodName='" + methodName + '\'' + ", methodParameters='" + methodParameters + '\'' + ", returnType=" + returnType + ", cacheName='" + cacheName + '\'' + ", cacheFieldName='" + cacheFieldName + '\'' + '}';
  }
}