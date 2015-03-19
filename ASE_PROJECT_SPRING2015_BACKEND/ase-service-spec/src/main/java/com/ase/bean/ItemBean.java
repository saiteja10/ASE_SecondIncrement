package com.ase.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by saiteja on 3/16/2015.
 */
@XmlRootElement(namespace = "ase")
public class ItemBean extends BaseBean {
    private String name;
    private Double price;
    private String description;
    private Long subCategoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
}
