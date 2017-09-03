package com.test.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by DaiYan on 2017/9/1.
 */
public class GithubRepoPageProcessor implements PageProcessor{
    //1)爬虫的配置
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        //2)定义如何抽取页面信息并保存下来
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
//            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        //3)发现新的url并加入队列
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
    }

    public Site getSite() {
        return site;
    }
}
