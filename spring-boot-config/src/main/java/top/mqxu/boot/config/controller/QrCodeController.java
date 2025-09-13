package top.mqxu.boot.config.controller;


import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    @Value("${custom.qrcode.content}")
    private String content;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateQrcode() {
        //字节数组输出流
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        QrCodeUtil.generate(content,300,300, ImgUtil.IMAGE_TYPE_PNG,out);
        byte[] bytes = out.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes,headers, HttpStatus.OK);
    }

    @GetMapping("/generate/{text}")
    public ResponseEntity<byte[]> generateQrcode(@PathVariable String text) {
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        QrCodeUtil.generate(text,300,300, ImgUtil.IMAGE_TYPE_PNG,out);
        byte[] bytes = out.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes,headers, HttpStatus.OK);
    }
}
