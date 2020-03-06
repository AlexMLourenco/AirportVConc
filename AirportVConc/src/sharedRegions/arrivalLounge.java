package sharedRegions;

import commonInfra.BAG;

public class arrivalLounge {

    /* Porter functions */

    char takeARest(){       // Ponto de Sincronização para bloqueio do bagageiro
        return '-';
    }

    BAG tryToColletABag() {
        // necessito do array de STACKS (constitui o array dos poroes dos avioes)
        // instancia a mala e devolver o manisfesto ou nulo

        BAG a = new BAG();

        return a;
    }

    void noMoreBagsToCollect() {

    }

    /* Passenger functions */

    char whatShouldIDo(int nLand) {
        //para cada passeigeiro vai ler se é o destino final ou nao

        // variavel que sinalize o fim de operação (boolean)

        return '-';
    }

    void setEndOfWork() {
        // variavel que conte os passageiros que chegam em cada voo (int)

    }


}
