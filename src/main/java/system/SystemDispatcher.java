package system;

import org.apache.commons.collections4.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemDispatcher {
    private Map<String, System> systems;

    private List<List<System>> tick_plan;

    public SystemDispatcher() {
        systems = new HashedMap<>();
        tick_plan = new ArrayList<>();
    }

    public void register(System to_register) {
        systems.put(to_register.identifier(), to_register);
        rebuild_tick_plan();
    }

    public void remove(System to_remove) {
        systems.remove(to_remove.identifier());
        rebuild_tick_plan();
    }

    public void rebuild_tick_plan() {
        int system_count = systems.size();
        int max_index = system_count - 1;
        char[][] matrix = new char[max_index][max_index];
        List<System> system_list = (List<System>) systems.values();
        boolean generation_error = false;
        // first run (known entries)
        for (int i = 0; i < system_count; i++) {
            System i_system = system_list.get(i);
            for (int j = 0; j < system_count; j++) {
                System j_system = system_list.get(j);
                char current_entry = 'X';
                if (i != j) {
                    boolean i_before_j = i_system.before().contains(j_system.identifier());
                    boolean i_after_j = i_system.after().contains(j_system.identifier());
                    if (i_after_j && i_before_j) {
                        current_entry = 'E'; // <- ERROR!
                        generation_error = true;
                    } else if (i_before_j) {
                        current_entry = '+'; // <- before
                    } else if (i_after_j) {
                        current_entry = '-'; // <- after
                    } else {
                        current_entry = 'o'; // <- not specified
                    }
                }
                matrix[i][j] = current_entry;
            }
        }
        // second run (fill missing entries -> mirroring)
        for (int i = 0; i < system_count; i++) {
            for (int j = 0; j < system_count; j++) {
                if (i == j) {
                    continue;
                }
                char current = matrix[i][j];
                char mirrored = matrix[j][i];
                if ((current == '+' && (mirrored == '-' || mirrored == 'o')) || (current == '-' && (mirrored == '+' || mirrored == 'o'))) {
                    continue;
                } else if (current == 'o') {
                    char real_mirrored = mirrored;
                    if(mirrored == '-'){
                        real_mirrored = '+';
                    }else if(mirrored == '+'){
                        real_mirrored = '-';
                    }
                    matrix[i][j] = real_mirrored;
                } else {
                    matrix[i][j] = 'E'; // <- ERROR!
                    generation_error = true;
                }
            }
        }
    }
}
