import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class App {

    private static String initFilePath;

    public static void main(String[] args) {
        System.out.println("+-- root");
        List<String> skip = new ArrayList<>();
        skip.add(".idea");
        initFilePath = System.getProperty("user.dir");
        createDirTree(initFilePath,skip);
    }

    private static void createDirTree(String path,List<String> skip){
        File dir = new File(path);
        File[] files = dir.listFiles();

        for (File file : files) {
            String fileName = file.getName();
            String childFilePath = file.getPath();
            Boolean isDir = file.isDirectory();
            String[] split = childFilePath.replace(initFilePath, "").split(Matcher.quoteReplacement(File.separator));
            long depths = Arrays.stream(split).filter(string -> !string.isEmpty()).count() - 1;
            String fileType = isDir ? "+" : "`";
            String outFilePath = "| ".repeat((int) depths) + fileType + "-- "+fileName;
            System.out.println(outFilePath);
            if(skip.contains(fileName)){
                continue;
            }
            if(isDir){
                createDirTree(childFilePath,skip);
            }

        }
    }
}