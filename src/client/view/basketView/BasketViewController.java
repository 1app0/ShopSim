package client.view.basketView;

import client.core.ViewHandler;
import client.view.cashierView.TableRowData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BasketViewController {
  @FXML private Label resultLabel;
  @FXML private TableView<TableRowData> list;
  @FXML private TableColumn<TableRowData, Number> idColumn;
  @FXML private TableColumn<TableRowData, String> nameColumn;
  @FXML private TableColumn<TableRowData, Number> barcodeColumn;

  private BasketViewModel viewModel;
  private ViewHandler viewHandler;

  public void init(BasketViewModel viewModel, ViewHandler viewHandler) {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;
    resultLabel.textProperty().bind(viewModel.getCheckoutResult());
    idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    barcodeColumn.setCellValueFactory(cellData -> cellData.getValue().getBarcodeProperty());
    list.setItems(viewModel.getShopItemList());
  }

  @FXML private void checkout() {
    viewModel.checkout();
  }

  @FXML private void cancel() {
    viewHandler.openCashierView();
  }
}
