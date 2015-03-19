package com.ase.bean.list;

import com.ase.bean.ItemBean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by saiteja on 3/16/2015.
 */
@XmlRootElement(namespace = "ase")
public class ItemBeanList {
    private List<ItemBean> itemBeans;

    @XmlElement
    public List<ItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(List<ItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }
}
