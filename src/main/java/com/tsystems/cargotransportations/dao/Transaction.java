package com.tsystems.cargotransportations.dao;

@FunctionalInterface
public interface Transaction {
    void execute();
}
