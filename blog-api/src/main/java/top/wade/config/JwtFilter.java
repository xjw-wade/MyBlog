package top.wade.config;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import top.wade.model.vo.Result;
import top.wade.util.JacksonUtils;
import top.wade.util.JwtUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/26 20:25
 * @Description: JWT请求过滤器
 */
public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //后台管理路径外的请求直接跳过
        if (!request.getRequestURI().startsWith(request.getContextPath() + "/admin")) {
            filterChain.doFilter(request, servletResponse);
            return;
        }
        String jwt = request.getHeader("Authorization");
        if (JwtUtils.judgeTokenIsExist(jwt)) {
            try {
                Claims claims = JwtUtils.getTokenBody(jwt);
                String username = claims.getSubject();
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("application/json;charset=utf-8");
                Result result = Result.create(403, "凭证已失效，请重新登录！");
                PrintWriter out = response.getWriter();
                out.write(JacksonUtils.writeValueAsString(result));
                out.flush();
                out.close();
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
