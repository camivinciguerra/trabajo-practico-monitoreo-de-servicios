package tp.controllers;

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

public class FactoryController {
  public static Object controller(String nombre) {
    Object controller = null;
    switch (nombre) {
      case "Usuario": controller = new UsuarioController(); break;
      case "Login" : controller = new LoginController(); break;
      case "UsuarioNuevo" : controller = new UsuarioNuevoController(); break;
      case "Comunidad" : controller = new ComunidadController(); break;
      case "Organismos": controller = new OrganismosController(); break;
      case "Incidente" : controller = new IncidentesController(); break;
      case "Ubicacion" : controller = new UbicacionController(); break;
      case "AperturaDeIncidente" : controller = new AperturaIncidenteController(); break;
      case "CierreDeIncidente" : controller = new CierreIncidenteController(); break;
      case "Establecimiento" : controller = new EstablecimientoController(); break;
      case "Perfil" : controller = new PerfilController(); break;
      case "RankingCantidadController" : controller = new RankingCantidadController(); break;
      case "RankingImpactoController" : controller = new RankingImpactoController(); break;
      case "RankingTiempoController" : controller = new RankingTiempoController(); break;
      case "Entidad": controller = new EntidadController(); break;
      case "Servicio": controller = new ServicioController(); break;
    }
    return controller;
  }
}

