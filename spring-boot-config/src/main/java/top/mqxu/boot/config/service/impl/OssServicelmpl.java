package top.mqxu.boot.config.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.mqxu.boot.config.config.OssConfig;
import top.mqxu.boot.config.service.OssService;

import java.io.InputStream;

@Slf4j
@Service
public class OssServicelmpl implements OssService {
    @Resource
    private OssConfig ossConfig;
    @Override
    public String upload(MultipartFile file) {
        if (file != null){
            //获取文件名
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            //UUID.jpg  取出扩展名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //生成新文件名
            String newFileName = System.currentTimeMillis() + suffix;
            log.info("上传文件名：{}",newFileName);
            //读取配置文件
            String endpoint = ossConfig.getEndpoint();
            String bucket = ossConfig.getBucket();
            String accessKey = ossConfig.getAccessKey();
            String secretKey = ossConfig.getSecretKey();
            String dir = ossConfig.getDir();
            //配置OSS客户端
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
            //设置ContentType是image/jpeg，可以在浏览器中直接查看图片
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpeg");
            String uploadPath = dir + newFileName;
            InputStream inputStream;
            try {
                inputStream = file.getInputStream();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //上传文件
            ossClient.putObject(bucket, uploadPath, inputStream, meta);
            ossClient.shutdown();
            return "https://" + bucket + "." + endpoint + "/" + uploadPath;
        }
        return "上传失败";
    }
}
