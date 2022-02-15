package com.example.ipscan.page;

import com.example.ipscan.model.IPInfoValue;
import com.example.ipscan.service.IIPInfoService;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {
    @SpringBean
    IIPInfoService ipInfoService;

    public HomePage() {
        var ipInfoValueList = ipInfoService.findAll();

        add(new PropertyListView<>("ipInfoList", Model.ofList(ipInfoValueList)) {
            @Override
            protected void populateItem(ListItem<IPInfoValue> listItem) {
                listItem.add(new Label("ipAddress"));
                listItem.add(new Label("hostName"));
                listItem.add(new Label("isUse"));
            }
        });
    }

    //CSSの適用メソッド
    @Override
    public void renderHead(IHeaderResponse response) {
        var cssFile =
                new PackageResourceReference(this.getClass(), "style.css");
        var cssItem = CssHeaderItem.forReference(cssFile);

        response.render(cssItem);
    }


}
