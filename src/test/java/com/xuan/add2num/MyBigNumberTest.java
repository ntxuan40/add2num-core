package com.xuan.add2num;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyBigNumberTest {

    @Test
    void shouldAddTwoNumbers() {

        MyBigNumber myBigNumber = new MyBigNumber();

        String result = myBigNumber.sum("1234", "897");

        assertEquals("2131", result);
    }
    private final MyBigNumber myBigNumber = new MyBigNumber();

    @Test
    void shouldAddSingleDigitNumbers() {
        assertEquals("3", myBigNumber.sum("1", "2"));
    }

    @Test
    void shouldAddNormalNumbers() {
        assertEquals("579", myBigNumber.sum("123", "456"));
    }

    @Test
    void shouldAddNumbersWithDifferentLengths() {
        assertEquals("2131", myBigNumber.sum("1234", "897"));
    }

    @Test
    void shouldHandleCarry() {
        assertEquals("10", myBigNumber.sum("9", "1"));
    }

    @Test
    void shouldHandleMultipleCarries() {
        assertEquals("100", myBigNumber.sum("99", "1"));
    }

    @Test
    void shouldHandleCarriesAcrossAllDigits() {
        assertEquals("1998", myBigNumber.sum("999", "999"));
    }

    @Test
    void shouldAddLargeNumbers() {
        assertEquals("1111111110", myBigNumber.sum("123456789", "987654321"));
    }

    @Test
    void shouldHandleZeroAndZero() {
        assertEquals("0", myBigNumber.sum("0", "0"));
    }

    @Test
    void shouldHandleAddingZeroToNumber() {
        assertEquals("123", myBigNumber.sum("123", "0"));
    }

    @Test
    void shouldHandleAddingNumberToZero() {
        assertEquals("123", myBigNumber.sum("0", "123"));
    }
    
    // ==========================================
    // TEST CASE VIẾT THÊM (Để phủ nhánh listener == null)
    // ==========================================
    @Test
    void testSumWithNullListener() {
        // 1. Khởi tạo Object chứa hàm sum
    	MyBigNumber calculator = new MyBigNumber();

        // 2. Chuẩn bị dữ liệu đầu vào hợp lệ để vượt qua hàm validate()
        String stn1 = "10"; 
        String stn2 = "20";
        ProgressListener listener = null; // Ép giá trị null ở đây

        // 3. Gọi hàm sum. Khi listener = null, code sẽ chạy vào trong khối if
        // (Thay "String result =" bằng kiểu dữ liệu thực tế nếu cần)
        String result = calculator.sum(stn1, stn2, listener);

        // 4. Assert (Kiểm tra kết quả trả về của hàm sum có đúng kỳ vọng không)
        // Ví dụ nếu hàm trả về chuỗi "30":
        assertEquals("30", result); 
    }

    @Test
    void shouldExecuteDebugLoggingBranch() {
        MyBigNumber calculator = new MyBigNumber();

        String result = calculator.sum("123", "456");

        assertEquals("579", result);
    }
    

}