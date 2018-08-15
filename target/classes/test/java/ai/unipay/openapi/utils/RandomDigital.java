package ai.unipay.openapi.utils;

import java.util.Random;

/**
 * 生成随机数
 * Created by zhenghang on 2016/4/22.
 */
public class RandomDigital {

    private static final char[] codeNumberSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 获取纯数字随机数
     *
     * @param number 随机数长度
     * @return
     */
    public static String randomOnlyNumber(int number) {
        return random(number, codeNumberSequence);
    }

    /**
     * 生成随机数
     *
     * @param number   随机数长度
     * @param sequence 需随机的字符串
     * @return
     */
    public static String random(int number, char[] sequence) {
        String stringBuilder = "";//保存随机获取的数
        for (int i = 0; i < number; i++) {
            stringBuilder += String.valueOf(new Random().nextInt(sequence.length));
        }
        return stringBuilder;
    }
}
