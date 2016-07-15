package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.OrderDao;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.OrderStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.tsystems.cargotransportations.constants.DaoMapping.ORDER_DAO;

/**
 * Specific DAO implementation for orders management.
 */
@Repository(ORDER_DAO)
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    /**
     * Default constructor.
     */
    public OrderDaoImpl() {
        super(Order.class);
    }

    /**
     * Injected instance of entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Order> getAllByStatus(OrderStatus status) {
        String query = String.format("SELECT o FROM Order AS o WHERE o.status = '%s'", status);
        return getEntityManager().createQuery(query, Order.class).getResultList();
    }

}
