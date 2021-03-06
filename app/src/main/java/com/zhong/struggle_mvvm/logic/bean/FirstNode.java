package com.zhong.struggle_mvvm.logic.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/9/24 9:37
 * @Description TODO
 */
public class FirstNode {
    @Expose
    private boolean folded = true;

    /**
     * name : 北京市
     * city : [{"name":"北京市","area":["东城区","西城区","海淀区","朝阳区","丰台区","石景山区","门头沟区","通州区","顺义区","房山区","大兴区","昌平区","怀柔区","平谷区","密云区","延庆区"]}]
     */

    private String name;
    private List<SecondNode> city;

    public boolean isFolded() {
        return folded;
    }

    public void setFolded(boolean folded) {
        this.folded = folded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecondNode> getCity() {
        return city;
    }

    public void setCity(List<SecondNode> city) {
        this.city = city;
    }
}
