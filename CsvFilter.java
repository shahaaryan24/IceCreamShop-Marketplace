import javax.swing.filechooser.FileFilter;
import java.io.File;
/**
 * Project05 -- CsvFilter
 *
 * This class creates the filter that
 * lets the user choose only csv files.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

class Utils {
    public static String csv = "csv";
    public static String getExtension(File f) {
        String extension = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            extension = s.substring(i + 1).toLowerCase();
        }
        return extension;
    }
}
/**
 * Project05 -- CsvFilter
 *
 * This class creates the filter that
 * lets the user choose only csv files.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class CsvFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            return extension.equals(Utils.csv);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "CSV Files";
    }
}
