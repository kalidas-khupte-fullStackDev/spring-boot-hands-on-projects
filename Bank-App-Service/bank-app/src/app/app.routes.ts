import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./promise-obser-demo/promise-obser-demo.component').then(m => m.PromiseObserDemoComponent),
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./dashboard/dashboard.component').then(m => m.DashboardComponent),
  },
   {
    path: 'table',
    loadComponent: () => import('./data-table/data-table.component').then(m => m.DataTableComponent)
  },
  {
    path: 'graph',
    loadComponent: () => import('./data-graph/data-graph.component').then(m => m.DataGraphComponent)
  },
];
