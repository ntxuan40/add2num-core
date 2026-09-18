package com.xuan.add2num;
public class MyBigNumber {

    public String sum(String stn1, String stn2) {

        int i = stn1.length() - 1;
        int j = stn2.length() - 1;

        int carry = 0;

        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0 || carry != 0) {

            int digit1 = 0;
            int digit2 = 0;

            if (i >= 0) {
                digit1 = stn1.charAt(i) - '0';
            }

            if (j >= 0) {
                digit2 = stn2.charAt(j) - '0';
            }

            int total = digit1 + digit2 + carry;

            int digit = total % 10;
            carry = total / 10;

            result.append(digit);

            i--;
            j--;
        }

        return result.reverse().toString();
    }
}