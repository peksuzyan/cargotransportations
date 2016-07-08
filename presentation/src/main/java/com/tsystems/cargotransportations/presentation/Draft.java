package com.tsystems.cargotransportations.presentation;

import com.tsystems.cargotransportations.service.interfaces.CargoService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Draft {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:WEB-INF/spring/root-context.xml");
        ctx.load("classpath:service-context.xml");
        ctx.load("classpath:dao-context.xml");
        ctx.refresh();

        CargoService cargoService = ctx.getBean("cargoService", CargoService.class);
    }
}
