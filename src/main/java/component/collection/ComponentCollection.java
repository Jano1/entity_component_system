package component.collection;

import component.Component;
import org.apache.commons.collections4.KeyValue;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Jan-Frederik Leißner on 16.02.2018.
 */
public class ComponentCollection<T extends Component> extends HashMap<ID,T> { }
