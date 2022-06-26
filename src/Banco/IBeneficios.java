package Banco;

import Cliente.Cliente;

public interface IBeneficios {
    public boolean temDescontoImposto();
    public boolean temBonusRend();
    double limiteCheque(Cliente cliente, double valor);
}
