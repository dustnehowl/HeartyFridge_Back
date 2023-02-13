package com.example.test.config;

import com.example.test.fridge.Fridge;
import com.example.test.fridge.repository.FridgeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final FridgeRepository fridgeRepository;

    @PostConstruct
    public void init(){
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader("src/main/resources/static/fridge.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            int jsonSize = jsonArray.size();
            for(int i=0; i<jsonSize; i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String name = jsonObject1.get("name").toString();
                String address = jsonObject1.get("address").toString();
                JSONObject loc = (JSONObject) jsonObject1.get("loc");
                double lat = (double) loc.get("lat");
                double lng = (double) loc.get("lng");

                Fridge fridge = fridgeRepository.findFridgeByAddress(address)
                        .orElseGet(() -> fridgeRepository.save(new Fridge(name, address, lat, lng)));
            }
        }
        catch(IOException | ParseException e)
        {
            throw new RuntimeException(e);
        }
    }
}
