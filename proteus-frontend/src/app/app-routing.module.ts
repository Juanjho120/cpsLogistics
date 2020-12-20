import { FinanzasComponent } from './pages/finanzas/finanzas.component';
import { ChecklistComponent } from './pages/checklist/checklist.component';
import { ServicioComponent } from './pages/servicio/servicio.component';
import { CotizacionComponent } from './pages/cotizacion/cotizacion.component';
import { FacturaCompraComponent } from './pages/factura-compra/factura-compra.component';
import { PlacaComponent } from './pages/placa/placa.component';
import { ProveedorComponent } from './pages/proveedor/proveedor.component';
import { SegmentoComponent } from './pages/segmento/segmento.component';
import { InventarioComponent } from './pages/inventario/inventario.component';
import { CardexNuevoComponent } from './pages/cardex/cardex-nuevo/cardex-nuevo.component';
import { CardexComponent } from './pages/cardex/cardex.component';
import { ServicioTipoComponent } from './pages/servicio-tipo/servicio-tipo.component';
import { MarcaRepuestoComponent } from './pages/marca-repuesto/marca-repuesto.component';
import { MarcaAutoComponent } from './pages/marca-auto/marca-auto.component';
import { SoporteComponent } from './pages/soporte/soporte.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'inicio', component: InicioComponent},
  { path: 'soporte', component: SoporteComponent},
  { path: 'marcas-autos', component: MarcaAutoComponent},
  { path: 'marcas-repuestos', component: MarcaRepuestoComponent},
  { path: 'servicio-tipos', component: ServicioTipoComponent},
  { path: 'cardex', component: CardexComponent, children : [
    { path : 'nuevo', component : CardexNuevoComponent }
  ]},
  { path: 'inventario', component: InventarioComponent},
  { path: 'segmento', component: SegmentoComponent},
  { path: 'proveedor', component: ProveedorComponent},
  { path: 'placa', component: PlacaComponent},
  { path: 'factura-compra', component: FacturaCompraComponent},
  { path: 'cotizacion', component: CotizacionComponent},
  { path: 'servicio', component: ServicioComponent},
  { path: 'checklist', component: ChecklistComponent},
  { path: 'finanzas', component: FinanzasComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
