package client.view.managerView;

import client.core.ViewHandler;
import client.view.cashierView.TableRowData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManagerViewController {
  @FXML private TableView<TableRowData> shopItemsTable;
  @FXML private TableColumn<TableRowData, Number> idColumn;
  @FXML private TableColumn<TableRowData, String> nameColumn;
  @FXML private TableColumn<TableRowData, Number> barcodeColumn;
  @FXML private Label errorLabel;

  private ViewHandler viewHandler;
  private ManagerViewModel viewModel;

  public void init(ManagerViewModel viewModel, ViewHandler viewHandler) {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;

    idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    barcodeColumn.setCellValueFactory(cellData -> cellData.getValue().getBarcodeProperty());
    shopItemsTable.setItems(viewModel.getList());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    viewModel.getSelectedProperty().bind(shopItemsTable.getSelectionModel().selectedItemProperty());
  }

  @FXML private void addItem() {
    viewHandler.openAddItemsView();
  }

  @FXML private void removeItem() {
    viewModel.removeItem();
    shopItemsTable.getSelectionModel().clearSelection();
  }

  @FXML private void logOut() {
    viewModel.clear();
    viewHandler.openLoginView();
  }
}
