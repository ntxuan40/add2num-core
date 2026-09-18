# Add2Num Core

A Java core library for adding two very large positive numbers represented as `String`.

The implementation performs addition digit by digit from right to left, following the same algorithm used for manual elementary-school addition.

## 1. Overview

`add2num-core` provides a simple core function:

```java
String sum(String stn1, String stn2)
```

The function accepts two numbers represented as `String` and returns their sum as a `String`.

### Example

```text
Input:
stn1 = "1234"
stn2 = "897"

Output:
"2131"
```

The implementation does not use `BigInteger` or other big-number libraries to perform the actual addition.

---

## 2. Requirements

The core function must:

* Accept two numbers as `String`.
* Return the result as `String`.
* Support numbers larger than the range of Java primitive numeric types.
* Perform addition digit by digit from right to left.
* Handle carry between digits.
* Handle input numbers with different lengths.
* Continue processing when a final carry remains.
* Provide unit tests for the core functionality.
* Be buildable by another developer after cloning the repository.

### Input assumption

The current implementation assumes that both input parameters:

* are valid;
* contain digits only (`0-9`);
* represent non-negative numbers.

Input validation is outside the current scope.

---

## 3. Algorithm

The addition follows the standard manual addition algorithm.

For example:

```text
   1234
+   897
-------
   2131
```

The calculation is performed from right to left:

```text
4 + 7       = 11 → digit = 1, carry = 1
3 + 9 + 1   = 13 → digit = 3, carry = 1
2 + 8 + 1   = 11 → digit = 1, carry = 1
1 + 0 + 1   =  2 → digit = 2, carry = 0
```

The digits are collected in reverse order:

```text
1312
```

and reversed before returning:

```text
2131
```

### Algorithm steps

1. Set pointers to the last character of both input strings.
2. Initialize `carry = 0`.
3. While either input still has digits or a carry remains:

    * Read the current digit from the first number, or use `0` if the number is exhausted.
    * Read the current digit from the second number, or use `0` if the number is exhausted.
    * Calculate `total = digit1 + digit2 + carry`.
    * Calculate the result digit using `total % 10`.
    * Calculate the next carry using `total / 10`.
    * Append the result digit.
    * Move both pointers to the left.
4. Reverse the generated result.
5. Return the result as a `String`.

### Pseudocode

```text
function sum(stn1, stn2):

    i = last index of stn1
    j = last index of stn2
    carry = 0
    result = empty string

    while i >= 0 OR j >= 0 OR carry != 0:

        digit1 = digit at i, or 0 if i < 0
        digit2 = digit at j, or 0 if j < 0

        total = digit1 + digit2 + carry

        digit = total % 10
        carry = total / 10

        append digit to result

        i = i - 1
        j = j - 1

    reverse result

    return result
```

---

## 4. Project Structure

```text
add2num-core/
├── README.md
├── pom.xml
├── .gitignore
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── yourname/
    │               └── add2num/
    │                   └── MyBigNumber.java
    │
    └── test/
        └── java/
            └── com/
                └── yourname/
                    └── add2num/
                        └── MyBigNumberTest.java
```

---

## 5. Prerequisites

The project requires:

* Java JDK
* Apache Maven

Recommended versions:

```text
Java: 17 or later
Maven: 3.8 or later
```

Verify the installed versions:

```bash
java -version
```

```bash
mvn -version
```

---

## 6. Build

Clone the repository and enter the project directory:

```bash
git clone <repository-url>
cd add2num-core
```

Build the project:

```bash
mvn clean package
```

This command:

1. Cleans previous build output.
2. Compiles the source code.
3. Compiles the test code.
4. Runs the unit tests.
5. Packages the application/library.

---

## 7. Run Unit Tests

Run all tests:

```bash
mvn test
```

The test suite verifies:

* Basic addition.
* Addition of numbers with different lengths.
* Carry handling.
* Multiple consecutive carries.
* Zero handling.
* Large-number addition.

Expected result:

```text
Tests run: 11
Failures: 0
Errors: 0
Skipped: 0
```

The exact number may change if additional test cases are added.

---

## 8. Test Cases

The current test suite includes cases such as:

| Test                | Input                   | Expected     |
| ------------------- | ----------------------- | ------------ |
| Single digit        | `1 + 2`                 | `3`          |
| Normal numbers      | `123 + 456`             | `579`        |
| Different lengths   | `1234 + 897`            | `2131`       |
| Carry               | `9 + 1`                 | `10`         |
| Multiple carry      | `99 + 1`                | `100`        |
| Consecutive carries | `999 + 999`             | `1998`       |
| Large numbers       | `123456789 + 987654321` | `1111111110` |
| Zero                | `0 + 0`                 | `0`          |
| Number + zero       | `123 + 0`               | `123`        |
| Zero + number       | `0 + 123`               | `123`        |

---

## 9. Usage

The core function can be used from another Java application.

Example:

```java
MyBigNumber myBigNumber = new MyBigNumber();

String result = myBigNumber.sum(
        "12345678901234567890",
        "98765432109876543210"
);

System.out.println(result);
```

Expected output:

```text
111111111011111111100
```

---

## 10. Design Notes

### Why `String`?

The function accepts numbers as `String` because the input may be larger than the maximum value supported by Java primitive types such as `int` and `long`.

The complete number is never converted into an `int` or `long`.

### Why `int` is still used?

`int` is used only for individual digits and intermediate values:

```text
digit1: 0-9
digit2: 0-9
carry:  0-1
total:  0-19
```

Therefore, the actual large numbers remain represented as `String`.

### Why not `BigInteger`?

The purpose of this task is to implement the addition algorithm manually, digit by digit, from right to left.

Therefore, `BigInteger` is not used to perform the actual addition.

### Why use `StringBuilder`?

The result is generated from right to left.

`StringBuilder` allows each calculated digit to be appended efficiently. The final result is then reversed before returning.

---

## 11. Input Assumptions and Scope

The current version assumes:

```text
- Input is not null.
- Input contains digits only.
- Input represents non-negative numbers.
```

Input validation is not currently implemented because it is outside the scope of the current requirement.

If input validation becomes a future requirement, it should be added together with corresponding unit tests.

---

## 12. Limitations

The current version does not define special behavior for:

* `null` input.
* Empty strings.
* Negative numbers.
* Decimal numbers.
* Non-numeric characters.
* Leading-zero normalization.

These behaviors should be clarified as requirements before implementing additional validation or normalization rules.

---

## 13. Release

The version prepared for evaluation is:

```text
0.0.1
```

The Task 1 implementation is released on the branch:

```text
core
```

Recommended Git release structure:

```text
Branch:
core

Tag:
0.0.1
```

---

## 14. Verification Before Handover

Before handing the project to another team, run:

```bash
mvn clean test
```

and:

```bash
mvn clean package
```

The project should compile successfully and all unit tests should pass.

The repository should also contain:

```text
README.md
pom.xml
src/main/java/...
src/test/java/...
```

so another developer can clone the repository and build it without relying on the original developer's local environment.

---

## 15. Future Integration

This core module is designed to be reused by another application.

For example, the web application in Task 2 can depend on this module rather than implementing the addition algorithm again.

Conceptually:

```text
+-----------------------+
|    add2num-web        |
|   Spring Boot App     |
+-----------+-----------+
            |
            | uses
            v
+-----------------------+
|    add2num-core       |
|     MyBigNumber       |
|       sum()           |
+-----------------------+
```

This separation keeps the calculation logic independent from the user interface.
