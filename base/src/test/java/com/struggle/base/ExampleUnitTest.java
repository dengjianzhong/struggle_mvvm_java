package com.struggle.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        PushBean<String> bean = new PushBean<>();
        bean.setActionType(1);
        bean.setContent("新订单 1100249 来了快抢，点击查看...");
        bean.setId(1);
        bean.setMsgId("2ebcc9c8-f80f-4a92-84b1-77da17d72a91");
        bean.setTitle("有新订单来了");
        bean.setVoice("3.wav");
        assertEquals(4, 2 + 2);
    }
}