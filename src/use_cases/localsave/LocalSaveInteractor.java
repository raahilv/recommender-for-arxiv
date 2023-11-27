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
//
    public void execute(LocalSaveInputData localSaveInputData) {
        localSaveDataAccessObject.localSave(localSaveInputData.getPaperUrl(), localSaveInputData.getPaperName());
    }
}
