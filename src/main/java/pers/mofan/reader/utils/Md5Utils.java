package pers.mofan.reader.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author mofan
 * @date 2021/1/18 20:31
 */
public class Md5Utils {
    public static String md5Digest(String source, Integer salt) {

        /*混淆元数据*/
        char[] chars = source.toCharArray();
        for (char aChar : chars) {
            aChar = (char) (aChar + salt);
        }

        String target = new String(chars);
        return DigestUtils.md5Hex(target);
    }
}
