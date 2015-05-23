/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countTopics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author apple
 */
public class GenerateTopicsJson {

    public static void main(String[] args) {
        try {
            String topicCountFilePath = "/Users/apple/NetBeansProjects/TopicModelingTools/test/word_top.txt";
            String topicsFilePath = "/Users/apple/NetBeansProjects/TopicModelingTools/test/topic_keys.txt";
            String outputFilePath = "/Users/apple/NetBeansProjects/TopicModelingTools/test.json";
            File outputFile = new File(outputFilePath);
            if (outputFile.createNewFile()) {
                System.out.println(outputFile.getName() + "create successful...");
            }

            Map<String, Integer> topicMap = new HashMap<>();

            try (
                    InputStream countIn = new FileInputStream(topicCountFilePath);
                    BufferedReader countReader = new BufferedReader(new InputStreamReader(countIn));
                    InputStream topicsIn = new FileInputStream(topicsFilePath);
                    BufferedReader topicsReader = new BufferedReader(new InputStreamReader(topicsIn));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                String countLine = "";
                while ((countLine = countReader.readLine()) != null) {
                    String[] topics = countLine.split("\t| ");
                    int count = 0;
                    for (String topic : topics) {
                        if (topic.contains(":")) {
                            count++;
                        }
                    }

                    topicMap.put(topics[1], count);
//                    System.out.print("in:" + topics[1] + " " + count);
//                    System.out.println();

                }
                
                JSONObject json = new JSONObject();
                json.put("name", "topics");
                
                JSONArray children = new JSONArray();
                json.put("children", children);

                String topicsLine = "";
                while ((topicsLine = topicsReader.readLine()) != null) {
                    JSONObject topicGroup = new JSONObject();
                    topicGroup.put("name", randomString(8));

                    JSONArray topicArray = new JSONArray(); 
                    topicGroup.put("children", topicArray);

                    String[] topics = topicsLine.split("\\s");
                    for (int i = 0; i < topics.length; ++i) {
                        if (topics.length > 2) {
                            if (!NumberUtils.isNumber(topics[i])) {
                                JSONObject topic = new JSONObject();
                                topic.put("name", topics[i]);
                                topic.put("size", topicMap.get(topics[i]) * 50);
                                topicArray.put(topic);
                            }
                        }
                    }
                    
                    children.put(topicGroup);
                }
                
                json.write(writer);
                
            }            

        } catch (IOException ex) {
            Logger.getLogger(GenerateTopicsJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

}
