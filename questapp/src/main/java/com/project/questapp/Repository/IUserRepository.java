package com.project.questapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.User;

public interface IUserRepository extends JpaRepository<User,Long> //Jparepository  Select*froma user a denk

{

}
