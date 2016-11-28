/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author edcracken
 */
public class ValidationsHelper {

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean containsAny(String source, String param) {
        return source.equalsIgnoreCase(param);
    }

    public static void main(String... arg) {
        System.out.println(ValidationsHelper.findBestMatchItem("test", Arrays.asList("testla1", "testla2", "testla3", "test1", "testMe")));
    }

    public static <T> T findBestMatchItem(final String param, List<T> options) {
        List<MatchItem> weights = new ArrayList<MatchItem>(Collections2
                .transform(options, new Function<T, MatchItem>() {
                    @Override
                    public MatchItem apply(T f) {
                        return new MatchItem(StringUtils.getLevenshteinDistance(param.toLowerCase(), f.toString().toLowerCase()), f);
                    }
                }));

        Collections.sort(weights);
        //fot those objects without tostring implementation it will be wrong so better return null
        return (T) weights.get(0).value;
    }

    public static class MatchItem implements Comparable<MatchItem> {

        public Integer weight;
        public Object value;

        public MatchItem(Integer weight, Object value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(MatchItem o) {
            return weight.compareTo(o.weight);
        }

    }
}
