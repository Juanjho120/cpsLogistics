import { ServerErrorsInterceptor } from './shared/server-errors.interceptor';
import { MaterialModule } from './material/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InicioComponent } from './pages/inicio/inicio.component';
import { SoporteComponent } from './pages/soporte/soporte.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MarcaAutoComponent } from './pages/marca-auto/marca-auto.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MarcaRepuestoComponent } from './pages/marca-repuesto/marca-repuesto.component';
import { ServicioTipoComponent } from './pages/servicio-tipo/servicio-tipo.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MarcaAutoDialogoComponent } from './pages/marca-auto/marca-auto-dialogo/marca-auto-dialogo.component';
import { MarcaAutoDialogoEliminarComponent } from './pages/marca-auto/marca-auto-dialogo-eliminar/marca-auto-dialogo-eliminar.component';
import { MarcaRepuestoDialogoComponent } from './pages/marca-repuesto/marca-repuesto-dialogo/marca-repuesto-dialogo.component';
import { MarcaRepuestoDialogoEliminarComponent } from './pages/marca-repuesto/marca-repuesto-dialogo-eliminar/marca-repuesto-dialogo-eliminar.component';
import { ServicioTipoDialogoComponent } from './pages/servicio-tipo/servicio-tipo-dialogo/servicio-tipo-dialogo.component';
import { ServicioTipoDialogoEliminarComponent } from './pages/servicio-tipo/servicio-tipo-dialogo-eliminar/servicio-tipo-dialogo-eliminar.component';
import { CardexComponent } from './pages/cardex/cardex.component';
import { CardexDialogoComponent } from './pages/cardex/cardex-dialogo/cardex-dialogo.component';
import { CardexNuevoComponent } from './pages/cardex/cardex-nuevo/cardex-nuevo.component';
import { CardexDialogoEliminarComponent } from './pages/cardex/cardex-dialogo-eliminar/cardex-dialogo-eliminar.component';
import { InventarioComponent } from './pages/inventario/inventario.component';
import { InventarioRegistroComponent } from './pages/inventario/inventario-registro/inventario-registro.component';
import { InventarioBusquedaComponent } from './pages/inventario/inventario-busqueda/inventario-busqueda.component';
import { InventarioEntradaSalidaComponent } from './pages/inventario/inventario-entrada-salida/inventario-entrada-salida.component';
import { SegmentoComponent } from './pages/segmento/segmento.component';
import { SegmentoIngresoBusquedaComponent } from './pages/segmento/segmento-ingreso-busqueda/segmento-ingreso-busqueda.component';
import { SegmentoIngresoBusquedaDialogoComponent } from './pages/segmento/segmento-ingreso-busqueda/segmento-ingreso-busqueda-dialogo/segmento-ingreso-busqueda-dialogo.component';
import { SegmentoIngresoBusquedaDialogoEliminarComponent } from './pages/segmento/segmento-ingreso-busqueda/segmento-ingreso-busqueda-dialogo-eliminar/segmento-ingreso-busqueda-dialogo-eliminar.component';
import { SegmentoCreditoPagoComponent } from './pages/segmento/segmento-credito-pago/segmento-credito-pago.component';
import { SegmentoPagoDialogoComponent } from './pages/segmento/segmento-credito-pago/segmento-pago-dialogo/segmento-pago-dialogo.component';
import { SegmentoPagoDialogoEliminarComponent } from './pages/segmento/segmento-credito-pago/segmento-pago-dialogo-eliminar/segmento-pago-dialogo-eliminar.component';
import { ProveedorComponent } from './pages/proveedor/proveedor.component';
import { ProveedorDialogoComponent } from './pages/proveedor/proveedor-dialogo/proveedor-dialogo.component';
import { ProveedorDialogoEliminarComponent } from './pages/proveedor/proveedor-dialogo-eliminar/proveedor-dialogo-eliminar.component';
import { AsesorDialogoComponent } from './pages/proveedor/asesor-dialogo/asesor-dialogo.component';
import { AsesorDialogoEliminarComponent } from './pages/proveedor/asesor-dialogo-eliminar/asesor-dialogo-eliminar.component';
import { PlacaComponent } from './pages/placa/placa.component';
import { PlacaDialogoEliminarComponent } from './pages/placa/placa-dialogo-eliminar/placa-dialogo-eliminar.component';
import { FacturaCompraComponent } from './pages/factura-compra/factura-compra.component';
import { FacturaCompraIngresoComponent } from './pages/factura-compra/factura-compra-ingreso/factura-compra-ingreso.component';
import { FacturaCompraBusquedaComponent } from './pages/factura-compra/factura-compra-busqueda/factura-compra-busqueda.component';
import { FacturaCompraDialogoEliminarComponent } from './pages/factura-compra/factura-compra-busqueda/factura-compra-dialogo-eliminar/factura-compra-dialogo-eliminar.component';
import { CotizacionComponent } from './pages/cotizacion/cotizacion.component';
import { CotizacionDialogoBuscarComponent } from './pages/cotizacion/cotizacion-dialogo-buscar/cotizacion-dialogo-buscar.component';
import { CotizacionDialogoEliminarComponent } from './pages/cotizacion/cotizacion-dialogo-eliminar/cotizacion-dialogo-eliminar.component';

@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    SoporteComponent,
    MarcaAutoComponent,
    MarcaRepuestoComponent,
    ServicioTipoComponent,
    MarcaAutoDialogoComponent,
    MarcaAutoDialogoEliminarComponent,
    MarcaRepuestoDialogoComponent,
    MarcaRepuestoDialogoEliminarComponent,
    ServicioTipoDialogoComponent,
    ServicioTipoDialogoEliminarComponent,
    CardexComponent,
    CardexDialogoComponent,
    CardexNuevoComponent,
    CardexDialogoEliminarComponent,
    InventarioComponent,
    InventarioRegistroComponent,
    InventarioBusquedaComponent,
    InventarioEntradaSalidaComponent,
    SegmentoComponent,
    SegmentoIngresoBusquedaComponent,
    SegmentoIngresoBusquedaDialogoComponent,
    SegmentoIngresoBusquedaDialogoEliminarComponent,
    SegmentoCreditoPagoComponent,
    SegmentoPagoDialogoComponent,
    SegmentoPagoDialogoEliminarComponent,
    ProveedorComponent,
    ProveedorDialogoComponent,
    ProveedorDialogoEliminarComponent,
    AsesorDialogoComponent,
    AsesorDialogoEliminarComponent,
    PlacaComponent,
    PlacaDialogoEliminarComponent,
    FacturaCompraComponent,
    FacturaCompraIngresoComponent,
    FacturaCompraBusquedaComponent,
    FacturaCompraDialogoEliminarComponent,
    CotizacionComponent,
    CotizacionDialogoBuscarComponent,
    CotizacionDialogoEliminarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide : HTTP_INTERCEPTORS,
      useClass : ServerErrorsInterceptor,
      multi : true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
