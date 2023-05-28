/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication.pistas;

import javafx.scene.control.ListCell;

/**
 *
 * @author erikb
 */
class ComboListCell<String> extends ListCell<String> {

    @Override
    protected void updateItem(String s, boolean empty) {
        super.updateItem(s, empty);

        if (empty || s == null) {
            setText("hola");
            setStyle("-fx-underline: true");

        } else {
            setText(s.toString());
            setStyle("");
        }
    }
}