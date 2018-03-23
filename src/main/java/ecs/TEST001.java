package ecs;

import component.implemented.PositionComponent;
import component.implemented.TimeComponent;
import component.implemented.VelocityComponent;
import system.implemented.MovementSystem;

/**
 * Created by jleissner on 19.03.2018.
 */
public class TEST001 {
    public static void main(String[] args){
        ECS ecs = new ECS(100);

        ecs.register_system(MovementSystem.class);

        ID runner_01 = ecs.create_entity(Blueprint.empty_blueprint());
        runner_01.add(new TimeComponent(20));
        runner_01.add(new PositionComponent(0f,0f,0f));
        runner_01.add(new VelocityComponent(1f,0f,0f, 20));

        runner_01.get(VelocityComponent.class).normalize();

        System.out.println(runner_01);

        for(int i = 0 ; i < (20*1) ; i++){
            ecs.tick();
            System.out.println(runner_01.get(PositionComponent.class));
        }

    }
}
