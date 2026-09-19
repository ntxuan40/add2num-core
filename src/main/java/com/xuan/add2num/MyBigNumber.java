package com.xuan.add2num;
public class MyBigNumber {

    /**
     * Add two non-negative integer strings.
     *
     * This method keeps compatibility with the original Task 1 API.
     */
    public String sum(String stn1, String stn2) {
        return sum(stn1, stn2, progress -> {
            // No progress listener.
        });
    }

    /**
     * Add two large numbers and report real progress.
     *
     * Progress is calculated based on the number of digit-addition steps.
     */
    public String sum(
            String stn1,
            String stn2,
            ProgressListener listener) {

        validate(stn1);
        validate(stn2);

        if (listener == null) {
            listener = progress -> {
            };
        }

        int totalSteps = Math.max(stn1.length(), stn2.length());

        StringBuilder result = new StringBuilder(totalSteps + 1);

        int index1 = stn1.length() - 1;
        int index2 = stn2.length() - 1;

        int carry = 0;
        int completedSteps = 0;

        while (index1 >= 0 || index2 >= 0 || carry != 0) {

            int digit1 = 0;
            int digit2 = 0;

            if (index1 >= 0) {
                digit1 = stn1.charAt(index1) - '0';
                index1--;
            }

            if (index2 >= 0) {
                digit2 = stn2.charAt(index2) - '0';
                index2--;
            }

            int sum = digit1 + digit2 + carry;

            int resultDigit = sum % 10;
            carry = sum / 10;

            result.append(resultDigit);

            /*
             * One progress step corresponds to one digit-addition step.
             */
            completedSteps++;

            int progress;

            if (totalSteps == 0) {
                progress = 100;
            } else {
                progress = completedSteps * 100 / totalSteps;

                if (progress > 100) {
                    progress = 100;
                }
            }

            listener.onProgress(progress);
        }

        return result.reverse().toString();
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