package br.com.felipemira.initializers.desktop.javafx.tela;

import br.com.felipemira.application.core.domain.model.Account;
import br.com.felipemira.application.core.domain.model.Transfer;
import br.com.felipemira.application.core.ports.in.AccountInfoUseCase;
import br.com.felipemira.application.core.ports.in.TransferUseCase;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.isNull;

// Responsavel por desenhar a tela de transferencia com a tecnologia javafx.
@Named
public class TransferForm {

    private Stage stage;
    private TextField tfDebit;
    private TextField tfNameDebit;
    private TextField tfCredit;
    private TextField tfNameCredit;
    private TextField tfBalance;
    private final AccountInfoUseCase accountInfoUseCase;
    private final TransferUseCase transferUseCase;

    @Inject
    public TransferForm(AccountInfoUseCase accountInfoUseCase, TransferUseCase transferUseCase) {
        this.accountInfoUseCase = accountInfoUseCase;
        this.transferUseCase = transferUseCase;
    }
    private void clear() {
        try{
            tfDebit.setText("");
            tfNameDebit.setText("");
            tfCredit.setText("");
            tfNameCredit.setText("");
            tfBalance.setText("");
        }catch(Exception ignore){}
    }
    
    private Integer get(String valor) {
        try {
            if(Objects.nonNull(valor) && !valor.isEmpty()){
                return Integer.valueOf(valor);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    private void message(String texto) {
        if(!isNull(texto)){
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initOwner(stage);
            alert.setTitle("Transferencia Bancaria");
            alert.setHeaderText(null);
            alert.setContentText(texto);
            alert.getDialogPane().idProperty().setValue("alert");
            alert.showAndWait();
        }
    }
    
    private void get(TextField tfInput, TextField tfOutput) {
        try {
            var numberAccount = get(tfInput.getText());
            var account = accountInfoUseCase.getAccount(new AccountInfoUseCase.AccountCommand(new Account(Objects.nonNull(numberAccount) ? Long.valueOf(numberAccount) : null)));
            if (isNull(account)) {
                tfOutput.setText("");
            } else {
                tfOutput.setText(account.getAccountHolder().getName() + " - Saldo R$ " +
                        account.getBalance());
            }
        } catch (Exception e) {
            message(e.getMessage());
        }
    }
    
    private BigDecimal get() {
        try {
            return new BigDecimal(tfBalance.getText());
        } catch (Exception e) {
            return null;
        }
    }
    
    private FlowPane buildForm() {
        var pn = new FlowPane();
        pn.setHgap(10);
        pn.setVgap(10);
        pn.getChildren().add(new Label(" Conta Debit:"));
        tfDebit = new TextField();
        tfDebit.setId("tfDebit");
        tfDebit.setPrefWidth(50);
        tfDebit.focusedProperty().addListener((o, v, n) -> {
            if (!n) get(tfDebit, tfNameDebit);
        });
        pn.getChildren().add(tfDebit);
        tfNameDebit = new TextField();
        tfNameDebit.setId("tfNameDebit");
        tfNameDebit.setPrefWidth(300);
        tfNameDebit.setEditable(false);
        pn.getChildren().add(tfNameDebit);
        pn.getChildren().add(new Label(" Conta Credit:"));
        tfCredit = new TextField();
        tfCredit.setId("tfCredit");
        tfCredit.setPrefWidth(50);
        tfCredit.focusedProperty().addListener((o, v, n) -> {
            if (!n) get(tfCredit, tfNameCredit);
        });
        pn.getChildren().add(tfCredit);
        tfNameCredit = new TextField();
        tfNameCredit.setId("tfNameCredit");
        tfNameCredit.setEditable(false);
        tfNameCredit.setPrefWidth(300);
        pn.getChildren().add(tfNameCredit);
        pn.getChildren().add(new Label(" Balance R$:"));
        tfBalance = new TextField();
        tfBalance.setId("tfBalance");
        tfBalance.setPrefWidth(200);
        pn.getChildren().add(tfBalance);
        var bt = new Button();
        bt.setId("bt");
        bt.setOnAction((ev) -> {
            try {
                var accountDebit = get(tfDebit.getText());
                var accountCredit = get(tfCredit.getText());

                transferUseCase.transfer(new TransferUseCase.TransferCommand(new Transfer(new Account(Objects.nonNull(accountDebit) ? Long.valueOf(accountDebit) : null),
                        new Account(Objects.nonNull(accountCredit) ? Long.valueOf(accountCredit) : null), get())));
                clear();
                message("Transferencia feita com sucesso!");
            } catch (Exception e) {
                message(e.getMessage());
            }
        });
        bt.setText("Transferir");
        pn.getChildren().add(bt);
        return pn;
    }
    
    public void show(Stage stage) {
        this.stage = stage;
        stage.setTitle("Adaptador JavaFX");
        var scene = new Scene(buildForm(), 500, 100);
        stage.setScene(scene);
        stage.show();
    }
}

