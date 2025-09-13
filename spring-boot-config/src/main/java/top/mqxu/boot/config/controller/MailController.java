package top.mqxu.boot.config.controller;

import enums.ResultStatus;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.mqxu.boot.config.ApiResponse;
import top.mqxu.boot.config.model.Mail;
import top.mqxu.boot.config.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private MailService mailService;

    @PostMapping("/simple")
    public ResponseEntity<ApiResponse<ResultStatus>> sendSimpleMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs=mailService.sendSimpleMail(mail);
        if(rs == ResultStatus.SUCCESS){
            return ResponseEntity.ok(ApiResponse.success("发送成功",rs));
        }
        //业务失败建议返回400（参数/业务错误），而不是200
        return ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }

    @PostMapping("/html")
    public ResponseEntity<ApiResponse<ResultStatus>> sendHtmlMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs=mailService.sendHtmlMail(mail);
        return rs == ResultStatus.SUCCESS?
                ResponseEntity.ok(ApiResponse.success("发送成功",rs)):
                ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }

    @PostMapping(value = "/attachments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ResultStatus>> sendAttachmentsMail(@Valid @RequestPart("to") String to, @Valid @RequestPart("subject") String subject,
                                                                         @Valid @RequestPart("content") String content,
                                                                         @RequestPart("files")MultipartFile[] files) {
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setContent(content);
        ResultStatus rs=mailService.sendAttachmentsMail(mail,files);
        return rs == ResultStatus.SUCCESS?
                ResponseEntity.ok(ApiResponse.success("发送成功",rs)):
                ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }
}