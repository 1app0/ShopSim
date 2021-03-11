package client.view.addItemView;

import client.core.ViewHandler;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class AddItemViewController {
  @FXML private TextField idTextField;
  @FXML private TextField nameTextField;
  @FXML private TextField barcodeTextField;
  @FXML private Label errorLabel;

  private AddItemViewModel viewModel;
  private ViewHandler viewHandler;

  public void init(AddItemViewModel viewModel, ViewHandler viewHandler) {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;

    nameTextField.textProperty().bindBidirectional(viewModel.getName());
    errorLabel.textProperty().bind(viewModel.getError());
    Bindings.bindBidirectional(idTextField.textProperty(), viewModel.getId(), new StringConverter<>()
    {
      @Override public String toString(Number n)
      {
        if (n == null || n.intValue() < 0)
        {
          return "";
        }
        return n.toString();
      }

      @Override public Number fromString(String s)
      {
        try
        {
          return Integer.parseInt(s);
        }
        catch (Exception e)
        {
          return -1;
        }
      }
    });
    Bindings.bindBidirectional(barcodeTextField.textProperty(), viewModel.getBarcode(), new StringConverter<>() {
      @Override public String toString(Number n)
      {
        if (n == null || n.intValue() < 0)
        {
          return "";
        }
        return n.toString();
      }

      @Override public Number fromString(String s)
      {
        try
        {
          return Integer.parseInt(s);
        }
        catch (Exception e)
        {
          return -1;
        }
      }
    });
  }

  public void reset() {
    viewModel.clear();
  }

  @FXML private void addItem() {
    boolean added = viewModel.addItem();
    if (added) {
      viewHandler.openManagerView();
    }
  }

  @FXML private void cancel() {
    viewHandler.openManagerView();
  }
}
