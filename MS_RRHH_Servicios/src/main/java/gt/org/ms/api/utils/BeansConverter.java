/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.utils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.global.Singleton;
import gt.org.ms.api.entities.CustomEntity;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author edcracken
 * @param <A>
 * @param <B>
 */
public class BeansConverter<A, B> {

    private static Class me;
    private String[] ignore;

    public static <T> T get() {
        return Singleton.<T>Instance(me.getName());
    }

    public BeansConverter() {
        this.me = this.getClass();
    }

    public void setIgnore(String[] ignore) {
        this.ignore = ignore;
    }

    /**
     * Stream based fast toDTO
     *
     * @param origin
     * @return
     */
    public List<B> toDTO(List<A> origin) {
        List<B> b = new ArrayList<B>();
        for (A a : origin) {
            b.add(this.toDTO(a));
        }
        return b;
        //return (List<B>) origin.stream().map(this::toDTO).collect(toList());
    }

    /**
     * spring fast toDTO
     *
     * @param iA
     * @return
     */
    public B toDTO(A iA) {
        B iB = BeanUtils.instantiate((Class<B>) resolve(1));
        BeanUtils.copyProperties(iA, iB, ignore);
        return iB;
    }

    /**
     * spring fast toDTO
     *
     * @param iB
     * @return
     */
    public A toEntity(B iB) {
        A iA = BeanUtils.instantiate((Class<A>) resolve(0));
        BeanUtils.copyProperties(iB, iA);
        if (iA instanceof CustomEntity) {
            EntitiesHelper.setDateCreatedInfo((CustomEntity) iA);
        }
        return iA;
    }

    /**
     *
     * @param origin
     * @return
     */
    public List<A> toEntity(List<B> origin) {
        return new ArrayList<A>(Collections2.transform(origin, new Function<B, A>() {
            @Override
            public A apply(B a) {
                return BeansConverter.this.toEntity(a);
            }
        }));
    }

    /**
     *
     *
     * @return
     */
    private Class<?> resolve(int index) {
        Type[] v = ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments();

        return (Class) v[index];
    }

}
