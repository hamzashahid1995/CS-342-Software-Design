import java.util.Scanner;
//
// Hamza Shahid
// hshahid
// CS 342 - Term Project Part 5
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