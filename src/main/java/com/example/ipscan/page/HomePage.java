package com.example.ipscan.page;

import com.example.ipscan.model.IPInfoModel;
import com.example.ipscan.util.IPScan;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Objects;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {
    public HomePage() {
        var ipScanner = new IPScan("192.168.11.0");
        var ipInfoList = Objects.requireNonNull(ipScanner.scanAll(255))
                .stream()
                .filter(IPInfoModel::isUse)
                .toList();

        add(new PropertyListView<>("ipInfoList", Model.ofList(ipInfoList)) {
            @Override
            protected void populateItem(ListItem<IPInfoModel> listItem) {
                listItem.add(new Label("ipAddress"));
                listItem.add(new Label("hostName"));
                listItem.add(new Label("isUse"));
            }
        });
    }

}
