package org.conan.rent.read;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindCoordinate {
    
    public List<String> coordAndWrite(List<String> list, String output) throws IOException {
        FileWriter fw = new FileWriter(output, true);
        List<String> l = new ArrayList<String>();
        for (String s : list) {
            if (s.trim().equals("") || s.equals("NONE")) {
                continue;
            }
            Map<String, String> map = GoogleMap.address2point(s);
            map.get("longitude");
            map.get("latitude");
            String line = s + "," + map.get("longitude") + "," + map.get("latitude");
            l.add(line);
            System.out.println(line);
            fw.write(line + "\n");
            fw.flush();
        }
        fw.close();
        return l;
    }
}
