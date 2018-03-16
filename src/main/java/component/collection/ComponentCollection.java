package component.collection;

import component.Component;
import ecs.ID;

import java.util.HashMap;

/**
 * Created by Jan-Frederik Leißner on 16.02.2018.
 */
public class ComponentCollection<T extends Component> extends HashMap<ID,T> { }
