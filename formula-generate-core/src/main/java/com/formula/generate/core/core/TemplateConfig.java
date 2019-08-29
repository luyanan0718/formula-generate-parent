package com.formula.generate.core.core;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2019/8/26
 * <p>模板配置文件</p>
 **/
public class TemplateConfig extends HashMap {


    /**
     * <p>需要继承的类,带报名</p>
     *
     * @author luyanan
     * @since 2019/8/26
     */
    private String extendsSuperClass;


    /**
     * <p>需要导入的包</p>
     *
     * @author luyanan
     * @since 2019/8/26
     */
    private Set<String> imports;


    /**
     * <p>类上需要加的注解</p>
     *
     * @author luyanan
     * @since 2019/8/26
     */
    private Set<String> annotations;


    /**
     * <p>生成的文件名称,也就是类名</p>
     *
     * @author luyanan
     * @since 2019/8/28
     */
    private String className;


    /**
     * <p>类的包路径</p>
     *
     * @author luyanan
     * @since 2019/8/28
     */
    private String classPkg;


    private String fullClassPkg;

    public String getFullClassPkg() {
        return getClassPkg() + "." + getClassName();
    }

    {
        this.put("imports", new HashSet<>());
        this.put("annotations", new HashSet<>());
    }


    public String getExtendsSuperClass() {
        return (String) this.get("extendsSuperClass");
    }

    public TemplateConfig setExtendsSuperClass(String extendsSuperClass) {
        this.put("extendsSuperClass", extendsSuperClass);
        return this;
    }

    public Set<String> getImports() {
        return (Set<String>) this.get("imports");
    }

    public TemplateConfig setImports(String... imports) {
        Set<String> importSet = getImports();
        importSet.addAll(Arrays.stream(imports).collect(Collectors.toList()));
        return this;
    }

    public Set<String> getAnnotations() {
        return (Set<String>) this.get("annotations");
    }

    public TemplateConfig setAnnotations(String... annotations) {
        Set<String> annotationsSet = getAnnotations();
        annotationsSet.addAll(Arrays.stream(annotations).collect(Collectors.toList()));
        return this;
    }

    public TemplateConfig setImports(Collection<String> imports) {
        Set<String> importSet = getImports();
        importSet.addAll(imports);
        return this;
    }

    public TemplateConfig setAnnotations(Collection<String> annotations) {
        Set<String> annotationSet = getAnnotations();
        annotationSet.addAll(annotations);
        return this;
    }


    public String getClassName() {
        return (String) this.get("className");
    }

    public TemplateConfig setClassName(String className) {
        this.put("className", className);
        return this;
    }

    public String getClassPkg() {
        return (String) this.get("classPkg");
    }

    public TemplateConfig setClassPkg(String classPkg) {
        this.put("classPkg", classPkg);
        return this;
    }
}
