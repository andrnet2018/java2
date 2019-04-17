package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HibernateProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product.getId();
    }

    public void update(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    public Optional<Product> findProductById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(product);
    }

    public boolean existsByName(String name) {
        String query = "select case when count(*)> 0 " +
                "then true else false end " +
                "from Product p where p.name='" + name + "'";
        return (boolean) sessionFactory.getCurrentSession().createQuery(query)
                .setMaxResults(1)
                .uniqueResult();
    }

    public Optional<Product> findProductByName(String name) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        return Optional.ofNullable(product);
    }

    public List<Product> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Product.class)
                .list();
    }

    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }
}
