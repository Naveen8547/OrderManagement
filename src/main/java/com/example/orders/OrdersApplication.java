package com.example.orders;



import com.example.orders.security.UserAuthRepository;
import com.example.orders.user.UserRepository;
import com.example.orders.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories()
public class OrdersApplication {


   @Autowired
   WalletRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

}
