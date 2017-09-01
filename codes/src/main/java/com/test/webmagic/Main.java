package com.test.webmagic;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * Created by DaiYan on 2017/9/1.
 */
public class Main {
    public static void main(String[] args){
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").addPipeline(new ConsolePipeline()).run();
    }
}
