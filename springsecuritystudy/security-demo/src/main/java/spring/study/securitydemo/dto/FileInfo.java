package spring.study.securitydemo.dto;

public class FileInfo {

//    private String fileName;

    private String path;

    public FileInfo() {
    }

    public FileInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
