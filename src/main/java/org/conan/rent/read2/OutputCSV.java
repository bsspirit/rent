package org.conan.rent.read2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import com.alibaba.fastjson.JSON;

public class OutputCSV {
    
    public static void main(String[] args) throws IOException {
        String file = "metadata/data/output.csv";
        String json = "metadata/data/output.json";
        OutputCSV o = new OutputCSV();
        List<String> list = o.csv(file, true);
        o.writeJSON(list, json);
    }
    
    public List<String> csv(String file, boolean header) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file));
        if (!header) {
            reader.readNext();
        }
        
        List<String> list = new ArrayList<String>();
        String[] line;
        while ((line = reader.readNext()) != null) {
            if (line[1] == null || line[1].trim().equals("") || line[1].equals("NONE") || line[2].equals("null") || line[3].equals("null")) {
                continue;
            }
            String coord = line[2] + "," + line[3];
            System.out.println(coord);
            list.add(coord);
        }
        return list;
    }
    
    public void writeJSON(List<String> list, String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        // for (String s : list) {
        // fw.write(s + "\n");
        // }
        String s = JSON.toJSONString(list);
        fw.write(s);
        fw.flush();
        fw.close();
    }
    
}
