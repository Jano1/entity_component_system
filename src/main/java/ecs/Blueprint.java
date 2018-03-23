package ecs;

import component.Component;

import java.util.ArrayList;

public class Blueprint extends ArrayList<Component> {

    private static Blueprint empty = new Blueprint();

    public static Blueprint empty_blueprint(){
        return empty;
    }
}
