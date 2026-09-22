package com.xuan.add2num;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides addition functionality for very large numbers represented as strings.
 *
 * <p>The addition is performed digit by digit from right to left without
 * converting the entire input into a numeric type such as {@code long}
 * or {@code BigInteger}.</p>
 */
public class MyBigNumber {

    private static final Logger logger =
            LoggerFactory.getLogger(MyBigNumber.class);

    /**
     * Adds two non-negative integer numbers represented as strings.
     *
     * <p>Each digit is processed from right to left, and carry values are
     * propagated to the next digit. The method supports numbers with
     * different lengths.</p>
     *
     * @param stn1 the first number represented as a numeric string
     * @param stn2 the second number represented as a numeric string
     * @return the sum of {@code stn1} and {@code stn2} as a numeric string
     */
    public String sum(String stn1, String stn2) {
        return sum(stn1, stn2, progress -> {
            // No progress listener.
        });
    }

    /**
     * Adds two non-negative integer numbers represented as strings.
     *
     * <p>Each digit is processed from right to left, and carry values are
     * propagated to the next digit. The method supports numbers with
     * different lengths.</p>
     *
     * @param stn1 the first number represented as a numeric string
     * @param stn2 the second number represented as a numeric string
     * @param listener show process status
     * @return the sum of {@code stn1} and {@code stn2} as a numeric string
     */
    public String sum(String stn1, String stn2, ProgressListener listener) {

        validate(stn1);
        validate(stn2);

        if (listener == null) {
            listener = progress -> {
            };
        }

        int i = stn1.length() - 1;
        int j = stn2.length() - 1;

        int carry = 0;
        int step = 1;

        int digit1;
        int digit2;
        int previousCarry;
        int total;
        int digit;
        int progress;

        int maxLength = Math.max(stn1.length(), stn2.length());

        char[] result = new char[maxLength + 1];

        int position = result.length - 1;

        while (i >= 0 || j >= 0 || carry != 0) {

            digit1 = 0;
            digit2 = 0;

            if (i >= 0) {
                digit1 = stn1.charAt(i) - '0';
            }

            if (j >= 0) {
                digit2 = stn2.charAt(j) - '0';
            }

            previousCarry = carry;

            total = digit1 + digit2 + previousCarry;

            digit = total % 10;
            carry = total / 10;

            result[position] = (char) ('0' + digit);

            progress = (step * 100) / maxLength;
            listener.onProgress(progress);

            if (logger.isDebugEnabled()) {
                logger.debug(
                        "Step {}: digit1={}, digit2={}, previousCarry={}, total={}, digit={}, nextCarry={}",
                        step,
                        digit1,
                        digit2,
                        previousCarry,
                        total,
                        digit,
                        carry
                );
            }

            i--;
            j--;
            position--;
            step++;
        }

        int start = position + 1;

        String finalResult =
                new String(result, start, result.length - start);

        logger.info(
                "Addition completed: {} + {} = {}",
                stn1,
                stn2,
                finalResult
        );

        return finalResult;
    }

    private void validate(String number) {

        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException(
                    "Number must not be null or empty.");
        }

        for (int i = 0; i < number.length(); i++) {

            char c = number.charAt(i);

            if (c < '0' || c > '9') {
                throw new IllegalArgumentException(
                        "Number must contain digits only.");
            }
        }
    }

}