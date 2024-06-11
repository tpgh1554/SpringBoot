package com.kh.totalEx.dto;

import com.kh.totalEx.constant.Authority;
import com.kh.totalEx.entity.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberReqDto {
    private String email;
    private String name;
    private String password;
    private String image;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .pwd(passwordEncoder.encode(password))
                .name(name)
                .image(image)
                .authority(Authority.ROLE_USER)
                .build();
    }
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
