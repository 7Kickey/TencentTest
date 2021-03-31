package src;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(transform(in.nextLine()));
    }

    public static String transform(String string) {
        String nums = "零壹贰叁肆伍陆柒捌玖";
        String units = "厘分角元拾佰仟万拾佰仟亿拾";
        String res = "";
        int len = 0;
        if (string.indexOf(".") == -1 && string.length() > 10) {
            return "输入数字最多小数点前十位";
        }
        int flag = string.indexOf(".") > 0 ? string.indexOf(".") : string.length();
        if (string.length() - flag > 4) {
            return "输入数字最多小数点后三位数";
        }
        if (flag == 1 && string.charAt(0) == '0') {
            res += "零";
            len++;
        }
        for (int i = 0; i < flag; i++) {
            int num = string.charAt(i) - '0';
            int unit = flag - i + 2;
            if (num == 0) {
                if (i == flag - 1) {
                    res += "元";
                    len++;
                } else if ((unit + 1)%4 == 0 && res.charAt(len - 1) != '零') {
                    res += units.substring(unit,unit + 1);
                    len ++;
                } else if (res.charAt(len - 1) != '零') {
                    res += nums.substring(num, num + 1);
                    len++;
                }
                continue;
            }
            res += nums.substring(num, num + 1);
            res += units.substring(unit,unit + 1);
            len += 2;
        }
        for (int i = flag + 1; i < string.length(); i++) {
            int num = string.charAt(i) - '0';
            int unit = string.length() - i;
            if (num == 0) {
                continue;
            }
            res += nums.substring(num, num + 1);
            res += units.substring(unit,unit + 1);
            len += 2;
        }
        return res;
    }
}
