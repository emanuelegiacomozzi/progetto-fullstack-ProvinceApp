import { Routes } from '@angular/router';
import { ListaProvince } from './components/lista-province/lista-province';
import { InserimentoProvincia } from './components/inserimento-provincia/inserimento-provincia';
import { ListaComuniProvincia } from './components/lista-comuni-provincia/lista-comuni-provincia';
import { InserimentoComuni } from './components/inserimento-comuni/inserimento-comuni';
import { ListaComuni } from './components/lista-comuni/lista-comuni';
import { ModificaProvincia } from './components/modifica-provincia/modifica-provincia';
import { ModificaComune } from './components/modifica-comune/modifica-comune';

export const routes: Routes = [
    {
        path:"",
        redirectTo:"italia/province",
        pathMatch:"full"
    },
    {
        path:"italia/province",
        component: ListaProvince
    }, 
    {
        path:"italia/inserimento/provincia",
        component: InserimentoProvincia
    },
    {
        path:"italia/comuni/:sigla",
        component:ListaComuniProvincia
    },
    {
        path:"italia/inserimento/comune",
        component: InserimentoComuni
    },
    {
        path:"italia/comuni",
        component: ListaComuni
    },
    {
        path:"italia/modifica/:sigla",
        component:ModificaProvincia
    },
    {
        path:"italia/modifica/comune/:sigla/:codice",
        component:ModificaComune
    }
];
