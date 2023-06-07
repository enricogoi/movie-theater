package com.jpmc.theater;

public class Customer {

    private final String name;
    // Keep a uniqueId even if not used at the moment because we could easily imagine discount rules that depend on the customer. E.g. loyalty cards.
    private final String uniqueId;


    /**
     * @param name customer name
     * @param uniqueId unique customer id - Service creating Customer objects should guarantee uniqueness
     */
    public Customer(String name, String uniqueId) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!name.equals(customer.name)) return false;
        return uniqueId.equals(customer.uniqueId);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + uniqueId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "name: " + getName() + ", uniqueId: " + getUniqueId();
    }
}