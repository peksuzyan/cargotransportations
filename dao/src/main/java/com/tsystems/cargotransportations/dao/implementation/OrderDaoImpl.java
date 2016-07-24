package com.tsystems.cargotransportations.dao.implementation;

import com.tsystems.cargotransportations.dao.interfaces.OrderDao;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.OrderStatus;
import com.tsystems.cargotransportations.entity.Truck;
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

    /**
     * Getter of the entity manager.
     * @return entityManager
     */
    @Override
    EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Gets orders with given status.
     * @param status status
     * @return orders list
     */
    @Override
    public List<Order> getAllByStatus(OrderStatus status) {
        String query = String.format("SELECT o FROM Order AS o WHERE o.status = '%s'", status);
        return getEntityManager().createQuery(query, Order.class).getResultList();
    }

    /**
     * Gets an order by given status and truck.
     * @param status order status
     * @param truck  truck
     * @return order
     */
    @Override
    public Order getByStatusAndTruck(OrderStatus status, Truck truck) {
        String query = String.format(
                "SELECT o FROM Order AS o WHERE o.status = '%s' AND o.truck.id = %d",
                status, truck.getId());
        return getEntityManager().createQuery(query, Order.class).getSingleResult();
    }
}
