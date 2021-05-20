package axmor.test_task.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    // Создание пользователей и поролей. Хранение в памяти
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("ivan").password("ivan").roles("EMPLOYEE"))
                .withUser(userBuilder.username("elena").password("elena").roles("EMPLOYEE"))
                .withUser(userBuilder.username("adam").password("adam").roles("EMPLOYEE"));
    }

    //    Авторизация пользователей
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").hasAnyRole("EMPLOYEE")
                .and().formLogin().permitAll();
    }

    /*
       Хранение пользовательских данных в БД. Реализовать с базой H2  не смог,ошибка с подключением к базе при авторизации.
        С базой MySql всё работает.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource());
        */
    }

