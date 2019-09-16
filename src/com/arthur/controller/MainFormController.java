/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arthur.controller;

import com.arthur.entity.Barang;
import com.arthur.entity.Category;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Arthur Damarwulan (1672025)
 */
public class MainFormController implements Initializable {

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField categorynameTxt;
    @FXML
    private TableColumn<Barang, String> nameCol;
    @FXML
    private TableColumn<Barang, String> priceCol;
    @FXML
    private TableColumn<Barang, String> categorycol;
    @FXML
    private ComboBox<Category> comboFruit;

    private ObservableList<Category> categories;

    private ObservableList<Barang> barangs;
    @FXML
    private TableView<Barang> tbBarang;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;

    public ObservableList<Category> getCategories() {
        if (categories == null) {
            categories = FXCollections.observableArrayList();
        }
        return categories;
    }

    public ObservableList<Barang> getBarangs() {
        if (barangs == null) {
            barangs = FXCollections.observableArrayList();
        }
        return barangs;
    }

    private Barang selectedBarang;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboFruit.setItems(getCategories());
        tbBarang.setItems(getBarangs());
        nameCol.setCellValueFactory(data -> {
            Barang barang = data.getValue();
            return new SimpleStringProperty(barang.getName());
        });
        priceCol.setCellValueFactory(data -> {
            Barang barang = data.getValue();
            return new SimpleStringProperty(String.valueOf(barang.getPrice()));
        });
        categorycol.setCellValueFactory(data -> {
            Barang barang = data.getValue();
            return new SimpleStringProperty(barang.getCategory().getName());
        });

    }

    private void clearFields() {
        nameTxt.setText(null);
        priceTxt.setText(null);
        categorynameTxt.setText(null);
    }

    @FXML
    private void savecategoryAction(javafx.event.ActionEvent event) {
        Category c = new Category();
        c.setName(categorynameTxt.getText());
        getCategories().add(c);
        comboFruit.setItems(getCategories());
    }

    @FXML
    private void saveAction(javafx.event.ActionEvent event) {
        //Nambah Nama Barang
        Barang b = new Barang();
        b.setName(nameTxt.getText());
        b.setPrice(Integer.valueOf(priceTxt.getText()));
        b.setCategory(comboFruit.getValue());
        getBarangs().add(b);
        tbBarang.refresh();
        //Nambah Harga Barang
    }

    @FXML
    private void resetAction(javafx.event.ActionEvent event) {
        clearFields();
    }

    @FXML
    private void updateAction(javafx.event.ActionEvent event) {

    }

    @FXML
    private void tableItemClick(MouseEvent event) {
        selectedBarang = tbBarang.getSelectionModel().getSelectedItem();
        if (selectedBarang != null) {
            nameTxt.setText(selectedBarang.getName());
            priceTxt.setText(String.valueOf(selectedBarang.getPrice()));
            categorynameTxt.setText(String.valueOf(selectedBarang.getCategory()));
            comboFruit.setValue(selectedBarang.getCategory());
            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnReset.setDisable(false);
        }
    }

}
