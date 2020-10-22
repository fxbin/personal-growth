package cn.fxbin.record.basic.stream;

/**
 * SpliteratorDemo
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/10/22 16:10
 */
public class SpliteratorDemo {

    public static void main(String[] args) {
        final String words = "China to continue improving its special transfer payment mechanism";
        System.out.println(countWordsIteratively(words));
    }


    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter ++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

}
