package com.tsystems.cargotransportations.dao;

import com.tsystems.cargotransportations.dao.implementation.CargoDaoImpl;
import com.tsystems.cargotransportations.entity.Cargo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CargoDaoImplTest {

    private static final int ENTITY_ID = 8;

    private static final String QUERY = "something query";

    private CargoDaoImpl dao;

    @Mock
    private EntityManager em;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dao = new CargoDaoImpl();
        dao.setEntityManager(em);
    }

    @Test
    public void read_MustBeFound() {

        Cargo expected = new Cargo();
        when(em.find(Cargo.class, ENTITY_ID)).thenReturn(expected);
        Cargo result = dao.read(ENTITY_ID);

        Assert.assertEquals(expected, result);
        verify(em).find(Cargo.class, ENTITY_ID);

    }

    @Test
    public void read_MustBeNotFound() {

        when(em.find(Cargo.class, ENTITY_ID)).thenReturn(null);
        Cargo result = dao.read(ENTITY_ID);

        Assert.assertNull(result);
        verify(em).find(Cargo.class, ENTITY_ID);

    }

    @Test
    public void create_MustBeCreated() {

        Cargo cargo = new Cargo();
        doAnswer(new Answer() {
            public Cargo answer(InvocationOnMock invocation) {
                return (Cargo) invocation.getArguments()[0];
            }}).when(em).persist(cargo);
        dao.create(cargo);

        verify(em).persist(cargo);

    }

    @Test(expected = PersistenceException.class)
    public void create_MustThrowAnException() {

        Cargo cargo = new Cargo();
        doThrow(PersistenceException.class).when(em).persist(cargo);
        dao.create(cargo);

    }

    @Test
    public void delete_MustBeDeletedWhenContains() {

        Cargo cargo = new Cargo();
        when(em.contains(cargo)).thenReturn(true);
        doAnswer(new Answer() {
            public Cargo answer(InvocationOnMock invocation) {
                return (Cargo) invocation.getArguments()[0];
            }}).when(em).remove(cargo);
        dao.delete(cargo);

        verify(em).contains(cargo);
        verify(em).remove(cargo);

    }

    @Test
    public void delete_MustBeDeletedWhenNotContains() {

        Cargo detached = new Cargo();
        Cargo attached = new Cargo();
        when(em.contains(detached)).thenReturn(false);
        when(em.merge(detached)).thenReturn(attached);
        doAnswer(new Answer() {
            public Cargo answer(InvocationOnMock invocation) {
                return (Cargo) invocation.getArguments()[0];
            }}).when(em).remove(attached);
        dao.delete(detached);

        verify(em).contains(detached);
        verify(em).remove(attached);

    }

    @Test(expected = PersistenceException.class)
    public void delete_MustThrowAnExceptionWhenContains() {

        Cargo cargo = new Cargo();
        when(em.contains(cargo)).thenReturn(true);
        doThrow(PersistenceException.class).when(em).remove(cargo);
        dao.delete(cargo);

    }

    @Test(expected = PersistenceException.class)
    public void delete_MustThrowAnExceptionWhenNotContainsAndMerge() {

        Cargo detached = new Cargo();
        when(em.contains(detached)).thenReturn(false);
        doThrow(PersistenceException.class).when(em).merge(detached);
        dao.delete(detached);

    }

    @Test(expected = PersistenceException.class)
    public void delete_MustThrowAnExceptionWhenNotContainsAndRemove() {

        Cargo detached = new Cargo();
        Cargo attached = new Cargo();
        when(em.contains(detached)).thenReturn(false);
        when(em.merge(detached)).thenReturn(attached);
        doThrow(PersistenceException.class).when(em).remove(attached);
        dao.delete(detached);

    }

    @Test
    public void getTotalCount_MustReturn() {

    }

}
