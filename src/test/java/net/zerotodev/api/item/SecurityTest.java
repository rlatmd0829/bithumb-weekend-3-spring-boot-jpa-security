package net.zerotodev.api.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityTest {
    @Test
    @DisplayName("패스워드 암호화 테스트")
    void main(){
        int i = 0;
        while(i<5){
            String password = "123456";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashPw = passwordEncoder.encode(password);
            System.out.println(hashPw);
            i++;
        }
    }
}
