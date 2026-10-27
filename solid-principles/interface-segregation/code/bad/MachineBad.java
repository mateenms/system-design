package solid.isp.bad;

/**
 * BAD EXAMPLE: Interface Segregation Principle violation.
 *
 * Problem:
 *   One fat interface forces every device to implement print, scan AND fax.
 *   A basic printer cannot scan or fax, yet it must provide those methods.
 *
 * Why it hurts:
 *   - Dummy methods that throw exceptions (a hidden LSP violation too)
 *   - Callers cannot tell from the type what a device really supports
 *   - Adding a method (e.g. email) forces changes in every implementor
 *
 * @author Mateen
 */
interface Machine {
    void print(String doc);
    void scan(String doc);
    void fax(String doc);
}

class AllInOneMachine implements Machine {
    public void print(String doc) { System.out.println("Printing " + doc); }
    public void scan(String doc)  { System.out.println("Scanning " + doc); }
    public void fax(String doc)   { System.out.println("Faxing " + doc); }
}

class BasicPrinter implements Machine {
    public void print(String doc) { System.out.println("Printing " + doc); }

    // Forced to implement methods it cannot support
    public void scan(String doc) { throw new UnsupportedOperationException("Cannot scan"); }
    public void fax(String doc)  { throw new UnsupportedOperationException("Cannot fax"); }
}

public class MachineBad {
    public static void main(String[] args) {
        Machine printer = new BasicPrinter();
        printer.print("report.pdf");   // fine
        printer.scan("report.pdf");    // crashes at runtime
    }
}
