package com.monica.todolist;

import com.monica.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadlineLabel;

    public void initialize() {
        TodoItem item1 = new TodoItem("Mail Father's Day cards", "Send Cassie's art",
                LocalDate.of(2020, Month.JUNE, 20));
        TodoItem item2 = new TodoItem("Fold clothes", "Get clothes from dryer and start new load",
                LocalDate.of(2020, Month.JUNE, 17));
        TodoItem item3 = new TodoItem("Fill out time card", "Go to work computer and fill out",
                LocalDate.of(2020, Month.JUNE, 27));
        TodoItem item4 = new TodoItem("Doctor Appointment", "Meet Dr. Gonden and bring paperwork",
                LocalDate.of(2020, Month.SEPTEMBER, 1));
        TodoItem item5 = new TodoItem("Call Mom", "Need to discuss ongoing therapy sessions",
                LocalDate.of(2018, Month.JANUARY, 30));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                }
            }
        });

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());

        deadlineLabel.setText(item.getDeadline().toString());
        //System.out.println("The selected item is " + item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadline().toString());
//        itemDetailsTextArea.setText(sb.toString());
    }

}
