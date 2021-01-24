package texthandler.entity;

import java.util.ArrayList;


public class TextComposite implements TextComponent {

    private ComponentType componentType;
    private ArrayList<TextComponent> components = new ArrayList<>();

    public TextComposite(ComponentType componentType) {
        this.componentType = componentType;
    }

    @Override
    public void addComponent(TextComponent component) {
        components.add(component);
    }

    public ArrayList<TextComponent> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent component : components) {
            stringBuilder.append(component);
        }
        return stringBuilder.toString();
    }
}
