package server;

import java.util.HashMap;

public class FilenameHandler {
    private HashMap<String,String> validFiles;
    
    public FilenameHandler () {
        this.validFiles = new HashMap<>();
        addValidFile(GlobalStrings.TYPE_START, GlobalStrings.FILE_START);
        addValidFile(GlobalStrings.TYPE_END, GlobalStrings.FILE_END);
        addValidFile(GlobalStrings.TYPE_NAME, GlobalStrings.FILE_NAME);
        addValidFile(GlobalStrings.TYPE_RESULT, GlobalStrings.FILE_RESULT);
    }

    public FilenameHandler addValidFile(String type, String name) {
        validFiles.put(type, name);
        return this;
    }

    public String getFile(String type) {
        return validFiles.getOrDefault(type, GlobalStrings.FILE_INVALID);
    }
}
