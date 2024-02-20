package com.brunosong.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class BrunoImportSelector implements ImportSelector {

    private final ClassLoader classloader;

    public BrunoImportSelector(ClassLoader classloader) {
        this.classloader = classloader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        List<String> list = new ArrayList<>();
        ImportCandidates.load(BrunoAutoConfiguration.class, classloader).forEach(list::add);

        return list.toArray(new String[0]);
    }

}
