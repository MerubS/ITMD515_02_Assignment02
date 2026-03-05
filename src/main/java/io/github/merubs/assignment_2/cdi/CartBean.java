package io.github.merubs.assignment_2.cdi;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class CartBean implements Serializable {
    private int orderList = 0;

    public void addItem()   { orderList++; }
    public void removeItem(){ orderList--; }
    public void reset()     { orderList = 0; } // bonus reset action

    public int getOrderList() { return orderList; }
    public void setOrderList(int orderList) { this.orderList = orderList; }
}
