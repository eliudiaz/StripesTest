/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home;

import gt.org.isis.controller.dto.PersonaRowsFileDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author eliud
 */
@Component
public class FileContentQueue {

    private Map<Integer, List<PersonaRowsFileDto>> queue = new LinkedHashMap<Integer, List<PersonaRowsFileDto>>();

    public synchronized List<PersonaRowsFileDto> pull(Integer key) {
        return queue.remove(key);
    }

    public synchronized void push(Integer key, List<PersonaRowsFileDto> feed) {
        queue.put(key, feed);
    }

}
