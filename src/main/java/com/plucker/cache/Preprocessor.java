package com.plucker.cache;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * @author dushyantmistry
 */
@SupportedAnnotationTypes({"com.plucker.annotation.Cached"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Preprocessor extends AbstractProcessor {

  /**
   * @param annotations annotation that'll br processed
   * @param roundEnv    env for processor
   * @return which to process
   */
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    annotations.stream()
            .map(roundEnv::getElementsAnnotatedWith)
            .forEach(elementsAnnotatedWith -> writeBuilderFile(elementsAnnotatedWith, annotations, roundEnv));
    return true;
  }


  /**
   * @param elementsAnnotatedWith annotation that'll br processed
   * @param annotations           annotations
   * @param roundEnv              env
   */
  private void writeBuilderFile(Set<? extends Element> elementsAnnotatedWith, Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Map<String, ? extends List<? extends Element>> collect = elementsAnnotatedWith.stream()
            .collect(Collectors.groupingBy((x) -> x.getEnclosingElement().toString()));
    collect.forEach((k, v) -> {
      try {
        String builderClassName = k + Utils.SUFFIX_CLASS_NAME;
        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(builderClassName);
        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
          CacheMethodWriter cacheMethodWriter = new CacheMethodWriter(out, k);
          cacheMethodWriter.setProcessingEnvironment(processingEnv);
          cacheMethodWriter.setAnnotations(annotations);
          cacheMethodWriter.setRoundEnvironment(roundEnv);
          cacheMethodWriter.writePackage();
          cacheMethodWriter.writeImports();
          cacheMethodWriter.writeClass();
          List<CacheMethod> cacheMethods = new ArrayList<>();
          int[] i = {0};
          v.forEach((element) -> {
            if (element.getModifiers().contains(Modifier.PUBLIC)) {
              CacheMethod cacheMethod = new CacheMethod(element, i[0]++);
              cacheMethods.add(cacheMethod);
            }
          });
          cacheMethodWriter.setCacheMethods(cacheMethods.toArray(new CacheMethod[0]));
          cacheMethodWriter.writeFields();
          cacheMethodWriter.writeConstructor();
          cacheMethodWriter.endConstructor();
          //
          cacheMethodWriter.writeMethods();
          cacheMethodWriter.testMethod1();
          cacheMethodWriter.testMethod();
          cacheMethodWriter.endClass();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    });
  }
}

