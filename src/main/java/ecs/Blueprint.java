package ecs;

import component.Component;

import java.util.ArrayList;

public class Blueprint extends ArrayList<Component> {

    private static Blueprint empty = new Blueprint();

    public static Blueprint empty_blueprint(){
        return empty;
    }

    public Component get_specific(String type){
        for(Component c : this){
            if(c.type().equals(type)){
                return c;
            }
        }
        return null;
    }
}
