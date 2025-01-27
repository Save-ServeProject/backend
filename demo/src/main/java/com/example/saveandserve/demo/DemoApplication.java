package com.example.saveandserve.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.saveandserve.demo.entity.Empresa;
import com.example.saveandserve.demo.entity.Empresa.Suscripcion;
import com.example.saveandserve.demo.repository.EmpresaRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EmpresaRepository empresaRepository) {
        return (args) -> {
            if (empresaRepository.count() == 0) {
                List<Empresa> empresas = Arrays.asList(
                    new Empresa(null, "Empresa 1", "empresa1@example.com", "Calle Ficticia 1, Ciudad 1", "123-456-7890", "CIF12345678", "contrasenia1", "Tipo1", "Ciudad 1", Suscripcion.ADMIN, null),
                    new Empresa(null, "Empresa 2", "empresa2@example.com", "Calle Ficticia 2, Ciudad 2", "123-456-7891", "CIF12345679", "contrasenia2", "Tipo2", "Ciudad 2", Suscripcion.USER, null),
                    new Empresa(null, "Empresa 3", "empresa3@example.com", "Calle Ficticia 3, Ciudad 3", "123-456-7892", "CIF12345680", "contrasenia3", "Tipo3", "Ciudad 3", Suscripcion.GUEST, null),
                    new Empresa(null, "Empresa 4", "empresa4@example.com", "Calle Ficticia 4, Ciudad 4", "123-456-7893", "CIF12345681", "contrasenia4", "Tipo4", "Ciudad 4", Suscripcion.ADMIN, null),
                    new Empresa(null, "Empresa 5", "empresa5@example.com", "Calle Ficticia 5, Ciudad 5", "123-456-7894", "CIF12345682", "contrasenia5", "Tipo5", "Ciudad 5", Suscripcion.USER, null),
                    new Empresa(null, "Empresa 6", "empresa6@example.com", "Calle Ficticia 6, Ciudad 6", "123-456-7895", "CIF12345683", "contrasenia6", "Tipo6", "Ciudad 6", Suscripcion.GUEST, null),
                    new Empresa(null, "Empresa 7", "empresa7@example.com", "Calle Ficticia 7, Ciudad 7", "123-456-7896", "CIF12345684", "contrasenia7", "Tipo7", "Ciudad 7", Suscripcion.ADMIN, null),
                    new Empresa(null, "Empresa 8", "empresa8@example.com", "Calle Ficticia 8, Ciudad 8", "123-456-7897", "CIF12345685", "contrasenia8", "Tipo8", "Ciudad 8", Suscripcion.USER, null),
                    new Empresa(null, "Empresa 9", "empresa9@example.com", "Calle Ficticia 9, Ciudad 9", "123-456-7898", "CIF12345686", "contrasenia9", "Tipo9", "Ciudad 9", Suscripcion.GUEST, null),
                    new Empresa(null, "Empresa 10", "empresa10@example.com", "Calle Ficticia 10, Ciudad 10", "123-456-7899", "CIF12345687", "contrasenia10", "Tipo10", "Ciudad 10", Suscripcion.ADMIN, null)
                );

                empresaRepository.saveAll(empresas);
            }
        };
    }
}
