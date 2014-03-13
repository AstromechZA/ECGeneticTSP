import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;


public class to_csv
{

    public static void main(String[] args) throws IOException
    {
        File f = new File(args[0]); // current directory
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.csv")));
        
        File[] files = f.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                
                String path = file.getCanonicalPath();
                if( path.endsWith(".dat") && file.getName().startsWith("run"))
                {
                    BufferedReader br = new BufferedReader(new FileReader(new File(path)));
                    
                    String line = br.readLine();
                    line = br.readLine();
                    do
                    {
                        
                        float v = Float.parseFloat(line);
                        bw.write(""+v);
                        bw.write(";");
                        
                        line = br.readLine();                        
                    }
                    while(line != null);
                    
                    br.close();
                    
                    bw.write("\n");
                }
                
            }
        }
        bw.flush();
        bw.close();
    }

}
