package com.example.springcloudboot.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class GpImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata arg0) {
        return new String[] {FirstClass.class.getName(), SecondClass.class.getName()};
    }

    
}
