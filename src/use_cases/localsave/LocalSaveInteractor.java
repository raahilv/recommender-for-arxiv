package use_cases.localsave;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalSaveInteractor implements LocalSaveInputBoundary {
    final LocalSaveDataAccessInterface localSaveDataAccessObject;

    public LocalSaveInteractor(LocalSaveDataAccessInterface localSaveDataAccessObject) {
        this.localSaveDataAccessObject = localSaveDataAccessObject;

    }

    public void execute() {
        try {
            URL url = new URL(localSaveDataAccessObject.getUrl());

            InputStream inputStream = url.openStream();

            // Get the user's home directory
            String userHome = System.getProperty("user.home");

            // Define the downloads directory path
            String downloadsPath = userHome + File.separator + "Downloads";

            // Create a Path object for the downloads directory
            Path downloadsDirectory = Paths.get(downloadsPath);

            // Ensure the downloads directory exists, create it if necessary
            if (!downloadsDirectory.toFile().exists()) {
                downloadsDirectory.toFile().mkdir();
            }

            String fileName = localSaveDataAccessObject.getTitle() + ".pdf";

            String filePath = downloadsPath + File.separator + fileName;

            // Create a FileOutputStream for the specified file path
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                // Read from the input stream and write to the file
                byte[] bytes = new byte[1024];
                int len;
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                }
            }

            inputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
