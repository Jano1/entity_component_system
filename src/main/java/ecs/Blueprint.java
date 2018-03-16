package ecs;

import component.Component;

import java.util.ArrayList;

public class Blueprint extends ArrayList<Component> {
    public static Blueprint empty_blueprint(){
        return new Blueprint();
    }
}
