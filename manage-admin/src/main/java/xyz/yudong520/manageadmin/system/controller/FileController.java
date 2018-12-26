package xyz.yudong520.manageadmin.system.controller;


import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.yudong520.manageadmin.system.controller.file.FileInfo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping(value = "/file")
public class FileController {
    final  String path="D:\\spring-image\\springsecuritystudy\\security-demo\\src\\main\\java\\spring\\study\\securitydemo\\controller";

    @PostMapping()
    public FileInfo uploadFile(MultipartFile file) throws IOException {
        System.out.println("文件名："+file.getName());
        System.out.println("原始文件名："+file.getOriginalFilename());
        System.out.println("文件大小："+file.getSize());
        File file1 = new File(path, System.currentTimeMillis() + ".txt");
        file.transferTo(file1);
        return  new FileInfo(file1.getAbsolutePath());
    }

    @GetMapping(value = "/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try (  InputStream in=new FileInputStream(new File(path,id+".txt"));
               OutputStream out=response.getOutputStream();){
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(in,out);
            out.flush();
        }catch (Exception e){

        }
    }


}
