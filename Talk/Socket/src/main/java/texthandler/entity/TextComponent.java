package texthandler.entity;

import java.util.ArrayList;


public interface TextComponent {
    void addComponent(TextComponent component);
    ArrayList<TextComponent> getComponents();
}
