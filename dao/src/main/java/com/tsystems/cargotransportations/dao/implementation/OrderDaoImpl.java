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

/**
 * Specific DAO implementation for orders management.
 */
@Repository("orderDao")
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

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
    public Order getByNumber(int number) {
        String query = String.format(
                "FROM %s WHERE number = %d", Order.class.getSimpleName(), number);
        List<Order> orders = getEntityManager().createQuery(query, Order.class).getResultList();
        return orders.size() != 0 ? orders.get(0) : null;
    }

    @Override
    public List<Order> getAllByStatus(OrderStatus status) {
        String query = String.format(
                "FROM %s WHERE status = '%s'", Order.class.getSimpleName(), status);
        return getEntityManager().createQuery(query, Order.class).getResultList();
    }

    public static void main(String[] args) {
        OrderDaoImpl obj1 = new OrderDaoImpl();
        System.out.println(obj1.getEntityManager());

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:dao-context.xml");
        ctx.refresh();
        OrderDaoImpl orderDaoImpl = ctx.getBean("orderDao", OrderDaoImpl.class);
        System.out.println("--------------------------------");
        System.out.println(orderDaoImpl.getEntityManager());
        System.out.println("--------------------------------");
        DriverDaoImpl driverDaoImpl = ctx.getBean("driverDao", DriverDaoImpl.class);
        System.out.println("--------------------------------");
        System.out.println(driverDaoImpl.getEntityManager());
        System.out.println("--------------------------------");
        System.out.println(driverDaoImpl.getEntityManager() == orderDaoImpl.getEntityManager());
    }
}
