package com.example.collabapp.views;

import java.util.UUID;

import com.vaadin.collaborationengine.FormManager;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Test")
@Route(value = "", layout = MainLayout.class)
public class TestView extends Div {

    public TestView() {
        UserInfo userInfo = new UserInfo(UUID.randomUUID().toString(), "Steve Lange");

        VerticalLayout layout = new VerticalLayout();
        TextArea textArea = new TextArea("Text Area");
        layout.add(textArea);
        add(layout);

        FormManager manager = new FormManager(textArea, userInfo, "text-area");
        manager.setPropertyChangeHandler(event -> {
            textArea.setValue(event.getValue(String.class));
        });

        textArea.addValueChangeListener(event -> {
            String value = event.getValue();
            manager.setValue("text", value);
        });

        Button button = new Button("Cancel", event -> {
            remove(layout);
            textArea.setValue("");
        });
        add(button);
    }
}
