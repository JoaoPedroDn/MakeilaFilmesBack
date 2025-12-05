package com.filmestore.model.pagamento;

import com.filmestore.enums.StatusPagamento;


public interface IPagamento {


    boolean processarPagamento(double valor);


    StatusPagamento getStatus();
}
