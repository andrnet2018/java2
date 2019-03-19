package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long insert(Product product) {
        String query = "insert into products (name, price, category, discount, description) values (" +
                "?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setString(3, product.getCategory());
            ps.setBigDecimal(4, product.getDiscount());
            ps.setString(5, product.getDescription());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        String query = "select * from products where id=" + id;
        List<Product> products = jdbcTemplate.query(query,
                new BeanPropertyRowMapper(Product.class));
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsByName(String name) {
        String query = "SELECT CASE WHEN count(*)> 0 " +
                "THEN true ELSE false END " +
                "FROM products where name=" + name;
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        String query = "select * from products where name=" + name;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class)).stream()
                .findFirst();
    }

}
