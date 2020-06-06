package az.maqa.project.model;

public class Item {
    private Long Id;
    private String Value;

    public Item(Long Id, String Value) {
        this.Id = Id;
        this.Value = Value;
    }

    public Long getId() {
        return Id;
    }

    public String getValue() {
        return Value;
    }

    @Override
    public String toString() {
        return Value;
    }
}
