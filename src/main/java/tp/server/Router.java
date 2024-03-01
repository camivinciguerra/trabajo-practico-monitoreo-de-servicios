package tp.server;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import tp.controllers.*;
import tp.controllers.comunidad.ComunidadController;
import tp.controllers.entidad.EntidadController;
import tp.controllers.incidentes.AperturaIncidenteController;
import tp.controllers.incidentes.CierreIncidenteController;
import tp.controllers.incidentes.IncidentesController;
import tp.controllers.ranking.RankingCantidadController;
import tp.controllers.ranking.RankingImpactoController;
import tp.controllers.ranking.RankingTiempoController;
import tp.controllers.servicios.ServicioController;
import tp.controllers.usuario.LoginController;
import tp.controllers.usuario.PerfilController;
import tp.controllers.usuario.UsuarioController;
import tp.controllers.usuario.UsuarioNuevoController;
import tp.models.entities.comunidad.Rol;
import tp.models.entities.comunidad.RolPersona;
import tp.models.entities.entidad.Entidad;
import tp.models.entities.servicios.Servicio;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router  {

  public static void init(){
    Server.app().routes(() -> {
      get("/", ((UsuarioController) FactoryController.controller("Usuario")):: index);
      get("/login", ((LoginController) FactoryController.controller("Login"))::iniciarSesion);
      post("/login", ((LoginController) FactoryController.controller("Login"))::verificarUsuario);
      get("/create", ((UsuarioNuevoController) FactoryController.controller("UsuarioNuevo"))::create);
      post("/create", ((UsuarioNuevoController) FactoryController.controller("UsuarioNuevo"))::crearCuenta);
      get("/create/es-valida/{contrasenia}",((UsuarioNuevoController) FactoryController.controller("UsuarioNuevo"))::esContraValidaGET);
      get("/create/carga-de-datos", ((UsuarioNuevoController) FactoryController.controller("UsuarioNuevo"))::cargarDatos);
      post("/create/carga-de-datos", ((UsuarioNuevoController) FactoryController.controller("UsuarioNuevo"))::procesarDatos);
      get("/create/carga-de-datos/finalizar", ((UsuarioNuevoController) FactoryController.controller("UsuarioNuevo"))::cargaDePassword);
      post("/create/carga-de-datos/finalizar", ((UsuarioNuevoController) FactoryController.controller("UsuarioNuevo"))::finalizarCarga);
      get("/inicio", ((LoginController) FactoryController.controller("Login"))::home, RolPersona.BASICO,RolPersona.ADMIN, RolPersona.RESPONSABLE);

      get("/incidentes/crear", ((AperturaIncidenteController) FactoryController.controller("AperturaDeIncidente"))::create,RolPersona.BASICO);
      post("/incidentes/crear", ((AperturaIncidenteController) FactoryController.controller("AperturaDeIncidente"))::save,RolPersona.BASICO);
      get("/incidentes/crear/{id}", ((AperturaIncidenteController) FactoryController.controller("AperturaDeIncidente"))::crearIncidente,RolPersona.BASICO);
      post("/incidentes/crear/finalizar", ((AperturaIncidenteController) FactoryController.controller("AperturaDeIncidente"))::finalizarCarga,RolPersona.BASICO);

      get("incidentes/cerrar",((CierreIncidenteController) FactoryController.controller("CierreDeIncidente"))::cerrar,RolPersona.BASICO);
      post("/incidentes/cerrar", ((CierreIncidenteController) FactoryController.controller("CierreDeIncidente"))::procesarCierre,RolPersona.BASICO);
      get("/incidentes/cerrar/{id}", ((CierreIncidenteController) FactoryController.controller("CierreDeIncidente"))::cargarIncidentes,RolPersona.BASICO);
      post("/incidentes/cerrar/{id}", ((CierreIncidenteController) FactoryController.controller("CierreDeIncidente"))::finalizarCierre,RolPersona.BASICO);

      get("/incidentes", ((IncidentesController) FactoryController.controller("Incidente"))::index,RolPersona.BASICO,RolPersona.ADMIN);
      get("/incidente/listado",((IncidentesController) FactoryController.controller("Incidente"))::crearListado,RolPersona.BASICO,RolPersona.ADMIN);
      post("/incidente/listado",((IncidentesController) FactoryController.controller("Incidente"))::crearListadoSave,RolPersona.BASICO,RolPersona.ADMIN);
      get("/incidente/listado/{id}",((IncidentesController) FactoryController.controller("Incidente"))::listarIncidentes,RolPersona.BASICO,RolPersona.ADMIN);

      get("/carga-masiva", ((OrganismosController) FactoryController.controller("Organismos"))::create,RolPersona.ADMIN);
      post("/carga-masiva", ((OrganismosController) FactoryController.controller("Organismos"))::save,RolPersona.ADMIN);
      get("/descargar-ejemplo", ((OrganismosController) FactoryController.controller("Organismos"))::descargarEjemplo, RolPersona.ADMIN);

      get("/usuarios", ((UsuarioController) FactoryController.controller("Usuario"))::cargarPersonas,RolPersona.ADMIN);
      get("/usuario/existe/{nombreUsuario}", ((UsuarioController) FactoryController.controller("Usuario"))::exist);
      post("/usuario/cerrarSesion",((UsuarioController) FactoryController.controller("Usuario"))::cerrarSesion);


      get("/perfil", ((PerfilController) FactoryController.controller("Perfil"))::perfil,RolPersona.BASICO,RolPersona.ADMIN);
      get("/ubicacion", ((PerfilController) FactoryController.controller("Perfil"))::nuevaUbicacion,RolPersona.BASICO,RolPersona.ADMIN);
      post("/ubicacion", ((PerfilController) FactoryController.controller("Perfil"))::setUbicacion,RolPersona.BASICO,RolPersona.ADMIN);

      get("/provincias", ((UbicacionController) FactoryController.controller("Ubicacion")):: provincias);
      get("/municipios/{idProvincia}", ((UbicacionController) FactoryController.controller("Ubicacion"))::municipios);
      get("/localidades/{idMunicipio}", ((UbicacionController) FactoryController.controller("Ubicacion"))::localidades);

      get("/establecimiento/comunidad/{idComunidad}", ((EstablecimientoController) FactoryController.controller("Establecimiento"))::establecimientoSegunComunidad,RolPersona.BASICO,RolPersona.ADMIN);

      get("/comunidades", ((ComunidadController) FactoryController.controller("Comunidad"))::all,RolPersona.ADMIN);
      get("/comunidades/usuario", ((ComunidadController) FactoryController.controller("Comunidad"))::allUser,RolPersona.BASICO,RolPersona.ADMIN);
      get("/mis-comunidades", ((ComunidadController) FactoryController.controller("Comunidad"))::cargarComunidades,RolPersona.BASICO,RolPersona.ADMIN);
      get("/comunidad/crear", ((ComunidadController) FactoryController.controller("Comunidad"))::cargarDatos,RolPersona.BASICO,RolPersona.ADMIN);
      post("/comunidad/crear", ((ComunidadController) FactoryController.controller("Comunidad"))::crearComunidad,RolPersona.BASICO,RolPersona.ADMIN);
      post("/mis-comunidades/{id}/{idComunidad}", ((ComunidadController) FactoryController.controller("Comunidad"))::cambiarCondicion,RolPersona.BASICO,RolPersona.ADMIN);
      post("/mis-comunidades/eliminar/{id}/{idComunidad}", ((ComunidadController) FactoryController.controller("Comunidad"))::eliminarseDeComunidad,RolPersona.BASICO,RolPersona.ADMIN);
      get("/comunidad/unirse", ((ComunidadController) FactoryController.controller("Comunidad"))::mostrarComunidades,RolPersona.BASICO,RolPersona.ADMIN);
      post("/comunidad/unirse/{id}", ((ComunidadController) FactoryController.controller("Comunidad"))::unirseAComunidad,RolPersona.BASICO,RolPersona.ADMIN);


      get("/ranking", ((RankingCantidadController) FactoryController.controller("RankingCantidadController"))::index,RolPersona.BASICO,RolPersona.ADMIN);
      get("/ranking/impacto", ((RankingImpactoController) FactoryController.controller("RankingImpactoController"))::cargarRankingImpacto,RolPersona.BASICO,RolPersona.ADMIN);
      get("/ranking/tiempo", ((RankingTiempoController) FactoryController.controller("RankingTiempoController"))::cargarRankingTiempo,RolPersona.BASICO,RolPersona.ADMIN);

      get("/inicio/admin", ((LoginController) FactoryController.controller("Login"))::inicioAdmin, RolPersona.ADMIN);
      get("/admin/establecimientos", ((EstablecimientoController) FactoryController.controller("Establecimiento"))::cargaEstablecimiento,RolPersona.ADMIN);
      post("/admin/establecimientos", ((EstablecimientoController) FactoryController.controller("Establecimiento"))::crearEstablecimiento,RolPersona.ADMIN);


      get("/admin/usuarios",((UsuarioController) FactoryController.controller("Usuario"))::getUsuarios);

      get("/admin/usuario/{idPersona}",((UsuarioController) FactoryController.controller("Usuario"))::rolAdmin);

      get("/admin/entidades",((EntidadController) FactoryController.controller("Entidad"))::getEntidad);
      post("/admin/entidad", ((EntidadController) FactoryController.controller("Entidad"))::crearEntidad);
      get("/admin/tipos-entidades",((EntidadController) FactoryController.controller("Entidad"))::getTiposDeEntidad);
      post("/admin/tipo-entidad", ((EntidadController) FactoryController.controller("Entidad"))::crearTipoEntidad);

      get("/admin/tipos-servicio",((ServicioController) FactoryController.controller("Servicio"))::getTiposDeServicio);
      post("/admin/tipo-servicio", ((ServicioController) FactoryController.controller("Servicio"))::crearTipoServicio);
    });
  }
}