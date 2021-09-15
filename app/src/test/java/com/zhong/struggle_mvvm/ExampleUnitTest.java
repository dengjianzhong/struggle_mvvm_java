package com.zhong.struggle_mvvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        getSourceData().stream()
                .flatMap((Function<List<String>, Stream<String>>) strings -> {
                    System.out.println("--------------");
                    return strings.stream();
                })
                .forEach(o -> System.out.println(o));

        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(6);
        list.add(25);
        list.add(21);

        List<String> list1 = null;

        List<Integer> integers = Optional.ofNullable(list).orElseGet(new Supplier<List<Integer>>() {
            @Override
            public List<Integer> get() {
                return new ArrayList<>();
            }
        });
        System.out.println();
    }

    /**
     * 获取数据源
     *
     * @return
     */
    private List<List<String>> getSourceData() {
        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<String> childList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                childList.add(i + "-" + j);
            }
            lists.add(childList);
        }

        return lists;
    }
}