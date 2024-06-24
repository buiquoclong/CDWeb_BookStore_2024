package com.cdweb.bookstore.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class ImageImpl {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACOUNT_KEY_PATH = getPathToGoodleCredentials();


    private static String getPathToGoodleCredentials() {
        String currentDirectory = System.getProperty("user.dir");    // thư mục của project
        Path filePath = Paths.get(currentDirectory, "cred.json"); // get file key google
        return filePath.toString();
    }

    public String uploadImageToDrive(File file) throws GeneralSecurityException, IOException {

        try {
            String folderId = "1snR86hAOJxsz7raM_UO38VUthWOAIw3y"; // thư mục drive
            Drive drive = createDriveService(); // tạo và cấu hình Drive
            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(file.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("image/jpeg", file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id").execute(); // upload
            String imageUrl = "https://drive.google.com/thumbnail?id=" + uploadedFile.getId(); // link drive image
            file.delete(); // xóa ảnh trong hệ thống
            return imageUrl;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Upload image to drive failed");
        }
        return null;
    }

    private Drive createDriveService() throws GeneralSecurityException, IOException {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));
        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .build();
    }
}
