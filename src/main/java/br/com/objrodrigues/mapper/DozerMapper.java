package br.com.objrodrigues.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <Origin, Target> Target parseObject(Origin origin, Class<Target> destination) {
        return mapper.map(origin, destination);
    }

    public static <Origin, Target> List<Target> parseListObjects(List<Origin> origin, Class<Target> destination) {
        List<Target> destinationObjects = new ArrayList<>();

        for (Origin o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}