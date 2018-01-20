package hr.foi.oauth2.facebook.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class PrivateSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/login**", "/logout**").permitAll()
                .antMatchers("/homepage*", "/rest**").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/")
                    .loginProcessingUrl("/")
                    .defaultSuccessUrl("/homepage")
                    .failureUrl("/?error=true")
                    .and()
                        .logout()
                            .logoutSuccessUrl("/").permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/webjars/**")
                .antMatchers("/css/**")
                .antMatchers("/img/**")
                .antMatchers("/json/**")
                .antMatchers("/js/**");
    }

}
