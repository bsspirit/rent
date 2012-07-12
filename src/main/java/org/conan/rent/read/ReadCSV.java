package org.conan.rent.read;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 读CSV并处理
 * 
 * @author conan
 * 
 */
public class ReadCSV {
    
    public static void main(String[] args) throws IOException {
        String file = "metadata/data/location.csv";
        ReadCSV r = new ReadCSV();
        List<String> list = r.csv(file, false);
        for (String s : list) {
            System.out.println(s);
        }
    }
    
    public List<String> csv(String file, boolean header) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file));
        if (!header) {
            reader.readNext();
        }
        
        List<String> list = new ArrayList<String>();
        String[] line;
        while ((line = reader.readNext()) != null) {
            if (line[1] == null || line[1].trim().equals("") || line[1].equals("NULL")) {
                continue;
            }
            list.add(line[0] + "," + toAddress(line));
        }
        return list;
    }
    
    public String toAddress(String[] line) {
        // System.out.print(line[0] + "\t" + line[1] + "\t" + line[2] + "\t" + line[3] + "\t===>\t\t\t");
        String line1 = "", line2 = "", line3 = "";
        if (!empty(line[1])) {
            line[1] = filterEmpty(line[1]);
            line1 = line[1].replaceAll("-", "");
        }
        
        if (!empty(line[2])) {
            line[2] = filterEmpty(line[2]);
            line[2] = filterWords(line[2]);
            
            if (!line1.contains(line[2])) {
                line2 = line[2];
            }
        }
        
        if (!empty(line[3])) {
            line[3] = filterEmpty(line[3]);
            line[3] = filterWords(line[3]);
            
            if (!line1.contains(line[3])) {
                line3 = line[3];
            }
        }
        String s = output(line1, line2, line3);
        // System.out.println(s);
        return s;
    }
    
    public String output(String line1, String line2, String line3) {
        String result = "";
        
        if (line2.equals("") && line3.equals("")) {
            return "NONE";
        }
        
        if (!line3.equals("") && !line3.equals("NULL")) {
            result = line1 + line3;
        } else {
            result = line1 + line2;
        }
        return result;
    }
    
    public String filterWords(String str) {
        String[] words = { "附近", "旁", "主路边", "边", "出租", "商铺", "商业街", "内", "底商", "商业旺铺", "旺铺转让", "旺铺", "转让", "查看地图", "临街", "门脸房", "门脸", "楼下", "没有名称", "111111111" };
        String tmp = str;
        
        for (String word : words) {
            if (tmp.contains(word)) {
                tmp = tmp.replaceAll(word, "");
            }
        }
        return tmp;
    }
    
    public String filterEmpty(String str) {
        return str.trim().replaceAll(" ", "").replaceAll("\t", "");
    }
    
    public boolean empty(String str) {
        return str == null || str.trim().equals("") || str.equals("NULL");
    }
}
