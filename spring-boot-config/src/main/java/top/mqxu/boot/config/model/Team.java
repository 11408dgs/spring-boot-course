package top.mqxu.boot.config.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Data
@Component
public class Team {
    @Value("${team.leader}")
    @NotBlank(message = "负责人姓名不能为空")
    @Length(min = 2, max = 10,message = "长度在2-10之间")
    private String leader;

    @Value("${team.phone}")
    @Pattern(regexp = "^1[0-9]{11}$",message = "手机号格式不正确")
    private String phone;

    @Value("${team.age}")
    @Max(value = 10,message = "团队年限不能超过10年")
    @Min(value = 1,message = "团队年限不能小于1年")
    private Integer age;

    @Value("${team.createDate}")
    @Past(message = "创建时间必须早于当前时间")
    private LocalDate creatDate;
}
