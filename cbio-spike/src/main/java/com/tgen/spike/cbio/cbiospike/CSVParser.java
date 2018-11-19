package com.tgen.spike.cbio.cbiospike;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVParser {

  private String jsonString;

  public String convertToJSON(String csvText) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    try {
      InputStream in = new ByteArrayInputStream(csvText.getBytes());
      CSV csv = new CSV(true, '\t', in);
      List<String> fieldNames = null;
      if (csv.hasNext()) fieldNames = new ArrayList<>(csv.next());
      List<Map<String, String>> list = new ArrayList<>();

      while (csv.hasNext()) {
        List<String> x = csv.next();
        Map<String, String> obj = new LinkedHashMap<>();
        for (int i = 0; i < fieldNames.size() - 1; i++) {
          obj.put(fieldNames.get(i), x.get(i));
        }
        list.add(obj);
      }
      ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      mapper.writeValue(byteArrayOutputStream, list);
      jsonString = byteArrayOutputStream.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return jsonString;
  }
}
