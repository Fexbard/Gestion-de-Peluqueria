import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CustomersListComponent } from './components/customers-list/customers-list.component';
import { CustomerRegisterComponent } from './components/customer-register/customer-register.component';
import { CustomerUpdateComponent } from './components/customer-update/customer-update.component';
import { CustomerDetailsComponent } from './components/customer-details/customer-details.component';
import { JobRegisterComponent } from './components/job-register/job-register.component';
import { JobsListComponent } from './components/jobs-list/jobs-list.component';
import { JobUpdateComponent } from './components/job-update/job-update.component';
import { StatisticsJobsComponent } from './components/statistics-jobs/statistics-jobs.component';

//Agregar las rutas de los componentes
const routes: Routes = [
  {path: '', component: DashboardComponent},
  {path: 'clientes', component: CustomersListComponent },
  {path: 'clientes/registrar', component: CustomerRegisterComponent},
  {path: 'clientes/actualizar/:id', component:CustomerUpdateComponent},
  {path: 'clientes/detalles/:id', component:CustomerDetailsComponent},
  {path: 'clientes/:id/agregar-trabajo', component:JobRegisterComponent},
  {path: 'trabajos', component:JobsListComponent},
  {path: 'trabajos/actualizar/:id', component:JobUpdateComponent},
  {path: 'estadisticas/trabajos', component:StatisticsJobsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
