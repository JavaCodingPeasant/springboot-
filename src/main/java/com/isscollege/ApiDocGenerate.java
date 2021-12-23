package com.isscollege;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * 作者：杜丹东
 * 日期：2021/12/16 15:34
 */
public class ApiDocGenerate {
    public static void main(String[] args) {
        System.out.println("生成AIP文档开始...");//接口文档
        DocsConfig config = new DocsConfig();
        config.setProjectPath("F:\\Workspaces_idea\\springboot_xaaut"); // 项目根目录
        config.setProjectName("springboot入门_建大"); // 项目名称
        config.setApiVersion("V1.0"); // 声明该API的版本
        config.setDocsPath("F:/Workspaces_idea/springboot_xaaut/target/API"); // 生成API文档所在目录
        config.setAutoGenerate(Boolean.TRUE); // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
        System.out.println("生成AIP文档结束...");
    }

}
