# Level 1 - Customised exceptions and error control
### What is the difference between a checked exception and an unchecked exception (such as `RuntimeException`)?

The main difference lies in when the compiler requires the exception to be handled:
- Checked: The compiler requires the exception to be caught using a `try-catch` block or declared using `throws`. These inherit from `Exception` but not from `RuntimeException`. Example: `IOException`.
- Unchecked: The compiler does not require them to be caught or declared. These are exceptions that inherit from `RuntimeException`. Examples: `NullPointerException`, `IllegalArgumentException`, `IndexOutOfBoundsException`.

Example:
```Java
// Checked: must be handled or declared
public void readFile() throws IOException {
FileReader f = new FileReader("file.txt"); 
}

// Unchecked: handling is not mandatory
public void printName(String name) {
    System.out.println(name.length());   // NullPointerException
}
```

In summary: checked exceptions are verified at compile time and must be explicitly handled; unchecked exceptions do not carry this requirement and typically represent programming errors or situations that do not need to be declared in the method signature.
