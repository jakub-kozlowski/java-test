package assignment;

public enum Item {
    SOUP("Soup")
    , BREAD("Bread")
    , MILK("Milk")
    , APPLE("Apple");

    final String name;

    Item(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
