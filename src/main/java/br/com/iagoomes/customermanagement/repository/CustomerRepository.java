package br.com.iagoomes.customermanagement.repository;

import br.com.iagoomes.customermanagement.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("""
            select c
            from Customer c
            where c.active = true
            and c.id = :id
            """)
    Optional<Customer> findActiveByid(@Param("id") Long id);

    Page<Customer> findAllByActiveIsTrue(Pageable page);
}
