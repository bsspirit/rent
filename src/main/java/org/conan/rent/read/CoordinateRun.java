package org.conan.rent.read;

import java.io.IOException;
import java.util.List;

public class CoordinateRun {
    
    public static void main(String[] args) throws IOException {
        String file = "metadata/data/location.csv";
        String output = "metadata/data/output.csv";
        if (args.length == 2) {
            file = args[0];
            output = args[1];
        }
        
        ReadCSV r = new ReadCSV();
        List<String> list = r.csv(file, false);
        FindCoordinate f = new FindCoordinate();
        f.coordAndWrite(list, output);
    }
    
}
