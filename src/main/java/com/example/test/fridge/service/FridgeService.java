package com.example.test.fridge.service;

import com.example.test.fridge.Fridge;
import com.example.test.fridge.repository.FridgeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FridgeService {

    private final FridgeRepository fridgeRepository;
    public String testFridge(){
        return "Fridge Test!!";
    }

    public List<Fridge> all(){
        return fridgeRepository.findAll();
    }

    public String saveFridge(){

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

                Optional<Fridge> fridge = fridgeRepository.findMemberByAddress(address);
                if(fridge.isPresent()){
                    continue;
                }
                else fridgeRepository.save(new Fridge(name, address, lat, lng));
            }
            return "Save Done!!";
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}