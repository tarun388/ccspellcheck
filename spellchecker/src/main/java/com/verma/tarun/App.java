package com.verma.tarun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        List<String> dict = new ArrayList<>();
        File file = new File("dict.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                dict.add(line);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BloomFilter bf = new BloomFilter(dict.size());
        for (String s: dict) {
            bf.add(s);
        }

        // Check if elements are present
        System.out.println(bf.contains("challengeeeeeeeeeee"));
    }
}
