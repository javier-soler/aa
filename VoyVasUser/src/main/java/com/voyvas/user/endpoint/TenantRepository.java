package com.voyvas.user.endpoint;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voyvas.user.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

}
