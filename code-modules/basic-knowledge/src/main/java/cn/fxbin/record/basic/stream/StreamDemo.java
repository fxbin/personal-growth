package cn.fxbin.record.basic.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FlatMapDemo
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/21 15:58
 */
public class StreamDemo {

    public static void main(String[] args) {

        // ["Hello", "World"] -> [G, o, d, b, y, e, W, r, l]
        String [] arrayOfWords = {"Goodbye", "World"};
        List<String> uniqueCharacters = Arrays.stream(arrayOfWords)
                .map(w -> w.split(""))
                // 将单词转化成由其字面组成的数组
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);


        // [1, 2, 3, 4, 5] -> [1, 4, 9, 16, 25]
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(squares);

        // [1, 2, 3] & [3, 4] -> [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList());

        // [1, 2, 3] & [3, 4] -> 只返回总和能被3整除的数对
        List<int[]> pairs1 = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i+j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                ).parallel()
                .collect(Collectors.toList());

        Integer product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);
    }


}
