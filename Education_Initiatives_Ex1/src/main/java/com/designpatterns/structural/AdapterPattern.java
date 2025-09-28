package com.designpatterns.structural;

import com.designpatterns.utils.UserInput;

interface ModernPrinter {
    void print(String text);
}

class LegacyPrinter {
    public void printText(String text) {
        System.out.println("Legacy Printer: " + text);
    }
}

class PrinterAdapter implements ModernPrinter {
    private LegacyPrinter legacyPrinter = new LegacyPrinter();

    @Override
    public void print(String text) {
        legacyPrinter.printText(text);
    }
}

public class AdapterPattern {
    public static void run() {
        ModernPrinter printer = new PrinterAdapter();
        String message = UserInput.getString("Enter text to print: ");
        printer.print(message);
    }
}
