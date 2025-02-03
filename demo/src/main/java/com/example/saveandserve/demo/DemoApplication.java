package com.example.saveandserve.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.saveandserve.demo.entity.Alergenos;
import com.example.saveandserve.demo.entity.BancoDeAlimentos;
import com.example.saveandserve.demo.entity.Donacion;
import com.example.saveandserve.demo.entity.Donacion.EstadoEnvio;
import com.example.saveandserve.demo.entity.Empresa;
import com.example.saveandserve.demo.entity.TipoTransporte;
import com.example.saveandserve.demo.entity.Empresa.Suscripcion;
import com.example.saveandserve.demo.entity.Transporte;
import com.example.saveandserve.demo.repository.AlergenosRepository;
import com.example.saveandserve.demo.repository.BancoDeAlimentosRepository;
import com.example.saveandserve.demo.repository.DonacionRepository;
import com.example.saveandserve.demo.repository.EmpresaRepository;
import com.example.saveandserve.demo.repository.TransporteRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EmpresaRepository empresaRepository, BancoDeAlimentosRepository bancoDeAlimentosRepository, DonacionRepository donacionRepository,
     AlergenosRepository alergenosRepository, TransporteRepository transporteRepository) {
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
        new BancoDeAlimentos(null, "Banco de Alimentos de Alicante", "Calle Agost, 7", "965117190", "alicante@bancodealimentos.es", "Alicante", "password1", null),
        new BancoDeAlimentos(null, "Banco de Alimentos de Madrid", "Carretera de Colmenar Km 13,600", "917346383", "madrid@bancodealimentos.es", "Madrid", "password2", null),
        new BancoDeAlimentos(null, "Banco de Alimentos de Barcelona", "Carrer Motors, 122", "933464404", "barcelona@bancodealimentos.es", "Barcelona", "password3", null),
        new BancoDeAlimentos(null, "Banco de Alimentos de Valencia", "Carrer dels Pedrapiquers, 5", "963924460", "valencia@bancodealimentos.es", "Valencia", "password4", null),
        new BancoDeAlimentos(null, "Banco de Alimentos de Sevilla", "Carretera Sevilla-Málaga Km 5", "954219311", "sevilla@bancodealimentos.es", "Sevilla", "password5", null),
        new BancoDeAlimentos(null, "Banco de Alimentos de Málaga", "Avenida Juan XXIII, 49", "952131894", "malaga@bancodealimentos.es", "Málaga", "password6", null),
        new BancoDeAlimentos(null, "Banco de Alimentos de Bilbao", "Calle Ribera de Zorrozaurre, 48", "944499158", "bilbao@bancodealimentos.es", "Bilbao", "password7", null),
        new BancoDeAlimentos(null, "Banco de Alimentos de Zaragoza", "Calle Mercazaragoza, 1", "976737136", "zaragoza@bancodealimentos.es", "Zaragoza", "password8", null),
        new BancoDeAlimentos(null, "Banco de Alimentos de Murcia", "Calle Alcalde Clemente García, 14", "968879940", "murcia@bancodealimentos.es", "Murcia", "password9", null),
        new BancoDeAlimentos(null, "Banco de Alimentos de Granada", "Calle Loja, Nave 7", "958303578", "granada@bancodealimentos.es", "Granada", "password10", null)
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
            


            if (alergenosRepository.count() == 0) { 
                List<Alergenos> alergenos = Arrays.asList(
                    new Alergenos(null, "Gluten", "gluten.png", null),
                    new Alergenos(null, "Crustáceos", "crustaceos.png", null),
                    new Alergenos(null, "Huevos", "huevos.png", null),
                    new Alergenos(null, "Pescado", "pescado.png", null),
                    new Alergenos(null, "Cacahuetes", "cacahuetes.png", null),
                    new Alergenos(null, "Soja", "soja.png", null),
                    new Alergenos(null, "Lácteos", "lacteos.png", null),
                    new Alergenos(null, "Frutos de cáscara", "frutos_cascara.png", null),
                    new Alergenos(null, "Apio", "apio.png", null),
                    new Alergenos(null, "Mostaza", "mostaza.png", null),
                    new Alergenos(null, "Granos de sésamo", "sesamo.png", null),
                    new Alergenos(null, "Dióxido de azufre y sulfitos", "sulfitos.png", null),
                    new Alergenos(null, "Altramuces", "altramuces.png", null),
                    new Alergenos(null, "Moluscos", "moluscos.png", null)
                );
                alergenosRepository.saveAll(alergenos);
            }
            
            if (transporteRepository.count() == 0) {
                List<Transporte> transportes = Arrays.asList(
                    new Transporte(null, "Transportes Norte", new HashSet<>(Arrays.asList(TipoTransporte.SECO, TipoTransporte.REFRIGERADO))),
                    new Transporte(null, "Logística Express", new HashSet<>(Arrays.asList(TipoTransporte.REFRIGERADO, TipoTransporte.CONGELADO))),
                    new Transporte(null, "Fletes del Sur", new HashSet<>(Arrays.asList(TipoTransporte.SECO, TipoTransporte.CONGELADO)))
                );
                transporteRepository.saveAll(transportes);
            }
        };
    }
}
