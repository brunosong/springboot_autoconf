package com.brunosong.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class BrunoConfigurationPropertyImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public BrunoConfigurationPropertyImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        MultiValueMap<String, Object> attr = importingClassMetadata.getAllAnnotationAttributes(EnableBrunoConfigurationProperties.class.getName());
        Class classes = (Class)attr.getFirst("classes");

        return new String[] { classes.getName() };

    }
}
