package localsave;

import LocalSaveDataAccess.LocalSaveDataAccessObject;

import interface_adapters.localsave.LocalSaveController;
import use_cases.localsave.LocalSaveInputData;
import use_cases.localsave.LocalSaveInteractor;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class LocalSave{
    LocalSaveDataAccessObject localSaveDataAccessObject = new LocalSaveDataAccessObject();
    LocalSaveInteractor localSaveInteractor = new LocalSaveInteractor(localSaveDataAccessObject);

    LocalSaveController localSaveController = new LocalSaveController(localSaveInteractor);
    @Test
    public void checkIfRightDirectory(){
        String sampleUrl = "https://arxiv.org/pdf/2311.18000.pdf";
        String paperName = "Lyman Limits";
        localSaveController.execute(sampleUrl, paperName);
        String fileNameToCheck = "Lyman Limits.pdf";
        String userHome = System.getProperty("user.home");
        String filePath = userHome + "/Downloads/" + fileNameToCheck;
        Path path = Paths.get(filePath);
        boolean fileExists = Files.exists(path);
        assert(fileExists);
        try {
            // Delete the file if it's in downloaded to right directory
            Files.deleteIfExists(path);
            System.out.println("File successfully deleted.");
        } catch (IOException e) {
            System.err.println("Error deleting the file: " + e.getMessage());
        }
    }
}
