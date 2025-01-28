package com.example.saveandserve.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.saveandserve.demo.entity.BancoDeAlimentos;
import com.example.saveandserve.demo.entity.Donacion;
import com.example.saveandserve.demo.entity.Donacion.EstadoEnvio;
import com.example.saveandserve.demo.entity.Empresa;
import com.example.saveandserve.demo.entity.Empresa.Suscripcion;
import com.example.saveandserve.demo.entity.Transporte;
import com.example.saveandserve.demo.repository.BancoDeAlimentosRepository;
import com.example.saveandserve.demo.repository.DonacionRepository;
import com.example.saveandserve.demo.repository.EmpresaRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EmpresaRepository empresaRepository, BancoDeAlimentosRepository bancoDeAlimentosRepository, DonacionRepository donacionRepository) {
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

            if (bancoDeAlimentosRepository.count() == 0) {
                List<BancoDeAlimentos> bancos = Arrays.asList(
                    new BancoDeAlimentos(null, "Banco de Alimentos 1", "Calle Solidaria 1, Ciudad 1", "111-111-1111", "banco1@example.com", "Ciudad 1", "password1", null),
                    new BancoDeAlimentos(null, "Banco de Alimentos 2", "Calle Solidaria 2, Ciudad 2", "222-222-2222", "banco2@example.com", "Ciudad 2", "password2", null),
                    new BancoDeAlimentos(null, "Banco de Alimentos 3", "Calle Solidaria 3, Ciudad 3", "333-333-3333", "banco3@example.com", "Ciudad 3", "password3", null),
                    new BancoDeAlimentos(null, "Banco de Alimentos 4", "Calle Solidaria 4, Ciudad 4", "444-444-4444", "banco4@example.com", "Ciudad 4", "password4", null),
                    new BancoDeAlimentos(null, "Banco de Alimentos 5", "Calle Solidaria 5, Ciudad 5", "555-555-5555", "banco5@example.com", "Ciudad 5", "password5", null),
                    new BancoDeAlimentos(null, "Banco de Alimentos 6", "Calle Solidaria 6, Ciudad 6", "666-666-6666", "banco6@example.com", "Ciudad 6", "password6", null),
                    new BancoDeAlimentos(null, "Banco de Alimentos 7", "Calle Solidaria 7, Ciudad 7", "777-777-7777", "banco7@example.com", "Ciudad 7", "password7", null),
                    new BancoDeAlimentos(null, "Banco de Alimentos 8", "Calle Solidaria 8, Ciudad 8", "888-888-8888", "banco8@example.com", "Ciudad 8", "password8", null),
                    new BancoDeAlimentos(null, "Banco de Alimentos 9", "Calle Solidaria 9, Ciudad 9", "999-999-9999", "banco9@example.com", "Ciudad 9", "password9", null),
                    new BancoDeAlimentos(null, "Banco de Alimentos 10", "Calle Solidaria 10, Ciudad 10", "101-101-1010", "banco10@example.com", "Ciudad 10", "password10", null)
                );
                bancoDeAlimentosRepository.saveAll(bancos);
            }


            //ejemplo de donacion
            // if (donacionRepository.count() == 0) {
            //     // Buscar una empresa existente
            //     Optional<Empresa> empresaOpt = empresaRepository.findById(1L);
            //     Optional<BancoDeAlimentos> bancoOpt = bancoDeAlimentosRepository.findById(1L);
            //     Optional<Transporte> transporteOpt = transporteRepository.findById(1L);

            //     if (empresaOpt.isPresent() && bancoOpt.isPresent() && transporteOpt.isPresent()) {
            //         Empresa empresa = empresaOpt.get();
            //         BancoDeAlimentos banco = bancoOpt.get();
            //         Transporte transporte = transporteOpt.get();

            //         // Crear dos donaciones de ejemplo
            //         Donacion donacion1 = new Donacion(
            //             null,                      // idDonacion (autogenerado)
            //             new BigDecimal("500.00"),  // totalDonacion
            //             LocalDate.now(),           // fechaEntrega
            //             EstadoEnvio.PENDIENTE,     // estadoEnvio
            //             null,                      // donacionesRelacionadas
            //             null,                      // donacionPrincipal
            //             empresa,                   // empresa
            //             null,                      // lineasProducto (se pueden agregar después)
            //             banco,                     // bancoDeAlimentos
            //             transporte                 // transporte
            //         );

            //         Donacion donacion2 = new Donacion(
            //             null,
            //             new BigDecimal("750.00"),
            //             LocalDate.now().plusDays(3),
            //             EstadoEnvio.ENVIADO,
            //             null,
            //             null,
            //             empresa,
            //             null,
            //             banco,
            //             transporte
            //         );

            //         // Guardar las donaciones en la base de datos
            //         donacionRepository.save(donacion1);
            //         donacionRepository.save(donacion2);

            //         System.out.println("✅ Donaciones de ejemplo insertadas en la base de datos.");
            //     } else {
            //         System.out.println("⚠️ No se encontraron empresa, banco o transporte para asociar a las donaciones.");
            //     }
            // }


        };
    }
}
