package top.mqxu.boot.config.controller;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.mqxu.boot.config.service.OssService;

@RestController
public class OssController {
    @Resource
    private OssService ossService;

    @PostMapping
    public String upload(MultipartFile file) {
        return ossService.upload(file);
    }
}
