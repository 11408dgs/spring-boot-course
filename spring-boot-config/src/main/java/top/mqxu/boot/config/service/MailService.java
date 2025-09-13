package top.mqxu.boot.config.service;

import enums.ResultStatus;
import org.springframework.web.multipart.MultipartFile;
import top.mqxu.boot.config.model.Mail;

public interface MailService {
    ResultStatus sendSimpleMail(Mail mail);
    ResultStatus sendHtmlMail(Mail mail);
    ResultStatus sendAttachmentsMail(Mail mail, MultipartFile[] files);
}
