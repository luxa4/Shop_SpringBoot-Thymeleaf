
package ru.belyaev.shop.config;

import com.restfb.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.NativeWebRequest;
import ru.belyaev.shop.service.UserService;
import ru.belyaev.shop.service.impl.FacebookConnectionSignup;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
	@Autowired
	private UserService userService;

	@Autowired
	private FacebookConnectionSignup facebookConnectionSignup;
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// connection our Postgresql DB
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/order/**").hasRole("USER")
			.antMatchers("/my-orders/**").hasRole("USER")
//			.antMatchers("/order/**").hasAnyRole("MANAGER", "ADMIN")
//			.antMatchers("/employees/delete").hasRole("ADMIN")
//			.antMatchers("/employees/**").hasRole("EMPLOYEE")
//			.antMatchers("/resources/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/error");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

//	@Bean
//	public ProviderSignInController providerSignInController() {
//		((InMemoryUsersConnectionRepository) usersConnectionRepository)
//				.setConnectionSignUp(facebookConnectionSignup);
//
//		return new ProviderSignInController(
//				connectionFactoryLocator,
//				usersConnectionRepository,
//				new FacebookSignInAdapter());
//	}
//
//	public class FacebookSignInAdapter implements SignInAdapter {
//		@Override
//		public String signIn(
//				String localUserId,
//				Connection<?> connection,
//				NativeWebRequest request) {
//
//			SecurityContextHolder.getContext().setAuthentication(
//					new UsernamePasswordAuthenticationToken(
//							connection.getDisplayName(), null,
//							Arrays.asList(new SimpleGrantedAuthority("FACEBOOK_USER"))));
//
//			return null;
//		}
//	}

}






