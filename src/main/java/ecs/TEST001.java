package ecs;

import component.implemented.PositionComponent;
import component.implemented.TimeComponent;
import component.implemented.VelocityComponent;

/**
 * Created by jleissner on 19.03.2018.
 */
public class TEST001 {
    public static void main(String[] args){
        ECS ecs = new ECS(100);

        ID world = ecs.create_entity(Blueprint.empty_blueprint());
        world.add_component(new TimeComponent(0,20,1));

        ID runner_01 = ecs.create_entity(Blueprint.empty_blueprint());
        runner_01.add_component(new TimeComponent(1, (TimeComponent) world.as_blueprint().get_specific(TimeComponent.class.toString())));
        runner_01.add_component(new PositionComponent(0,0,0));
        runner_01.add_component(new VelocityComponent(1,0,0));

        System.out.println(world);
        System.out.println(runner_01);

    }
}
