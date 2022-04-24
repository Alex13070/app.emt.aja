package org.dam2.appEmt.configuration;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// // import org.springframework.security.core.Authentication;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// //import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter { 
    
//     /**
//      * Si explota, comentad esta clase entera, hasta los import,
//      * en el aplication quitais el @Import y quitais el comentario en el exclude
//      */



//     @Autowired
//     public void configure(AuthenticationManagerBuilder auth) throws Exception {
//         //No password encrypt for demo proposes only (do NOT use in Prod environments)!!

//         PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

//         auth.inMemoryAuthentication()
//             .withUser("admin")
//             .password(encoder.encode("admin"))
//             .roles("ADMIN");
        
//         auth.inMemoryAuthentication()
//         .withUser("user")
//         .password("{noop}user")
//         .roles("USER");
        
//     }
    
//     // @Override
//     // protected void configure(HttpSecurity http) throws Exception {
//     //     http.csrf().disable()
//     //         .authorizeRequests()
//     //         .anyRequest()
//     //         .hasRole("CLIENT")    
//     //         .and().httpBasic();
        
//     // 	System.out.println("ejecutando SecurityConfig");
//     // 	http.csrf().disable().
//     // 		addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class).
//     // 		authorizeRequests().
//     // 		antMatchers("/listar-paradas").permitAll(). // sin autorizacion
//     // 		antMatchers("/login/listar-paradas").authenticated(). // con token
//     // 		antMatchers("/login").hasRole("CLIENT").and().httpBasic(); // autorización usuario y contrasena
//     // 		//antMatchers("/mensajes/post").hasRole("CLIENT").and().httpBasic(); // autorización usuario y contrasena
//     // 		//antMatchers("/token").
        
        
//     // }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//             .antMatchers("/**/**").hasRole("ADMIN")
//             .antMatchers("/**").hasRole("ADMIN")//El usuario administrador puede acceder a cualquier cosa 
//             .anyRequest().authenticated();//any other request just need authentication
//             //.and()
//             //.formLogin();//enable form login
//     }

// }
