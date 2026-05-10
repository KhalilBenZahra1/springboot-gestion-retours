import { Routes } from '@angular/router';

import { Utilisateurs } from './pages/utilisateurs/utilisateurs';
import { Retours } from './pages/retours/retours';
import { NonConformites } from './pages/non-conformites/non-conformites';
import { Stock } from './pages/stock/stock';
import { Historique } from './pages/historique/historique';

export const routes: Routes = [

  {
    path: 'utilisateurs',
    component: Utilisateurs
  },

  {
    path: 'retours',
    component: Retours
  },

  {
    path: 'non-conformites',
    component: NonConformites
  },

  {
  path: 'stock',
  component: Stock
  },

  {
  path: 'historique',
    component: Historique
  },

  {
    path: '',
    redirectTo: 'utilisateurs',
    pathMatch: 'full'
  }

];