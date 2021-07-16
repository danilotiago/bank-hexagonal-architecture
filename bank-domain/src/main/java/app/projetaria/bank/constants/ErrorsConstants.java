package app.projetaria.bank.constants;

public class ErrorsConstants {

    public static final String CREDIT_VALUE_IS_REQUIRED = "O valor para efetuar o crédito é obrigatório";
    public static final String CREDIT_VALUE_IS_LESS_OR_EQUAL_THAN_ZERO = "O valor para efetuar o crédito deve ser maior que zero";
    public static final String DEBIT_VALUE_IS_REQUIRED = "O valor para efetuar o débito é obrigatório";
    public static final String DEBIT_VALUE_IS_LESS_THAN_OR_ZERO = "O valor para efetuar o débito deve ser maior que zero";
    public static final String INSUFICIENT_FOUNDS = "Saldo insuficiente para efetuar o débito";

    public static final String TRANSFER_VALUE_IS_REQUIRED = "O valor para efetuar a transferência é obrigatório";
    public static final String TRANSFER_VALUE_IS_LESS_OR_EQUAL_THAN_ZERO = "O valor para efetuar a transferência deve ser maior que zero";
    public static final String ACCOUNT_DEBIT_IS_REQUIRED = "A conta de débito para efetuar a transferência é obrigatória";
    public static final String ACCOUNT_CREDIT_IS_REQUIRED = "A conta de crédito para efetuar a transferência é obrigatória";

    public static final String ACCOUNT_NOT_FOUND = "Conta inexistente";
    public static final String ACCOUNT_DEBIT_NOT_FOUND = "Conta de débito inexistente";
    public static final String ACCOUNT_CREDIT_NOT_FOUND = "Conta de crédito inexistente";

    public static final String TRANSFER_SAME_ACCOUNT_IS_NOT_AUTHORIZED = "Uma transferência não pode ser efetuada para a mesma conta";
}
