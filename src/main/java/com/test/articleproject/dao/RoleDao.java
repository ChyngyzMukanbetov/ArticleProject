package com.test.articleproject.dao;

import com.test.articleproject.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
//    public Role findByRolename(String rolename);
}
