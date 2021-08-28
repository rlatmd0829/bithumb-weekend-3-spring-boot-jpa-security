package net.zerotodev.api.security.filter;

import lombok.RequiredArgsConstructor;
import net.zerotodev.api.security.domain.SecurityProvider;
import net.zerotodev.api.security.exception.SecurityRuntimeException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final SecurityProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = provider.resolveToken(request); // 토큰체크
        try{
            if(token != null && provider.validateToken(token)){
                Authentication auth = provider.getAuthentication(token);
                SecurityContextHolder.clearContext(); // 기존에 있던거 지움
                SecurityContextHolder.getContext().setAuthentication(auth); // 토큰 등록
            }
        } catch (SecurityRuntimeException ex) {
            //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext();
            response.sendError(ex.getHttpStatus().value(), ex.getMessage());
            return;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        filterChain.doFilter(request, response); // filter에 등록
    }
}
