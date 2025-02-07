package com.example.saveandserve.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.saveandserve.demo.entity.Alergenos;
import com.example.saveandserve.demo.entity.BancoDeAlimentos;
import com.example.saveandserve.demo.entity.Empresa;
import com.example.saveandserve.demo.entity.Empresa.Suscripcion;
import com.example.saveandserve.demo.entity.TipoTransporte;
import com.example.saveandserve.demo.entity.Transporte;
import com.example.saveandserve.demo.entity.Usuario;
import com.example.saveandserve.demo.entity.UsuarioRol;
import com.example.saveandserve.demo.repository.AlergenosRepository;
import com.example.saveandserve.demo.repository.BancoDeAlimentosRepository;
import com.example.saveandserve.demo.repository.DonacionRepository;
import com.example.saveandserve.demo.repository.EmpresaRepository;
import com.example.saveandserve.demo.repository.TransporteRepository;
import com.example.saveandserve.demo.repository.UsuarioRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EmpresaRepository empresaRepository, BancoDeAlimentosRepository bancoDeAlimentosRepository, DonacionRepository donacionRepository,
     AlergenosRepository alergenosRepository, TransporteRepository transporteRepository, BCryptPasswordEncoder passwordEncoder,UsuarioRepository usuarioRepository) {
        return (args) -> {
            if (empresaRepository.count() == 0) {
                List<Empresa> empresas = Arrays.asList(
                    new Empresa(null, "SaborArte Catering", "contacto@saborartecatering.com", "Explanada de España, 1, 03002", "123-456-7890", "CIF12345678", passwordEncoder.encode("1234"), "Tipo1", "Alicante", Suscripcion.ESTANDAR, null),
                    new Empresa(null, "Delicia Express", "pedidos@deliciaexpress.com", "Gran Vía, 28, 28013", "123-456-7891", "CIF12345679", passwordEncoder.encode("1234"), "Tipo2", "Madrid", Suscripcion.BASICA, null),
                    new Empresa(null, "La Cava Bistró", "contacto@lacavabistro.com", "Passeig de Gràcia, 92, 08008", "123-456-7892", "CIF12345680", passwordEncoder.encode("1234"), "Tipo3", "Barcelona", Suscripcion.PREMIUM, null),
                    new Empresa(null, "Verde Sano Foods", "info@verdesanofoods.com", "Calle de la Paz, 48, 46003", "123-456-7893", "CIF12345681", passwordEncoder.encode("1234"), "Tipo4", "Valencia", Suscripcion.BASICA, null),
                    new Empresa(null, "Brasa & Gril", "atencion@brasaandgrill.com", "Avenida de la Constitución, 21, 41004", "123-456-7894", "CIF12345682", passwordEncoder.encode("1234"), "Tipo5", "Sevilla", Suscripcion.BASICA, null),
                    new Empresa(null, "Dulce Tentación Bakery", "ventas@dulcetentacionbakery.com", "Calle Larios, 10, 29005", "123-456-7895", "CIF12345683", passwordEncoder.encode("1234"), "Tipo6", "Málaga", Suscripcion.BASICA, null),
                    new Empresa(null, "Pura Vida Juice Bar", "hola@puravidajuicebar.com", "Gran Vía del Escultor Salzillo, 7, 30004", "123-456-7896", "CIF12345684", passwordEncoder.encode("1234"), "Tipo7", "Murcia", Suscripcion.PREMIUM, null),
                    new Empresa(null, "Gourmet Urbano", "contacto@gourmeturbano.com", "Plaza Moyúa, 4, 48009", "123-456-7897", "CIF12345685", passwordEncoder.encode("1234"), "Tipo8", "Bilbao", Suscripcion.PREMIUM, null),
                    new Empresa(null, "La Mesa Italiana", "reservas@lamesaitaliana.com", "Paseo de la Independencia, 12, 50004", "123-456-7898", "CIF12345686", passwordEncoder.encode("1234"), "Tipo9", "Zaragoza", Suscripcion.PREMIUM, null),
                    new Empresa(null, "Fresh Market Deli", "pedidos@freshmarketdeli.com", "\"Calle Mayor, 5, 20003", "123-456-7899", "CIF12345687", passwordEncoder.encode("1234"), "Tipo10", "San Sebastián", Suscripcion.PREMIUM, null)
                );
                empresaRepository.saveAll(empresas);
            }
          if (bancoDeAlimentosRepository.count() == 0) {
                List<BancoDeAlimentos> bancos = Arrays.asList(
                    new BancoDeAlimentos(null, "Banco de Alimentos de Alicante", "Calle Agost, 7", "965117190", "alicante@bancodealimentos.es", "Alicante", passwordEncoder.encode("1234"), null),
                    new BancoDeAlimentos(null, "Banco de Alimentos de Madrid", "Carretera de Colmenar Km 13,600", "917346383", "madrid@bancodealimentos.es", "Madrid", passwordEncoder.encode("1234"), null),
                    new BancoDeAlimentos(null, "Banco de Alimentos de Barcelona", "Carrer Motors, 122", "933464404", "barcelona@bancodealimentos.es", "Barcelona",passwordEncoder.encode("1234"), null),
                    new BancoDeAlimentos(null, "Banco de Alimentos de Valencia", "Carrer dels Pedrapiquers, 5", "963924460", "valencia@bancodealimentos.es", "Valencia", passwordEncoder.encode("1234"), null),
                    new BancoDeAlimentos(null, "Banco de Alimentos de Sevilla", "Carretera Sevilla-Málaga Km 5", "954219311", "sevilla@bancodealimentos.es", "Sevilla", passwordEncoder.encode("1234"), null),
                    new BancoDeAlimentos(null, "Banco de Alimentos de Málaga", "Avenida Juan XXIII, 49", "952131894", "malaga@bancodealimentos.es", "Málaga", passwordEncoder.encode("1234"), null),
                    new BancoDeAlimentos(null, "Banco de Alimentos de Bilbao", "Calle Ribera de Zorrozaurre, 48", "944499158", "bilbao@bancodealimentos.es", "Bilbao", passwordEncoder.encode("1234"), null),
                    new BancoDeAlimentos(null, "Banco de Alimentos de Zaragoza", "Calle Mercazaragoza, 1", "976737136", "zaragoza@bancodealimentos.es", "Zaragoza", passwordEncoder.encode("1234"), null),
                    new BancoDeAlimentos(null, "Banco de Alimentos de Murcia", "Calle Alcalde Clemente García, 14", "968879940", "murcia@bancodealimentos.es", "Murcia", passwordEncoder.encode("1234"), null),
                    new BancoDeAlimentos(null, "Banco de Alimentos de Granada", "Calle Loja, Nave 7", "958303578", "granada@bancodealimentos.es", "Granada", passwordEncoder.encode("1234"), null)
                );
                bancoDeAlimentosRepository.saveAll(bancos);
            }

             if (usuarioRepository.count() == 0) {
            List<Usuario> usuarios = Arrays.asList(
                new Usuario(null, "admin1", passwordEncoder.encode("1234"), "admin1@example.com", UsuarioRol.ADMINISTRADOR, null, null),
                new Usuario(null, "admin2", passwordEncoder.encode("1234"), "admin2@example.com", UsuarioRol.ADMINISTRADOR, null, null),
                new Usuario(null, "admin3", passwordEncoder.encode("1234"), "admin3@example.com", UsuarioRol.ADMINISTRADOR, null, null),
                new Usuario(null, "admin4", passwordEncoder.encode("1234"), "admin4@example.com", UsuarioRol.ADMINISTRADOR, null, null)
            );
            usuarioRepository.saveAll(usuarios);
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
