/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.utils;

/**
 *
 * @author eliud
 */
public class FieldDto implements Comparable<FieldDto> {

    private String name;
    private String title;
    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public FieldDto(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public FieldDto(String name, String title, Integer order) {
        this.name = name;
        this.title = title;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(FieldDto o) {
        return this.order.compareTo(o.getOrder());
    }

}
