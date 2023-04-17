package com.example.demo.kit.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtEmailCertificateFormat {
    public boolean certificate;
    public String accountEmail;

    public JwtEmailCertificateFormat(boolean certificate, String accountEmail) {
        this.certificate = certificate;
        this.accountEmail = accountEmail;
    }

}
