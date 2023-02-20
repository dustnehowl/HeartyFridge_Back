package com.example.test.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Binding;
import com.google.cloud.Policy;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class GoogleStorageConfig {

    @Bean
    public Storage storage() throws IOException {
        String keyFileName = "hopeful-canto-373101-aa354203c638.json";
        InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();
        Storage storage = StorageOptions.newBuilder().setProjectId("hopeful-canto-373101")
                // Key 파일 수동 등록
                .setCredentials(GoogleCredentials.fromStream(keyFile))
                .build().getService();
        Policy originalPolicy =
                storage.getIamPolicy("slowy_storage", Storage.BucketSourceOption.requestedPolicyVersion(3));

        String bucketName = "slowy_storage";
        String role = "roles/storage.legacyObjectOwner";
        String member = "serviceAccount:dustnrkfnfn@hopeful-canto-373101.iam.gserviceaccount.com";

        // getBindingsList() returns an ImmutableList and copying over to an ArrayList so it's mutable.
        List<Binding> bindings = new ArrayList<>();

        // Create a new binding using role and member
        Binding.Builder newMemberBindingBuilder = Binding.newBuilder();
        newMemberBindingBuilder.setRole(role).setMembers(List.of(member));
        bindings.add(newMemberBindingBuilder.build());

        // Update policy to add member
        Policy.Builder updatedPolicyBuilder = originalPolicy.toBuilder();
        updatedPolicyBuilder.setBindings(bindings).setVersion(3);
        storage.setIamPolicy(bucketName, updatedPolicyBuilder.build());


        return storage;
    }

}
