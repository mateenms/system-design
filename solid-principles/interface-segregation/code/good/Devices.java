package solid.isp.good;

/**
 * GOOD EXAMPLE: each device implements only the capabilities it really has.
 * No dummy methods, no UnsupportedOperationException.
 *
 * @author Mateen
 */
public class Devices {

    /** Only prints. */
    static class BasicPrinter implements Printable {
        @Override
        public void print(String doc) { System.out.println("BasicPrinter printing " + doc); }
    }

    /** Prints and scans. */
    static class ScannerPrinter implements Printable, Scannable {
        @Override
        public void print(String doc) { System.out.println("ScannerPrinter printing " + doc); }

        @Override
        public void scan(String doc) { System.out.println("ScannerPrinter scanning " + doc); }
    }

    /** Does everything. */
    static class AllInOneMachine implements Printable, Scannable, Faxable {
        @Override
        public void print(String doc) { System.out.println("AllInOne printing " + doc); }

        @Override
        public void scan(String doc) { System.out.println("AllInOne scanning " + doc); }

        @Override
        public void fax(String doc) { System.out.println("AllInOne faxing " + doc); }
    }

    /**
     * Clients depend only on the capability they need.
     * This method works with ANY Printable and knows nothing about scanning or faxing.
     */
    static void printReport(Printable device) {
        device.print("report.pdf");
    }

    public static void main(String[] args) {
        printReport(new BasicPrinter());
        printReport(new ScannerPrinter());
        printReport(new AllInOneMachine());

        // new BasicPrinter().scan("x");   // compile error: BasicPrinter has no scan()
    }
}
