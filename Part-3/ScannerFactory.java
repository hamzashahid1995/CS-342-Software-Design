import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 3
//
public class ScannerFactory
{
    private static Scanner keyboardScanner = null;

    public static Scanner getKeyboardScanner()
    {
        if (keyboardScanner == null)
            keyboardScanner = new Scanner(System.in);
        return keyboardScanner;
    }

    private ScannerFactory()
    {
    }
}
