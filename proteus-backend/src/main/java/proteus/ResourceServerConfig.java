package proteus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

//Segunda Clase
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
    private ResourceServerTokenServices tokenServices;
	
	
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
                http
                .exceptionHandling().authenticationEntryPoint(new AuthException())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()                  
                .antMatchers("/v2/api-docs/**" ).permitAll()
                .antMatchers("/v3/api-docs/**" ).permitAll()
                .antMatchers("/bancos/**" ).authenticated()                
                .antMatchers("/boletas/**" ).authenticated()
                .antMatchers("/boleta-tipos-documentos/**" ).authenticated()
                .antMatchers("/cajas-chicas/**" ).authenticated()
                .antMatchers("/categorias/**" ).authenticated()
                .antMatchers("/checklists/**" ).authenticated()
                .antMatchers("/checklist-detalles/**" ).authenticated()
                .antMatchers("/checklist-evaluaciones/**" ).authenticated()
                .antMatchers("/checklist-items/**" ).authenticated()
                .antMatchers("/checklist-servicio-tipos/**" ).authenticated()
                .antMatchers("/cheques/**" ).authenticated()
                .antMatchers("/comprobante-tipos/**" ).authenticated()
                .antMatchers("/conceptos/**" ).authenticated()
                .antMatchers("/cotizaciones/**" ).authenticated()
                .antMatchers("/creditos-proveedores/**" ).authenticated()
                .antMatchers("/credito-proveedor-detalles/**" ).authenticated()
                .antMatchers("/cuentas-bancarias/**" ).authenticated()
                .antMatchers("/cuenta-bancaria-tipos/**" ).authenticated()
                .antMatchers("/facturas-compras/**" ).authenticated()
                .antMatchers("/inventarios/**" ).authenticated()
                .antMatchers("/marcas-autos/**" ).authenticated()
                .antMatchers("/marcas-repuestos/**" ).authenticated()
                .antMatchers("/menus/**" ).authenticated()
                .antMatchers("/modificaciones/**" ).authenticated()
                .antMatchers("/monedas/**" ).authenticated()
                .antMatchers("/notas-credito/**" ).authenticated()
                .antMatchers("/pagos-proveedores/**" ).authenticated()
                .antMatchers("/pago-tipos/**" ).authenticated()
                .antMatchers("/personal/**" ).authenticated()
                .antMatchers("/personal-puestos/**" ).authenticated()
                .antMatchers("/placas/**" ).authenticated()
                .antMatchers("/proveedor-asesores/**" ).authenticated()
                .antMatchers("/proveedores/**" ).authenticated()
                .antMatchers("/proveedores-menores/**" ).authenticated()
                .antMatchers("/reportes/**" ).authenticated()
                .antMatchers("/repuestos/**" ).authenticated()
                .antMatchers("/roles/**" ).authenticated()
                .antMatchers("/saldos/**" ).authenticated()
                .antMatchers("/segmentos/**" ).authenticated()
                .antMatchers("/segmento-creditos/**" ).authenticated()
                .antMatchers("/segmento-credito-detalles/**" ).authenticated()
                .antMatchers("/segmento-pagos/**" ).authenticated()
                .antMatchers("/servicios/**" ).authenticated()
                .antMatchers("/servicio-tipos/**" ).authenticated()
                .antMatchers("/transacciones/**" ).authenticated()
                .antMatchers("/transaccion-estados/**" ).authenticated()
                //.antMatchers("/usuarios/**" ).authenticated()
                .antMatchers("/tokens/anular/**" ).permitAll()
                .antMatchers("/tokens/**" ).authenticated()                
                .antMatchers("/consultaexamenes/**" ).authenticated()
                .antMatchers("/pacientes/**" ).authenticated();
                
    }    

}
