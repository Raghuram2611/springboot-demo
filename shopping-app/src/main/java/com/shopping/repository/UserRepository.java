package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Account;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
	
	Account findAccountByUsername(String username);

}
