package com.formula.generate.core.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>全局配置文件</p>
 **/


public class GlobalConfig {


    /**
     * <p>作者</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String author;


    /**
     * <p>版本号</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String version;


    /**
     * <p>父包路径</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String parentPkg;


    /**
     * <p>输出路径</p>
     *
     * @author luyanan
     * @since 2019/8/27
     */
    private String outPutFile;


    /**
     * <p>当前项目</p>
     *
     * @author luyanan
     * @since 2019/8/28
     */
    private String outPutFileInThisProject;


    public String getAuthor() {
        return author;
    }

    public GlobalConfig setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public GlobalConfig setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getParentPkg() {
        return parentPkg;
    }

    public GlobalConfig setParentPkg(String parentPkg) {
        this.parentPkg = parentPkg;
        return this;
    }

    public String getOutPutFile() {
        return outPutFile;
    }

    public GlobalConfig setOutPutFile(String outPutFile) {
        this.outPutFile = outPutFile;
        return this;
    }

    public String getOutPutFileInThisProject() {
        return this.outPutFile;
    }

    public GlobalConfig setOutPutFileInThisProject() {
        this.outPutFile = System.getProperty("user.dir") + "/src/main/java/";
        return this;
    }
}
