package client.view.cashierView;

import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class CashierViewController {
  @FXML private TableView<TableRowData> shopItemsTable;
  @FXML private TableColumn<TableRowData, Number> idColumn;
  @FXML private TableColumn<TableRowData, String> nameColumn;
  @FXML private TableColumn<TableRowData, Number> barcodeColumn;
  @FXML private Label errorLabel;

  private ViewHandler viewHandler;
  private CashierViewModel viewModel;

  public void init(CashierViewModel viewModel, ViewHandler viewHandler) {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;

    idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    barcodeColumn.setCellValueFactory(cellData -> cellData.getValue().getBarcodeProperty());
    shopItemsTable.setItems(viewModel.getList());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    viewModel.getSelectedProperty().bind(shopItemsTable.getSelectionModel().selectedItemProperty());
  }

  @FXML private void addToBasket() {
    viewModel.addToBasket();
    shopItemsTable.getSelectionModel().clearSelection();
    shopItemsTable.refresh();
  }

  @FXML private void showBasket() {
    viewHandler.openBasketView();
  }

  @FXML private void logOut() {
    viewModel.clear();
    viewHandler.openLoginView();
  }
}
