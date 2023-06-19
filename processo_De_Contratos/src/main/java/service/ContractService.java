package service;
import entities.*;

import java.time.LocalDate;

public class ContractService {
    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months){

        double basicQuota = contract.getTotalValue() / months; //a cota basica por mês sera o valor total dividido pelo numero de meses.

        //a data de vencimento de cada parecela é a data original do contrato somada a quantidade de meses da variável "i"
        //EX: a primeira parcela vai ser a data atual(mes) + 1(mes)
        for (int i = 1; i <= months; i++){
            LocalDate dueDate = contract.getDate().plusMonths(i);
            double interest = onlinePaymentService.interest(basicQuota, i);
            /*EX: double resul = obj.interest(200,1);
             *      (result = 2)
             * double resul = obj.interest(200,2);
             *       (result = 4)
             * double resul = obj.interest(200,3);
             *       (result = 6)
             * */
            double fee = onlinePaymentService.paymentFee(basicQuota + interest);
            /*EX: double result = obj.paymentFee(200(cota basica do mês) + 2(interest));
             *              (rusult = 4.04)
             * double result = obj.paymentFee(200 + 4);
             *              (result = 4.08)
             * double result = obj.paymentFee(206);
             *              (result = 4.12)
             * */

            double quota = basicQuota + interest + fee;
            /*Cálculos (1% juro simples mensal + 2% taxa de pagamento):
        Parcela #1:                 Parcela #2:                 Parcela #3:
        200 + 1% * 1 = 202          200 + 1% * 2 = 204          200 + 1% * 3 = 206
        202 + 2% = 206.04           204 + 2% = 208.08           206 + 2% = 210.12      */
            contract.getInstallments().add(new Installment(dueDate,quota));

        }

    }
}
