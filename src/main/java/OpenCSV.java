import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.nio.file.Path;
import java.util.List;

public class OpenCSV {
    public void writeAllLines(List<String[]> lines, String path) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(path));
        writer.writeAll(lines);
        writer.close();
    }
}
