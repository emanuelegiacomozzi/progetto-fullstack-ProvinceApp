import { Provincia } from "./provincia";

export class Comune {
    provincia?:Provincia;
    codice_istat?:number;
    denominazione_ita_altra?:string;
    denominazione_ita?:string;
    denominazione_altra?:string;
    flag_capoluogo?:string;
    codice_belfiore?:string;
    lat?:string;
    lon?:string;
    superficie_kmq?:string;
    codice_sovracomunale?:number;
}
