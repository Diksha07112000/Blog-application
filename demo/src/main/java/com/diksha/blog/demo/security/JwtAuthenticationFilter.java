package com.diksha.blog.demo.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        // 1) get token (token aa rha h request k header se)
        String requestToken = request.getHeader("Authorization");
        System.out.println(requestToken);
        //Bearer 2r356rt76ygbjm

        String username = null;
        String token = null;



        if(requestToken!=null && requestToken.startsWith("Bearer"))
        {
            token = requestToken.substring(7);

            //3) get user from token
            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Unable to get JWT token");
            }
            catch (ExpiredJwtException e){
                System.out.println("JWT token has been expired!");
            }
            catch (MalformedJwtException e){
                System.out.println("Invalid JWT!");
            }
        }else {
            System.out.println("Request token null or JWT token doesn't start with Bearer.");
        }
    //once we get the token , 2) now validate
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            {

                //4) load user associated with token
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if(this.jwtTokenHelper.validateToken(token,userDetails)){
                    //shi chl rha hai
                    //authentication krna h
                    //5) set spring security authentication
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }else
                {
                    System.out.println("Invalid jwt token");
                }
            }
        }else {
            System.out.println("Username is null or context is not null");
        }
        //itna hone k baad , we need to filter this request through filterChain
        filterChain.doFilter(request,response);
    }
}

//called whenever Api request is hit
