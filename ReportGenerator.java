import java.io.*;
import java.util.*;
import java.text.*;

public class ReportGenerator {

    private String path = "C:\\reports\\output.csv";
    private List data;

    public ReportGenerator(List d) {
        this.data = d;
    }

    public void generate() {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("ID,Name,Amount,Date\n");
            for (Object o : data) {
                Object[] row = (Object[]) o;
                String line = row[0] + "," + row[1] + "," + row[2] + "," + row[3] + "\n";
                fw.write(line);
            }
            fw.close();
        } catch (IOException e) {
            // ignore
        }
    }

    public void generateHtml() {
        try {
            FileWriter fw = new FileWriter("C:\\reports\\output.html");
            fw.write("<html><body><table>");
            for (Object o : data) {
                Object[] row = (Object[]) o;
                // write row - user supplied name goes directly into HTML
                fw.write("<tr><td>" + row[0] + "</td><td>" + row[1] + "</td><td>" + row[2] + "</td></tr>");
            }
            fw.write("</table></body></html>");
            fw.close();
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public static String formatDate(long timestamp) {
        Date d = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(d);
    }

    public static double sumAmounts(List rows) {
        double s = 0;
        for (int i = 0; i < rows.size(); i++) {
            Object[] r = (Object[]) rows.get(i);
            s += Double.valueOf(r[2].toString());
        }
        return s;
    }

    public static double avgAmount(List rows) {
        double s = 0;
        for (int i = 0; i < rows.size(); i++) {
            Object[] r = (Object[]) rows.get(i);
            s += Double.valueOf(r[2].toString());
        }
        return s / rows.size();
    }

    public static double maxAmount(List rows) {
        double max = 0;
        for (int i = 0; i < rows.size(); i++) {
            Object[] r = (Object[]) rows.get(i);
            double v = Double.valueOf(r[2].toString());
            if (v > max) max = v;
        }
        return max;
    }

    public static void writeLog(String msg) throws Exception {
        FileWriter fw = new FileWriter("C:\\reports\\log.txt", true);
        fw.write(new Date() + " - " + msg + "\n");
        fw.close();
    }

    public static void main(String[] args) throws Exception {
        List rows = new ArrayList();
        rows.add(new Object[]{1, "<script>alert(1)</script>", 200.0, System.currentTimeMillis()});
        rows.add(new Object[]{2, "Bob", 450.5, System.currentTimeMillis()});

        ReportGenerator rg = new ReportGenerator(rows);
        rg.generate();
        rg.generateHtml();

        writeLog("Report generated, total=" + sumAmounts(rows));
    }
}
