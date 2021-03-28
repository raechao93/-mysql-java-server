package com.example.wbdvsp2102ruichaoserverjava.services;

import com.example.wbdvsp2102ruichaoserverjava.models.Widget;
import com.example.wbdvsp2102ruichaoserverjava.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository repositary;

    private List<Widget> widgets = new ArrayList<Widget>();
    {
        Widget w1 = new Widget(123l, "60492c244bf566001716e033", "HEADING", 1, "I Love Coding: 1");
        Widget w2 = new Widget(124l, "60492c244bf566001716e033", "PARAGRAPH", 4, "I Love Coding: 1");
        Widget w3 = new Widget(125l, "60492c244bf566001716e033", "HEADING", 2, "I Love Coding: 1");
        Widget w4 = new Widget(126l, "60492c244bf566001716e033", "PARAGRAPH", 3, "I Love Coding: 1");

        widgets.add(w1);
        widgets.add(w2);
        widgets.add(w3);
        widgets.add(w4);
    }

    public Widget createWidgetForTopic(String topicId, Widget widget) {

        widget.setTopicId(topicId);
        return repositary.save(widget);

//        widget.setId((new Date()).getTime());
//        widgets.add(widget);
//        return widget;
    }

    public List<Widget> findAllWidgets() {
        return (List<Widget>) repositary.findAll();

//        return widgets;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {

        return repositary.findWidgetsForTopic(topicId);
//
//        List<Widget> ws = new ArrayList<Widget>();
//        for(Widget w:widgets) {
//            if(w.getTopicId().equals(topicId)) {
//                ws.add(w);
//            }
//        }
//        return ws;
    }

    public Integer deleteWidget(Long widgetId) {
        repositary.deleteById(widgetId);
        return 1;
//        int index = -1;
//        for(int i = 0; i < widgets.size();i++) {
//            if(widgets.get(i).getId().equals(widgetId)){
//                index = i;
//                widgets.remove(index);
//                return 1;
//            }
//        }
//        return -1;

    }

    public Integer updateWidget(Long widgetId, Widget widget) {

        Widget originalWidget = repositary.findById(widgetId).get();

        //TODO: copy all the other fields teaasting for null
        originalWidget.setText(widget.getText());
        originalWidget.setType(widget.getType());
        originalWidget.setSize(widget.getSize());

        repositary.save(originalWidget);

        return 1;


//        for(int i = 0; i < widgets.size();i++) {
//            if(widgets.get(i).getId().equals(widgetId)){
//                widgets.set(i, widget);
//                return 1;
//            }
//        }
//        return -1;
    }

    public Widget findWidgetForDelete(Long widgetId) {
        for(int i = 0; i < widgets.size();i++) {
            if(widgets.get(i).getId().equals(widgetId)){
                return widgets.get(i);
            }
        }
        return widgets.get(0);
    }
}