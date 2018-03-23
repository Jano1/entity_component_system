package component.collection;

import component.Component;
import ecs.ID;
import org.apache.commons.collections4.map.HashedMap;

import java.util.HashMap;

/**
 * Created by Jan-Frederik Leißner on 16.02.2018.
 */
public class ComponentCollection<T extends Component> extends HashedMap<ID,T> {

}
