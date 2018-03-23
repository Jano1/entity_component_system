package ecs;

import component.Component;

import java.util.ArrayList;
import java.util.List;

public class Blueprint extends ArrayList<Component> {

    private static Blueprint empty = new Blueprint();

    public Blueprint(){ }

    public Blueprint(List<Component> components) {
        super(components);
    }

    public static Blueprint empty_blueprint(){
        return empty;
    }
}
