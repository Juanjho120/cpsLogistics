package proteus.token.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

	public boolean tieneAcceso(String path) {
		boolean rpta = false;
		
		String metodoRol = "";
		
		switch(path) {
		case "getAll":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,BODEGA,COMPRAS,SERVICIOS,PROGRAMADOR";
			break;
		case "getById":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,BODEGA,COMPRAS,SERVICIOS,PROGRAMADOR";
			break;
		case "getByParam":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,BODEGA,COMPRAS,SERVICIOS,PROGRAMADOR";
			break;
		case "getByParamContable":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,PROGRAMADOR";
			break;
		case "getByIdContable":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,PROGRAMADOR";
			break;
		case "getAllContable":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,PROGRAMADOR";
			break;
		case "getByParamServicio":
				metodoRol = "ADMINISTRADOR,SERVICIOS,PROGRAMADOR";
			break;
		case "getByIdServicio":
				metodoRol = "ADMINISTRADOR,SERVICIOS,PROGRAMADOR";
			break;
		case "getAllServicio":
				metodoRol = "ADMINISTRADOR,SERVICIOS,PROGRAMADOR";
			break;
		case "getByParamCompra":
				metodoRol = "ADMINISTRADOR,COMPRAS,PROGRAMADOR";
			break;
		case "getByIdCompra":
				metodoRol = "ADMINISTRADOR,COMPRAS,PROGRAMADOR";
			break;
		case "getAllCompra":
				metodoRol = "ADMINISTRADOR,COMPRAS,PROGRAMADOR";
			break;
		case "getByParamBodega":
				metodoRol = "ADMINISTRADOR,BODEGA,PROGRAMADOR";
			break;
		case "getByIdBodega":
				metodoRol = "ADMINISTRADOR,BODEGA,PROGRAMADOR";
			break;
		case "getAllBodega":
				metodoRol = "ADMINISTRADOR,BODEGA,PROGRAMADOR";
			break;
		case "create":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,BODEGA,COMPRAS,SERVICIOS,PROGRAMADOR";
			break;
		case "update":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,BODEGA,COMPRAS,SERVICIOS,PROGRAMADOR";
			break;
		case "delete":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,BODEGA,COMPRAS,SERVICIOS,PROGRAMADOR";
			break;
		case "createContable":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,PROGRAMADOR";
			break;
		case "updateContable":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,PROGRAMADOR";
			break;
		case "deleteContable":
				metodoRol = "ADMINISTRADOR,AREA CONTABLE,PROGRAMADOR";
			break;
		case "createCompra":
				metodoRol = "ADMINISTRADOR,COMPRAS,PROGRAMADOR";
			break;
		case "updateCompra":
				metodoRol = "ADMINISTRADOR,COMPRAS,PROGRAMADOR";
			break;
		case "deleteCompra":
				metodoRol = "ADMINISTRADOR,COMPRAS,PROGRAMADOR";
			break;
		case "createServicio":
				metodoRol = "ADMINISTRADOR,SERVICIOS,PROGRAMADOR";
			break;
		case "updateServicio":
				metodoRol = "ADMINISTRADOR,SERVICIOS,PROGRAMADOR";
			break;
		case "deleteServicio":
				metodoRol = "ADMINISTRADOR,SERVICIOS,PROGRAMADOR";
			break;
		case "createBodega":
				metodoRol = "ADMINISTRADOR,BODEGA,PROGRAMADOR";
			break;
		case "updateBodega":
				metodoRol = "ADMINISTRADOR,BODEGA,PROGRAMADOR";
			break;
		case "deleteBodega":
				metodoRol = "ADMINISTRADOR,BODEGA,PROGRAMADOR";
			break;
		case "getAllAdmin":
				metodoRol = "ADMINISTRADOR,PROGRAMADOR";
			break;
		case "getByIdAdmin":
				metodoRol = "ADMINISTRADOR,PROGRAMADOR";
			break;
		case "getByParamAdmin":
				metodoRol = "ADMINISTRADOR,PROGRAMADOR";
			break;
		case "createAdmin":
				metodoRol = "ADMINISTRADOR,PROGRAMADOR";
			break;
		case "updateAdmin":
				metodoRol = "ADMINISTRADOR,PROGRAMADOR";
			break;
		case "deleteAdmin":
				metodoRol = "ADMINISTRADOR,PROGRAMADOR";
			break;
		case "getAllProgramador":
				metodoRol = "PROGRAMADOR";
			break;
		case "getByIdProgramador":
				metodoRol = "PROGRAMADOR";
			break;
		case "getByParamProgramador":
				metodoRol = "PROGRAMADOR";
			break;
		case "createProgramador":
				metodoRol = "PROGRAMADOR";
			break;
		case "updateProgramador":
				metodoRol = "PROGRAMADOR";
			break;
		case "deleteProgramador":
				metodoRol = "PROGRAMADOR";
			break;
		}
		
		String metodoRoles[] = metodoRol.split(",");
		
		Authentication usuarioLogueado = SecurityContextHolder.getContext().getAuthentication();
		
		for(GrantedAuthority auth : usuarioLogueado.getAuthorities()) {
			String rolUser = auth.getAuthority();
			
			for(String rolMet : metodoRoles) {
				if(rolUser.equalsIgnoreCase(rolMet)) {
					rpta = true;
				}
			}
		}
		
		return rpta;
	}
}
